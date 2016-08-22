/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.lameonj.decoder;

import com.innowhere.jnieasy.core.JNIEasy;
import com.innowhere.jnieasy.core.typedec.NativeTypeManager;

/**
 *
 * @author jmarranz
 */
public class TestDecoder
{
    public static void main(String[] args) throws Exception
    {
        String mp3File = args[0];
        String wavFileBase = args[1];

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

        TestDecoderPlainAPI.test(mp3File,  getWAVFileName(wavFileBase,1));
        TestDecoderGeneric.test(mp3File,   getWAVFileName(wavFileBase,2));
        TestDecoderStream.test1(mp3File,   getWAVFileName(wavFileBase,3));
        TestDecoderStream.test2(mp3File,   getWAVFileName(wavFileBase,4));
        // TestDecoderConcurrent.test(mp3File,getWAVFileName(wavFileBase,5),getWAVFileName(wavFileBase,6));        
    }

    public static String getWAVFileName(String wavFileBase,int num)
    {
        return wavFileBase + "_" + num + ".wav";
    }
}
