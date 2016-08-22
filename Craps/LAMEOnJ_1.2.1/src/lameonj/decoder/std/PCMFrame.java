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

/**
 * This interface represents a mono/stereo PCM frame.
 * 
 * <p>Each short element of the data arrays represents a PCM sample (PCM 16 bits).</p>
 * 
 * <p>If the MP3 data used to build this PCM frame is mono only the left PCM data is valid.</p>
 * 
 * <p>Note: a PCM 16 bits frame contains 1152 samples, a PCM 8 bits frame contains 576 samples.</p> 
 * 
 * @author jmarranz
 */
public interface PCMFrame 
{
    /**
     * Returns the PCM samples of the left channel. 
     * 
     * @return the samples of the left channel.
     */
    public short[] getPCMDataLeft();
    
    /**
     * Returns the PCM samples of the right channel. 
     * 
     * <p>Ignore this channel if MP3 data source is mono.</p>
     * 
     * @return the samples of the right channel.
     */
    public short[] getPCMDataRight(); 
    
}
