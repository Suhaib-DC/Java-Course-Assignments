package CSV.hottestTempManyRefactored;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CSVMin {
    
    public void testColdestInDay () {
            FileResource fr = new FileResource();
            CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
            System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                       " at " + smallest.get("TimeEST"));
    }
    
    public void testLowestHumidityInFile () {
            FileResource fr = new FileResource();
            CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
            System.out.println("Lowest humidity was " + smallest.get("Humidity") +
                       " at " + smallest.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile () {
            FileResource fr = new FileResource();
            double avg = averageTemperatureInFile(fr.getCSVParser());
            System.out.println("Average temperature in file is " + avg);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
            FileResource fr = new FileResource();
            double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
            System.out.println("Average temperature in file is " + avg);
    }
    
    
    public void testFileWithColdestTemperature () {
		String name = fileWithColdestTemperature();
		System.out.println(name);
	}
    
	public void testLowestHumidityInManyFiles () {
		CSVRecord smallest = lowestHumidityInManyFiles();
		System.out.println("Lowest humidity was " + smallest.get("Humidity") +
                       " at " + smallest.get("DateUTC"));
	}
	
    public String fileWithColdestTemperature() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		String name = null;
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			
			// use method to compare two records
			smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
			if (currentRow == smallestSoFar){
			    name =  f.getName();
			 }
		}
		//The largestSoFar is the answer
		return name;
	}
    
	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		String name = null;
		// iterate over files
    	for (File f : dr.selectedFiles()) {
    			FileResource fr = new FileResource(f);
    			// use method to get largest in file.
    			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
    			
    			// use method to compare two records
    			smallestSoFar = getSmallestOfTwoH(currentRow, smallestSoFar);
    			
           }
		//The largestSoFar is the answer
		return smallestSoFar;
	}
	
    public CSVRecord coldestHourInFile(CSVParser parser) {
            //start with largestSoFar as nothing
            CSVRecord smallestSoFar = null;
            //For each row (currentRow) in the CSV File
            for (CSVRecord currentRow : parser) {
                // use method to compare two records
                smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
            }
            //The largestSoFar is the answer
            return smallestSoFar;
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
            //start with largestSoFar as nothing
            double sumSoFar = 0;
            double num = 0;
            //For each row (currentRow) in the CSV File
            for (CSVRecord currentRow : parser) {
                // use method to compare two records
                sumSoFar += Double.parseDouble(currentRow.get("TemperatureF")) ;
                num++;
            }
            //The largestSoFar is the answer
            return sumSoFar/num;
    }
    
    public double averageTemperatureWithHighHumidityInFile  (CSVParser parser, int value) {
            //start with largestSoFar as nothing
            double sumSoFar = 0;
            double num = 0;
            //For each row (currentRow) in the CSV File
            for (CSVRecord currentRow : parser) {
                // use method to compare two records
                double val = Double.parseDouble(currentRow.get("Humidity"));
                if ( val >= value){
                    sumSoFar += Double.parseDouble(currentRow.get("TemperatureF")) ;
                    num++;
                }
            }
            //The largestSoFar is the answer
            if (num == 0 ){
                return 0;
            }
            return sumSoFar/num;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
            //start with largestSoFar as nothing
            CSVRecord smallestSoFar = null;
            //For each row (currentRow) in the CSV File
            for (CSVRecord currentRow : parser) {
                // use method to compare two records
                smallestSoFar = getSmallestOfTwoH(currentRow, smallestSoFar);
            }
            //The largestSoFar is the answer
            return smallestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
            }
            
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                }
            }
            return smallestSoFar;
    }
    
    public CSVRecord getSmallestOfTwoH (CSVRecord currentRow, CSVRecord smallestSoFar) {
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
            }
            
            else {
                if (currentRow.get("Humidity") != "N/A") {
                
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
                //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < smallestTemp) {
                    //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                }
            }
            }
            return smallestSoFar;
}
}
