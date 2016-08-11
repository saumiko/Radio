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

import lame.std.Lame;
import lame.std.LameMsgCallback;
import lame.std.lame_global_flags;
import lame.std.lame_version_t;
import lameonj.LAMEOnJException;
import lameonj.encoder.std.EncoderConfig;

/**
 *
 * @author jmarranz
 */
public class EncoderConfigImpl implements EncoderConfig
{
    protected EncoderImpl encoder;
    
    /** Creates a new instance of EncoderConfigImpl */
    public EncoderConfigImpl(EncoderImpl encoder)
    {
        this.encoder = encoder;
    }
    
    public static int toInt(boolean value)
    {
        return (value ? 1 : 0);
    }
    
    public static boolean toBoolean(int value)
    {
        return (value == 0? false : true);
    }
    
    public lame_global_flags getLameFlags()    
    {
        return encoder.getLameFlags();
    }
    
    public lame_global_flags preSetCall()
    {
        if (encoder.isEncodingTaskInProcess())
            throw new LAMEOnJException("An encoding task is in process");
            
        return getLameFlags();
    }
    
    public lame_global_flags preGetCall()
    {
        return getLameFlags();
    }    
    
    public static void checkError(int rc)
    {
        LAMEOnJStdEncoderImpl.checkError(rc);
    }
    
