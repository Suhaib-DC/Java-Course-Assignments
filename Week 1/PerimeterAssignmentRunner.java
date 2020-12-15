package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int i = 0;
        for (Point p : s.getPoints()){
            i++ ;
        }
        return i;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        Point a = s.getLastPoint();
        double l = 0;

        for (Point p : s.getPoints()){
            l =l +  p.distance(a);
            a = p;
            
        }
        return l/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point a = s.getLastPoint();
        double l = 0;
        double l1 = 0;
        for (Point p : s.getPoints()){
           l1 = p.distance(a);
           if (l1 > l ){
               l = l1;
            }
            a = p;
            
        }
        return l;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point a = s.getLastPoint();
        double l = 0;
        double l1 = 0;
        for (Point p : s.getPoints()){
           l1 = p.getX();
           if (l1 > l ){
               l = l1;
            }
            a = p;
            
        }
        return l;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double l = 0;
        double l1 = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);  
            Shape s = new Shape(fr); 
            l1 = getPerimeter(s);
            if (l1 > l ){
                l = l1;
            }
            
        }
        return l;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double l = 0;
        double l1 = 0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);  
            Shape s = new Shape(fr); 
            l1 = getPerimeter(s);
            if (l1 > l ){
                l = l1;
                temp = f;
            }
            
        }
            // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Num = " + getNumPoints(s));
        System.out.println("Avg = " + getAverageLength(s));
        System.out.println("Larg = " + getLargestSide(s));
        System.out.println("LargX = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double l = getLargestPerimeterMultipleFiles();
        System.out.println("perimeter = " + l );
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String f = getFileWithLargestPerimeter();
        System.out.println("perimeter = " + f );
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
