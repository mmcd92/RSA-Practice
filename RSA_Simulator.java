import java.io.*;
import java.math.BigInteger;
import java.util.Random;

public class RSA_Simulator
{
     private BigInteger p;
     private BigInteger q;
     private BigInteger n_;
     private BigInteger p_minus_1;
     private BigInteger q_minus_1;
     private BigInteger phi_n;
     private BigInteger e_;
     private BigInteger d_;
     
     private boolean naive;
     
     private BigInteger[] c;
     
     private String message;
     private String encryptedMessage;
     private String decryptedMessage;
     
     
     public RSA_Simulator()
     {
          generateKeys();
     }
     
     
     public static void main(String[] args) throws IOException
     {
          RSA_Simulator sim = new RSA_Simulator();
          
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
          
          //sim.generateKeys();
          
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
          System.out.println("\nThis is the decrypted message:\n\n" + sim.getDecryptedMessage());//*/
          
          
     }//end main
     
     
     public void generateKeys()
     {
          generatePQ();
          computeN();
          generatePQMin1();
          computePhiN();
          generateE();
          generateD();
     }
     
     
     public void generatePQ()
     {
          // 1. Generate p,q; compute n
          p=pseudo_prime(16, 100);
          q=pseudo_prime(16, 100);
          
          System.out.println(p + "\n" + q);
     }

     
     public void computeN()
     {
          n_=p.multiply(q); //n_=p*q
     }
     
     
     public void generatePQMin1() 
     {
          // 2. Compute phi(n)=(p-1)*(q-1)
          p_minus_1=p.subtract(BigInteger.ONE);
          q_minus_1=q.subtract(BigInteger.ONE);
     }
     
     
     public void computePhiN()
     {
          phi_n=p_minus_1.multiply(q_minus_1);
          //System.out.println("\n this is phi(n) \n"+phi_n);
     }
     
     
     public void generateE()
     {
          // 3. Select encrytion key e_
          BigInteger e_=BigInteger.ONE;
          for( int i=3;i<100;i++)
          {
               BigInteger big_i=BigInteger.valueOf(i);
               BigInteger t=phi_n.gcd(big_i);
               
               if (t.equals(BigInteger.ONE))
               {
                    //e_=big_i;
                    setE(big_i);
                    break;
               }
          }
          //System.out.println("\nThis is encrytion key: e_\n\n" +e_);
     }
     
     
     public void setE(BigInteger e)
     {
          e_ = e;
     }
     
     
     public void generateD()
     {
          //4. compute decrytion key d_
          //System.out.println("\t" + e_ + "\n\t" + phi_n);
          d_=e_.modInverse(phi_n);
          //System.out.println("\nthis is the decrytion key: d_\n\n"+d_);
     }
     
     
     public void printPublicKey()
     {
          //5. publish the public key
          //System.out.println("\nThis is the public key:\n\n");
          System.out.println("\t("+e_.toString()+","+n_.toString()+")");
     }
     
     
     public BigInteger[] encryptMessage()
     {
          // This is the message
          //String message = "Math is the King, Cryptology is the Queen, and We are Subjects!";
          BigInteger[] m = new BigInteger[message.length()];
          
          for(int i = 0; i < message.length(); i++)
          {
               String index = "";
               index += (int)message.charAt(i);
               m[i] = new BigInteger(index);
          }
          //System.out.println("\nThis is the message:\n"+message);
          
          //6. Encrpytion
          c= new BigInteger[m.length];
          String encrypted = "";
          
          for(int i = 0; i < c.length; i++)
          {
               c[i] = m[i].modPow(e_,n_);
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
          //7. decryption
          BigInteger[] m2 = new BigInteger[c.length];
          //char[] dec = new char[m2.length];
          String decrypted = "";
          
          for(int i = 0; i < m2.length; i++)
          {
               m2[i] = c[i].modPow(d_, n_);
               //dec[i] = (char)m2[i].intValue();
               decrypted+=(char)m2[i].intValue();
          }
          //System.out.println("\nThis is the retrieved message:\n\n"+decrypted+"\n\n");
          
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
     
     
     // Generate a pseudo-prime number with # of bits: ’bit_length’
     // The probability that this number is prime > 1-(1/2)^certainty
     public static BigInteger pseudo_prime(int bit_length, int certainty)
     {
          Random rnd=new Random();
          BigInteger p_prime=new BigInteger(bit_length, certainty, rnd);
          return p_prime;
     }//end pseudo_prime
     
     
     public static long naiveGen()
     {
          NaivePrimalityTest tester = new NaivePrimalityTest();
          
          long num = generatePrime();
          
          if(tester.isPrime(num))
               return num;
          else
               return naiveGen();
          
          
     }
     
     
     public static long generatePrime()
     {
          Random rnd = new Random();
          
          long num = rnd.nextInt(1000) + 100;
          
          return num;
     }
}//end rsa_simulation 
