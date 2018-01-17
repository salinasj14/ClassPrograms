//Jose Salinas <salinasj14>
//CSCI 2310, section 3
//October 29, 2014
//
//Program for computing monthly payments and total interest payments for loans.
//
//The loan with the minimum monthly payment is $492.57 *loan 1*
//The loan with the minimum total interest payment is $37,993.29 *loan 3*


import java.util.*;

public class Lab8
{
    
    public static void main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        
        // Other declarations for main program go here
        
        double Amount, Percent, Monthly, Interest;
        int Term;
        
        // Step 1: prompt for, read in, and store the input values.
        System.out.println("Enter loan amount: ");
        Amount= kbd.nextDouble();
        System.out.println("Enter loan duration in years: ");
        Term= kbd.nextInt();
        System.out.println("Enter interest rate as a percent: ");
        Percent=kbd.nextDouble();
        
        // Step 2: use the payment function to calculate the monthly payment.
        Monthly= payment(Amount, Term, Percent);
        
        // Step 3: calculate the total amount of interest paid over the term
        //of the loan.
        Interest= Monthly * (Term*12) - Amount;
        
        // Step 4: use the printTotals function to output the results
        printTotals(Term, Monthly, Amount, Percent, Interest);
        
    } // end of main
    
    /***********************************************************************/
    // Must specify three parameters in the parameter list for amount borrowed,
    // interest rate as a percentage, and length of the loan in years.
    // and write the remainder of the method. Method should return a value.
    public static double payment (double Amount, int Term, double Percent)
    {
        double js1,js2,js3,js4,js5,js6,js7,js8,js9;
        js1 = Percent/100;
        js2 = js1/12;
        js3 = Term * 12;
        js4 = 1 + js2;
        js5 = Math.pow(js4, js3);
        js6 = js2 * js5;
        js7 = js5 - 1;
        js8 = js6 / js7;
        js9 = Amount * js8;
        return js9;
        // return something with the "return" statement
    }
    
    // end of function payment
    
    /***********************************************************************/
    // You must determine what parameters are needed
    public static void printTotals(int Term, double Monthly, double Amount, double Percent, double Interest)
    {
        
        // You will have to figure out what goes in place of EXPRESSION---it could
        // be a variable provided as a parameter, a local variable you calculate,
        // or some arithmetic expression as needed.
        
        System.out.printf("\n** RESULTS **\n\n");
        System.out.printf("For a %d year loan of ", Term);
        System.out.printf("$%.2f", Amount);
        System.out.printf(" at %.2f%% interest ---\n\n", Percent);
        System.out.printf("Monthly payment =  $%.2f\n", Monthly);
        System.out.printf("Total interest =   $%.2f\n", Interest);
    }
    // end of function printTotals
    
}// end of class Lab8
