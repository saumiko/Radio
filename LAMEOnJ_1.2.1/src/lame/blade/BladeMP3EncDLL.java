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

package lame.blade;

import com.innowhere.jnieasy.core.data.NativeInteger;

/** 
 * The <code>BladeMP3EncDLL</code> class is the container of 
 * the symmetric C LAME exported methods and constants declared 
 * in the file BladeMP3EncDLL.h.
 */
public class BladeMP3EncDLL
{
    /* encoding formats */
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_CONFIG_MP3			0
     *  </pre>
     * </code>
     */
    public static final int BE_CONFIG_MP3 = 0;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_CONFIG_LAME			256
     *  </pre>
     * </code>
     */    
    public static final int BE_CONFIG_LAME = 256;
    
    /* error codes */

    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_ERR_SUCCESSFUL	0x00000000
     *  </pre>
     * </code>
     */    
    public static final int BE_ERR_SUCCESSFUL       = 0x00000000;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_ERR_INVALID_FORMAT	0x00000001
     *  </pre>
     * </code>
     */    
    public static final int BE_ERR_INVALID_FORMAT   = 0x00000001;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_ERR_INVALID_FORMAT_PARAMETERS	0x00000002
     *  </pre>
     * </code>
     */      
    public static final int BE_ERR_INVALID_FORMAT_PARAMETERS = 0x00000002;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_ERR_NO_MORE_HANDLES	0x00000003
     *  </pre>
     * </code>
     */    
    public static final int BE_ERR_NO_MORE_HANDLES  = 0x00000003;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_ERR_INVALID_HANDLE	0x00000004
     *  </pre>
     * </code>
     */    
    public static final int BE_ERR_INVALID_HANDLE   = 0x00000004;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_ERR_BUFFER_TOO_SMALL	0x00000005
     *  </pre>
     * </code>
     */    
    public static final int BE_ERR_BUFFER_TOO_SMALL = 0x00000005;
    
/* other constants */

    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_MAX_HOMEPAGE			128
     *  </pre>
     * </code>
     */    
    public static final int BE_MAX_HOMEPAGE = 128;

/* format specific variables */

    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_MP3_MODE_STEREO		0
     *  </pre>
     * </code>
     */    
    public static final byte BE_MP3_MODE_STEREO      = 0;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_MP3_MODE_JSTEREO		1
     *  </pre>
     * </code>
     */    
    public static final byte BE_MP3_MODE_JSTEREO     = 1;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_MP3_MODE_DUALCHANNEL		2
     *  </pre>
     * </code>
     */    
    public static final byte BE_MP3_MODE_DUALCHANNEL = 2;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		BE_MP3_MODE_MONO		3
     *  </pre>
     * </code>
     */        
    public static final byte BE_MP3_MODE_MONO        = 3;

    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		MPEG1	1
     *  </pre>
     * </code>
     */    
    public static final int MPEG1   = 1;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define		MPEG1	0
     *  </pre>
     * </code>
     */     
    public static final int MPEG2   = 0;   
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define CURRENT_STRUCT_VERSION 1
     *  </pre>
     * </code>
     */     
    public static final int CURRENT_STRUCT_VERSION = 1;
    
    /**
     * Corresponds to C declaration:
     * <code>
     *  <pre>
     *  #define CURRENT_STRUCT_SIZE sizeof(BE_CONFIG)	// is currently 331 bytes
     *  </pre>
     * </code>
     */    
    public static final int CURRENT_STRUCT_SIZE = 331; 
    
    
    private BladeMP3EncDLL() // To remove from javadoc
    {        
    }
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        BE_ERR	beCloseStream(HBE_STREAM hbeStream);
        </pre>
     </code>
     */
    public static native int beCloseStream(int hbeStream);

    /**
     * Corresponds with the C method:
     <code>
        <pre>
        BE_ERR	beDeinitStream(HBE_STREAM hbeStream, PBYTE pOutput, PDWORD pdwOutput);
        </pre>
     </code>
     */    
    public static native int beDeinitStream(int hbeStream,byte[] pOutput,NativeInteger pdwOutput);
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        BE_ERR	beEncodeChunk(HBE_STREAM hbeStream, DWORD nSamples, PSHORT pSamples, PBYTE pOutput, PDWORD pdwOutput);
        </pre>
     </code>
     */    
    public static native int beEncodeChunk(int hbeStream,int nSamples,short[] pSamples,byte[] pOutput,NativeInteger pdwOutput);
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        BE_ERR	beEncodeChunk(HBE_STREAM hbeStream, DWORD nSamples, PSHORT pSamples, PBYTE pOutput, PDWORD pdwOutput);
        </pre>
     </code>
     * This is a convenience method using byte[] array instead of short[], the byte[] buffer used
     * must be 2x the length of the analogous short[] version.
     *
     * @see #beEncodeChunk(int,int,short[],byte[],com.innowhere.jnieasy.core.data.NativeInteger)
     */    
    public static native int beEncodeChunk(int hbeStream,int nSamples,byte[] pSamples,byte[] pOutput,NativeInteger pdwOutput);
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        BE_ERR	beEncodeChunkFloatS16NI(HBE_STREAM hbeStream, DWORD nSamples, PFLOAT buffer_l, PFLOAT buffer_r, PBYTE pOutput, PDWORD pdwOutput);
        </pre>
     </code>
     */     
    public static native int beEncodeChunkFloatS16NI(int hbeStream,int nSamples,float[] buffer_l,float[] buffer_r,byte[] pOutput,NativeInteger pdwOutput);
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        BE_ERR	beFlushNoGap(HBE_STREAM hbeStream, PBYTE pOutput, PDWORD pdwOutput);
        </pre>
     </code>
     */    
    public static native int beFlushNoGap(int hbeStream,byte[] pOutput,NativeInteger pdwOutput);
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        BE_ERR	beInitStream(PBE_CONFIG pbeConfig, PDWORD dwSamples, PDWORD dwBufferSize, PHBE_STREAM phbeStream);
        </pre>
     </code>
     */
    public static native int beInitStream(BE_CONFIG pbeConfig,NativeInteger pDwSamples,NativeInteger pDwBufferSize,NativeInteger phbeStream);
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        VOID beVersion(PBE_VERSION pbeVersion);
        </pre>
     </code>
     */      
    public static native void beVersion(BE_VERSION pbeVersion);
    
    /**
     * Corresponds with the C method:
     <code>
        <pre>
        VOID beWriteVBRHeader( LPCSTR pszMP3FileName );
        </pre>
     </code>
     */    
    public static native void beWriteVBRHeader(String pszMP3FileName);
    
}