    /**
     * number of samples.  default = 2^32-1   
     *
     * <pre>int CDECL lame_set_num_samples(lame_global_flags *, unsigned long);</pre> 
     */    
    public void setNumSamples(int samples)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_num_samples(flags,samples);
        checkError(rc);
    }    

    /**
     * number of samples.  default = 2^32-1      
     *
     * <pre>unsigned long CDECL lame_get_num_samples(const lame_global_flags *);</pre>
     */    
    public int getNumSamples()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_num_samples(flags);
    }          
    
    /**
     * input sample rate in Hz.  default = 44100hz 
     *
       <pre>int CDECL lame_set_in_samplerate(lame_global_flags *, int);</pre>
     */
    public void setInSamplerate(int rate)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_in_samplerate(flags,rate);
        checkError(rc);
    }       

    /**
     * input sample rate in Hz.  default = 44100hz      
     *
        <pre>int CDECL lame_get_in_samplerate(const lame_global_flags *);</pre>    
     */
    public int getInSamplerate()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_in_samplerate(flags); 
    }           
    
    /** 
     * number of channels in input stream. default=2  
     *
     * <pre>int CDECL lame_set_num_channels(lame_global_flags *, int);</pre>
     */
    public void setNumChannels(int channels)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_num_channels(flags,channels);
        checkError(rc);
    }  
    
    /** 
     * number of channels in input stream. default=2  
     *
     * <pre>int CDECL lame_get_num_channels(const lame_global_flags *);</pre>
     */    
    public int getNumChannels()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_num_channels(flags);
    }      
        
    /**
     * scale the input by this amount before encoding.  default=0 (disabled)
     * (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale(lame_global_flags *, float);</pre>
     */
    public void setScale(float scale)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_scale(flags,scale);
        checkError(rc);
    }   
    
    /**
     * scale the input by this amount before encoding.  default=0 (disabled)
     * (not used by decoding routines)     
     *
     * <pre>float CDECL lame_get_scale(const lame_global_flags *);</pre>
     */    
    public float getScale()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_scale(flags);
    }       
    
   
    /**
      scale the channel 0 (left) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale_left(lame_global_flags *, float);</pre>
    */
    public void setScaleLeft(float scale)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_scale_left(flags,scale);
        checkError(rc);
    }
    
    /**
      scale the channel 0 (left) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)
     *     
     * <pre>float CDECL lame_get_scale_left(const lame_global_flags *);</pre>
    */    
    public float getScaleLeft()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_scale_left(flags);
    }    
    
    /**
     * scale the channel 1 (right) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)
     *
     * <pre>int CDECL lame_set_scale_right(lame_global_flags *, float);</pre>
    */
    public void setScaleRight(float scale)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_scale_right(flags,scale);
        checkError(rc);
    }  
    
    /**
     * scale the channel 1 (right) input by this amount before encoding.
        default=0 (disabled)
      (not used by decoding routines)     
     *
     * <pre>float CDECL lame_get_scale_right(const lame_global_flags *);</pre> 
    */    
    public float getScaleRight()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_scale_right(flags);
    }      


    /**
     *      output sample rate in Hz.  default = 0, which means Lame picks best value
     *      based on the amount of compression.  MPEG only allows:
     *      MPEG1    32, 44.1,   48khz
     *      MPEG2    16, 22.05,  24
     *      MPEG2.5   8, 11.025, 12
     *      (not used by decoding routines)
     * 
     * <pre>int CDECL lame_set_out_samplerate(lame_global_flags *, int);</pre>
     */
    public void setOutSamplerate(int rate)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_out_samplerate(flags,rate);
        checkError(rc);
    }          
    
    /**
     *      output sample rate in Hz.  default = 0, which means Lame picks best value
     *      based on the amount of compression.  MPEG only allows:
     *      MPEG1    32, 44.1,   48khz
     *      MPEG2    16, 22.05,  24
     *      MPEG2.5   8, 11.025, 12
     *      (not used by decoding routines)     
     * 
     * <pre>int CDECL lame_get_out_samplerate(const lame_global_flags *);</pre>
     */    
    public int getOutSamplerate()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_out_samplerate(flags);
    }        
    
    
    /**
     * 1=cause Lame to collect data for an MP3 frame analyzer. default=0 
     * <pre>int CDECL lame_set_analysis(lame_global_flags *, int);</pre>
     */
    public void setAnalysis(boolean analysis)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_analysis(flags,toInt(analysis));
        checkError(rc);
    }   
    
    /**
     * 1=cause Lame to collect data for an MP3 frame analyzer. default=0
     *    
     * <pre>int CDECL lame_get_analysis(const lame_global_flags *);</pre>
     */
    public boolean isAnalysis()   
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_analysis(flags));
    }     
    
    /**
      1 = write a Xing VBR header frame.
      default = 1
      this variable must have been added by a Hungarian notation Windows programmer :-)
     *
     * <pre>int CDECL lame_set_bWriteVbrTag(lame_global_flags *, int);</pre>
    */
    public void setBWriteVbrTag(boolean vbrTag)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_bWriteVbrTag(flags,toInt(vbrTag));
        checkError(rc);
    } 
    
    /**
      1 = write a Xing VBR header frame.
      default = 1
      this variable must have been added by a Hungarian notation Windows programmer :-)
     
     *
     * <pre>int CDECL lame_get_bWriteVbrTag(const lame_global_flags *);</pre>   
    */     
    public boolean isBWriteVbrTag()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_bWriteVbrTag(flags));
    }            
    
    /** 
     * 1=decode only.  use lame/mpglib to convert mp3/ogg to wav.  default=0 
     *
     * <pre>int CDECL lame_set_decode_only(lame_global_flags *, int);</pre>
     */
    public void setDecodeOnly(boolean deconly)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_decode_only(flags,toInt(deconly));
        checkError(rc);
    }     
    
    /** 
     * 1=decode only.  use lame/mpglib to convert mp3/ogg to wav.  default=0 
     *
     * <pre>int CDECL lame_get_decode_only(lame_global_flags *);</pre>
     */    
    public boolean isDecodeOnly()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_decode_only(flags));
    }     
    
    /**
     * 1=encode a Vorbis .ogg file.  default=0 
     * DEPRECATED
     *
     * <pre>int CDECL lame_set_ogg(lame_global_flags*, int);</pre>
     */
    public void setOgg(boolean ogg)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_ogg(flags,toInt(ogg));
        checkError(rc);
    }     
    
    /**
     * 1=encode a Vorbis .ogg file.  default=0 
     * DEPRECATED
     *
     * <pre>int CDECL lame_get_ogg(const lame_global_flags *);</pre>
     */    
    public boolean isOgg()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_ogg(flags));
    }         
    
    /**
      internal algorithm selection.  True quality is determined by the bitrate
      but this variable will effect quality by selecting expensive or cheap algorithms.
      quality=0..9.  0=best (very slow).  9=worst.
      recommended:  2     near-best quality, not too slow
                    5     good quality, fast
                    7     ok quality, really fast
     *
     * <pre>int CDECL lame_set_quality(lame_global_flags *, int);</pre>
    */
    public void setQuality(int quality)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_quality(flags,quality);
        checkError(rc);
    }      
    
    /**
      internal algorithm selection.  True quality is determined by the bitrate
      but this variable will effect quality by selecting expensive or cheap algorithms.
      quality=0..9.  0=best (very slow).  9=worst.
      recommended:  2     near-best quality, not too slow
                    5     good quality, fast
                    7     ok quality, really fast     
     *
     * <pre>int CDECL lame_get_quality(const lame_global_flags *);</pre>
    */    
    public int getQuality()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_quality(flags);
    }          
    
    /**
      mode = 0,1,2,3 = stereo, jstereo, dual channel (not supported), mono
      default: lame picks based on compression ration and input channels
     *
     * <pre>int CDECL lame_set_mode(lame_global_flags *, MPEG_mode);</pre>
    */
    public void setMode(int mpeg_mode)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_mode(flags,mpeg_mode);
        checkError(rc);
    }  
    
    /**
      mode = 0,1,2,3 = stereo, jstereo, dual channel (not supported), mono
      default: lame picks based on compression ration and input channels     
     *
     * <pre>MPEG_mode CDECL lame_get_mode(const lame_global_flags *);</pre>  
    */    
    public int getMode()   
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_mode(flags);
    } 
    
    /**
      mode_automs.  Use a M/S mode with a switching threshold based on
      compression ratio
      DEPRECATED
     *
     * <pre>int CDECL lame_set_mode_automs(lame_global_flags *, int);</pre>
    */
    public void setModeAutoms(int mode)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_mode_automs(flags,mode);
        checkError(rc);
    } 
    
    /**
      mode_automs.  Use a M/S mode with a switching threshold based on
      compression ratio
      DEPRECATED
     *
     * <pre>int CDECL lame_get_mode_automs(const lame_global_flags *);</pre>
    */    
    public int getModeAutoms()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_mode_automs(flags); 
    }    
    
    /**
      force_ms.  Force M/S for all frames.  For testing only.
      default = 0 (disabled)
     *
     * <pre>int CDECL lame_set_force_ms(lame_global_flags *, int);</pre>
    */
    public void setForceMs(boolean force)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_force_ms(flags,toInt(force));
        checkError(rc);
    } 
    
    /**
      force_ms.  Force M/S for all frames.  For testing only.
      default = 0 (disabled)
     *
     * <pre>int CDECL lame_get_force_ms(const lame_global_flags *);</pre>
    */    
    public boolean isForceMs()    
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_force_ms(flags));
    }     
    
    /**
     * use free_format?  default = 0 (disabled) 
     
       <pre>int CDECL lame_set_free_format(lame_global_flags *, int);</pre>
     */
    public void setFreeFormat(boolean free)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_free_format(flags,toInt(free));
        checkError(rc);
    }      
        
    /**
     * use free_format?  default = 0 (disabled) 
     
       <pre>int CDECL lame_get_free_format(const lame_global_flags *);</pre>
     */    
    public boolean isFreeFormat()   
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_free_format(flags));
    }     

    
    /**
     * perform ReplayGain analysis?  default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_findReplayGain(lame_global_flags *, int);</pre>
     */
    public void setFindReplayGain(boolean repGain)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_findReplayGain(flags,toInt(repGain));
        checkError(rc);
    }     
    
    /**
     * perform ReplayGain analysis?  default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_findReplayGain(const lame_global_flags *);</pre>
     */    
    public boolean isFindReplayGain()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_findReplayGain(flags));
    }     
    

    /**
     * decode on the fly. Search for the peak sample. If the ReplayGain
     * analysis is enabled then perform the analysis on the decoded data
     * stream. default = 0 (disabled) 
     * NOTE: if this option is set the build-in decoder should not be used 
     
     * <pre>int CDECL lame_set_decode_on_the_fly(lame_global_flags *, int);</pre>
     */
    public void setDecodeOnTheFly(boolean onthefly)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_decode_on_the_fly(flags,toInt(onthefly));
        checkError(rc);
    }     
    
    /**
     * decode on the fly. Search for the peak sample. If the ReplayGain
     * analysis is enabled then perform the analysis on the decoded data
     * stream. default = 0 (disabled) 
     * NOTE: if this option is set the build-in decoder should not be used 
     
     * <pre>int CDECL lame_get_decode_on_the_fly(const lame_global_flags *);</pre>
     */    
    public boolean isDecodeOnTheFly()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_decode_on_the_fly(flags));
    }     
    
    
    /**
     * DEPRECATED: now does the same as lame_set_findReplayGain() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_ReplayGain_input(lame_global_flags *, int);</pre>
     */
    public void setReplayGainInput(boolean repGain)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_ReplayGain_input(flags,toInt(repGain));
        checkError(rc); 
    }    
        
    /**
     * DEPRECATED: now does the same as lame_set_findReplayGain() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_ReplayGain_input(const lame_global_flags *);</pre>
     */    
    public boolean isReplayGainInput()   
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_ReplayGain_input(flags));
    }    
    
    /**
     * DEPRECATED: now does the same as 
       lame_set_decode_on_the_fly() && lame_set_findReplayGain()
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_ReplayGain_decode(lame_global_flags *, int);</pre>
     */
    public void setReplayGainDecode(boolean repGain)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_ReplayGain_decode(flags,toInt(repGain));
        checkError(rc);
    }    
        
    /**
     * DEPRECATED: now does the same as 
       lame_set_decode_on_the_fly() && lame_set_findReplayGain()
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_ReplayGain_decode(const lame_global_flags *);</pre>
     */    
    public boolean isReplayGainDecode()  
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_ReplayGain_decode(flags));
    }    

    /**
     * DEPRECATED: now does the same as lame_set_decode_on_the_fly() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_set_findPeakSample(lame_global_flags *, int);</pre>
     */
    public void setFindPeakSample(boolean findPeak)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_findPeakSample(flags,toInt(findPeak));
        checkError(rc);
    }   
    
    /**
     * DEPRECATED: now does the same as lame_set_decode_on_the_fly() 
       default = 0 (disabled) 
     
     * <pre>int CDECL lame_get_findPeakSample(const lame_global_flags *);</pre>
     */    
    public boolean isFindPeakSample()   
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_findPeakSample(flags));
    }       
        
    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_set_nogap_total(lame_global_flags*, int);</pre>
     */
    public void setNogapTotal(int total)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_nogap_total(flags,total);
        checkError(rc);
    }     
    
    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_get_nogap_total(const lame_global_flags*);</pre>
     */    
    public int getNogapTotal()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_nogap_total(flags);
    }     
    
    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_set_nogap_currentindex(lame_global_flags* , int);</pre>
     */    
    public void setNogapCurrentIndex(int currindex)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_nogap_currentindex(flags,currindex);
        checkError(rc);
    }   
    
    /**      
     * counter for gapless encoding 
     *
     * <pre>int CDECL lame_get_nogap_currentindex(const lame_global_flags*);</pre>
     */    
    public int getNogapCurrentindex()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_nogap_currentindex(flags);
    }   
    
    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     *   void my_debugf(const char *format, va_list ap)
     *   {
     *       (void) vfprintf(stdout, format, ap);
     *   }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_errorf(lame_global_flags *,
                              void (*func)(const char *, va_list));</pre>
     */
    public void setErrorf(LameMsgCallback func)    
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_errorf(flags,func);
        checkError(rc);
    }           
    
    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     *   void my_debugf(const char *format, va_list ap)
     *   {
     *       (void) vfprintf(stdout, format, ap);
     *   }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_debugf(lame_global_flags *,
                              void (*func)(const char *, va_list));</pre>
     */
    public void setDebugf(LameMsgCallback func)    
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_debugf(flags,func);
        checkError(rc);
    }      
    
    
    /**
     * OPTIONAL:
     * Set printf like error/debug/message reporting functions.
     * The second argument has to be a pointer to a function which looks like
     *   void my_debugf(const char *format, va_list ap)
     *   {
     *       (void) vfprintf(stdout, format, ap);
     *   }
     * If you use NULL as the value of the pointer in the set function, the
     * lame buildin function will be used (prints to stderr).
     * To quiet any output you have to replace the body of the example function
     * with just "return;" and use it in the set function.
     *
     * <pre>int CDECL lame_set_msgf(lame_global_flags *,
                              void (*func)(const char *, va_list));</pre>
     */
    public void setMsgf(LameMsgCallback func)    
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_msgf(flags,func);
        checkError(rc);
    }     
 
    /**
     * set one of brate compression ratio.  default is compression ratio of 11.  
     
     * <pre>int CDECL lame_set_brate(lame_global_flags *, int);</pre>
     */
    public void setBrate(int brate)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_brate(flags,brate);
        checkError(rc);
    }     
    
    /**
     * set one of brate compression ratio.  default is compression ratio of 11.  
     
     * <pre>int CDECL lame_get_brate(const lame_global_flags *);
     */    
    public int getBrate()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_brate(flags);
    } 
    
    /* 
     * <pre>int CDECL lame_set_compression_ratio(lame_global_flags *, float);</pre>
     */       
    public void setCompressionRatio(float ratio)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_compression_ratio(flags,ratio);
        checkError(rc);
    } 
    
    /* 
     * <pre>float CDECL lame_get_compression_ratio(const lame_global_flags *);</pre>
     */          
    public float getCompressionRatio()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_compression_ratio(flags);
    } 
            
    /**
     * <pre>int CDECL lame_set_preset( lame_global_flags*  gfp, int );</pre>
     */
    public void setPreset(int preset)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_preset(flags,preset);
        checkError(rc);
    }     
    
    /**
     * <pre>int CDECL lame_set_asm_optimizations( lame_global_flags*  gfp, int, int );</pre>
     */
    public void setAsmOptimizations(int param1, int param2)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_asm_optimizations(flags,param1,param2);
        checkError(rc);
    }      

    /**
     * mark as copyright.  default=0 
     *
       <pre>int CDECL lame_set_copyright(lame_global_flags *, int);</pre>
     */
    public void setCopyright(boolean cprght)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_copyright(flags,toInt(cprght));
        checkError(rc);
    }      
    
    /**
     * mark as copyright.  default=0 
     *
       <pre>int CDECL lame_get_copyright(const lame_global_flags *);</pre>
     */    
    public boolean isCopyright()   
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_copyright(flags));
    }
    
    /**
     * mark as original.  default=1 
     *
       <pre>int CDECL lame_set_original(lame_global_flags *, int);</pre>
     */
    public void setOriginal(boolean original)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_original(flags,toInt(original));
        checkError(rc);
    }

    /**
     * mark as original.  default=1      
     *
       <pre>int CDECL lame_get_original(const lame_global_flags *);</pre>    
     */
    public boolean isOriginal()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_original(flags));
    }        
    
    /**
     * error_protection.  Use 2 bytes from each frame for CRC checksum. default=0
     *
       <pre>int CDECL lame_set_error_protection(lame_global_flags *, int);</pre>  
    */    
    public void setErrorProtection(boolean prot)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_error_protection(flags,toInt(prot));
        checkError(rc);
    }  
    
    /**
     * error_protection.  Use 2 bytes from each frame for CRC checksum. default=0     
     *
       <pre>int CDECL lame_get_error_protection(const lame_global_flags *);</pre>
    */       
    public boolean isErrorProtection()   
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_error_protection(flags));
    } 
    
    /**
     * padding_type. 0=pad no frames  1=pad all frames 2=adjust padding(default)
     *
       <pre>int CDECL lame_set_padding_type(lame_global_flags *, Padding_type);</pre>
    */    
    public void setPaddingType(int padding_type)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_padding_type(flags,padding_type);
        checkError(rc);
    }  
    
    /**
     * padding_type. 0=pad no frames  1=pad all frames 2=adjust padding(default)    
     *
       <pre>Padding_type CDECL lame_get_padding_type(const lame_global_flags *);</pre>    
    */       
    public int getPaddingType()   
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_padding_type(flags);
    }         
    
    /**
    * MP3 'private extension' bit  Meaningless.  default=0 

    * <pre>int CDECL lame_set_extension(lame_global_flags *, int);</pre>
    */
    public void setExtension(int ext)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_extension(flags,ext);
        checkError(rc);
    }
        
    /**
     * MP3 'private extension' bit  Meaningless.  default=0 
     
     * <pre>int CDECL lame_get_extension(const lame_global_flags *); </pre>
     */     
    public int getExtension()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_extension(flags); 
    }    
    
    /**
     * enforce strict ISO compliance.  default=0 
     *
     * <pre>int CDECL lame_set_strict_ISO(lame_global_flags *, int);</pre>
     */
    public void setStrictISO(boolean iso)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_strict_ISO(flags,toInt(iso));
        checkError(rc);
    }    

    /**
     * enforce strict ISO compliance.  default=0 
     *
     * <pre>int CDECL lame_get_strict_ISO(const lame_global_flags *);</pre>
     */
    public boolean isStrictISO()  
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_strict_ISO(flags));
    }       
    
    /**
     * disable the bit reservoir. For testing only. default=0
     *
       <pre>int CDECL lame_set_disable_reservoir(lame_global_flags *, int);</pre>
    */    
    public void setDisableReservoir(boolean preset)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_disable_reservoir(flags,toInt(preset));
        checkError(rc);
    }  
    
    /**
     * disable the bit reservoir. For testing only. default=0     
     *
       <pre>int CDECL lame_get_disable_reservoir(const lame_global_flags *);</pre>
    */       
    public boolean isDisableReservoir()   
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_disable_reservoir(flags)); 
    }               
    
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_set_quant_comp(lame_global_flags *, int);</pre>
     */
    public void setQuantComp(int quant)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_quant_comp(flags,quant);
        checkError(rc);
    }    
    
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_get_quant_comp(const lame_global_flags *);</pre>
     */    
    public int getQuantComp()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_quant_comp(flags);
    }    
    
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_set_quant_comp_short(lame_global_flags *, int);</pre>
     */    
    public void setQuantCompShort(int quant)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_quant_comp_short(flags,quant);
        checkError(rc);
    }    
    
    /**
     * select a different "best quantization" function. default=0  
     
       <pre>int CDECL lame_get_quant_comp_short(const lame_global_flags *);</pre>
     */        
    public int getQuantCompShort() 
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_quant_comp_short(flags);
    }
    
    /**
     * <pre>int CDECL lame_set_experimentalX(lame_global_flags *, int);</pre>
     */
    public void setExperimentalX(int x)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_experimentalX(flags,x);
        checkError(rc);
    }
    
    /**
     * <pre>int CDECL lame_get_experimentalX(const lame_global_flags *);</pre>
     */    
    public int getExperimentalX()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_experimentalX(flags);
    }
    
    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_set_experimentalY(lame_global_flags *, int);</pre>
     */
    public void setExperimentalY(int y)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_experimentalY(flags,y);
        checkError(rc);
    }    

    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_get_experimentalY(const lame_global_flags *);</pre>
     */    
    public int getExperimentalY()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_experimentalY(flags);
    }        
    
    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_set_experimentalZ(lame_global_flags *, int);</pre>
     */
    public void setExperimentalZ(int z)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_experimentalZ(flags,z);
        checkError(rc);
    }    

    /** 
     * another experimental option.  for testing only 
      
     * <pre>int CDECL lame_get_experimentalZ(const lame_global_flags *);</pre>
     */    
    public int getExperimentalZ()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_experimentalZ(flags);
    }            
    
    /* Naoki's psycho acoustic model.  default=0 
     
      <pre>int CDECL lame_set_exp_nspsytune(lame_global_flags *, int);</pre>
     */
    public void setExpNspsytune(int tune)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_exp_nspsytune(flags,tune);
        checkError(rc);
    }    
    
    /* Naoki's psycho acoustic model.  default=0 
     
      <pre>int CDECL lame_get_exp_nspsytune(const lame_global_flags *);</pre>
     */    
    public int getExpNspsytune()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_exp_nspsytune(flags);
    }    
    
    /**
     * <pre>void CDECL lame_set_msfix(lame_global_flags *, double);</pre>
     */
    public void setMsfix(double msfix)
    {
        lame_global_flags flags = preSetCall();
        Lame.lame_set_msfix(flags,msfix);
    }    
    
    /**
     * <pre>float CDECL lame_get_msfix(const lame_global_flags *);</pre>
     */    
    public float getMsfix()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_msfix(flags);
    }        
    
    /**
     * <pre>int lame_set_exp_nspsytune2_int( lame_global_flags*, int, int);</pre>
     */      
    public int setExpNspsytune2Int( int p1, int p2)
    {
        lame_global_flags flags = preSetCall();
        return Lame.lame_set_exp_nspsytune2_int(flags,p1,p2);
    }    
    
    /**
     * <pre>float lame_set_exp_nspsytune2_real( lame_global_flags*, int, float);</pre>
     */     
    public float setExpNspsytune2Real( int p1, float p2)
    {
        lame_global_flags flags = preSetCall();
        return Lame.lame_set_exp_nspsytune2_real(flags,p1,p2);
    }        
    
    /**
     * <pre>void * lame_set_exp_nspsytune2_pointer( lame_global_flags*, int, void *);</pre>
     */      
    public int setExpNspsytune2Pointer( int p1, int p2)
    {
        lame_global_flags flags = preSetCall();
        return Lame.lame_set_exp_nspsytune2_pointer(flags,p1,p2);
    }       

    /** 
     * Types of VBR.  default = vbr_off = CBR 
     *
     * <pre>int CDECL lame_set_VBR(lame_global_flags *, vbr_mode);</pre>
     */
    public void setVBR(int vbr_mode)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_VBR(flags,vbr_mode);
        checkError(rc);
    }     
    
    /** 
     * Types of VBR.  default = vbr_off = CBR 
     *     
     * <pre>vbr_mode CDECL lame_get_VBR(const lame_global_flags *);</pre>
     */    
    public int getVBR()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_VBR(flags);
    }   
    
    /**
     * VBR quality level.  0=highest  9=lowest  
     *
        <pre>int CDECL lame_set_VBR_q(lame_global_flags *, int);</pre>
     */
    public void setVBRQ(int level)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_VBR_q(flags,level);
        checkError(rc); 
    }   
    
    /**
     * VBR quality level.  0=highest  9=lowest
     *     
        <pre>int CDECL lame_get_VBR_q(const lame_global_flags *);</pre>
     */    
    public int getVBRq()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_VBR_q(flags);
    }   
    
    /**
     * Ignored except for VBR=vbr_abr (ABR mode) 
     *
        <pre>int CDECL lame_set_VBR_mean_bitrate_kbps(lame_global_flags *, int);</pre>    
     */
    public void setVBRMeanBitrateKbps(int bitrate)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_VBR_mean_bitrate_kbps(flags,bitrate);
        checkError(rc); 
    }      
    
    /**
     * Ignored except for VBR=vbr_abr (ABR mode)     
     *
        <pre>int CDECL lame_get_VBR_mean_bitrate_kbps(const lame_global_flags *);</pre>  
     */    
    public int getVBRMeanBitrateKbps()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_VBR_mean_bitrate_kbps(flags);
    }      

    /**
        <pre>int CDECL lame_set_VBR_min_bitrate_kbps(const lame_global_flags *);</pre>  
     */     
    public void setVBRMinBitrateKbps(int bitrate)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_VBR_min_bitrate_kbps(flags,bitrate);
        checkError(rc); 
    }      
    
    /**
        <pre>int CDECL lame_get_VBR_min_bitrate_kbps(const lame_global_flags *);</pre>  
     */    
    public int getVBRMinBitrateKbps()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_VBR_min_bitrate_kbps(flags);
    }      

    /**
        <pre>int CDECL lame_set_VBR_max_bitrate_kbps(const lame_global_flags *);</pre>  
     */    
    public void setVBRMaxBitrateKbps(int bitrate)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_VBR_max_bitrate_kbps(flags,bitrate);
        checkError(rc);
    }      
    
    /**
        <pre>int CDECL lame_get_VBR_max_bitrate_kbps(const lame_global_flags *);</pre>  
     */     
    public int getVBRMaxBitrateKbps()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_VBR_max_bitrate_kbps(flags);
    }      

    /**
      1=strictly enforce VBR_min_bitrate.  Normally it will be violated for
      analog silence
     *
     * <pre>int CDECL lame_set_VBR_hard_min(lame_global_flags *, int);</pre>
    */
    public void setVBRHardMin(boolean vbr)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_VBR_hard_min(flags,toInt(vbr));
        checkError(rc);
    } 
    
    /**
      1=strictly enforce VBR_min_bitrate.  Normally it will be violated for
      analog silence
     *
     * <pre>int CDECL lame_get_VBR_hard_min(const lame_global_flags *);</pre>
    */    
    public boolean isVBRHardMin()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_VBR_hard_min(flags));
    }     
    
    /** 
     * for preset 
     
     * <pre>int CDECL lame_set_preset_expopts(lame_global_flags *, int);</pre>
     */
    public void setPresetExpopts(int expopts)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_preset_expopts(flags,expopts);
        checkError(rc);
    }        
    
    /**
     * freq in Hz to apply lowpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_set_lowpassfreq(lame_global_flags *, int);</pre>
     */
    public void setLowpassfreq(int freq)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_lowpassfreq(flags,freq);
        checkError(rc);
    }     
    
    /**
     * freq in Hz to apply lowpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_get_lowpassfreq(const lame_global_flags *);</pre>
     */    
    public int getLowpassfreq()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_lowpassfreq(flags);      
    }    
    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_set_lowpasswidth(lame_global_flags *, int);</pre>
     */
    public void setLowpasswidth(int width)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_lowpasswidth(flags,width);
        checkError(rc);
    }      
    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_get_lowpasswidth(const lame_global_flags *);</pre>
     */    
    public int getLowpasswidth()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_lowpasswidth(flags);
    }      
    
    /** freq in Hz to apply highpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_set_highpassfreq(lame_global_flags *, int);</pre>
     */
    public void setHighpassfreq(int freq)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_highpassfreq(flags,freq);
        checkError(rc);
    }      
    
    /** freq in Hz to apply highpass. Default = 0 = lame chooses.  -1 = disabled 
     
     * <pre>int CDECL lame_get_highpassfreq(const lame_global_flags *);</pre>
     */    
    public int getHighpassfreq()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_highpassfreq(flags);
    }      
    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_set_highpasswidth(lame_global_flags *, int);</pre>
     */
    public void setHighpasswidth(int width)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_highpasswidth(flags,width);
        checkError(rc);
    }    
    
    /**
     * width of transition band, in Hz.  Default = one polyphase filter band 
     
     * <pre>int CDECL lame_get_highpasswidth(const lame_global_flags *);</pre>
     */    
    public int getHighpasswidth()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_highpasswidth(flags);
    }    
    
    /** 
     * only use ATH for masking 
     
     * <pre>int CDECL lame_set_ATHonly(lame_global_flags *, int);</pre>
     */
    public void setATHonly(boolean athOnly)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_ATHonly(flags,toInt(athOnly));
        checkError(rc);
    }       
    
    /** 
     * only use ATH for masking 
     
     * <pre>int CDECL lame_get_ATHonly(const lame_global_flags *);</pre>
     */    
    public boolean isATHonly()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_ATHonly(flags));
    }       
    
    /**
     * only use ATH for short blocks 
     
     * <pre>int CDECL lame_set_ATHshort(lame_global_flags *, int);</pre>
     */
    public void setATHshort(boolean athShort)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_ATHshort(flags,toInt(athShort));
        checkError(rc);
    }       
    
    /**
     * only use ATH for short blocks 
     
     * <pre>int CDECL lame_get_ATHshort(const lame_global_flags *);</pre>
     */    
    public boolean isATHshort()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_ATHshort(flags));
    }       
    
    /**
     * disable ATH 
     
     * <pre>int CDECL lame_set_noATH(lame_global_flags *, int);</pre>
     */
    public void setNoATH(boolean noATH)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_noATH(flags,toInt(noATH));
        checkError(rc);
    }     
    
    /**
     * disable ATH 
     
     * <pre>int CDECL lame_get_noATH(const lame_global_flags *);</pre>
     */    
    public boolean isNoATH()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_noATH(flags)); 
    }     
    
    /**
     * select ATH formula 
     
     * <pre>int CDECL lame_set_ATHtype(lame_global_flags *, int);</pre>
     */
    public void setATHtype(int ATHType)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_ATHtype(flags,ATHType);
        checkError(rc);
    } 
    
    /**
     * select ATH formula 
     
     * <pre>int CDECL lame_get_ATHtype(lame_global_flags *);</pre>
     */    
    public int getATHtype()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_ATHtype(flags);
    }    

    /**
     * lower ATH by this many db 
     
     * <pre>int CDECL lame_set_ATHlower(lame_global_flags *, float);</pre>
     */
    public void setATHlower(float ATHLower)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_ATHlower(flags,ATHLower);
        checkError(rc);
    }        
    
    /**
     * lower ATH by this many db 
     
     * <pre>floag CDECL lame_get_ATHlower(lame_global_flags *);</pre>
     */    
    public float getATHlower()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_ATHlower(flags);
    }      
    
    /**
     * select ATH adaptive adjustment type 
     
     * <pre>int CDECL lame_set_athaa_type( lame_global_flags *, int);</pre>
     */
    public void setAthaaType( int ATHadjust)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_athaa_type(flags,ATHadjust);
        checkError(rc);
    }          
    
    /**
     * select ATH adaptive adjustment type 
     
     * <pre>int CDECL lame_get_athaa_type( const lame_global_flags *);</pre>
     */    
    public int getAthaaType()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_athaa_type(flags);
    }          
    
    /**
     * select the loudness approximation used by the ATH adaptive auto-leveling  
     
     * <pre>int CDECL lame_set_athaa_loudapprox( lame_global_flags *, int);</pre>
     */
    public void setAthaaLoudapprox( int loud)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_athaa_loudapprox(flags,loud);
        checkError(rc);
    }              
    
    /**
     * select the loudness approximation used by the ATH adaptive auto-leveling  
     
     * <pre>int CDECL lame_get_athaa_loudapprox( const lame_global_flags *);</pre>
     */    
    public int getAthaaLoudapprox()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_athaa_loudapprox(flags);
    }              
    
    /**
     * adjust (in dB) the point below which adaptive ATH level adjustment occurs 
     
     * <pre>int CDECL lame_set_athaa_sensitivity( lame_global_flags *, float);</pre>
     */
    public void setAthaaSensitivity( float sen)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_athaa_sensitivity(flags,sen);
        checkError(rc);
    }     
    
    /**
     * adjust (in dB) the point below which adaptive ATH level adjustment occurs 
     
     * <pre>float CDECL lame_get_athaa_sensitivity( const lame_global_flags* );</pre>
     */    
    public float getAthaaSensitivity()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_athaa_sensitivity(flags);
    }     
    
    /** 
     * predictability limit (ISO tonality formula) 
     
       <pre>int CDECL lame_set_cwlimit(lame_global_flags *, int);</pre>
     */
    public void setCwlimit(int limit)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_cwlimit(flags,limit);
        checkError(rc);
    }       
    
    /** 
     * predictability limit (ISO tonality formula) 
     
       <pre>int CDECL lame_get_cwlimit(const lame_global_flags *);</pre>
     */    
    public int getCwlimit()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_cwlimit(flags);
    }       
    
    /**
      allow blocktypes to differ between channels?
      default: 0 for jstereo, 1 for stereo
     *
     * <pre>int CDECL lame_set_allow_diff_short(lame_global_flags *, int);</pre>
    */
    public void setAllowDiffShort(int allow)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_allow_diff_short(flags,allow);
        checkError(rc);
    }          
    
    /**
      allow blocktypes to differ between channels?
      default: 0 for jstereo, 1 for stereo
     *
     * <pre>int CDECL lame_get_allow_diff_short(const lame_global_flags *);</pre>
    */    
    public int getAllowDiffShort()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_allow_diff_short(flags);
    }          
    
    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>int CDECL lame_set_useTemporal(lame_global_flags *, int);</pre>
     */
    public void setUseTemporal(boolean maskEffect)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_useTemporal(flags,toInt(maskEffect));
        checkError(rc);
    }      
    
    
    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>int CDECL lame_get_useTemporal(const lame_global_flags *);</pre>
     */    
    public boolean isUseTemporal()    
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_useTemporal(flags));
    }      
    
    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>int CDECL lame_set_interChRatio(lame_global_flags *, float);</pre>
     */
    public void setInterChRatio(float interChRatio)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_interChRatio(flags,interChRatio);
        checkError(rc);
    }     
        
    /** 
     * use temporal masking effect (default = 1) 
     
     * <pre>float CDECL lame_get_interChRatio(const lame_global_flags *);</pre>
     */    
    public float getInterChRatio()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_interChRatio(flags);
    }     
    
    /**
     * disable short blocks 
     
       <pre>int CDECL lame_set_no_short_blocks(lame_global_flags *, int);</pre>
     */
    public void setNoShortBlocks(boolean noShort)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_no_short_blocks(flags,toInt(noShort));
        checkError(rc);
    }       
    
    /**
     * disable short blocks 
     
       <pre>int CDECL lame_get_no_short_blocks(const lame_global_flags *);</pre>
     */    
    public boolean isNoShortBlocks()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_no_short_blocks(flags));
    }       

    /** 
     * force short blocks 
     
     * <pre>int CDECL lame_set_force_short_blocks(lame_global_flags *, int);</pre>
     */
    public void setForceShortBlocks(boolean force)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_force_short_blocks(flags,toInt(force));
        checkError(rc);
    }  
    
    /** 
     * force short blocks 
     
     * <pre>int CDECL lame_get_force_short_blocks(const lame_global_flags *);</pre>
     */    
    public boolean isForceShortBlocks()    
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_force_short_blocks(flags));
    }  

    /**
     * Input PCM is emphased PCM (for instance from one of the rarely
       emphased CDs), it is STRONGLY not recommended to use this, because
       psycho does not take it into account, and last but not least many decoders
       ignore these bits      
     
     * <pre>int CDECL lame_set_emphasis(lame_global_flags *, int);</pre>
     */
    public void setEmphasis(boolean emph)
    {
        lame_global_flags flags = preSetCall();
        int rc = Lame.lame_set_emphasis(flags,toInt(emph));
        checkError(rc);
    }      
    
    /**
     * Input PCM is emphased PCM (for instance from one of the rarely
       emphased CDs), it is STRONGLY not recommended to use this, because
       psycho does not take it into account, and last but not least many decoders
       ignore these bits      
     
     * <pre>int CDECL lame_get_emphasis(const lame_global_flags *);</pre>
     */    
    public boolean isEmphasis()
    {
        lame_global_flags flags = preGetCall();
        return toBoolean(Lame.lame_get_emphasis(flags));
    }      
    
    /**
     * version  0=MPEG-2  1=MPEG-1  (2=MPEG-2.5)    
     *
     * <pre>int CDECL lame_get_version(const lame_global_flags *);</pre> 
     */
    public int getVersion()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_version(flags);
    }               

    /** 
     * encoder delay   
     
     * <pre>int CDECL lame_get_encoder_delay(const lame_global_flags *);</pre>
     */
    public int getEncoderDelay()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_encoder_delay(flags);
    }    
    
    /**
      padding appended to the input to make sure decoder can fully decode
      all input.  Note that this value can only be calculated during the
      call to lame_encoder_flush().  Before lame_encoder_flush() has
      been called, the value of encoder_padding = 0.
     *
     * <pre>int CDECL lame_get_encoder_padding(const lame_global_flags *);  </pre>
    */
    public int getEncoderPadding()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_encoder_padding(flags);
    }        
    
    /**
     * size of MPEG frame 
     
      <pre>int CDECL lame_get_framesize(const lame_global_flags *);</pre>
     */
    public int getFramesize()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_framesize(flags);
    }    
    
    /**
     * number of PCM samples buffered, but not yet encoded to mp3 data. 
     
     * <pre>int CDECL lame_get_mf_samples_to_encode( const lame_global_flags*  gfp );</pre>
     */
    public int getMfSamplesToEncode( )
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_mf_samples_to_encode(flags);
    }     
    
    /**
      size (bytes) of mp3 data buffered, but not yet encoded.
      this is the number of bytes which would be output by a call to 
      lame_encode_flush_nogap.  NOTE: lame_encode_flush() will return
      more bytes than this because it will encode the reamining buffered
      PCM samples before flushing the mp3 buffers.
     *
     * <pre>int CDECL lame_get_size_mp3buffer( const lame_global_flags*  gfp );</pre>
    */
    public int getSizeMP3buffer()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_size_mp3buffer(flags);
    }    
    
    /**
     * number of frames encoded so far 
     
      <pre>int CDECL lame_get_frameNum(const lame_global_flags *);</pre>
     */
    public int getFrameNum()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_frameNum(flags);
    }  
    
    /**
      lame's estimate of the total number of frames to be encoded
       only valid if calling program set num_samples
     *
     * <pre>int CDECL lame_get_totalframes(const lame_global_flags *);</pre>
    */
    public int getTotalframes()    
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_totalframes(flags);
    }      
    
    /** 
     * RadioGain value. Multiplied by 10 and rounded to the nearest. 
     
     * <pre>int CDECL lame_get_RadioGain(const lame_global_flags *);</pre>
     */
    public int getRadioGain()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_RadioGain(flags);
    }   
    
    /**
     * AudiophileGain value. Multipled by 10 and rounded to the nearest. 
     
      <pre>int CDECL lame_get_AudiophileGain(const lame_global_flags *);</pre>
     */
    public int getAudiophileGain()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_AudiophileGain(flags);
    }
    
    /**
     * the peak sample 
     
      <pre>float CDECL lame_get_PeakSample(const lame_global_flags *);</pre>
     */
    public float getPeakSample()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_PeakSample(flags);
    }    
    
    /**
     * Gain change required for preventing clipping. The value is correct only if 
       peak sample searching was enabled. If negative then the waveform 
       already does not clip. The value is multiplied by 10 and rounded up. 
     
      <pre>int CDECL lame_get_noclipGainChange(const lame_global_flags *);</pre>
     */
    public int getNoclipGainChange()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_noclipGainChange(flags);
    }      
    
    /**
     * user-specified scale factor required for preventing clipping. Value is 
       correct only if peak sample searching was enabled and no user-specified
       scaling was performed. If negative then either the waveform already does
       not clip or the value cannot be determined 
     
     * <pre>float CDECL lame_get_noclipScale(const lame_global_flags *);</pre>
     */
    public float getNoclipScale()
    {
        lame_global_flags flags = preGetCall();
        return Lame.lame_get_noclipScale(flags);
    }

    
    /**
     * OPTIONAL:
     * get the version number, in a string. of the form:  
     * "3.63 (beta)" or just "3.63". 
     *
     * <pre>const char*  CDECL get_lame_version( void );</pre>
     */
    public String getLameVersion()
    {
        return Lame.get_lame_version();
    } 
    
    /**
        <pre>const char*  CDECL get_lame_short_version( void );</pre>
     */    
    public String getLameShortVersion()
    {
        return Lame.get_lame_short_version();
    } 
    
    /**
        <pre>const char*  CDECL get_lame_very_short_version( void );</pre>
     */    
    public String getLameVeryShortVersion()
    {
        return Lame.get_lame_very_short_version();
    } 
    
    /**
        <pre>const char*  CDECL get_psy_version( void );</pre>
     */     
    public String getPsyVersion()
    {
        return Lame.get_psy_version();
    }     
    
    /**
        <pre>const char*  CDECL get_lame_url( void );</pre>
     */     
    public String getLameUrl()
    {
        return Lame.get_lame_url();
    }         
    
    
    /*
     * OPTIONAL:
     * get the version numbers in numerical form.
     *
     * <pre>void CDECL get_lame_version_numerical ( lame_version_t *const );</pre>
     */
    public void getLameVersionNumerical( lame_version_t version )    
    {
        Lame.get_lame_version_numerical(version);
    }    
        
    /*
     * OPTIONAL:
     * print internal lame configuration to message handler
     *
     * <pre>void CDECL lame_print_config(const lame_global_flags*  gfp);</pre>
     */
    public void printConfig()    
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_print_config(flags);
    }    
    
    /**
     * <pre>void CDECL lame_print_internals( const lame_global_flags *gfp);</pre>
     */
    public void printInternals( )    
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_print_internals(flags);
    }            
    

    /*
     * OPTIONAL:    some simple statistics
     * a bitrate histogram to visualize the distribution of used frame sizes
     * a stereo mode histogram to visualize the distribution of used stereo
     *   modes, useful in joint-stereo mode only
     *   0: LR    left-right encoded
     *   1: LR-I  left-right and intensity encoded (currently not supported)
     *   2: MS    mid-side encoded
     *   3: MS-I  mid-side and intensity encoded (currently not supported)
     *
     * attention: don't call them after lame_encode_finish
     * suggested: lame_encode_flush -> lame_*_hist -> lame_close
     *
     * <pre>
     *    void CDECL lame_bitrate_hist( 
                const lame_global_flags *const gfp, 
                int   bitrate_count[14] );
     * </pre>
     */
    public void bitrateHist(int[] bitrate_count)
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_bitrate_hist(flags,bitrate_count);
    }     
    
    /**
     * See {@link #lame_bitrate_hist(lame_global_flags,int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_kbps( 
            const lame_global_flags *const gfp, 
            int bitrate_kbps [14] );  
     * </pre>
     */
    public void bitrateKbps(int[]  bitrate_kbps)    
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_bitrate_kbps(flags,bitrate_kbps);
    }
    
    /**
     * See {@link #lame_bitrate_hist(lame_global_flags,int[])}
     *
     * <pre>
     * void CDECL lame_stereo_mode_hist( 
            const lame_global_flags *const gfp, 
            int stereo_mode_count[4] );  
     * </pre>
     */    
    public void stereoModeHist(int[] stereo_mode_count)
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_stereo_mode_hist(flags,stereo_mode_count);
    }    
    
    /**
     * See {@link #lame_bitrate_hist(lame_global_flags,int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_stereo_mode_hist ( 
        const lame_global_flags * const gfp, 
        int  bitrate_stmode_count [14] [4] );
     * </pre>
     */      
    public void bitrateStereoModeHist(int[][]  bitrate_stmode_count)
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_bitrate_stereo_mode_hist(flags,bitrate_stmode_count);
    }    
    
    /**
     * See {@link #lame_bitrate_hist(lame_global_flags,int[])}
     *
     * <pre>
     * void CDECL lame_block_type_hist (
        const lame_global_flags * const gfp, 
        int btype_count[6] );
     * </pre>
     */      
    public void blockTypeHist(int[]  btype_count)
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_block_type_hist(flags,btype_count);
    }     
    
    /**
     * See {@link #lame_bitrate_hist(lame_global_flags,int[])}
     *
     * <pre>
     * void CDECL lame_bitrate_block_type_hist ( 
        const lame_global_flags * const gfp, 
        int bitrate_btype_count[14][6] );
     * </pre>
     */      
    public void bitrateBlockTypeHist(int[][]  bitrate_btype_count)
    {
        lame_global_flags flags = preGetCall();
        Lame.lame_bitrate_block_type_hist(flags,bitrate_btype_count);
    }      
}
