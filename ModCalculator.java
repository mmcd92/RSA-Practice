import java.util.*;

public class ModCalculator
{
     public ModCalculator()
     {
     }
     
     
     public long calculate(long b, long e, long m)
     {
          long exp = getBase2(e);
          
          long test = calculateExp(b,exp,m);
          
          long left = e - exp;

          //System.out.println("\tExp = " + exp + "\n\tRemainder = " + left);
          //System.out.println("\tClosest power of 2 to " + left + " --> " + nextExp);
          
          if(left == 0)
               return test;
          else
          {
               long next = calculate(b,left,m);
               long temp = test;
               long possible = (temp*next)%m;
               //System.out.println(temp + " * " + next + " % " + m + " = " + possible);
               return (test * next)%m;
          }
     }
     
     
     public long calculateExp(long b, long e, long m)
     {
          if(e == 1)
               return b%m;
          
          long hold = 2;
          
          long res = b%m;
          
          while(hold <= e)
          {
               //System.out.print(hold + ": " + res);
               res = ((long)Math.pow(res,2))%m;
               //System.out.println("^" + 2 + "%" + m + " = " + res);
               hold*=2;
          }
          
          return res;
     }
     
     
     public long getBase2(long e)
     {
          boolean found = false;
          long start = 1;
          
          while(/*!found && */start < e && e > 1)
          {
               //System.out.println("\nStart is " + start + "\tE is " + e);
               start*=2;
               
               //System.out.println("Start becomes " + start + "\tE is " + e + "\n");
               
               
               /*double log = Math.log((double)e)/Math.log(2.0);
               
               if(Math.ceil(log) > log)
               {
                    System.out.println(e + " is not a power of 2.\nChecking with " + (e-1));
                    //return getBase2(e-1);
                    e--;
               }
               else
               {
                    System.out.println(e + " is a power of 2.\nReturning " + e);
                    //return e;
                    found = true;
               }*/
          }
          
          //System.out.println("Exiting the loop, value is " + start);
          
          if(start > 1)
               start/=2;
          
          //System.out.println("Value to return: " + start);
          
          return start;
     }
     
     
     public static void main(String guitar[])
     {
          ModCalculator calc = new ModCalculator();
          
          Scanner in = new Scanner(System.in);
          
          //System.out.println("Enter your base, exponent, and divisor:");
          
          
          long base = in.nextInt();
          long exp = in.nextInt();
          long div = in.nextInt();
          
          long res = calc.calculate(base,exp,div);
          
          System.out.println(res);
          //System.out.println((16*8)%17);
          //System.out.println((int)Math.pow(2,133)%17);
     }
}
