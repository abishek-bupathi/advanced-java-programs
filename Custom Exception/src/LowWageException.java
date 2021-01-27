/*
 *  Name : Abishek Bupathi
 */

// User define exception LowWageException which is thrown when the earnings of an employee is less than $100
public class LowWageException extends Exception{
    LowWageException(String msg){
        super(msg);
    }
}
