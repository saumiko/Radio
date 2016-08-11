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
    Presets:
 *  <pre>
    typedef enum preset_mode_e {
        //values from 8 to 320 should be reserved for abr bitrates
        //for abr I'd suggest to directly use the targeted bitrate as a value
        ABR_8 = 8,
        ABR_320 = 320,

        V9 = 410,  // Vx to match Lame and VBR_xx to match FhG
        VBR_10 = 410,
        V8 = 420,
        VBR_20 = 420,
        V7 = 430,
        VBR_30 = 430,
        V6 = 440,
        VBR_40 = 440,
        V5 = 450,
        VBR_50 = 450,
        V4 = 460,
        VBR_60 = 460,
        V3 = 470,
        VBR_70 = 470,
        V2 = 480,
        VBR_80 = 480,
        V1 = 490,
        VBR_90 = 490,
        V0 = 500,
        VBR_100 = 500,


        //still there for compatibility
        R3MIX = 1000,
        STANDARD = 1001,
        EXTREME = 1002,
        INSANE = 1003,
        STANDARD_FAST = 1004,
        EXTREME_FAST = 1005,
        MEDIUM = 1006,
        MEDIUM_FAST = 1007
    } preset_mode;
 *  </pre>
 */
public interface preset_mode
{
    int ABR_8 = 8;
    int ABR_320 = 320;
    int V9 = 410;
    int VBR_10 = 410;
    int V8 = 420;
    int VBR_20 = 420;    
    int V7 = 430;    
    int VBR_30 = 430;    
    int V6 = 440;
    int VBR_40 = 440;    
    int V5 = 450;    
    int VBR_50 = 450;    
    int V4 = 460;  
    int VBR_60 = 460;  
    int V3 = 470;  
    int VBR_70 = 470;  
    int V2 = 480;      
    int VBR_80 = 480;
    int V1 = 490;
    int VBR_90 = 490;
    int V0 = 500;    
    int VBR_100 = 500;  
    
    
    int R3MIX = 1000;   
    int STANDARD = 1001;   
    int EXTREME = 1002;   
    int INSANE = 1003;   
    int STANDARD_FAST = 1004;       
    int EXTREME_FAST = 1005;
    int MEDIUM = 1006;
    int MEDIUM_FAST = 1007;         
    
}
