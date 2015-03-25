import java.util.*;

public class Factorer
{
     private Scanner in;
     
     private NaivePrimalityTest tester;
     private GCDCalculator gcd;
     
     private ArrayList<Long> pvals;
     private ArrayList<Long> qvals;
     
     private ArrayList<BigInteger> pvalsBig;
     private ArrayList<BigInteger> qvalsBig;
     
     private long e_;
     
     public Factorer(long e)
     {
          e_ = e;
          
          in = new Scanner(System.in);
          
          tester = new NaivePrimalityTest();
          
          gcd = new GCDCalculator();
          
          pvals = new ArrayList<Long>();
          qvals = new ArrayList<Long>();
     }
     
     public void findFactors(long value)
     {
          double start = Math.sqrt(value);
          
          for(double i = 2.0; i < start; i++)
          {
               double factor = value/i;
               
               //checks to see if the factor is a whole number
               if(Math.ceil(factor) == factor)
               {
                    if(tester.isPrime((long)factor) && tester.isPrime((long)i))
                    {
                         pvals.add((long)factor);
                         qvals.add((long)i);
                    }
               }
          }
          
          
          if(pvals.size() == 0)
          {
               System.out.println(value + " has no prime factors");
          }
          System.out.println("Possible p and q values:\n");
          for(int i = 0; i < pvals.size(); i++)
          {
               System.out.println("\t(" + pvals.get(i) + "," + qvals.get(i) + ")");
          }
     }
     
     public void findBigFactors(BigInteger n_)
     {
          BigInteger p_ = new BigInteger(“2”);
          BigInteger q_ = new BigInteger(“3”);
          
          while(p_.compareTo(q_) < 0)
          {
               if(checkPrime(p_))
               {
                    BigInteger [] x = n_.divideAndRemainder(p_);
                    
                    if(x[1].compareTo(BigInteger.ZERO) == 0)
                    {
                         if(checkPrime(x[0]))
                         {
                              pListBig.add(p_);
                              qListBig.add(x[0]);
                              q_ = x[0];
                              p_.add(BigInteger.ONE);
                         }
                    }
                    else
                    {
                         q_.add(BigInteger.ONE);
                         p_.add(BigInteger.ONE);
                    }
               }//end if
          }//end while
     }//end findBigFactors
     
     
     public void findPQ(long n)
     {
          findFactors(n);
          for(int i = 0; i < pvals.size(); i++)
          {
               long phi_n = calculatePhiN(pvals.get(i),qvals.get(i));
               
               if(!checkPhiN(phi_n,e_))
               {
                    pvals.remove(i);
                    qvals.remove(i);
               }
          }
     }

     
     public ArrayList<Long> getPVals()
     {
          return pvals;
     }
     
     public ArrayList<Long> getQVals()
     {
          return qvals;
     }
     
     public long calculatePhiN(long p, long q)
     {
          return (p-1)*(q-1);
     }//end calculatePhiN
     
     public boolean checkPhiN(long phi_n, long e)
     {
          boolean works = false;
          
          if(gcd.calculate(phi_n,e) == 1)
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
     
     public static void main(String guitar[])
     {
          Scanner in = new Scanner(System.in);
          
          System.out.println("Enter your n value:");
          long n = in.nextLong();
          
          System.out.println("Enter your e value:");
          long e = in.nextLong();
          
          Factorer factorer = new Factorer(e);
          StopWatch watch = new StopWatch();
          
          watch.start();
          factorer.findPQ(n);
          watch.stop();
          
          Long foundP = factorer.getPVals().get(0);
          Long foundQ = factorer.getQVals().get(0);
          
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
