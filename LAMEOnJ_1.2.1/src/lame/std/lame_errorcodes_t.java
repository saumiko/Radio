/*
 * LAMEOnJ Java based API for LAME MP3 encoder
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

package lame.std;

/**
 * <pre>
     typedef enum {
        LAME_OKAY             =   0,
        LAME_NOERROR          =   0,
        LAME_GENERICERROR     =  -1,
        LAME_NOMEM            = -10,
        LAME_BADBITRATE       = -11,
        LAME_BADSAMPFREQ      = -12,
        LAME_INTERNALERROR    = -13,

        FRONTEND_READERROR    = -80,
        FRONTEND_WRITEERROR   = -81,
        FRONTEND_FILETOOLARGE = -82

    } lame_errorcodes_t;
 * </pre>
 */
public interface lame_errorcodes_t
{
        int LAME_OKAY             =   0;
        int LAME_NOERROR          =   0;
        int LAME_GENERICERROR     =  -1;
        int LAME_NOMEM            = -10;
        int LAME_BADBITRATE       = -11;
        int LAME_BADSAMPFREQ      = -12;
        int LAME_INTERNALERROR    = -13;

        int FRONTEND_READERROR    = -80;
        int FRONTEND_WRITEERROR   = -81;
        int FRONTEND_FILETOOLARGE = -82;    
}
