/*
 * LAMEOnJ Java based API for LAME MP3 encoder
 *
 * Copyright (c) 2006 Jose Maria Arranz
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

package test.lameonj.encoder;

import com.innowhere.jnieasy.core.JNIEasy;
import com.innowhere.jnieasy.core.typedec.NativeTypeManager;

public class TestEncoder
{
    
    /** Creates a new instance of Test */
    public TestEncoder()
    {
    }  
            
    public static void main(String[] args) throws Exception
    {
        String wavFile = args[0];
        String mp3FileBase = args[1];       
        
        JNIEasy jniEasy = JNIEasy.get();
        //jniEasy.setFeature("jnieasy.license.dir","a path");
        jniEasy.load();    
        
        NativeTypeManager typeMgr = jniEasy.getTypeManager();
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows"))
            typeMgr.defineMacro("Windows");
        else if (osName.startsWith("Mac OS X"))
            typeMgr.defineMacro("MacOSX");
        else if (osName.startsWith("Linux"))
            typeMgr.defineMacro("Linux");
        else if (osName.startsWith("SunOS"))
            typeMgr.defineMacro("SunOS");        
        else
            throw new RuntimeException("Platform not supported");

        if (typeMgr.isDefinedMacro("Windows"))
        {
            TestBladeAPI.testPlainAPI(wavFile,getMP3FileName(mp3FileBase,1));
            TestBladeAPI.testOOPAPI(wavFile,getMP3FileName(mp3FileBase,2));            
        }
        
        TestEncoderStdAPI.testPlainAPI(wavFile,getMP3FileName(mp3FileBase,3));
        TestEncoderStdAPI.testOOPAPIStreamEncoder(wavFile,getMP3FileName(mp3FileBase,4));  
        TestEncoderStdAPI.testOOPAPIStreamEncoderProgressive(wavFile,getMP3FileName(mp3FileBase,5));        
        TestEncoderStdAPI.testOOPAPIGenericEncoder(wavFile,getMP3FileName(mp3FileBase,6));      
    }

    public static String getMP3FileName(String mp3FileBase,int num)
    {
        return mp3FileBase + "_" + num + ".mp3";
    }
/*    
    public static void checkFiles(String mp3File,String mp3RefFile) throws IOException
    {
        checkFiles(mp3File,mp3RefFile,true);
    }
    
    public static void checkFiles(String mp3File,String mp3RefFile, boolean fully) throws IOException
    {
        File fileRes = new File(mp3File);
        File fileRef = new File(mp3RefFile);
        
        if (fileRef.length() == 0) 
            return; // Mounted Windows directory, cannot check
        
        if (fileRef.length() != fileRes.length())
            throw new RuntimeException("Bad Test, different files");
        
        if (!fully) return;
        
        BufferedInputStream inputStream1 = new BufferedInputStream(new FileInputStream(mp3File));
        BufferedInputStream inputStream2 = new BufferedInputStream(new FileInputStream(mp3RefFile));

        byte[] inputBuff1 = new byte[10*1024];
        byte[] inputBuff2 = new byte[10*1024];        
        
        int read;  
        do
        {
            read = inputStream1.read(inputBuff1,0,inputBuff1.length);                     
            read = inputStream2.read(inputBuff2,0,inputBuff2.length);
           
            for(int i = 0; i < read; i++)
                if (inputBuff1[i] != inputBuff2[i])   
                {
                    throw new RuntimeException("Bad Test, different files");
                }
        }
        while(read != 0);

        inputStream1.close();
        inputStream2.close();        
    }    
*/    
}
