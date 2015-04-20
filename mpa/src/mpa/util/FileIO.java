package mpa.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class FileIO {
   public static void WriteToFile(String content, String file, boolean append){
      Writer writer  = null;
      try {
          writer = new BufferedWriter(new FileWriter(file, append));
          writer.write(content);
          writer.close();
      } catch (Exception ex) {
          System.out.println("cannot write to file "+file);
      }   
   }
}
