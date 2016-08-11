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
	VBR_METHOD_NONE			= -1,
	VBR_METHOD_DEFAULT		=  0,
	VBR_METHOD_OLD			=  1,
	VBR_METHOD_NEW			=  2,
	VBR_METHOD_MTRH			=  3,
	VBR_METHOD_ABR			=  4
} VBRMETHOD;
 </pre>
</code> 
 */

public interface VBRMETHOD
{
    int VBR_METHOD_NONE     = -1;
    int VBR_METHOD_DEFAULT  =  0;
    int VBR_METHOD_OLD      =  1;
    int VBR_METHOD_NEW	=  2;
    int VBR_METHOD_MTR	=  3;
    int VBR_METHOD_ABR	=  4;   
}
