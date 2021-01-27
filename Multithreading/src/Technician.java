// Name: Abishek Bupathi

import java.util.concurrent.LinkedBlockingQueue;

public class Technician implements Runnable {

    // Declaring class attributes
    String name;
    LinkedBlockingQueue<Job> jobsQueue2;
    // Declaring instance variables
    Job job;
    static int pos1, pos2 = 0;

    Technician(String name, LinkedBlockingQueue<Job> jobsQueue2){
        this.name = name;
        this.jobsQueue2 = jobsQueue2;
    }

    @Override
    public void run() {
            // Infinite loop used to run through the queue
            while (true) {
                try {
                    // Taking out the job from the top of the 2nd queue
                    job = jobsQueue2.take();
                    // Checking if it's poison pill ie. end of the queue
                    if(isJobsCompleted())
                        break;
                    // Getting the issue name of the job
                    String task = job.getTaskName();
                    // Printing the current process
                    System.out.println("Technician " + name + " is fixing " + job.getTaskName() + " " + job.getId());
                    // Checking if the technician handling this job is andrew
                    if (name.equalsIgnoreCase("andrew"))
                        pos1 = 0;
                    // else it's Emily
                    else pos1 = 1;
                    // Checking if it's battery failure
                    if (task.equalsIgnoreCase("Battery Failure"))
                        pos2 = 0;
                    // Checking if it's Screen crack
                    else if (task.equalsIgnoreCase("Screen Crack"))
                        pos2 = 1;
                    // else it's definitely Keyboard issue
                    else pos2 = 2;
                    /*
                     * The 1st index represents the person who fixed the job
                     * The 2nd index represents the type of issue fixed
                     *
                     * when value of 1st index (pos1) is 0 then it's andrew, if 1 then it's Emily
                     * when value of 2nd index (pos2) is 0 it's battery failure, if 1 then screen crack and if 2 then its keyboard issue
                     */
                    // The values in the array for technicians is incremented for the corresponding person and issue type
                    RepairShop.technicianJobs[pos1][pos2] += 1;
                    // The total number of issue fixed is store in each person's 4th element of the 2nd index
                    RepairShop.technicianJobs[pos1][3] += 1;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    /*
     * Function Name    :   isJobsCompleted
     * Parameters       :   None
     * Returns          :   Boolean
     * Description      :   Checks for the poison pill in the jobsQueue2
     */
    public boolean isJobsCompleted(){
        return job == RepairShop.POISON;
    }

}

