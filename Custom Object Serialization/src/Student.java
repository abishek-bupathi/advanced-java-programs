import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Student implements Serializable{

    // Initalising the instance variables
    int id;
    String name, address, course;
    LocalDate dateofbirth;
    // declaring grade list as transient to avoid getting serialized
    transient List<Grade> grades;

    Student(int id, String name, String address, String course, LocalDate dateofbirth, List<Grade> grades){
        this.id = id;
        this.name = name;
        this.address = address;
        this.course = course;
        this.dateofbirth = dateofbirth;
        this.grades = grades;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCourse() {
        return course;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    /*
     * Function Name    :   writeObject
     * Parameters       :   List of students
     * Returns          :   None
     * Description      :   Serializes the list of student objects passed in except grades and stores it in the
     *                      file name given while the grades are stored as csv format in grades.csv file
     */
    private void writeObject(ObjectOutputStream oos) throws IOException{

        // Default serialized Object writing function
        oos.defaultWriteObject();

        // Customising the attributes to be written
        oos.writeObject(id);
        oos.writeObject(name);
        oos.writeObject(address);
        oos.writeObject(course);
        oos.writeObject(dateofbirth);
        oos.flush();

        // Initializing file writer to write grades as text
        FileWriter fileWriter = new FileWriter("Grades.csv", true);

        // looping through all the grades the list and writing to the text/csv file one by one on separate lines
        // Student ID is added to each line for reference when reading the data
        for (Grade grade: grades ) {
            fileWriter.append(id+","+grade.getModuleCode()+","+ grade.getDate()+","+grade.getPercentage()+"\n");
        }
        // terminating the file writer
        fileWriter.flush();
    }

    /*
     * Function Name    :   readObject
     * Parameters       :   None
     * Returns          :   List of student objects
     * Description      :   Deserializes list of student objects from the file provided
     *                      and grades are read from grades.csv file
     */
    private void readObject(ObjectInputStream ois) throws Exception{

        // default readObject method implementation
        ois.defaultReadObject();

        // Initalizing file reader and buffered read to read data from grades.csv
        FileReader fileReader = new FileReader("Grades.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Initalising local variables to store raw data read from the file
        List<Grade> grades_temp = new ArrayList<>();
        // Reading the 1st line of the file to get the loop start running
        String line = bufferedReader.readLine();
        // Loop that runs until last line is reached
        while (line != null){
            // Using split() function of String to seperate the values from each line
            String grade[] = line.split(",");
            /*
             * The length of grade[] will always be 4 so the values of the elements will be:
             * grade[0] - Student ID
             * grade[1] - Module Code
             * grade[2] - date
             * grade[3] - percentage
             */
            // The grade array is populated with grade objects with matching ID
            if(id == Integer.parseInt(grade[0])) {
                grades_temp.add(new Grade(grade[1], LocalDate.parse(grade[2]), Short.parseShort(grade[3])));
            }else{
                // When the change in ID is detected the list of grades is passed to the Student grades attribute
                grades = grades_temp;
            }
            // Reading the next line of the data file
            line = bufferedReader.readLine();
        }
        // Closing the reader
        bufferedReader.close();
    }
}
