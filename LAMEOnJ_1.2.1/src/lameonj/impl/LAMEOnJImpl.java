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

package lameonj.impl;

import lameonj.LAMEOnJ;
import lameonj.decoder.std.LAMEOnJStdDecoder;
import lameonj.encoder.blade.LAMEOnJBlade;
import lameonj.impl.encoder.blade.LAMEOnJBladeImpl;
import lameonj.impl.encoder.std.LAMEOnJStdEncoderImpl;
import lameonj.encoder.std.LAMEOnJStdEncoder;
import lameonj.impl.decoder.std.LAMEOnJStdDecoderImpl;

public class LAMEOnJImpl implements LAMEOnJ
{
    protected LAMEOnJBladeImpl blade = new LAMEOnJBladeImpl();
    protected LAMEOnJStdEncoderImpl stdEnc = new LAMEOnJStdEncoderImpl();    
    protected LAMEOnJStdDecoderImpl stdDec = new LAMEOnJStdDecoderImpl();
    
    /**
     * Creates a new instance of LAMEOnJImpl
     */
    public LAMEOnJImpl()
    {
    }

    public LAMEOnJBlade getLAMEOnJBlade()
    {
        return blade;
    }

    public LAMEOnJStdEncoder getLAMEOnJStdEncoder()
    {
        return stdEnc;
    }

    public LAMEOnJStdDecoder getLAMEOnJStdDecoder()
    {
        return stdDec;
    }
    
}
