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
 *
    Asm optimizations:
 *  <pre>
    typedef enum asm_optimizations_e {
        MMX = 1,
        AMD_3DNOW = 2,
        SSE = 3
    } asm_optimizations;
 *  </pre>
 */
public interface asm_optimizations
{
    int MMX = 1;
    int AMD_3DNOW = 2;
    int SSE = 3;
}
