import java.util.*;
import java.math.*;

public class BigIntFactorer
{
     private Scanner in;
     
     private final BigInteger TWO = new BigInteger("2");
     
     private NaivePrimalityTest tester;
     private GCDCalculator gcd;
     
     private ArrayList<BigInteger> pvals;
     private ArrayList<BigInteger> qvals;
     
     private BigInteger e;
     
     public BigIntFactorer(BigInteger e_)
     {
          e = e_;
          
          in = new Scanner(System.in);
          
          tester = new NaivePrimalityTest();
          
          gcd = new GCDCalculator();
          
          pvals = new ArrayList<BigInteger>();
          qvals = new ArrayList<BigInteger>();
     }
     
     public void findFactors(BigInteger value)
     {
          BigInteger start = sqrt(value);
          
          System.out.println("Square root of\n" + value + "\n\nis\n\n" + start);
          
          boolean found = false;
          
          for(int i = 3; i < start.longValue(); i+=2)
          {
               String add = "";
               add+=i;
               
               //System.out.println(add);
               
               BigInteger x = new BigInteger(add);
               
               System.out.println("Checking " + x);
               BigInteger factor = value.divide(x);
               
               if(factor.isProbablePrime(100) && x.isProbablePrime(100))
               {
                    pvals.add(factor);
                    qvals.add(x);
               }
               
               if(x.compareTo(start) == 1)
                    found = true;
               
          }
          
          
          if(pvals.size() == 0)
          {
               System.out.println(value + " has no prime factors");
          }
          System.out.println("Possible p and q values:\n");
          for(int j = 0; j < pvals.size(); j++)
          {
               System.out.println("\t(" + pvals.get(j) + "," + qvals.get(j) + ")");
          }
     }
     
     public void findPQ(BigInteger n)
     {
          findFactors(n);
          for(int i = 0; i < pvals.size(); i++)
          {
               BigInteger phi_n = calculatePhiN(pvals.get(i),qvals.get(i));
               
               if(!checkPhiN(phi_n,e))
               {
                    pvals.remove(i);
                    qvals.remove(i);
               }
          }
     }
     
     public ArrayList<BigInteger> getPVals()
     {
          for(int i = 0; i < pvals.size(); i++)
               System.out.println(pvals.get(i));
          return pvals;
     }
     
     public ArrayList<BigInteger> getQVals()
     {
          for(int i = 0; i < qvals.size(); i++)
               System.out.println(qvals.get(i));
          return qvals;
     }
     
     public BigInteger calculatePhiN(BigInteger p, BigInteger q)
     {
          BigInteger p_min_1 = p.subtract(BigInteger.ONE);
          BigInteger q_min_1 = q.subtract(BigInteger.ONE);
          
          BigInteger phi_n = p_min_1.multiply(q_min_1);
          return phi_n;
     }//end calculatePhiN
     
     public boolean checkPhiN(BigInteger phi_n, BigInteger e)
     {
          boolean works = false;
          
          if(phi_n.gcd(e).equals(BigInteger.ONE))
               works = true;
          
          return works;
     }//end checkPhiN
     
     public void printValues()
     {
          System.out.println("Final values for p and q:");
          
          for(int i = 0; i < pvals.size(); i++)
          {
               System.out.println("\t(" + pvals.get(i) + "," + qvals.get(i) + ")");
          }
     }
     
     
     public BigInteger sqrt(BigInteger n)
     {
          BigInteger a = BigInteger.ONE;
          BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
          while(b.compareTo(a) >= 0)
          {
               BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
               if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
               else a = mid.add(BigInteger.ONE);
          }
          return a.subtract(BigInteger.ONE);
     }
     
     public static void main(String guitar[])
     {
          Scanner in = new Scanner(System.in);
          
          System.out.println("Enter your n value:");
          BigInteger n = new BigInteger(in.next());
          
          System.out.println("Enter your e value:");
          BigInteger e = new BigInteger(in.next());
          
          BigIntFactorer factorer = new BigIntFactorer(e);
          StopWatch watch = new StopWatch();
          
          watch.start();
          factorer.findPQ(n);
          watch.stop();
          
          BigInteger foundP = factorer.getPVals().get(0);
          BigInteger foundQ = factorer.getQVals().get(0);
          
          System.out.println("Enter the p and q values to check");
          Long p = in.nextLong();
          Long q = in.nextLong();
          
          if((p.equals(foundP) && q.equals(foundQ)) || (p.equals(foundQ) && q.equals(foundP)))
          {
               factorer.printValues();
               System.out.println("Broke RSA in " + watch.getElapsedTime() + "ms");
          }
          else
               System.out.println("RSACracker failed :-(");
     }//end main
}
