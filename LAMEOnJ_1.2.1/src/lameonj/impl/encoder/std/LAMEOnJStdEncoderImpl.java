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

package lameonj.impl.encoder.std;

import lame.std.lame_errorcodes_t;
import lameonj.LAMEOnJException;
import lameonj.encoder.std.GenericEncoder;
import lameonj.encoder.std.LAMEOnJStdEncoder;
import lameonj.encoder.std.StreamEncoder;
import java.io.InputStream;

/**
 *
 * @author jmarranz
 */
public class LAMEOnJStdEncoderImpl implements LAMEOnJStdEncoder
{
    
    /** Creates a new instance of LAMEOnJStdImpl */
    public LAMEOnJStdEncoderImpl()
    {
    }

    public StreamEncoder createStreamEncoder(String file)
    {
        if (StreamEncoderWAVImpl.isWAV(file))
            return new StreamEncoderWAVImpl(file,this);
        else
            return new StreamEncoderPCMImpl(file,this);
    }

    public StreamEncoder createStreamEncoder(InputStream stream,String mime)
    {
        // http://en.wikipedia.org/wiki/WAV
        if (mime != null)
            if (mime.equals("audio/wav") || mime.equals("audio/wave") || 
                mime.equals("audio/x-wav"))        
                return new StreamEncoderWAVImpl(stream,this);        
            else
                throw new LAMEOnJException("Unsupported mime type:" + mime);
        else
            return new StreamEncoderPCMImpl(stream,this);
    }
    
    public GenericEncoder createGenericEncoder()
    {
        return new GenericEncoderImpl(this);
    }
    
    public static void checkError(int err)
    {
        if (err == lame_errorcodes_t.LAME_OKAY) return; // Idem LAME_NOERROR

        String errMsg;
        switch(err)
        {
            case lame_errorcodes_t.LAME_GENERICERROR : 
                        errMsg = "LAME_GENERICERROR"; break;
            case lame_errorcodes_t.LAME_NOMEM : 
                        errMsg = "LAME_NOMEM"; break;
            case lame_errorcodes_t.LAME_BADBITRATE : 
                        errMsg = "LAME_BADBITRATE"; break;            
            case lame_errorcodes_t.LAME_BADSAMPFREQ : 
                        errMsg = "LAME_BADSAMPFREQ"; break;            
            case lame_errorcodes_t.LAME_INTERNALERROR : 
                        errMsg = "LAME_INTERNALERROR"; break;            
            case lame_errorcodes_t.FRONTEND_READERROR : 
                        errMsg = "FRONTEND_READERROR"; break;
            case lame_errorcodes_t.FRONTEND_WRITEERROR : 
                        errMsg = "FRONTEND_WRITEERROR"; break;
            case lame_errorcodes_t.FRONTEND_FILETOOLARGE : 
                        errMsg = "FRONTEND_FILETOOLARGE"; break;
            default:
                errMsg = "UNKNOWN";
        }
                  
        throw new LAMEOnJException("ERROR: " + errMsg,err);     
    }             
}
