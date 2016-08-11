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

/** 
 * The <code>BE_CONFIG</code> class is the Java symmetric representation of the corresponding 
 * C LAME structure as declared in the file BladeMP3EncDLL.h. 

<code><pre>  
typedef struct	{
	DWORD	dwConfig;			// BE_CONFIG_XXXXX
						// Currently only BE_CONFIG_MP3 is supported
	union	{

		struct	{

			DWORD	dwSampleRate;		// 48000, 44100 and 32000 allowed
			BYTE	byMode;			// BE_MP3_MODE_STEREO, BE_MP3_MODE_DUALCHANNEL, BE_MP3_MODE_MONO
			WORD	wBitrate;		// 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256 and 320 allowed
			BOOL	bPrivate;		
			BOOL	bCRC;
			BOOL	bCopyright;
			BOOL	bOriginal;

			} mp3;				// BE_CONFIG_MP3

			struct
			{
			// STRUCTURE INFORMATION
			DWORD			dwStructVersion;	
			DWORD			dwStructSize;

			// BASIC ENCODER SETTINGS
			DWORD			dwSampleRate;		// SAMPLERATE OF INPUT FILE
			DWORD			dwReSampleRate;		// DOWNSAMPLERATE, 0=ENCODER DECIDES  
			LONG			nMode;			// BE_MP3_MODE_STEREO, BE_MP3_MODE_DUALCHANNEL, BE_MP3_MODE_MONO
			DWORD			dwBitrate;		// CBR bitrate, VBR min bitrate
			DWORD			dwMaxBitrate;		// CBR ignored, VBR Max bitrate
			LONG			nPreset;		// Quality preset, use one of the settings of the LAME_QUALITY_PRESET enum
			DWORD			dwMpegVersion;		// FUTURE USE, MPEG-1 OR MPEG-2
			DWORD			dwPsyModel;		// FUTURE USE, SET TO 0
			DWORD			dwEmphasis;		// FUTURE USE, SET TO 0

			// BIT STREAM SETTINGS
			BOOL			bPrivate;		// Set Private Bit (TRUE/FALSE)
			BOOL			bCRC;			// Insert CRC (TRUE/FALSE)
			BOOL			bCopyright;		// Set Copyright Bit (TRUE/FALSE)
			BOOL			bOriginal;		// Set Original Bit (TRUE/FALSE)
			
			// VBR STUFF
			BOOL			bWriteVBRHeader;	// WRITE XING VBR HEADER (TRUE/FALSE)
			BOOL			bEnableVBR;		// USE VBR ENCODING (TRUE/FALSE)
			INT			nVBRQuality;		// VBR QUALITY 0..9
			DWORD			dwVbrAbr_bps;		// Use ABR in stead of nVBRQuality
			VBRMETHOD		nVbrMethod;
			BOOL			bNoRes;			// Disable Bit resorvoir (TRUE/FALSE)

			// MISC SETTINGS
			BOOL			bStrictIso;		// Use strict ISO encoding rules (TRUE/FALSE)
			WORD			nQuality;		// Quality Setting, HIGH BYTE should be NOT LOW byte, otherwhise quality=5

			// FUTURE USE, SET TO 0, align strucutre to 331 bytes
			BYTE			btReserved[255-4*sizeof(DWORD) - sizeof( WORD )];

			} LHV1;			// LAME header version 1

		struct	{

			DWORD	dwSampleRate;
			BYTE	byMode;
			WORD	wBitrate;
			BYTE	byEncodingMethod;

		} aac;

	} format;
		
} BE_CONFIG, *BE_CONFIG ATTRIBUTE_PACKED;
</pre></code>
 
*/

public class BE_CONFIG
{
    protected int dwConfig; // DWORD
    protected STRUCT_FORMAT format = new STRUCT_FORMAT(); // embedded

    /**
     * Creates a new configuration object
     */
    public BE_CONFIG()
    {
        
    }
    
