/**
 * Jose Salinas
 * Derivatives.java
 * 12/01/16
 * Tabs: 2 spaces
 */

import java.io.*;

//==========================================================
//                     Class Expression
//==========================================================
abstract class Expression
{
    //-----------------------------------------------------
    // simplify() returns a possibly simplified version of
    // this expression.
    //-----------------------------------------------------
    
    public abstract Expression simplify();
    
    //-----------------------------------------------------
    // toString() returns a string that described this
    // expression.  It can be used for printing this
    // expression.
    //-----------------------------------------------------
    
    public abstract String toString();
    
    //-----------------------------------------------------
    // rawderiv() returns the derivative of this expression,
    // without simplification.
    //-----------------------------------------------------
    
    public abstract Expression rawderiv();
    
    //-----------------------------------------------------
    // deriv() returns the derivative of this expression,
    // with simplification.
    //-----------------------------------------------------
    
    public Expression deriv()
    {
        return rawderiv().simplify();
    }
}

//==========================================================
//                     Class ConstantExpression
//==========================================================
// A constant expression is the expression equivalent of a
// real number.
//==========================================================

class ConstantExpression extends Expression
{
    private double value;
    
    public ConstantExpression(double v)
    {
        value = v;
    }
    
    public Expression rawderiv()
    {
        return new ConstantExpression(0.0);
    }
    
    public Expression simplify()
    {
        return this;
    }
    
    public String toString()
    {
        return "" + value;
    }
    
    public double getvalue()
    {
        return value;
    }
}

//==========================================================
//                     Class VarExpression
//==========================================================
// A VarExpression is the independent variable x.
//==========================================================

class VarExpression extends Expression
{
    public VarExpression()
    {
    }
    
    public Expression rawderiv()
    {
        return new ConstantExpression(1.0);
    }
    
    public Expression simplify()
    {
        return this;
    }
    
    public String toString()
    {
        return "x";
    }
    
}

//==========================================================
//                    Class SumExpression
//==========================================================
// A SumExpression is an expression that is the sum of two
// other expressions.
//==========================================================

class SumExpression extends Expression
{
    private Expression addend1, addend2;
    
    public SumExpression(Expression e1, Expression e2)
    {
        addend1 = e1;
        addend2 = e2;
    }
    
    public Expression rawderiv()
    {
        return new SumExpression(addend1.rawderiv(), addend2.rawderiv());
    }
    
    public Expression simplify()
    {
        return simplifySum(addend1.simplify(), addend2.simplify());
    }
    
    public String toString()
    {
        return "(" + addend1.toString() + "+" + addend2.toString() + ")";
    }
    
    private static Expression simplifySum(Expression e1, Expression e2)
    {
        // 0 + x = x and constant arithmetic
        
        if(e1 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e1;
            
            // 0 + x = x
            
            if(ce.getvalue() == 0.0)
            {
                return e2;
            }
            
            // Constant arithmetic
            
            if(e2 instanceof ConstantExpression)
            {
                ConstantExpression ce2 = (ConstantExpression) e2;
                return new ConstantExpression(ce.getvalue() + ce2.getvalue());
            }
        }
        
        // x + 0 = x
        
        if(e2 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e2;
            if(ce.getvalue() == 0.0)
            {
                return e1;
            }
        }
        
        // Default: do not simplify.
        
        return new SumExpression(e1, e2);
        
    }
}

//==========================================================
//                    Class SubExpression
//==========================================================
// A SubExpression is an expression that is the difference of
// two other expressions.
//==========================================================
class SubExpression extends Expression
{
    private Expression addend1, addend2;
    
    public SubExpression(Expression e1, Expression e2)
    {
        addend1 = e1;
        addend2 = e2;
    }
    
    public Expression rawderiv()
    {
        return new SubExpression(addend1.rawderiv(), addend2.rawderiv());
    }
    
    public Expression simplify()
    {
        return simplifySub(addend1.simplify(), addend2.simplify());
    }
    
    public String toString()
    {
        return "(" + addend1.toString() + "-" + addend2.toString() + ")";
    }
    
