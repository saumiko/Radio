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
   typedef enum vbr_mode_e {
      vbr_off=0,
      vbr_mt,               // obsolete, same as vbr_mtrh 
      vbr_rh,
      vbr_abr,
      vbr_mtrh,
      vbr_max_indicator,    // Don't use this! It's used for sanity checks.       
      vbr_default=vbr_rh    // change this to change the default VBR mode of LAME 
    } vbr_mode;
 *  </pre>
 */
public interface vbr_mode
{
    int vbr_off  = 0;
    int vbr_mt   = 1;
    int vbr_rh   = 2;
    int vbr_abr  = 3;
    int vbr_mtrh = 4;
    int vbr_max_indicator = 5;
    int vbr_default = vbr_rh;     
}
