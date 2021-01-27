import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class TestWrite {

    public static void main(String args[]){

        // Initialising separate grade lists for every student
        List<Grade> grades1 = new ArrayList<>();
        List<Grade> grades2 = new ArrayList<>();
        List<Grade> grades3 = new ArrayList<>();
        List<Grade> grades4 = new ArrayList<>();
        List<Grade> grades5 = new ArrayList<>();

        // Initialising list for students
        List<Student> students = new ArrayList<>();

        // Creating 4 grade objects and populating into the grade list for 1st student
        grades1.add(new Grade("CT210", LocalDate.of(2020, 10, 2), (short)95));
        grades1.add(new Grade("CT132", LocalDate.of(2020, 10, 2), (short)90));
        grades1.add(new Grade("BCT55", LocalDate.of(2020, 10, 2), (short)93));
        grades1.add(new Grade("CT200", LocalDate.of(2020, 10, 2), (short)99));

        // Creating 4 grade objects and populating into the grade list for 2nd student
        grades2.add(new Grade("CT210", LocalDate.of(2020, 10, 2), (short)85));
        grades2.add(new Grade("CT132", LocalDate.of(2020, 10, 2), (short)90));
        grades2.add(new Grade("BCT55", LocalDate.of(2020, 10, 2), (short)83));
        grades2.add(new Grade("CT200", LocalDate.of(2020, 10, 2), (short)89));

        // Creating 4 grade objects and populating into the grade list for 3rd student
        grades3.add(new Grade("EEE32", LocalDate.of(2020, 10, 2), (short)57));
        grades3.add(new Grade("CT132", LocalDate.of(2020, 10, 2), (short)67));
        grades3.add(new Grade("BCT55", LocalDate.of(2020, 10, 2), (short)70));
        grades3.add(new Grade("CT200", LocalDate.of(2020, 10, 2), (short)66));

        // Creating 4 grade objects and populating into the grade list for 4th student
        grades4.add(new Grade("EEE32", LocalDate.of(2020, 10, 2), (short)98));
        grades4.add(new Grade("CT132", LocalDate.of(2020, 10, 2), (short)97));
        grades4.add(new Grade("BCT55", LocalDate.of(2020, 10, 2), (short)99));
        grades4.add(new Grade("CT200", LocalDate.of(2020, 10, 2), (short)95));

        // Creating 4 grade objects and populating into the grade list for 5th student
        grades5.add(new Grade("CT210", LocalDate.of(2020, 10, 2), (short)78));
        grades5.add(new Grade("CT132", LocalDate.of(2020, 10, 2), (short)87));
        grades5.add(new Grade("BCT55", LocalDate.of(2020, 10, 2), (short)67));
        grades5.add(new Grade("CT200", LocalDate.of(2020, 10, 2), (short)90));

        // Creating 5 student objects with their attributes and populating with their grade list and adding to the list
        students.add(new Student(1213, "Joe", "11 Highfield, galway", "3BCT", LocalDate.of(2000, 12, 1), grades1));
        students.add(new Student(1234, "Rich", "233 Corrib village, galway", "3BCT", LocalDate.of(1999, 11, 18), grades2));
        students.add(new Student(1232, "Emma", "112 Goldrcrest, galway", "4BP3", LocalDate.of(2000, 5, 3), grades3));
        students.add(new Student(1256, "Abi", "2 Woodquay, galway", "4BP3", LocalDate.of(2000, 12, 8), grades4));
        students.add(new Student(1211, "Jack", "32 Newcastle, galway", "3BCT", LocalDate.of(2000, 1, 9), grades5));

        // Handling exceptions using try block as writeObject() method may throw IOE exception
        try {
            System.out.println("Writing data to +"+args[0]+" and grades.csv......");
            // Initialising file output stream and passing file name got from main method argument (args[])
            FileOutputStream fos = new FileOutputStream(args[0]);
            // Initialising object output stream for serialization
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Calling the writeObject method to serialize and store the students list
            oos.writeObject(students);
            // Closing the output stream
            System.out.println("Writing Completed!");
            oos.close();
        }catch (IOException e){
            // In case of any exception or error, the details is printed
            System.out.println(e);
        }

    }

}
