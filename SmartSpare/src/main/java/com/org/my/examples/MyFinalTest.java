package com.org.my.examples;

public class MyFinalTest {

    public int doMethod()
    {
        try
        {
            System.out.println(""+10/0);
            return 5;
        }
        catch(Exception ex)
        {
            System.out.println(""+ex.getMessage());
        	return 5;
        }
        
        finally
        {
        	System.out.println("final");
            return 10;
        }
    }

    public static void main(String[] args) {

        MyFinalTest testEx = new MyFinalTest();
        int rVal = testEx.doMethod();
        System.out.println("The return Val : "+rVal);
    }

}