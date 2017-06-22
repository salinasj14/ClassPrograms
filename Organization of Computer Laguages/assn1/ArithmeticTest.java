/**
 *
 * Jose Salinas
 * Date: August 27,2016
 * ArithmeticTest.java
 */

public class ArithmeticTest {
    
    private static void printBinary(byte[] A)
    {
        for(int i = 0; i < A.length; i++)
        {
            System.out.print(A[i]);
        }
    }
    
    public static void main(String[] args)
    {
        byte[] A1= {0};
        byte[] A2= {1,1,1,1,0};
        byte[] A3= {1,0,1};
        byte[] A4= {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        byte[] A5= {1,1,0,0,0,0,0,0,0,1,1};
        
        System.out.println("============");
        System.out.println("    Inc     ");
        System.out.println("============");
        
        printBinary(A1);
        System.out.print(" + 1 = ");
        printBinary(Arithmetic.inc(A1));
        System.out.println();

        printBinary(A2);
        System.out.print(" + 1 = ");
        printBinary(Arithmetic.inc(A2));
        System.out.println();

        printBinary(A3);
        System.out.print(" + 1 = ");
        printBinary(Arithmetic.inc(A3));
        System.out.println();

        printBinary(A4);
        System.out.print(" + 1 = ");
        printBinary(Arithmetic.inc(A4));
        System.out.println();

        printBinary(A5);
        System.out.print(" + 1 = ");
        printBinary(Arithmetic.inc(A5));
        System.out.println();
        System.out.println();
        
        System.out.println("============");
        System.out.println("   Sum      ");
        System.out.println("============");
        
        byte[] B = {0};
        byte[] B1 = {1};
        byte[] B2 = {1, 0, 1};
        byte[] B3 = {0, 1, 0};
        byte[] B4 = {0, 1, 0, 1, 0};
        byte[] B5 = {1, 0, 0, 0};
        byte[] B6 = {0, 0, 1, 0, 1, 1};
        byte[] B7 = {1,1,1,1,1,1,1};
        byte[] B8 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        
        printBinary(B);
        System.out.print(" + ");
        printBinary(B);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B,B));
        System.out.println();

        printBinary(B1);
        System.out.print(" + ");
        printBinary(B2);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B1,B2));
        System.out.println();

        printBinary(B2);
        System.out.print(" + ");
        printBinary(B2);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B2,B2));
        System.out.println();

        printBinary(B2);
        System.out.print(" + ");
        printBinary(B3);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B2,B3));
        System.out.println();

        printBinary(B4);
        System.out.print(" + ");
        printBinary(B5);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B4,B5));
        System.out.println();

        printBinary(B2);
        System.out.print(" + ");
        printBinary(B5);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B2,B5));
        System.out.println();

        printBinary(B2);
        System.out.print(" + ");
        printBinary(B4);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B2,B4));
        System.out.println();

        printBinary(B5);
        System.out.print(" + ");
        printBinary(B6);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B5,B6));
        System.out.println();

        printBinary(B8);
        System.out.print(" + ");
        printBinary(B1);
        System.out.print(" = ");
        printBinary(Arithmetic.sum(B8,B1));
        System.out.println();
        System.out.println();
        
        byte[] D = {0};
        byte[] D1 = {1};
        byte[] D2 = {1, 0, 1};
        byte[] D3 = {1, 1, 1, 1};
        byte[] D6 = {1, 1};
        byte[] D4 = {1,1,1,1,1,1};
        byte[] D5 = {1,1,1,1,1,1,1,1,1,1,1,1};
        
        System.out.println("============");
        System.out.println("  Product   ");
        System.out.println("============");
        
        printBinary(D);
        System.out.print(" X ");
        printBinary(D);
        System.out.print(" = ");
        printBinary(Arithmetic.product(D,D));
        System.out.println();

        printBinary(D1);
        System.out.print(" X ");
        printBinary(D1);
        System.out.print(" = ");
        printBinary(Arithmetic.product(D1,D1));
        System.out.println();

        printBinary(D2);
        System.out.print(" X ");
        printBinary(D2);
        System.out.print(" = ");
        printBinary(Arithmetic.product(D2,D2));
        System.out.println();

        printBinary(D3);
        System.out.print(" X ");
        printBinary(D3);
        System.out.print(" = ");
        printBinary(Arithmetic.product(D3,D3));
        System.out.println();

        printBinary(D6);
        System.out.print(" X ");
        printBinary(D6);
        System.out.print(" = ");
        printBinary(Arithmetic.product(D6,D6));
        System.out.println();

        printBinary(D2);
        System.out.print(" X ");
        printBinary(D2);
        System.out.print(" = ");
        printBinary(Arithmetic.product(D4,D4));
        System.out.println();

        printBinary(D4);
        System.out.print(" X ");
        printBinary(D5);
        System.out.print(" = ");
        printBinary(Arithmetic.product(D4,D5));
        System.out.println();
        System.out.println();
        
    }
}