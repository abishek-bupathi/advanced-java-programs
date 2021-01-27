Write a program that uses two LinkedBlockingQueue objects, as described below, to simulate processing issues in a laptop repair workshop. Please see the Java API documentation for details on how to use the LinkedBlockingQueue class. After an Assistant receives a laptop with an issue, a Technician fixes the problem. Make sure to include some random delays in both receiving and fixing issues. The program should include three classes “Assistant”, “Technician” and “RepairShop”. An Assistant receives the laptops with issues, a Technician then is for fixing the issues and the RepairShop class is the main application class for creating the Assistants and Technicians etc. In the RepairShop class, create and start two Assistant thread objects “Assistant Mark”,  and “Assistant Kate”, and two Technician thread objects "Technician Andrew" and “Technician Emily”. Print out information to show what is happening while each thread is running, see Example 1 below. Each repair job should only be received and fixed exactly once. After all threads have finished running, print out the number of issues received by each Assistant and fixed by each Technician, see Example 2 below. The issue list that the Assistants need to receive is attached as “issueList.txt”. The main() method in the RepairShop class should open and read this file and then assign the list of jobs to a queue for processing by one of the Assistants. One of the Assistant threads will take jos from this queue and then assign the job to a different queue for repair by one of the Technician threads. You may also want to create a class called Job to represent each item that is processed for repair. The following are samples of what the resulting output may look like.

Example 1:

Assistant Mark is receiving Battery Issue 92

Technician Emily is fixing Battery Issue 92

Assistant Kate is receiving Screen Cracked 93

Technician Emily is fixing Screen Cracked 93

Assistant Mark is receiving Screen Cracked 94

Technician Emily is fixing Screen Cracked 94

Assistant Kate is receiving Battery Issue 95

Technician Emily is fixing Battery Issue 95

Assistant Mark is receiving Screen Cracked 96

Technician Emily is fixing Screen Cracked 96

Assistant Mark is receiving Battery Issue 97

Technician Emily is fixing Battery Issue 97

Assistant Kate is receiving Battery Issue 98

Technician Emily is fixing Battery Issue 98

Assistant John is receiving Screen Cracked 99

Technician Andrew is fixing Screen Cracked 99

Assistant John is receiving Battery Issue100

Technician Andrew is fixing Battery Issue100

Example 2:

Assistant Mark finished receiving 53 orders including 23 battery issues, 18 screen cracks and 12 keyboard issues

Assistant Kate finished receiving 47 orders including 18 battery issues, 20 screen cracks and 9 keyboard issues

Technician Andrew finished fixing 49 orders including 10 battery issues, 10 screen cracks and 29 keyboard issues

Technician Emily finished fixing 51 orders including 13 battery issues, 17 screen cracks and 21 keyboard issues