    /**
     * Returns the current value of <code>dwConfig</code> attribute.
     *
     * @return the value of <code>dwConfig</code>
     */    
    public int getDwConfig()
    {
        return dwConfig;
    }

    /**
     * Sets the value of the <code>dwConfig</code> attribute.
     *
     * @param dwConfig the new value
     */    
    public void setDwConfig(int dwConfig)
    {
        this.dwConfig = dwConfig;
    }

    /**
     * Returns the current value of <code>format</code> attribute.
     *
     * @return the value of <code>format</code>
     */     
    public STRUCT_FORMAT getFormat()
    {
        return format;
    }
    
    /** 
     * The <code>STRUCT_FORMAT</code> class is the Java symmetric representation of the 
     * anonymous union data type of the 
     * <code>format</code> field of the LAME <code>BE_CONFIG</code> structure. 
     *
     * @see BE_CONFIG
     */   
    public static class STRUCT_FORMAT
    {
        protected STRUCT_MP3 mp3 = new STRUCT_MP3(); // embedded
        protected STRUCT_LHV1 LHV1 = new STRUCT_LHV1(); // embedded
        protected STRUCT_AAC aac = new STRUCT_AAC(); // embedded                  
        
        /**
         * Returns the current value of <code>mp3</code> attribute.
         *
         * @return the value of <code>mp3</code>
         */        
        public STRUCT_MP3 getMp3()
        {
            return mp3;
        }

        /**
         * Returns the current value of <code>LHV1</code> attribute.
         *
         * @return the value of <code>LHV1</code>
         */        
        public STRUCT_LHV1 getLHV1()
        {
            return LHV1;
        }

        /**
         * Returns the current value of <code>aac</code> attribute.
         *
         * @return the value of <code>aac</code>
         */        
        public STRUCT_AAC getAac()
        {
            return aac;
        }
   
        
        /** 
         * The <code>STRUCT_MP3</code> class is the Java symmetric representation of the 
         * structure data type of the <code>mp3</code> field of the 
         * <code>STRUCT_FORMAT</code> structure. 
         <code><pre>
            struct	{

                    DWORD	dwSampleRate;		// 48000, 44100 and 32000 allowed
                    BYTE	byMode;			// BE_MP3_MODE_STEREO, BE_MP3_MODE_DUALCHANNEL, BE_MP3_MODE_MONO
                    WORD	wBitrate;		// 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256 and 320 allowed
                    BOOL	bPrivate;		
                    BOOL	bCRC;
                    BOOL	bCopyright;
                    BOOL	bOriginal;

                    }
         </pre></code>
         * @see STRUCT_FORMAT
         */          
        public static class STRUCT_MP3
        {   
            protected int dwSampleRate; // DWORD
            protected byte byMode;      // BYTE
            protected short wBitrate;   // WORD
            protected int bPrivate;     // BOOL
            protected int bCRC;         // BOOL
            protected int bCopyright;   // BOOL
            protected int bOriginal;    // BOOL

            /**
             * Returns the current value of <code>dwSampleRate</code> attribute.
             *
             * @return the value of <code>dwSampleRate</code>
             */            
            public int getDwSampleRate()
            {
                return dwSampleRate;
            }

            /**
             * Sets the value of the <code>dwSampleRate</code> attribute.
             *
             * @param dwSampleRate the new value
             */            
            public void setDwSampleRate(int dwSampleRate)
            {
                this.dwSampleRate = dwSampleRate;
            }

            /**
             * Returns the current value of <code>byMode</code> attribute.
             *
             * @return the value of <code>byMode</code>
             */            
            public byte getByMode()
            {
                return byMode;
            }
            
            /**
             * Sets the value of the <code>byMode</code> attribute.
             *
             * @param byMode the new value
             */
            public void setByMode(byte byMode)
            {
                this.byMode = byMode;
            }
            
