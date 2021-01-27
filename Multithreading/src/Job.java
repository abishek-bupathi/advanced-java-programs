// Name: Abishek Bupathi

public class Job {

    int id;
    String taskName;

    Job(int id, String taskName){
        this.id = id;
        this.taskName = taskName;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return "{ Job: " +
                "id=" + id +
                ", taskName=" + taskName +"}";
    }
}
