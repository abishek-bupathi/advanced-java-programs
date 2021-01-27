// Name: Abishek Bupathi

import java.util.concurrent.LinkedBlockingQueue;

public class Assistant implements Runnable{

    // Declaring class attributes
    String name;
    LinkedBlockingQueue<Job> jobsQueue1, jobsQueue2;
    // Initializing instance variables
    static int pos1, pos2 = 0;

    Assistant( String name,LinkedBlockingQueue<Job> jobsQueue1, LinkedBlockingQueue<Job> jobsQueue2 ){
        this.name = name;
        this.jobsQueue1 = jobsQueue1;
        this.jobsQueue2 = jobsQueue2;
    }

    @Override
    public void run() {
               // Loop that runs until all the issues from the 1st queue is received by either of the assistants
               while(!jobsQueue1.isEmpty()) {

                    try {
                        // Taking out the job from the top of the queue
                        Job job = jobsQueue1.take();
                        // Getting the name of the issue
                        String task = job.getTaskName();
                        // Printing the process happening
                        System.out.println("Assistant " + name + " is receiving " + task + " " + job.getId());
                        // Checking if the person receiving this issue is Mark
                        if(name.equalsIgnoreCase("Mark"))
                            pos1 = 0;
                        else pos1 = 1;
                        // Checking if the issue is Battery failure
                        if(task.equalsIgnoreCase("Battery Failure"))
                            pos2 = 0;
                        // Checking if the issue is Screen Crack
                        else if(task.equalsIgnoreCase("Screen Crack"))
                            pos2 = 1;
                        // else it's definitely be keyboard issue
                        else pos2 = 2;

                        // Adding the job to 2nd queue for the technicians to fix it
                        jobsQueue2.put(job);

                        /*
                         * The 1st index represents the person who received the job
                         * The 2nd index represents the type of issue received
                         *
                         * when value of 1st index (pos1) is 0 then it's mark, if 1 then it's Kate
                         * when value of 2nd index (pos2) is 0 it's battery failure, if 1 then screen crack and if 2 then its keyboard issue
                         */
                        // The values in the array for assistants is incremented for the corresponding person and issue type
                        RepairShop.assistantJobs[pos1][pos2] += 1;
                        // The total number of issue received is store in each person's 4th element of the 2nd index
                        RepairShop.assistantJobs[pos1][3] += 1;
                        // Putting the thread to sleep for a random time period
                        Thread.sleep((long) (Math.random() * 100));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               // When all the jobs from the queue is received and transferred to the 2nd queue the jobsCompleted() function is called
               jobsCompleted();
    }

    /*
     * Function Name    :   jobsCompleted
     * Parameters       :   None
     * Returns          :   None
     * Description      :   Puts the poison pill at the end of the jobQueue2
     *                      Note: Poison pill is a Job object with value of id -1 and task name as "END"
     *                            that helps to know that end of Jobs queue is reached and all the jobs
     *                            are completed so that the thread can be safely stopped
     */
    public void jobsCompleted() {
        try {
            // Adding the POISON pill to the end of 2nd queue
            jobsQueue2.put(RepairShop.POISON);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