            /**
             * Returns the current value of <code>wBitrate</code> attribute.
             *
             * @return the value of <code>wBitrate</code>
             */            
            public short getWBitrate()
            {
                return wBitrate;
            }

            /**
             * Sets the value of the <code>wBitrate</code> attribute.
             *
             * @param wBitrate the new value
             */            
            public void setWBitrate(short wBitrate)
            {
                this.wBitrate = wBitrate;
            }
            
            /**
             * Returns the current value of <code>bPrivate</code> attribute.
             *
             * @return the value of <code>bPrivate</code>
             */
            public boolean isBPrivate()
            {
                return bPrivate != 0;
            }

            /**
             * Sets the value of the <code>bPrivate</code> attribute.
             *
             * @param bPrivate the new value
             */            
            public void setBPrivate(boolean bPrivate)
            {
                this.bPrivate = (bPrivate ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bCRC</code> attribute.
             *
             * @return the value of <code>bCRC</code>
             */            
            public boolean isBCRC()
            {
                return bCRC != 0;
            }

            /**
             * Sets the value of the <code>bCRC</code> attribute.
             *
             * @param bCRC the new value
             */             
            public void setBCRC(boolean bCRC)
            {
                this.bCRC = (bCRC ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bCopyright</code> attribute.
             *
             * @return the value of <code>bCopyright</code>
             */             
            public boolean isBCopyright()
            {
                return bCopyright != 0;
            }

            /**
             * Sets the value of the <code>bCopyright</code> attribute.
             *
             * @param bCopyright the new value
             */             
            public void setBCopyright(boolean bCopyright)
            {
                this.bCopyright = (bCopyright ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bOriginal</code> attribute.
             *
             * @return the value of <code>bOriginal</code>
             */              
            public boolean isBOriginal()
            {
                return bOriginal != 0;
            }

            /**
             * Sets the value of the <code>bOriginal</code> attribute.
             *
             * @param bOriginal the new value
             */            
            public void setBOriginal(boolean bOriginal)
            {
                this.bOriginal = (bOriginal ? 1 : 0);
            }
        }
        
        /** 
         * The <code>STRUCT_LHV1</code> class is the Java symmetric representation of the 
         * structure data type of the <code>LHV1</code> field of the 
         * <code>STRUCT_FORMAT</code> structure. 

         <code><pre>
                struct
                {
                // STRUCTURE INFORMATION
                DWORD			dwStructVersion;	
                DWORD			dwStructSize;

                // BASIC ENCODER SETTINGS
                DWORD			dwSampleRate;		// SAMPLERATE OF INPUT FILE
                DWORD			dwReSampleRate;		// DOWNSAMPLERATE, 0=ENCODER DECIDES  
                LONG			nMode;			// BE_MP3_MODE_STEREO, BE_MP3_MODE_DUALCHANNEL, BE_MP3_MODE_MONO
                DWORD			dwBitrate;		// CBR bitrate, VBR min bitrate
                DWORD			dwMaxBitrate;		// CBR ignored, VBR Max bitrate
                LONG			nPreset;		// Quality preset, use one of the settings of the LAME_QUALITY_PRESET enum
                DWORD			dwMpegVersion;		// FUTURE USE, MPEG-1 OR MPEG-2
                DWORD			dwPsyModel;		// FUTURE USE, SET TO 0
                DWORD			dwEmphasis;		// FUTURE USE, SET TO 0

                // BIT STREAM SETTINGS
                BOOL			bPrivate;		// Set Private Bit (TRUE/FALSE)
                BOOL			bCRC;			// Insert CRC (TRUE/FALSE)
                BOOL			bCopyright;		// Set Copyright Bit (TRUE/FALSE)
                BOOL			bOriginal;		// Set Original Bit (TRUE/FALSE)

                // VBR STUFF
                BOOL			bWriteVBRHeader;	// WRITE XING VBR HEADER (TRUE/FALSE)
                BOOL			bEnableVBR;		// USE VBR ENCODING (TRUE/FALSE)
                INT                     nVBRQuality;		// VBR QUALITY 0..9
                DWORD			dwVbrAbr_bps;		// Use ABR in stead of nVBRQuality
                VBRMETHOD		nVbrMethod;
                BOOL			bNoRes;			// Disable Bit resorvoir (TRUE/FALSE)

                // MISC SETTINGS
                BOOL			bStrictIso;		// Use strict ISO encoding rules (TRUE/FALSE)
                WORD			nQuality;		// Quality Setting, HIGH BYTE should be NOT LOW byte, otherwhise quality=5

                // FUTURE USE, SET TO 0, align strucutre to 331 bytes
                BYTE			btReserved[255-4*sizeof(DWORD) - sizeof( WORD )];

                }         
         </pre></code>
         * @see STRUCT_FORMAT
         */        
        public static class STRUCT_LHV1
        {
            protected int dwStructVersion;  // DWORD
            protected int dwStructSize;     // DWORD
            
            protected int dwSampleRate;     // DWORD
            protected int dwReSampleRate;   // DWORD
            protected int nMode;            // LONG
            protected int dwBitrate;        // DWORD
            protected int dwMaxBitrate;     // DWORD
            protected int nPreset;          // LONG
            protected int dwMpegVersion;    // DWORD
            protected int dwPsyModel;       // DWORD
            protected int dwEmphasis;       // DWORD
            
            protected int bPrivate;     // BOOL
            protected int bCRC;         // BOOL
            protected int bCopyright;   // BOOL
            protected int bOriginal;    // BOOL
            
            protected int bWriteVBRHeader;  // BOOL
            protected int bEnableVBR;       // BOOL
            protected int nVBRQuality;      // INT
            protected int dwVbrAbr_bps;     // DWORD
            protected int nVbrMethod;       // VBRMETHOD (enum)
            protected int bNoRes;           // BOOL
            
            protected int bStrictIso;       // BOOL
            protected short nQuality;       // WORD
            protected byte[] btReserved = new byte[255 - 4*4 - 2]; // 237, embedded

            /**
             * Returns the current value of <code>dwStructVersion</code> attribute.
             *
             * @return the value of <code>dwStructVersion</code>
             */             
            public int getDwStructVersion()
            {
                return dwStructVersion;
            }
            
            /**
             * Sets the value of the <code>dwStructVersion</code> attribute.
             *
             * @param dwStructVersion the new value
             */       
            public void setDwStructVersion(int dwStructVersion)
            {
                this.dwStructVersion = dwStructVersion;
            }

            /**
             * Returns the current value of <code>dwStructSize</code> attribute.
             *
             * @return the value of <code>dwStructSize</code>
             */
            public int getDwStructSize()
            {
                return dwStructSize;
            }

            /**
             * Sets the value of the <code>dwStructSize</code> attribute.
             *
             * @param dwStructSize the new value
             */            
            public void setDwStructSize(int dwStructSize)
            {
                this.dwStructSize = dwStructSize;
            }

            /**
             * Returns the current value of <code>dwSampleRate</code> attribute.
             *
             * @return the value of <code>dwSampleRate</code>
             */            
            public int getDwSampleRate()
            {
                return dwSampleRate;
            }

            /**
             * Sets the value of the <code>dwSampleRate</code> attribute.
             *
             * @param dwSampleRate the new value
             */               
            public void setDwSampleRate(int dwSampleRate)
            {
                this.dwSampleRate = dwSampleRate;
            }

            /**
             * Returns the current value of <code>dwReSampleRate</code> attribute.
             *
             * @return the value of <code>dwReSampleRate</code>
             */             
            public int getDwReSampleRate()
            {
                return dwReSampleRate;
            }

            /**
             * Sets the value of the <code>dwReSampleRate</code> attribute.
             *
             * @param dwReSampleRate the new value
             */            
            public void setDwReSampleRate(int dwReSampleRate)
            {
                this.dwReSampleRate = dwReSampleRate;
            }

            /**
             * Returns the current value of <code>nMode</code> attribute.
             *
             * @return the value of <code>nMode</code>
             */             
            public int getNMode()
            {
                return nMode;
            }

            /**
             * Sets the value of the <code>nMode</code> attribute.
             *
             * @param nMode the new value
             */            
            public void setNMode(int nMode)
            {
                this.nMode = nMode;
            }

            /**
             * Returns the current value of <code>dwBitrate</code> attribute.
             *
             * @return the value of <code>dwBitrate</code>
             */            
            public int getDwBitrate()
            {
                return dwBitrate;
            }

            /**
             * Sets the value of the <code>dwBitrate</code> attribute.
             *
             * @param dwBitrate the new value
             */            
            public void setDwBitrate(int dwBitrate)
            {
                this.dwBitrate = dwBitrate;
            }

            /**
             * Returns the current value of <code>dwMaxBitrate</code> attribute.
             *
             * @return the value of <code>dwMaxBitrate</code>
             */             
            public int getDwMaxBitrate()
            {
                return dwMaxBitrate;
            }

            /**
             * Sets the value of the <code>dwMaxBitrate</code> attribute.
             *
             * @param dwMaxBitrate the new value
             */            
            public void setDwMaxBitrate(int dwMaxBitrate)
            {
                this.dwMaxBitrate = dwMaxBitrate;
            }

            /**
             * Returns the current value of <code>nPreset</code> attribute.
             *
             * @return the value of <code>nPreset</code>
             */            
            public int getNPreset()
            {
                return nPreset;
            }

            /**
             * Sets the value of the <code>nPreset</code> attribute.
             *
             * @param nPreset the new value
             */            
            public void setNPreset(int nPreset)
            {
                this.nPreset = nPreset;
            }

            /**
             * Returns the current value of <code>dwMpegVersion</code> attribute.
             *
             * @return the value of <code>dwMpegVersion</code>
             */            
            public int getDwMpegVersion()
            {
                return dwMpegVersion;
            }

            /**
             * Sets the value of the <code>dwMpegVersion</code> attribute.
             *
             * @param dwMpegVersion the new value
             */            
            public void setDwMpegVersion(int dwMpegVersion)
            {
                this.dwMpegVersion = dwMpegVersion;
            }

            /**
             * Returns the current value of <code>dwPsyModel</code> attribute.
             *
             * @return the value of <code>dwPsyModel</code>
             */             
            public int getDwPsyModel()
            {
                return dwPsyModel;
            }

            /**
             * Sets the value of the <code>dwPsyModel</code> attribute.
             *
             * @param dwPsyModel the new value
             */               
            public void setDwPsyModel(int dwPsyModel)
            {
                this.dwPsyModel = dwPsyModel;
            }

            /**
             * Returns the current value of <code>dwEmphasis</code> attribute.
             *
             * @return the value of <code>dwEmphasis</code>
             */             
            public int getDwEmphasis()
            {
                return dwEmphasis;
            }
            
            /**
             * Sets the value of the <code>dwEmphasis</code> attribute.
             *
             * @param dwEmphasis the new value
             */
            public void setDwEmphasis(int dwEmphasis)
            {
                this.dwEmphasis = dwEmphasis;
            }

            /**
             * Returns the current value of <code>bPrivate</code> attribute.
             *
             * @return the value of <code>bPrivate</code>
             */             
            public boolean isBPrivate()
            {
                return bPrivate != 0;
            }

            /**
             * Sets the value of the <code>bPrivate</code> attribute.
             *
             * @param bPrivate the new value
             */            
            public void setBPrivate(boolean bPrivate)
            {
                this.bPrivate = (bPrivate ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bCRC</code> attribute.
             *
             * @return the value of <code>bCRC</code>
             */             
            public boolean isBCRC()
            {
                return bCRC != 0;
            }

            /**
             * Sets the value of the <code>bCRC</code> attribute.
             *
             * @param bCRC the new value
             */           
            public void setBCRC(boolean bCRC)
            {
                this.bCRC = (bCRC ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bCopyright</code> attribute.
             *
             * @return the value of <code>bCopyright</code>
             */             
            public boolean isBCopyright()
            {
                return bCopyright != 0;
            }

            /**
             * Sets the value of the <code>bCopyright</code> attribute.
             *
             * @param bCopyright the new value
             */            
            public void setBCopyright(boolean bCopyright)
            {
                this.bCopyright = (bCopyright ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bOriginal</code> attribute.
             *
             * @return the value of <code>bOriginal</code>
             */              
            public boolean isBOriginal()
            {
                return bOriginal != 0;
            }

            /**
             * Sets the value of the <code>bOriginal</code> attribute.
             *
             * @param bOriginal the new value
             */             
            public void setBOriginal(boolean bOriginal)
            {
                this.bOriginal = (bOriginal ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bWriteVBRHeader</code> attribute.
             *
             * @return the value of <code>bWriteVBRHeader</code>
             */              
            public boolean isBWriteVBRHeader()
            {
                return bWriteVBRHeader != 0;
            }

            /**
             * Sets the value of the <code>bWriteVBRHeader</code> attribute.
             *
             * @param bWriteVBRHeader the new value
             */             
            public void setBWriteVBRHeader(boolean bWriteVBRHeader)
            {
                this.bWriteVBRHeader = (bWriteVBRHeader ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bEnableVBR</code> attribute.
             *
             * @return the value of <code>bEnableVBR</code>
             */             
            public boolean isBEnableVBR()
            {
                return bEnableVBR != 0;
            }

            /**
             * Sets the value of the <code>bEnableVBR</code> attribute.
             *
             * @param bEnableVBR the new value
             */             
            public void setBEnableVBR(boolean bEnableVBR)
            {
                this.bEnableVBR = (bEnableVBR ? 1 : 0);
            }

            /**
             * Returns the current value of <code>nVBRQuality</code> attribute.
             *
             * @return the value of <code>nVBRQuality</code>
             */            
            public int getNVBRQuality()
            {
                return nVBRQuality;
            }

            /**
             * Sets the value of the <code>nVBRQuality</code> attribute.
             *
             * @param nVBRQuality the new value
             */            
            public void setNVBRQuality(int nVBRQuality)
            {
                this.nVBRQuality = nVBRQuality;
            }

            /**
             * Returns the current value of <code>dwVbrAbr_bps</code> attribute.
             *
             * @return the value of <code>dwVbrAbr_bps</code>
             */            
            public int getDwVbrAbr_bps()
            {
                return dwVbrAbr_bps;
            }

            /**
             * Sets the value of the <code>dwVbrAbr_bps</code> attribute.
             *
             * @param dwVbrAbr_bps the new value
             */            
            public void setDwVbrAbr_bps(int dwVbrAbr_bps)
            {
                this.dwVbrAbr_bps = dwVbrAbr_bps;
            }

            /**
             * Returns the current value of <code>nVbrMethod</code> attribute.
             *
             * @return the value of <code>nVbrMethod</code>
             */             
            public int getNVbrMethod()
            {
                return nVbrMethod;
            }

            /**
             * Sets the value of the <code>nVbrMethod</code> attribute.
             *
             * @param nVbrMethod the new value
             */             
            public void setNVbrMethod(int nVbrMethod)
            {
                this.nVbrMethod = nVbrMethod;
            }

            /**
             * Returns the current value of <code>bNoRes</code> attribute.
             *
             * @return the value of <code>bNoRes</code>
             */             
            public boolean isBNoRes()
            {
                return bNoRes != 0;
            }

            /**
             * Sets the value of the <code>bNoRes</code> attribute.
             *
             * @param bNoRes the new value
             */               
            public void setBNoRes(boolean bNoRes)
            {
                this.bNoRes = (bNoRes ? 1 : 0);
            }

            /**
             * Returns the current value of <code>bStrictIso</code> attribute.
             *
             * @return the value of <code>bStrictIso</code>
             */            
            public boolean isBStrictIso()
            {
                return bStrictIso != 0;
            }

            /**
             * Sets the value of the <code>bStrictIso</code> attribute.
             *
             * @param bStrictIso the new value
             */            
            public void setBStrictIso(boolean bStrictIso)
            {
                this.bStrictIso = (bStrictIso ? 1 : 0);
            }

            /**
             * Returns the current value of <code>nQuality</code> attribute.
             *
             * @return the value of <code>nQuality</code>
             */            
            public short getNQuality()
            {
                return nQuality;
            }

            /**
             * Sets the value of the <code>nQuality</code> attribute.
             *
             * @param nQuality the new value
             */            
            public void setNQuality(short nQuality)
            {
                this.nQuality = nQuality;
            }

            /**
             * Returns the current value of <code>btReserved</code> attribute.
             *
             * @return the value of <code>btReserved</code>
             */             
            public byte[] getBtReserved()
            {
                return btReserved;
            }

            /**
             * Sets the value of the <code>btReserved</code> attribute.
             *
             * @param btReserved the new value
             */               
            public void setBtReserved(byte[] btReserved)
            {
                this.btReserved = btReserved;
            }
        }
        
        /** 
         * The <code>STRUCT_AAC</code> class is the Java symmetric representation of the 
         * structure data type of the <code>acc</code> field of the 
         * <code>STRUCT_FORMAT</code> structure. 
         *
         <code><pre>
		struct	{

			DWORD	dwSampleRate;
			BYTE	byMode;
			WORD	wBitrate;
			BYTE	byEncodingMethod;

		}
         </code></pre>
         *
         * @see STRUCT_FORMAT
         */        
        public static class STRUCT_AAC
        {        
            protected int dwSampleRate; // DWORD
            protected byte byMode;      // BYTE
            protected short wBitrate;   // WORD
            protected byte byEncodingMethod;    // BYTE

            /**
             * Returns the current value of <code>dwSampleRate</code> attribute.
             *
             * @return the value of <code>dwSampleRate</code>
             */            
            public int getDwSampleRate()
            {
                return dwSampleRate;
            }

            /**
             * Sets the value of the <code>dwSampleRate</code> attribute.
             *
             * @param dwSampleRate the new value
             */                     
            public void setDwSampleRate(int dwSampleRate)
            {
                this.dwSampleRate = dwSampleRate;
            }

            /**
             * Returns the current value of <code>byMode</code> attribute.
             *
             * @return the value of <code>byMode</code>
             */             
            public byte getByMode()
            {
                return byMode;
            }

            /**
             * Sets the value of the <code>byMode</code> attribute.
             *
             * @param byMode the new value
             */             
            public void setByMode(byte byMode)
            {
                this.byMode = byMode;
            }

            /**
             * Returns the current value of <code>wBitrate</code> attribute.
             *
             * @return the value of <code>wBitrate</code>
             */               
            public short getWBitrate()
            {
                return wBitrate;
            }

            /**
             * Sets the value of the <code>wBitrate</code> attribute.
             *
             * @param wBitrate the new value
             */            
            public void setWBitrate(short wBitrate)
            {
                this.wBitrate = wBitrate;
            }

            /**
             * Returns the current value of <code>byEncodingMethod</code> attribute.
             *
             * @return the value of <code>byEncodingMethod</code>
             */             
            public byte getByEncodingMethod()
            {
                return byEncodingMethod;
            }

            /**
             * Sets the value of the <code>byEncodingMethod</code> attribute.
             *
             * @param byEncodingMethod the new value
             */              
            public void setByEncodingMethod(byte byEncodingMethod)
            {
                this.byEncodingMethod = byEncodingMethod;
            }
            
        }

    }


    
}
