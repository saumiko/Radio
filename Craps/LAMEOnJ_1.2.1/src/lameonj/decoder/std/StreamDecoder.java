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

import java.io.InputStream;
import java.io.OutputStream;
import lame.std.mp3data_struct;

/**
 * This interface may be used to decode the specified source file or
 * stream to MP3 with only one call or progressively by chunks.
 *
 * <p>A decoding process must start decoding MP3 headers, the method
 * {@link #decodeHeaders()} must be called first.
 * </p>
 *
 * @see LAMEOnJStdDecoder#createStreamDecoder(String)
 * @see LAMEOnJStdDecoder#createStreamDecoder(InputStream)
 */
public interface StreamDecoder extends Decoder
{
    /**
     * Returns the input stream used to decode.
     *
     * @return the input stream.
     */
    public InputStream getSourceInputStream();

    /**
     * Decodes source MP3 data until MP3 header info is obtained.
     *
     * @return the MP3 header object.
     */
    public mp3data_struct decodeHeaders();

    /**
     * Decodes source MP3 data to build one PCM frame.
     *
     * @return a PCM frame object. Null if there is no more MP3 data.
     */
    public PCMFrame decodeFrame();

    /**
     * Decodes the MP3 input source to WAV saving to the specified file.
     *
     * <p>The decoder task is automatically closed.</p>
     *
     * @param wavFile the WAV file path.
     */
    public void decode(String wavFile);

    /**
     * Decodes the MP3 input source to WAV saving to the specified stream.
     *
     * <p>The decoder task is automatically closed.</p>
     *
     * @param wavStream the WAV output stream.
     */
    public void decode(OutputStream wavStream);
}
