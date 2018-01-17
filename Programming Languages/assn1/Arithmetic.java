/**
 *
 * Jose Salinas
 * Date: August 27,2016
 * Arithmetic.java
 */


public class Arithmetic
{
	//fixBit fixes the leading 0 when incrementing and returns 
	//the corresponding array A or B 
    private static byte[] fixBit(byte[] A,byte[] B)
    {
        int test = 0;
        for(int i = 0; i<A.length;i++)
        {
            if(A[i] == 1)
            {
                test++;
            }
        }
        //if all the numbers in A are 1, leading 0 is one and the rest of the 
        //numbers are 0
        if(test == A.length)
        {
            B[0] = 1;
            return B;
        }
        else
        {
            byte[] C = new byte[A.length];
            for(int m = 0; m < A.length; m++)
            {
                C[m] = B[m];
            }
            return C;
        }
    }
    
    //fixBits gets rid of leading 0's from byte array A
    private static byte[] fixBits(byte[] A)
    {
        int i = 0;
        if(A[0] == 0 && A.length > 1)
        {
            byte[] B = new byte[A.length - 1];
            for(int n =0; n < A.length - 1; n++)
            {
                B[n] = A[n + 1];
            }
            return fixBits(B);
        }
        return A;
    }
    
    
    
    //=================================================================
    // inc(A) returns an array of bits representing A + 1.
    //=================================================================
    public static byte[] inc(byte[] A)
    {
        byte[] result = new byte[A.length + 1];
        boolean carry = true;
        int i = (A.length - 1);
        //if array is not empty
        //if array is empty, return 1
        while(i>=0)
        {
            result[i] = A[i];
            if (carry)
            {
                if (A[i] == 0)
                {
                    result[i] = 1;
                    carry = false;
                }
                else
                {
                    result[i] = 0;
                    carry = true;
                }
            }
            i--;
        }
        return fixBit(A,result);
    }
    
    //=================================================================
    // sum(A,B) returns an array of bits representing A + B.
    //=================================================================
    public static byte[] sum(byte[] A, byte[] B)
    {
        byte[] result = new byte[Math.max(A.length, B.length) + 1];
        byte[] Aa = new byte[A.length];
        byte[] Bb = new byte[B.length];
        int arr1  = A.length - 1;
        int arr2 = B.length - 1;
        //do the sum backwards, for A and B
        for(int i = 0; i < A.length;i++)
        {
            Aa[i] = A[arr1];
            arr1--;
        }
        for(int j = 0; j < B.length;j++)
        {
            Bb[j] = B[arr2];
            arr2--;
        }
        int remainder = 0;
        for(int n = 0; n < Math.max(A.length,B.length);n++)
        {
        	//if array B is larger than array A
            if(n > A.length - 1)
            {
                if(remainder == 1 && Bb[n] == 1)
                {
                    result[n] = 0;
                    remainder = 1;
                }
                else if(remainder == 1 && Bb[n] == 0)
                {
                    result[n] = 1;
                    remainder = 0;
                }
                else
                {
                    result[n] = Bb[n];
                }
            }
            //if array A is larger than array B
            else if(n > B.length - 1)
            {
                if(remainder == 1 && Aa[n] == 1)
                {
                    result[n] = 0;
                    remainder = 1;
                }
                else if(remainder == 1 && Aa[n] == 0)
                {
                    result[n] = 1;
                    remainder = 0;
                }
                else
                {
                    result[n] = Aa[n];
                }
            }
            else if(Aa[n] == 0 && Bb[n] == 0)
            {
                if(remainder == 1)
                {
                    result[n] = 1;
                    remainder = 0;
                }
                else
                {
                    result[n] = 0;
                }
            }
            else if(Aa[n] == 1 && Bb[n] == 1)
            {
                if(remainder == 1)
                {
                    result[n] = 1;
                }
                else
                {
                    result[n] = 0;
                }
                remainder = 1;
            }
            else if(Aa[n] != Bb[n])
            {
                result[n] = 1;
                if(remainder == 1)
                {
                    result[n] = 0;
                    remainder = 1;
                }
            }
        }
        //if remainder is left, put at end of array which when flipped is location 0
        if(remainder == 1)
        {
            result[result.length - 1] = 1;
        }
        byte[] flipped = new byte[result.length];
        int ticker = result.length - 1;
        //flip array backwards for final answer
        for(int finalArray = 0; finalArray < result.length;finalArray++)
        {
            flipped[finalArray] = result[ticker];
            ticker--;
        }
        return fixBits(flipped);
    }
    
    //=================================================================
    // product(A,B) returns an array of bits representing A*B.
    //=================================================================
    public static byte[] product(byte[] A, byte[] B)
    {
        byte[] summed = new byte[(A.length + B.length)];
        byte[] summed2 = new byte[(A.length + B.length)];
       // ticker is used for the binary shifting
        int ticker = summed.length - 1;
        //loc is updated from ticker for binary shifting
        int loc;
        for(int temp = B.length - 1; temp >= 0; temp--)
        {
        	//erase summed array to get new summation
            summed = new byte[(A.length + B.length)];
            loc = ticker;
            for(int temp2 = A.length - 1; temp2 >= 0; temp2--)
            {
                if(A[temp2] == 1 && B[temp] == 1)
                {
                    summed[loc] = 1;
                }
                else
                {
                    summed[loc] = 0;
                }
                loc--;
            }
            summed2 = sum(summed2,summed);
            ticker--;
        }
        return fixBits(summed2);
    }
}