    private static Expression simplifySub(Expression e1, Expression e2)
    {
        // 0 - x = -x and constant arithmetic
        
        if(e1 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e1;
            
            if(e2 instanceof ConstantExpression)
            {
                ConstantExpression ce2 = (ConstantExpression) e2;
                return new ConstantExpression(ce.getvalue() - ce2.getvalue());
            }
        }
        
        // x - 0 = x
        
        if(e2 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e2;
            if(ce.getvalue() == 0.0)
            {
                return e1;
            }
        }
        
        // Default: do not simplify.
        
        return new SubExpression(e1, e2);
        
    }
}
//==========================================================
//                    Class ProdExpression
//==========================================================
// A ProdExpression is an expression that is the product of
// two other expressions.
//==========================================================

class ProdExpression extends Expression
{
    private Expression addend1, addend2;
    
    public ProdExpression(Expression e1, Expression e2)
    {
        addend1 = e1;
        addend2 = e2;
    }
    
    public Expression rawderiv()
    {
        return new SumExpression(new ProdExpression(addend2.rawderiv(),addend1), new ProdExpression(addend1.rawderiv(),addend2));
    }
    
    public Expression simplify()
    {
        return simplifyProd(addend1.simplify(), addend2.simplify());
    }
    
    public String toString()
    {
        return "(" + addend1.toString() + "*" + addend2.toString() + ")";
    }
    
    private static Expression simplifyProd(Expression e1, Expression e2)
    {
        // 0 * x = 0 and 1 * x = x and constant arithmetic
        
        if(e1 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e1;
            
            // 0 * x = 0
            if(ce.getvalue() == 0.0)
            {
                return new ConstantExpression(0.0);
            }
            // 1 * x = x
            if(ce.getvalue() == 1.0)
            {
                return e2;
            }
            
            // Constant arithmetic
            
            if(e2 instanceof ConstantExpression)
            {
                ConstantExpression ce2 = (ConstantExpression) e2;
                return new ConstantExpression(ce.getvalue() * ce2.getvalue());
            }
        }
        
        if(e2 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e2;
            // x * 0 = 0
            if(ce.getvalue() == 0.0)
            {
                return new ConstantExpression(0.0);
            }
            // x * 1 = x
            if(ce.getvalue() == 1.0)
            {
                return e1;
            }
        }
        
        // Default: do not simplify.
        
        return new ProdExpression(e1, e2);
        
    }
}


//==========================================================
//                    Class PowExpression
//==========================================================
// A PowExpression is an expression that is the power of two
// other expressions.
//==========================================================
class PowExpression extends Expression
{
    private Expression addend1, addend2;
    
    public PowExpression(Expression e1, Expression e2)
    {
        addend1 = e1;
        addend2 = e2;
    }
    
    public Expression rawderiv()
    {
        return new ProdExpression(new ProdExpression(addend2,new PowExpression(addend1,
        new SubExpression(addend2,new ConstantExpression (1.0)))),addend1.rawderiv());
    }
    
    public Expression simplify()
    {
        return simplifyPow(addend1.simplify(), addend2.simplify());
    }
    
    public String toString()
    {
        return "(" + addend1.toString() + "^" + addend2.toString() + ")";
    }
    
    private static Expression simplifyPow(Expression e1, Expression e2)
    {
        
        if(e1 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e1;
            
            // 0 ^ x = null
            if(!(e2 instanceof ConstantExpression))
            {
                return null;
            }
            
            // 0 ^ ? = 0
            if(ce.getvalue() == 0.0)
            {
                // 0 ^ 0 = null
                if(e2 instanceof ConstantExpression)
                {
                    ConstantExpression ce2 = (ConstantExpression) e2;
                    if(ce2.getvalue() == 0.0)
                    {
                        return null;
                    }
                }
                return new ConstantExpression(0.0);
            }
            
            // 1 ^ ? = 0
            if(ce.getvalue() == 1.0)
            {
                return new ConstantExpression(1.0);
            }
            
            // Constant arithmetic
            if(e2 instanceof ConstantExpression)
            {
                ConstantExpression ce2 = (ConstantExpression) e2;
                return new ConstantExpression(Math.pow(ce.getvalue(),ce2.getvalue()));
            }
        }
        
        // Constant arithmetic
        
        if(e2 instanceof ConstantExpression)
        {
            ConstantExpression ce = (ConstantExpression) e2;
            
            // X ^ 0 = 1
            if(ce.getvalue() == 0.0)
            {
                return new ConstantExpression (1.0);
            }
            // X ^ 1 = X
            if(ce.getvalue() == 1.0)
            {
                return e1;
            }
        }
        // Default: do not simplify.
        return new PowExpression(e1,e2);
    }
}