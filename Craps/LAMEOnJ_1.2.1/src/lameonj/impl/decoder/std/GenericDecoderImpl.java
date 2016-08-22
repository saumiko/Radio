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

package lameonj.impl.decoder.std;

import java.util.Iterator;
import java.util.LinkedList;
import lame.std.mp3data_struct;
import lameonj.decoder.std.PCMFrame;
import lameonj.decoder.std.GenericDecoder;

/**
 *
 * @author jmarranz
 */
public class GenericDecoderImpl extends DecoderImpl implements GenericDecoder
{
    protected LinkedList pendingMP3 = new LinkedList();
    
    public GenericDecoderImpl(LAMEOnJStdDecoderImpl parent)
    {
        super(parent);
    }

    public mp3data_struct decodeHeaders(byte[] mp3buf,int size)
    {
        return decodeHeadersBase(mp3buf,size);
    }

    public PCMFrame decodeFrame(byte[] mp3buf,int size)
    {
        PCMFrame frame = decodePendingFrame();
        if (frame != null)
        {
            // No podemos perder los nuevos datos
            pendingMP3.add(new MP3Data(mp3buf,size));
            return frame;
        }
        else return decodeFrameFromBuffer(mp3buf,size);
    }

    public PCMFrame decodePendingFrame()
    {
        PCMFrame frame = super.decodePendingFrameBase();
        if (frame == null) 
            frame = decodePendingFrameFromSavedData();        
        
        return frame;
    }

    protected PCMFrame decodePendingFrameFromSavedData()
    {
        if (pendingMP3.size() == 0) return null;
        for(Iterator it = pendingMP3.iterator(); it.hasNext(); )
        {
            MP3Data data = (MP3Data)it.next();
            it.remove();

            byte[] mp3buf = data.getBuffer();
            int size = data.getSize();
            PCMFrame frame = decodeFrameFromBuffer(mp3buf,size);
            if (frame != null) return frame;
        }
        return null;
    }


}
