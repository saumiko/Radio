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

package lameonj.impl;

import lame.std.FileUtil;
import lame.std.Lame;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

/**
 *
 * @author jmarranz
 */
public class GenerateLameXML
{
    
    /**
     * Creates a new instance of GenerateLameXML
     */
    public GenerateLameXML()
    {
    }
 
    public static void main(String[] args) throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[0])));

        Class clasz = Lame.class; // Supposed not enhanced          
        
        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
        out.newLine();
        out.write("<!-- THIS FILE IS GENERATED, use generateLameXML Ant Task to update --> \n");         
        out.write("<jniEasyEnhancer version=\"1.0\" \n");
        out.write("     xmlns=\"http://www.innowhere.com/jnieasy/enhancer\" \n");
        out.write("     xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" \n");
        out.write("     xsi:schemaLocation=\"http://www.innowhere.com/jnieasy/enhancer ../../../../schemas/JNIEasy.enh.xsd\"> \n");        
        out.newLine();
        
        out.write("  <package name=\"lame.std\"> \n");
        out.write("    <imports> \n");
        out.write("      <import class=\"com.innowhere.jnieasy.core.data.NativeInteger\" /> \n");        
        out.write("    </imports> \n");            
        
        out.write("    <class name=\"" + clasz.getSimpleName() + "\" type=\"class\" libraryPath=\"Windows:lame_enc.dll;MacOSX:libmp3lame.dylib;Linux:libmp3lame.so;SunOS:libmp3lame.so\"> \n");
        
        generateMethods(out);
                
        out.write("    </class> \n");       
        out.write("  </package> \n");
        out.write("</jniEasyEnhancer> \n");        
        out.close();
    }
    
    public static void generateMethods(BufferedWriter out) throws Exception
    {
        Class clasz = Lame.class; // Supposed not enhanced        
        
        Method[] methods = clasz.getDeclaredMethods();
        
        for(int i = 0; i < methods.length; i++)
        {
            generateMethod(methods[i],out);
        }
    }
    
    public static void generateMethod(Method method,BufferedWriter out) throws Exception
    {
        StringBuffer codeParams = new StringBuffer();
        Class[] params = method.getParameterTypes();
        for(int i = 0; i < params.length; i++)
        {
            if (i >= 1) codeParams.append(", ");
            generateParam(params[i],codeParams);
        }        
        
        out.write("      <method name=\"" + method.getName() + "\" params=\"" + codeParams + "\" onLibrary=\"true\" callConv=\"c_call\" > \n");
        out.write("      </method> \n"); 
    }    
    
    public static void generateParam(Class param,StringBuffer out) throws Exception
    {
        String className;
        if (FileUtil.FILE.class.equals(param))
            className = FileUtil.FILE.class.getName();
        else
            className = param.getSimpleName();
        out.append(className);         
    }    
}
