/*
 * LAMEOnJ Java based API for LAME MP3 encoder/decoder
 *
 * Copyright (c) 2006-2008 Jose Maria Arranz
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 */

package lameonj.decoder.std;

import java.io.OutputStream;
import lame.std.mp3data_struct;

/**
 * This object represents a decoding task. Is the base interface of the MP3 decoder
 * object oriented API using the LAME standard API.
 */
public interface Decoder
{
    /**
     * Returns the <pre>mp3data_struct</pre> object with header information
     * of the MP3 data being decoded.
     *
     * @return the object with MP3 header info. Null if MP3 header has not been processed.
     */
    public mp3data_struct getMP3DataStruct();

    /**
     * Returns the number of bytes of PCM data decoded at the moment.
     *
     * @return the amount of PCM data decoded in bytes.
     * @see #fixWAVHeader(String)
     */
    public int getPCMDataSize();

    /**
     * Informs whether parsed MP3 header data is enough to build the header of
     * a WAV file.
     *
     * <p>Current implementation returns false if {@link mp3data_struct#getTotalframes()}
     * returns 0. For instance only MP3 files with Xing header save the number of MP3 frames
     * contained. The total number of frames is used to calculate the data size in bytes
     * of the PCM data to build a WAV file.</p>
     *
     * @return true if MP3 header is complete.
     * @see #fixWAVHeader(String)
     */
    public boolean isMP3HeaderComplete();

    /**
     * Writes on the specified stream a WAV header with the information
     * provided by the {@link mp3data_struct} object of this decoding task.
     *
     * <p>Even though MP3 header data is not complete, the WAV header is written
     * with incomplete information (PCM data size is 0). This WAV invalid header can
     * be fixed later calling {@link #fixWAVHeader(String)} after the decoding
     * task finishes.
     * </p>
     *
     * @param output the stream to write the WAV header.
     * @see #isMP3HeaderComplete()
     */
    public void writeWAVHeader(OutputStream output);

    /**
     * Modifies the header of the specified WAV file fixing invalid data
     * saved by {@link #writeWAVHeader(OutputStream)}.
     * 
     * <p>You do not need this method if {@link #isMP3HeaderComplete()} returns true.</p> 
     * 
     * <p>This method must be called after this decoding task has finished (is closed).
     * </p>
     * 
     * <p>The value returned by {@link #getPCMDataSize()} is used to fix the WAV header.
     * 
     * @param wavPath the path of the WAV file.
     */
    public void fixWAVHeader(String wavPath);

    /**
     * Writes the specified PCM frame to the specified stream.
     * 
     * @param frame the PCM frame to save.
     * @param output the output stream to write the frame.
     */
    public void writeDecodedFrame(PCMFrame frame,OutputStream output);


    /**
     * Informs whether this decoder task is closed (finished).
     *
     * @return true if this decoder is closed.
     */
    public boolean isClosed();

    /**
     * Closes this decoder task. A closed/finished decoder task cannot be reused.
     */
    public void close();
}
