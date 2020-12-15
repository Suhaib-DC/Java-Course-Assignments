
/**
 * Write a description of Part1 here.
 * 
 * @author Suhaib DC
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene (String dna){
        int st = dna.indexOf("ATG");
        String gene = "";
        if (st == -1) {
            return "";
        }
        int en = dna.indexOf("TAA", st + 3);
        if (en == -1) {
            return "";
        }
        
        if ((en - st) % 3 == 0) {
            gene = dna.substring(st, en + 3);
        }
        else {
            gene = "";
        }
        return gene;  
    }
    
    public void testSimpleGene () {
        String dna1 = "ATGBCCZIANBATAA";
        System.out.println(dna1);
        System.out.println(findSimpleGene(dna1));
        
        String dna2 = "BCCZIANBATAA";
        System.out.println(dna2);
        System.out.println(findSimpleGene(dna2));
        
        String dna3 = "ATGBCZIANBATAA";
        System.out.println(dna3);
        System.out.println(findSimpleGene(dna3));
        
        String dna4 = "ATGBCCZIANBA";
        System.out.println(dna4);
        System.out.println(findSimpleGene(dna4));
        
    }
}
