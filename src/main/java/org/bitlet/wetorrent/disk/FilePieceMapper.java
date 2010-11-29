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

package org.bitlet.wetorrent.disk;

import java.io.RandomAccessFile;

public class FilePieceMapper {

    private Long fileOffset;
    private Long pieceOffset;
    private RandomAccessFile file;

    public FilePieceMapper(RandomAccessFile file, Long fileOffset, Long pieceOffset) {
        this.file = file;
        this.fileOffset = fileOffset;
        this.pieceOffset = pieceOffset;
    }

    public Long getFileOffset() {
        return fileOffset;
    }

    public Long getPieceOffset() {
        return pieceOffset;
    }

    public RandomAccessFile getFile() {
        return file;
    }
}
