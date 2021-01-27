/*
 *  Name : Abishek Bupathi
 */


// PieceWorker class derived from Employee

import org.joda.money.Money;
import java.time.LocalDate;
import java.time.Period;

public final class PieceWorker extends Employee {

    private Money wagePerPiece; // wage per piece output
    private int quantity; // output for week

    // constructor for class PieceWorker
    public PieceWorker(String first, String last,
            Money wage, int numberOfItems, LocalDate joinDate) {
        super(first, last, joinDate); // call superclass constructor
        setWage(wage);
        setQuantity(numberOfItems);
    }

    // set PieceWorker's wage
    public void setWage(Money wage) {
        wagePerPiece = (wage.isPositive() ? wage : Money.parse("0"));
    }

    // set number of items output
    public void setQuantity(int numberOfItems) {
        quantity = (numberOfItems > 0 ? numberOfItems : 0);
    }

    // The earnings() methods returns the total earning of Piece worker for a month and also throws LowWageException when the earnings is less than $100
    public Money earnings() throws LowWageException{

        // Calculating monthly earning for Piece Worker
        Money totalEarnings = wagePerPiece.multipliedBy(quantity).multipliedBy(4);

        // Checking whether the employee had worked more than 5 years
        if (Period.between(getJoinDate(), LocalDate.now()).getYears() >= 5) {
            // $200 bonus
            totalEarnings = totalEarnings.plus(Money.parse("USD 200"));
        }

        // checking if the total earning is less than $100
        if(totalEarnings.isLessThan(Money.parse("USD 100"))){
            // throwing low wage exception with the info of total earnings
            throw new LowWageException("Calculated earning: $"+totalEarnings.getAmount());
        }

        return  totalEarnings;
    }

    // get piece worker's name
    public String toString() {
        return "Piece worker: " + super.toString();
    }
}
