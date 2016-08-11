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
 * The <code>LAME_QUALITY_PRESET</code> interface is the Java symmetric representation of the corresponding 
 * C LAME enumeration as declared in the file BladeMP3EncDLL.h. 
<code>
 <pre>
typedef enum 
{
	LQP_NOPRESET			=-1,

	// QUALITY PRESETS
	LQP_NORMAL_QUALITY		= 0,
	LQP_LOW_QUALITY			= 1,
	LQP_HIGH_QUALITY		= 2,
	LQP_VOICE_QUALITY		= 3,
	LQP_R3MIX				= 4,
	LQP_VERYHIGH_QUALITY	= 5,
	LQP_STANDARD			= 6,
	LQP_FAST_STANDARD		= 7,
	LQP_EXTREME				= 8,
	LQP_FAST_EXTREME		= 9,
	LQP_INSANE				= 10,
	LQP_ABR					= 11,
	LQP_CBR					= 12,
	LQP_MEDIUM				= 13,
	LQP_FAST_MEDIUM			= 14,

	// NEW PRESET VALUES
	LQP_PHONE	=1000,
	LQP_SW		=2000,
	LQP_AM		=3000,
	LQP_FM		=4000,
	LQP_VOICE	=5000,
	LQP_RADIO	=6000,
	LQP_TAPE	=7000,
	LQP_HIFI	=8000,
	LQP_CD		=9000,
	LQP_STUDIO	=10000

} LAME_QUALITY_PRESET;
 </pre>
</code>
 */
public interface LAME_QUALITY_PRESET
{
    int LQP_NOPRESET		=-1;

    // QUALITY PRESETS
    int LQP_NORMAL_QUALITY		= 0;
    int LQP_LOW_QUALITY		= 1;
    int LQP_HIGH_QUALITY		= 2;
    int LQP_VOICE_QUALITY		= 3;
    int LQP_R3MIX			= 4;
    int LQP_VERYHIGH_QUALITY	= 5;
    int LQP_STANDARD		= 6;
    int LQP_FAST_STANDARD		= 7;
    int LQP_EXTREME			= 8;
    int LQP_FAST_EXTREME		= 9;
    int LQP_INSANE			= 10;
    int LQP_ABR			= 11;
    int LQP_CBR			= 12;
    int LQP_MEDIUM			= 13;
    int LQP_FAST_MEDIUM		= 14;

    // NEW PRESET VALUES
    int LQP_PHONE           =1000;
    int LQP_SW		=2000;
    int LQP_AM		=3000;
    int LQP_FM		=4000;
    int LQP_VOICE           =5000;
    int LQP_RADIO           =6000;
    int LQP_TAPE            =7000;
    int LQP_HIFI            =8000;
    int LQP_CD		=9000;
    int LQP_STUDIO          =10000;
}
