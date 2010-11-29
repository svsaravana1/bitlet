/*
 *              bitlet - Simple bittorrent library
 *
 * Copyright (C) 2008 Alessandro Bahgat Shehata, Daniele Castagna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Alessandro Bahgat Shehata - ale dot bahgat at gmail dot com
 * Daniele Castagna - daniele dot castagna at gmail dot com
 *
 */

package org.bitlet.wetorrent.peer.task;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import org.bitlet.wetorrent.Event;
import org.bitlet.wetorrent.Torrent;
import org.bitlet.wetorrent.peer.TorrentPeer;
import org.bitlet.wetorrent.util.stream.OutputStreamLimiter;
import org.bitlet.wetorrent.util.thread.ThreadTask;

public class SendBitfield implements ThreadTask {

    TorrentPeer peer;

    public SendBitfield(TorrentPeer peer) {
        this.peer = peer;
    }

    public boolean execute() throws Exception {
        if (Torrent.verbose) {
            peer.getPeersManager().getTorrent().addEvent(new Event(this, "Sending bitField ", Level.FINER));
        }
        try {
            DataOutputStream os = new DataOutputStream(new OutputStreamLimiter(peer.getSocket().getOutputStream(), peer.getPeersManager().getTorrent().getUploadBandwidthLimiter()));
            byte[] bitField = peer.getPeersManager().getTorrent().getTorrentDisk().getBitfieldCopy();

            os.writeInt(1 + bitField.length);
            os.writeByte(5);
            os.write(bitField);

        } catch (Exception e) {
            if (Torrent.verbose) {
                peer.getPeersManager().getTorrent().addEvent(new Event(this, "Problem sending bitfield", Level.WARNING));
            }
            throw e;
        }
        return false;
    }

    public void interrupt() {
        try {
            peer.getSocket().close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void exceptionCought(Exception e) {
        e.printStackTrace();
        peer.interrupt();
    }
}