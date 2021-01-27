import java.time.LocalDate;

public class Grade {

    String moduleCode;
    LocalDate date;
    Short percentage;

    Grade(String moduleCode, LocalDate date, Short percentage){
        this.moduleCode = moduleCode;
        this.date = date;
        this.percentage = percentage;
    }

    // Get module code
    public String getModuleCode() {
        return moduleCode;
    }
    // Get Date
    public LocalDate getDate() {
        return date;
    }
    // Get percentage
    public Short getPercentage() {
        return percentage;
    }
    // Get string representation of the values
    public String toString(){
        return "\nMod Code: "+moduleCode +" | Date: "+date+" | Percentage: "+percentage;
    }
}
