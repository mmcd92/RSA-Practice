import java.util.*;

public class NaivePrimalityTest
{
     public static void main(String guitar[])
     {
          NaivePrimalityTest test = new NaivePrimalityTest();
          
          Scanner in = new Scanner(System.in);
          long x = 1;
          
          while(x > 0)
          {
               System.out.println("Enter a number to test:");
               boolean prime = true;
               
               x = in.nextLong();
               
               long sqrt = (long)Math.sqrt(x);
               
               long index = 2;
               
               while(prime && index <= sqrt)
               {
                    if(test.checkPrime(index))
                    {
                         if(x%index == 0)
                              prime = false;
                    }
                    index++;
               }
               if(x > 0)
                    System.out.println("Is " + x + " prime?\t" + prime);
               else
                    System.out.println("Goodbye");
          }
     }
     
     
     public NaivePrimalityTest()
     {
     }
     
     
     public boolean isPrime(long x)
     {
          boolean prime = true;
          
          long sqrt = (long)Math.sqrt(x);
               
          long index = 2;
          
          while(prime && index <= sqrt)
          {
               if(checkPrime(index))
               {
                    if(x%index == 0)
                         prime = false;
               }
               index++;
          }//end while
          
          return prime;
     }
     
     
     public boolean checkPrime(long x)
     {
          boolean prime = true;
          long index = 2;
          
          while(prime && index < x)
          {
               if(x%index == 0)
                    prime = false;
               index++;
          }
          return prime;
     }
}


/*
 * Sample output:

Enter a number to test:
107
Is 107 prime? true
Enter a number to test:
1187
Is 1187 prime? true
Enter a number to test:
2312
Is 2312 prime? false
Enter a number to test:
7091
Is 7091 prime? false
Enter a number to test:
95171
Is 95171 prime? false
Enter a number to test:
177111
Is 177111 prime? false
Enter a number to test:
8992231
Is 8992231 prime? true
Enter a number to test:
20131107
Is 20131107 prime? false
Enter a number to test:
-1
Goodbye

 * 
 * 
 */ 
