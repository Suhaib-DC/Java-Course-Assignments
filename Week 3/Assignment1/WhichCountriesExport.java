package CSV;

/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void countryInfo(CSVParser parser, String country ) {
        //for each row in the CSV File
        boolean found = false;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String con = record.get("Country");
            //Check if it contains exportOfInterest
            if (con.contains(country)) {
                //If so, write down the "Country" from that row
                String ex = record.get("Exports");
                String val = record.get("Value (dollars)");
                System.out.println(country + ": " + ex + ": " + val);
                found = true;
                break;
            }
        }
        
        if (found == false){
            System.out.println("NOT FOUND");
        }
        
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        boolean found = false;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String ex = record.get("Exports");
            //Check if it contains exportOfInterest
            if (ex.contains(exportItem1) && ex.contains(exportItem2)) {
                //If so, write down the "Country" from that row
                String con = record.get("Country");
                
                System.out.println(con);
                found = true;
                
            }
        }
        
        if (found == false){
            System.out.println("NOT FOUND");
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String ex = record.get("Exports");
            //Check if it contains exportOfInterest
            if (ex.contains(exportItem)) {
                //If so, write down the "Country" from that row
                count++;
            }
        }
        System.out.println(count);
    }
    
    public void bigExporters (CSVParser parser, String amount) {
        
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String val = record.get("Value (dollars)");
            //Check if it contains exportOfInterest
            if (val.length() > amount.length()) {
                //If so, write down the "Country" from that row
                String con = record.get("Country");
                
                System.out.println(con + " " + val);
                
                
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Germany");
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser();
        numberOfExporters(parser, "cocoa");
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    
}
    
    