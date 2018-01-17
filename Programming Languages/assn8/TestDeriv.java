/**
 * Jose Salinas
 * TestDeriv.java
 * 12/01/16
 * Tabs: 2 spaces
 */
public class TestDeriv
{
    public static void main(String args[])
    {
        Expression a = new ConstantExpression(5.0);
        Expression b = new ConstantExpression (0.0);
        Expression c = new ConstantExpression(2.0);
        Expression x = new VarExpression();
        Expression s = new SumExpression(x, x);
        Expression t = new SumExpression(x, a);
        Expression me = new SumExpression(a, x);
        Expression me2 = new PowExpression(a, x);
        Expression te = new SumExpression(a, a);
        Expression u = new SubExpression(a, a);
        Expression v = new SubExpression(x, a);
        Expression w = new SubExpression(x, x);
        Expression z = new SubExpression(a, x);
        Expression prd1 = new ProdExpression(a, a);
        Expression prd2 = new ProdExpression(a, x);
        Expression prd22 = new ProdExpression(a, x);
        Expression jose = new SumExpression(prd22, prd22);
        Expression prd3 = new ProdExpression(x, x);
        Expression t1 = new PowExpression(b,x);
        Expression t2 = new PowExpression(b,b);
        Expression v1 = new PowExpression(a,b);
        Expression x11 = new PowExpression(x,a);
        Expression y1 = new PowExpression(a,c);
        Expression prd5 = new SubExpression(c, x11);
        Expression c21 = t1.deriv();
        Expression c41 = v1.deriv();
        Expression c51 = x11.deriv();
        Expression c61 = y1.deriv();
        Expression d12 = prd5.deriv();
        Expression d50 = y1.simplify();
        Expression d1 = s.deriv();
        Expression d2 = t.deriv();
        Expression d3 = u.deriv();
        Expression d4 = v.deriv();
        Expression d5 = w.deriv();
        Expression d6 = z.deriv();
        Expression d7 = me.deriv();
        Expression d8 = te.deriv();
        Expression d9 = prd1.deriv();
        Expression d10 = prd2.deriv();
        Expression d11 = prd3.deriv();
        Expression d13 = prd1.simplify();
        Expression d14 = prd2.simplify();
        Expression d15 = prd3.simplify();
        Expression d16 = te.simplify();
        System.out.println("--------------------\nTesting Derivatives\n--------------------");
        System.out.println("deriv(" + s + ") = " + d1);
        System.out.println("deriv(" + t + ") = " + d2);
        System.out.println("deriv(" + me + ") = " + d7);
        System.out.println("deriv(" + te + ") = " + d8);
        System.out.println("deriv(" + u + ") = " + d3);
        System.out.println("deriv(" + v + ") = " + d4);
        System.out.println("deriv(" + w + ") = " + d5);
        System.out.println("deriv(" + z + ") = " + d6);
        System.out.println("deriv(" + prd1 + ") = " + d9);
        System.out.println("deriv(" + prd2 + ") = " + d10);
        System.out.println("deriv(" + prd3 + ") = " + d11);
        System.out.println("deriv(" + t1 + ") = " + c21);
        System.out.println("deriv(" + v1 + ") = " + c41);
        System.out.println("deriv(" + x11 + ") = " + c51);
        System.out.println("deriv(" + y1 + ") = " + c61);
        System.out.println("deriv(" + prd5 + ") = " + d12);
        System.out.println("deriv(" + jose + ") = " + jose.deriv());
        System.out.println("------------------\nTesting Simplify\n------------------");
        System.out.println("simplify(" + prd1 + ") = " + d13);
        System.out.println("simplify(" + prd2 + ") = " + d14);
        System.out.println("simplify(" + prd3 + ") = " + d15);
        System.out.println("simplify(" + te + ") = " + d16);
        System.out.println("simplify(" + y1 + ") = " + d50);
        System.out.println("simplify(" + t2 + ") = " + t2.simplify());
        System.out.println("simplify(" + me2 + ") = " + me2.simplify());
    }
}
