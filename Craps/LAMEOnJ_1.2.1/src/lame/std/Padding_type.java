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
 *  Padding types:
 *
   <pre>
    typedef enum Padding_type_e {
      PAD_NO = 0,
      PAD_ALL,
      PAD_ADJUST,
      PAD_MAX_INDICATOR    // Don't use this! It's used for sanity checks. 
    } Padding_type;
   </pre>
 */
public interface Padding_type
{
    int PAD_NO  = 0;
    int PAD_ALL   = 1;
    int PAD_ADJUST   = 2;
    int PAD_MAX_INDICATOR  = 3;   
}
