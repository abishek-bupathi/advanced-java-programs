/*
 *  Name : Abishek Bupathi
 */


// CommissionWorker class derived from Employee

import org.joda.money.Money;
import java.time.LocalDate;
import java.time.Period;

public final class CommissionWorker extends Employee {

    private Money salary; // base salary per week
    private Money commission; // amount per item sold
    private int quantity; // total items sold for week

    // constructor for class CommissionWorker
    public CommissionWorker(String first, String last,
            Money salary, Money commission, int quantity, LocalDate joinDate) {
        super(first, last, joinDate); // call superclass constructor
        setSalary(salary);
        setCommission(commission);
        setQuantity(quantity);
    }

    // set CommissionWorker's weekly base salary
    public void setSalary(Money weeklySalary) {
        salary = (weeklySalary.isPositive()? weeklySalary : Money.parse("0"));
    }

    // set CommissionWorker's commission
    public void setCommission(Money itemCommission) {
        commission = (itemCommission.isPositive() ? itemCommission : Money.parse("0"));
    }

    // set CommissionWorker's quantity sold
    public void setQuantity(int totalSold) {
        quantity = (totalSold > 0 ? totalSold : 0);
    }

    // The earnings() methods returns the total earning of Commission worker for a month and also throws LowWageException when the earnings is less than $100
    public Money earnings()  throws LowWageException{

        // Calculating monthly earning for commission worker
        Money totalEarnings = salary.plus(commission.multipliedBy(quantity)).multipliedBy(4);

        // Checking whether the employee had worked more than 5 years
        if (Period.between(getJoinDate(), LocalDate.now()).getYears() >= 5) {
            // $200 bonus
            totalEarnings = totalEarnings.plus(Money.parse("USD 200"));
        }

        // checking if the total earning is less than $100
        if(totalEarnings.isLessThan(Money.parse("USD 100"))){
            // throwing low wage exception and with the information of the calculated earnings
            throw new LowWageException("Calculated earning: $"+totalEarnings.getAmount());
        }

        return  totalEarnings;
    }

    // get String representation of CommissionWorker's name
    public String toString() {
        return "Commission worker: " + super.toString();
    }
} // end class CommissionWorker
