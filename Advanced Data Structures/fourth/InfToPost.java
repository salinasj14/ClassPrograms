//Jose Salinas
//Assignment 4
// I attempted to get the 10 bonus points for evaluating
// infix expression while converting to postfix

import java.util.*;
import java.io.*;

public class InfToPost
{
    public static Stack<Integer> operands = new Stack<Integer>();//Stack to hold infix operands
    
    public static Stack<Character> operators = new Stack<Character>();//Stack to hold infix operators
    
    public static Stack<Character> postSymbols = new Stack<Character>();//Stack to hold symbols for postfix

    public static int addr(char ch)
    {
        return (int) ch - (int) 'A';
    }
    
    // Returns true or false if the character passed to function is either
    // + - * / (
    public static boolean isOperator(char c)// Tell whether c is an operator.
    {
        if(c == '+' || c == '-' || c == '*' || c == '/' || c == '(')
        {
            return true;
        }
        return false;
    }
    
    // Returns true or false if char c1 has lower precedence than char c2
    // where c1 & c2 are assumed to be '+' or '-' or '/' or '*'
    public static boolean lowerPrecedence(char c1, char c2)
    {
        if((c2 == '+' || c2 == '-') && (c1 == '+' || c1 == '-'))
        {
            return true;
        }
        else if((c2 == '*' || c2 == '/') && (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/'))
        {
            return true;
        }
        return false;
    }
    
    // calculates and returns the value of operands x and y
    // from operator z
    public static int calculate(char z, int x, int y)
    {
        int finalResult = 0;
        if(z == '+')
        {
            finalResult = x + y;
        }
        else if(z == '-')
        {
            finalResult = y - x;
        }
        else if(z == '*')
        {
            finalResult = x * y;
        }
        else if(z == '/')
        {
            finalResult = y / x;
        }
        return finalResult;
    }
    
    // convertToPostfix takes in string infix and values from array letterArray
    // and evaluates the infix expression while converting to postfix.
    public static void convertToPostfix(String infix,int[] letterArray)
    {
        int result = 0;
        String postfix = "";
        for(int i = 0; i < infix.length(); i++)
        {
            char inputSymbol = infix.charAt(i);
            if(isOperator(inputSymbol))
            {
                if(postSymbols.isEmpty())
                {
                    postSymbols.push(inputSymbol);
                }
                if(operators.isEmpty())
                {
                    operators.push(inputSymbol);
                }
                else
                {
                    if(lowerPrecedence(inputSymbol,operators.peek()))
                    {
                        result = calculate(operators.pop(),operands.pop(),operands.pop());
                        operands.push(result);
                    }
                    while(lowerPrecedence(inputSymbol,postSymbols.peek()))
                    {
                        postfix += postSymbols.pop();// insert operators from Stack to string
                        if(postSymbols.isEmpty())
                        {
                            break;
                        }
                    }
                    operators.push(inputSymbol);// push higher precedence char to Stack
                    postSymbols.push(inputSymbol);// push higher precedence char to Stack
                }
            }
            else if(inputSymbol == ')')
            {
                while(operators.peek() != '(')// evaluate infix expression inside parentheses
                {
                    result = calculate(operators.pop(),operands.pop(),operands.pop());
                    operands.push(result);
                }
                while(postSymbols.peek() != '(')// pop until left parenthesis is found
                {
                    postfix += postSymbols.pop();// insert operators from Stack to string
                }
                operators.pop();// removes '(' from Stack
                postSymbols.pop();// removes '(' from Stack
            }
            else
            {
                int realNum = letterArray[addr(inputSymbol)];
                operands.push(realNum);
                postfix += inputSymbol;
            }
        }
        while(!(postSymbols.isEmpty()))//pop out remaming characters in stack to postfix string
        {
            postfix += postSymbols.pop();
        }
        while(!operators.isEmpty())//calculate the final result with remaining operators
        {
            int x = operands.pop();
            if(operands.isEmpty())
            {
                operands.push(x);
                break;
            }
            int y = operands.pop();
            char z = operators.pop();
            result = calculate(z,x,y);
            operands.push(result);
        }
        System.out.println("Postfix: "+ postfix);
        int finalResult = operands.pop();
        System.out.print("Result: "+ finalResult);
        System.out.println("\n");
    }
    
    public static void main(String[] args)
    {
        Scanner kbd = new Scanner(System.in);
        int[] letterArray = new int[26];
        for(int i = 0; i < 26; i++)//sets values for letters in alphabet
        {
            letterArray[i] = i+1;
        }
        String letter = "";
        String useless = "";// holds equals symbols
        int value = 0;
        while(kbd.hasNext())
        {
            letter = kbd.next();
            if(!letter.equals("$PART2"))//collect values from user input
            {
                useless = kbd.next();
                value = kbd.nextInt();
                letterArray[addr(letter.charAt(0))] = value;
            }
            else// convert infix to postfix and evaluate result
            {
                while(kbd.hasNext())
                {
                    letter = kbd.next();
                    System.out.println("Infix: "+letter);
                    convertToPostfix(letter,letterArray);
                }
            }
        }
    }
}
