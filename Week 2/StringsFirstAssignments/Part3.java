
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    public boolean twoOccurrences(String stringa, String stringb){
       int r = stringb.indexOf(stringa);
       if (r == -1){
           return false;
        }
       else {
           int s = stringb.indexOf(stringa, r);
           if (s == -1){
               return false;
            }
            else {
                return true;
            }
        }
    }
    
    public void testing () {
        String a = "b";
        String b = "bb";
        System.out.println(a + b + twoOccurrences(a,b));
        
        a = "b";
        b = "aa";
        System.out.println(a + b + twoOccurrences(a,b));
        
        a = "b";
        b = "bb";
        System.out.println(a + b + lastPart(a,b));
    }
    
    public String lastPart (String stringa, String stringb) {
        String rslt = stringb;
        int i = stringb.indexOf(stringa);
        if (i != -1){
            rslt = stringb.substring(i,stringb.length()-1);
        }
        return rslt;
    }
}
