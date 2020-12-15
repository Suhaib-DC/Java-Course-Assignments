
import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
     public void find() {
        URLResource ur = new URLResource ("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        int i = 0;
        for(String word : ur.words()){
             String word1 = word.toLowerCase();
             int y = word1.indexOf("youtube.com");
             
             if (y == -1){
                i++;
                continue;
                }
             int st = word.lastIndexOf("\"",y);
             int en = word.indexOf("\"",y);
             String rslt = word.substring (st,en + 1);
             i++;
             System.out.println(i + "- " + rslt);
        }
        
    } 

}
