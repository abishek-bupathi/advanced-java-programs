import java.io.*;
import java.util.List;
import javax.swing.JOptionPane;

public class TestRead {

    public static void main(String[] args) {
        List<Student> students;
        String output = "";

        // Handling exception to call the readObject function as it may throw an exception
        try {
            System.out.println("Reading data from +"+args[0]+" and grades.csv......");
            // Initialising file input stream and passing the file name got from command line (args[])
            FileInputStream fis = new FileInputStream(args[0]);
            // Initialising object input stream for deserialization
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Calling the custom readObject() method and the value in stored in students list
            students = (List<Student>) ois.readObject();
            // Closing the Input stream
            ois.close();
            System.out.println("Reading Complete!");
            // Getting the details of the of each student to display
            for(Student student: students){
               output += "ID: "+student.getId()+","+student.getName()+", Name: "+student.getAddress()+", Course: "+student.getCourse()+", DOB: "+student.getDateofbirth()+", Grades: "+student.getGrades()+"\n";
            }
            // Displaying the details of the students
            JOptionPane.showMessageDialog(null, output, "Student Details", JOptionPane.PLAIN_MESSAGE);

        }catch (IOException | ClassNotFoundException e){
            // Printing the details of exception caught thrown by the readObject() method
            System.out.println(e);
        }
    }
}
