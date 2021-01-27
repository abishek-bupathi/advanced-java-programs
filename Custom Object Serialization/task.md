Write a Java class called Student that has the following class attributes:

int id, String name, String address, String course (e.g. 3BCT), LocalDate dateofbirth, List  grades (list of Grade objects)

The Grade class should have the following class attributes:

String moduleCode, LocalDate date, Short percentage

Write a test program that creates a list of five Student objects, each Student object should be also populated with a list of Grade objects.  The program should then write out the list of Student objects, using Object Serialisation, to a file that can be used later to load up the objects again.  The name of the file should be passed in the command line to the main() method. The Student class should use custom serialisation so that it writes out all attributes, except the list of grades, to the ObjectOutputStream passed to the writeObject() method. The list of grades should instead be written, in text format, to a CSV (Comma Separated Values) file called grades.csv by the writeObject() method. Each Grade object in the list of grades should be written as a separate line in the CSV file and each line should contain the attributes of the Grade object separated by a comma, and some way to identify which student the grade belongs to e.g. you could use the student id for this purpose.

Write another test program to load up the Students objects from a file using Object Serialisation. The name of the file should again be passed in the command line to the main() method. The Student class should implement a readObject() method that complements the writeObject() method to read all attributes, except the list of grades, from he ObjectInputStream passed to the readObject() method. The list of grades should instead be read, in text format, from the CSV (Comma Separated Values) file previously created by the writeObject() method. Use the String class split() method to help process each line of the CSV file as it is been processed.

Try manually editing the CSV file to add some additional grades for each student. Run the program again that loads up the Student objects and verify that the new grades are present as expected. Include appropriate exception handling as required.

Include your name and student number in a comment in each java source file, and explain your code with comments. Submit your work in a single zip file using the name format assignment3_firstname_lastname.zip via Blackboard - other file formats will NOT be accepted and email submission will NOT be accepted. Include a screen shot of the testing / results in the same zip file. Use proper indentation in your source code. If you submit more than one attempt then only the last attempt will be marked.