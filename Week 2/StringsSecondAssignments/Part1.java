package StringsSecondAssignments;


/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int rslt = 0;
        int st = startIndex;
        while (true) {
            int en = dna.indexOf(stopCodon, st);
            if (en == -1){
                rslt = dna.length();
                break;
            }
            if ((en - startIndex)%3 == 0){
                rslt = en;
                break;
            }
            st = en + 3;
        }
        
        return rslt;
    }
    
    public void test() {
        int t = findStopCodon("ATGAATAAGGBCFFFF", 0, "TAA");
        System.out.println(t);
    }
    
    public String findGene (String dna) {
        String gene = "";
        
        int st = dna.indexOf("ATG");
        if (st == -1) {
            return "";
        }
        
        int etaa = findStopCodon(dna, st, "TAA");
        int etag = findStopCodon(dna, st, "TAG");
        int etga = findStopCodon(dna, st, "TGA");
        
        if (etaa < etag) { etag = etaa;}
        if (etga < etag) {etag = etga;}
        if (etaa == etag && etaa == etga) {return "";}
        gene = dna.substring (st, etag + 3);
        
        return gene;
    }
    
    public void testFindGene () {
        String s1 = findGene ("HHATGBBRLTAAOIYUFBJTAGEECETGA");
        String s2 = findGene ("HHATGBBRLTAOIYUFBJTAGEECEHHTGA");
        System.out.println("HHATGBBRLTAAOIYUFBJTAGEECETGA");
        System.out.println(s1);
        System.out.println("HHATGBBRLTAOIYUFBJTAGEECEHHTGA");
        System.out.println(s2);
    }
    
    public void printAllGenes (String dna) {
        
        while (true) {
            String s = findGene (dna);
            if (s == "") {
                break;
            }
            System.out.println (s);
            int en = dna.indexOf(s) + s.length();
            dna = dna.substring(en, dna.length());
        }
    }
    
    

}
