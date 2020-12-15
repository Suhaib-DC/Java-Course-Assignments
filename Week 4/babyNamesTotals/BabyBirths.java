package babyNamesTotals;

/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            totalBirths++;
            if (rec.get(1).equals("M")) {
                totalBoys++;
            }
            else {
                totalGirls++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }
    
    public int getRank(int year, String name, String gender) {
            int rank = -1;
            int count = 0;
            String g = "F";
            String file = "C:/Users/suhai/Desktop/All Folders/java/babyNamesTotals/data/yob" + year + ".csv";
            FileResource fr = new FileResource(file);
            for (CSVRecord rec : fr.getCSVParser(false)){
                
                if (!rec.get(1).equals(g)){
                    count = 0;
                    g = "M";
                }
                count++;
                if (rec.get(0).equals(name) && rec.get(1).equals(gender)){
                    
                    rank = count;
                    break;
                }
            }
            
            return rank;
    }

    public String getName(int year, int rank, String gender) {
            String name = "NO NAME";
            int count = 0;
            String g = "F";
            String file = "C:/Users/suhai/Desktop/All Folders/java/babyNamesTotals/data/yob" + year + ".csv";
            FileResource fr = new FileResource(file);
            for (CSVRecord rec : fr.getCSVParser(false)){
                
                if (!rec.get(1).equals(g)){
                    count = 0;
                    g = "M";
                }
                count++;
                if (count == rank && rec.get(1).equals(gender)){
                    
                    name = rec.get(0);
                    break;
                }
            }
            
            return name;
    }
    
    public String whatIsNameInYear (String name, int year, int newyear, String gender){
        String newname = null;
        int r = getRank(year, name, gender);
        newname = getName(newyear, r, gender);
        return newname;
    }
    
    public int yearOfHighestRank (String name, String gender){
        int year = 0;
        int rank = 0;
        int minRank = 0;
        int rslt = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String file = f.getName().substring(3,7);
            year = Integer.parseInt(file);
            rank = getRank(year, name, gender);
            if (rank == -1){
                continue;
            }
            
            if (minRank == 0){
                minRank = rank;
                rslt = year;
            }
            
            if (rank < minRank) {
                rslt = year;
                minRank = rank;
            }
            
        }
        
        if (minRank == 0){year = -1;}
        return rslt;
    }
    
    public double getAverageRank (String name, String gender){
        int year = 0;
        int rank = 0;
        double sumRank = 0;
        double rslt = 0;
        double count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            String file = f.getName().substring(3,7);
            year = Integer.parseInt(file);
            rank = getRank(year, name, gender);
            if (rank == -1){
                continue;
            }
            
            sumRank += rank;
            count++;
        }
        
        
        if (sumRank == 0){return -1;}
        rslt = sumRank/count;
        return rslt;
    }
    
    public int getTotalBirthsRankedHigher (int year, String name, String gender){
        int numBirth = 0;
        int count = 0;
        String g = "F";
        int rank = getRank (year, name, gender);
        String file = "C:/Users/suhai/Desktop/All Folders/java/babyNamesTotals/data/yob" + year + ".csv";
        FileResource fr = new FileResource(file);
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (!rec.get(1).equals(g)){
                count = 0;
                numBirth = 0;
                g = "M";
            }
            count++;
            if (count>=rank && rec.get(1).equals(gender)){
                break;
            }
            
            numBirth += Integer.parseInt(rec.get(2));
            
        }
        
        
        return numBirth;
    }
    
    public void tester () {
        
        //FileResource fr = new FileResource();
        //totalBirths(fr);
        
        // testing getRank
        //System.out.println("The rank " + getRank(1960, "Emily", "F"));
        //System.out.println("The rank " + getRank(1971, "Drew", "M"));
        // testing getName
        //System.out.println("The name " + getName(1982, 450, "M"));
        
        // testing whatIsNameInYear
        System.out.println("Name in different year " + whatIsNameInYear("Owen", 1972, 2014, "M"));
        
        // testing yearOfHighestRank
        //System.out.println(yearOfHighestRank("Mich", "M"));
        
        // testing getAverageRank
        //System.out.println(getAverageRank("Susan", "F"));
        
        // testing getTotalBirthsRankedHigher
        //System.out.println(getTotalBirthsRankedHigher(1990 ,"Drew", "M"));
    }
}
