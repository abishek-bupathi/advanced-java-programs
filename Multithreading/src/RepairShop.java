// Name: Abishek Bupathi

import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class RepairShop extends Thread{

    // Initializing integer 2D array to store the number of jobs handled by the workers
    static int assistantJobs[][] = new int[2][4];
    static int technicianJobs[][] = new int[2][4];

    // Creating a job object which is used to stop the Thread when the jobs in the queues are finished
    final static Job POISON = new Job(-1, "END");

    public static void main(String args[]){

        // Creating 2 linked blocking queue to store the jobs to be received and fixed
        LinkedBlockingQueue<Job> jobsQueue1 = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<Job> jobsQueue2 = new LinkedBlockingQueue<>();

        try {
            // Initalizing file reader and buffered read to read data from issueList.txt
            FileReader fileReader = new FileReader("./resources/issueList.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Reading the 1st line of the file to get the loop start running
            String line = bufferedReader.readLine();
            // Loop that runs until last line is reached
            while (line != null){
                // Using split() function of String to separate the values from each line
                String issue[] = line.split(" ");
                /*
                 * The length of issue[] will always be 3 so the values of the elements will be:
                 * issue[0] - ID
                 * issue[1] - Part name
                 * issue[2] - Problem name
                 */
                // Creating an job object with the id and issue
                Job job = new Job(Integer.parseInt(issue[0]), issue[1]+" "+issue[2]);
                // Adding the job to the 1st Queue
                jobsQueue1.add(job);
                // Reading the next line of the text file
                line = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        // Creating the 2 Assistant objects
        Assistant mark = new Assistant("mark", jobsQueue1, jobsQueue2);
        Assistant kate = new Assistant("kate", jobsQueue1, jobsQueue2);
        // Creating the 2 Assistant thread
        Thread assistantMark = new Thread(mark);
        Thread assistantKate = new Thread(kate);

        // Creating the 2 Technician objects
        Technician andrew = new Technician("andrew", jobsQueue2);
        Technician emily = new Technician("emily", jobsQueue2);
        // Creating the 2 Technician thread
        Thread technicianAndrew = new Thread(andrew);
        Thread technicianEmily = new Thread(emily);

        System.out.println("All the workers are ready to receive and fix the issues!\nStarting the threads...\n");
        // Starting all the threads
        assistantKate.start();
        assistantMark.start();
        technicianAndrew.start();
        technicianEmily.start();

        // Closing all the threads
        try {
            assistantKate.join();
            assistantMark.join();
            technicianAndrew.join();
            technicianEmily.join();
        }
        catch (InterruptedException ex)
        {
            System.out.println("Thread interrupted...");
        }

        System.out.println("\nThe Workers have completed all the jobs (ie. All threads are executed and terminated properly) \n\nThe Details of jobs done by each worker:\n");
        // Printing all the details of the issues received by each worker
        System.out.println("Assistant Mark finished receiving "+assistantJobs[0][3]+" orders including " + assistantJobs[0][0] + " battery issues, " + assistantJobs[0][1] + " screen cracks and " + assistantJobs[0][2] + " keyboard issues");
        System.out.println("Assistant Kate finished receiving "+assistantJobs[1][3]+" orders including " + assistantJobs[1][0] + " battery issues, " + assistantJobs[1][1] + " screen cracks and " + assistantJobs[1][2] + " keyboard issues");
        System.out.println("Technician Andrew finished fixing "+technicianJobs[0][3]+" orders including " + technicianJobs[0][0] + " battery issues, " + technicianJobs[0][1] + " screen cracks and " + technicianJobs[0][2] + " keyboard issues");
        System.out.println("Technician Emily finished fixing "+technicianJobs[1][3]+" orders including " + technicianJobs[1][0] + " battery issues, " + technicianJobs[1][1] + " screen cracks and " + technicianJobs[1][2] + " keyboard issues");
    }
    }
