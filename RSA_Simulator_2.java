import java.io.*;
import java.util.Random;

public class RSA_Simulator_2
{
     private long p;
     private long q;
     private long n;
     private long phi_n;
     private long e;
     private long d;
     
     private long[] c;
     
     private String message;
     private String encryptedMessage;
     private String decryptedMessage;
     
     private ModCalculator mod;
     private GCDCalculator gcd;
     NaivePrimalityTest tester;
     
     
     public RSA_Simulator_2()
     {
          mod = new ModCalculator();
          gcd = new GCDCalculator();
          tester = new NaivePrimalityTest();
          
          generateKeys();
     }
     
     
     public void generateKeys()
     {
          generateP();
          generateQ();
          computeN();
          computePhiN();
          generateE();
          generateD();
     }
     
     
     public void generateP()
     {
          p = naiveGen(10000,1000);
          while(p == q)
               p = naiveGen(10000,1000);
          //p = 41;
          System.out.println("\nP is " + p);
     }
     
     
     public void generateQ()
     {
          q = naiveGen(10000,1000);
          while(q == p)
               q = naiveGen(10000,1000);
          //q = 83;
          System.out.println("\nQ is " + q);
     }
     
     
     public void computeN()
     {
          n = p*q;
     }
     
     
     public void computePhiN()
     {
          phi_n = (p-1)*(q-1);
          //System.out.println("\n this is phi(n) \n"+phi_n);
     }
     
     
     public void generateE()
     {
          long e = 1;
          for(int i = 3; i < 100; i++)
          {
               long t = gcd.calculate(phi_n,i);
               
               if (t == 1)
               {
                    setE(i);
                    break;
               }
          }
          //System.out.println("\nThis is encrytion key: e_\n\n" +e_);
     }
     
     
     public void setE(long num)
     {
          e = num;
          //e = 7;
     }
     
     
     public void generateD()
     {
          boolean found = false;
          //boolean prime = false;
          
          long index = 1;
          
          while(!found)
          {
               d = (((phi_n)*index) + 1) / e;
               //System.out.println(d);

                    //prime = tester.isPrime(d);
                    //if(prime)
                    //{
                         //System.out.println("It's a prime number");
                         long result = (e*d)%phi_n;
                         //System.out.println("Result is " + result);
                         long compare = 1%phi_n;
                         
                         if(result == compare)
                         {
                              //System.out.println("FOUND A D!!!! " + d);
                              //d = (int)test;
                              found = true;
                         }
                    //}
               //}
               
               index++;
          }
          //System.out.println("d is " + d + "\nindex is " + index);
     }
     
     
     public void printPublicKey()
     {
          //5. publish the public key
          //System.out.println("\nThis is the public key:\n\n");
          System.out.println("\t(" + e + "," + n + ")");
     }
     
     
     public long[] encryptMessage()
     {
          // This is the message
          //String message = "Math is the King, Cryptology is the Queen, and We are Subjects!";
          long[] m = new long[message.length()];
          
          for(int i = 0; i < message.length(); i++)
          {
               m[i] = (int)message.charAt(i);
          }
          //System.out.println("\nThis is the message:\n"+message);
          
          //6. Encrpytion
          c = new long[m.length];
          String encrypted = "";
          
          for(int i = 0; i < c.length; i++)
          {
               c[i] = mod.calculate(m[i],e,n);
               //c[i] = ((long)Math.pow(m[i],e))%n;
               //System.out.println("############################################Encrypting " + m[i] + "############################################\nBecomes " + c[i]);
               encrypted+=c[i];
          }
          //System.out.println("\n"+"This is the encrypted message:\n"+encrypted);
          
          setEncryptedMessage(encrypted);
          
          return c;
     }//end encryptMessage
     
     
     public void setEncryptedMessage(String s)
     {
          encryptedMessage = s;
     }
     
     
     public String getEncryptedMessage()
     {
          return encryptedMessage;
     }
     
     
     public void decryptMessage()
     {
          long[] m2 = new long[c.length];
          
          String decrypted = "";
          
          for(int i = 0; i < m2.length; i++)
          {
               //System.out.println("********************************************DECRYPTING " + c[i] + "********************************************");
               m2[i] = mod.calculate(c[i],d,n);
               //m2[i] = ((long)Math.pow(c[i],d))%n;
               //System.out.println("Becomes " + m2[i]);
               
               decrypted+=(char)m2[i];
          }
          
          setDecryptedMessage(decrypted);
     }//end decryptMessage
     
     
     public void setDecryptedMessage(String s)
     {
          decryptedMessage = s;
     }
     
     
     public String getDecryptedMessage()
     {
          return decryptedMessage;
     }
     
     
     public void setMessage(String s)
     {
          message = s;
     }
     
     
     public String getMessage()
     {
          return message;
     }
     
     
     public long naiveGen(int size, int offset)
     {
          long num = generatePrime(size, offset);
          
          if(tester.isPrime(num))
               return num;
          else
               return naiveGen(size, offset);
     }
     
     
     public long generatePrime(int size, int offset)
     {
          Random rnd = new Random();
          
          long num = rnd.nextInt(size) + offset;
          
          return num;
     }
     
     
     public static void main(String guitar[])
     {
          RSA_Simulator_2 sim = new RSA_Simulator_2();
          
          String test1 = "Math is the King, Cryptology is the Queen, and We are Subjects!";
          String test2 = "$1+$1=$1000? It is likely....:)";
          String test3 = "AUGGCCACAUUGGCACCUCCCTTTTAAATGG";
          String test4 = "DES is not as beautiful as RSA mathematically";
          String test5 = "FALL CISC4080 COMPUTER ALGORITHMS";
          
          System.out.println("This program will encrypt and decrypt the following:");
          System.out.println("\t" + test1);
          System.out.println("\t" + test2);
          System.out.println("\t" + test3);
          System.out.println("\t" + test4);
          System.out.println("\t" + test5);
          
          System.out.println("Using the following public key:");
          sim.printPublicKey();
          
          System.out.println("\n\n\n*************************************************");
          System.out.println("*****Here are the encryption and decryptions*****");
          System.out.println("*************************************************\n");
          
          sim.setMessage(test1);
          sim.encryptMessage();
          sim.decryptMessage();
          System.out.println("\nThis is the original message:\n\n" + sim.getMessage());
          System.out.println("\nThis is the encrypted message:\n\n" + sim.getEncryptedMessage());
          System.out.println("\nThis is the decrypted message:\n\n" + sim.getDecryptedMessage());
          
          sim.setMessage(test2);
          sim.encryptMessage();
          sim.decryptMessage();
          System.out.println("\nThis is the original message:\n\n" + sim.getMessage());
          System.out.println("\nThis is the encrypted message:\n\n" + sim.getEncryptedMessage());
          System.out.println("\nThis is the decrypted message:\n\n" + sim.getDecryptedMessage());
          
          sim.setMessage(test3);
          sim.encryptMessage();
          sim.decryptMessage();
          System.out.println("\nThis is the original message:\n\n" + sim.getMessage());
          System.out.println("\nThis is the encrypted message:\n\n" + sim.getEncryptedMessage());
          System.out.println("\nThis is the decrypted message:\n\n" + sim.getDecryptedMessage());
          
          sim.setMessage(test4);
          sim.encryptMessage();
          sim.decryptMessage();
          System.out.println("\nThis is the original message:\n\n" + sim.getMessage());
          System.out.println("\nThis is the encrypted message:\n\n" + sim.getEncryptedMessage());
          System.out.println("\nThis is the decrypted message:\n\n" + sim.getDecryptedMessage());
          
          sim.setMessage(test5);
          sim.encryptMessage();
          sim.decryptMessage();
          System.out.println("\nThis is the original message:\n\n" + sim.getMessage());
          System.out.println("\nThis is the encrypted message:\n\n" + sim.getEncryptedMessage());
          System.out.println("\nThis is the decrypted message:\n\n" + sim.getDecryptedMessage());
     }
}
