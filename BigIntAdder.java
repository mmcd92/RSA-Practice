import java.math.*;

public class BigIntAdder
{
     public static void main(String guitar[])
     {
          BigInteger test = new BigInteger("0");
          
          for(int i = 0; i < 5; i++)
          {
               String add = "";
               add+=i;
               
               System.out.println(add);
               
               BigInteger adder = new BigInteger(add);
               
               test.add(adder);
               
          }
          
          System.out.println(test);
     }
}
