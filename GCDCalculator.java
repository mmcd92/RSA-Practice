public class GCDCalculator
{
     public GCDCalculator()
     {
     }
     
     public long calculate(long a, long b)
     {
          if(a < b)
          {
               long temp = a;
               a = b;
               b = temp;
          }
          
          long r = a%b;
          
          if(r == 0)
               return b;
          else
               return calculate(b,r);
     }
     
     public int calculate(int a, int b)
     {
          if(a < b)
          {
               int temp = a;
               a = b;
               b = temp;
          }
          
          int r = a%b;
          
          if(r == 0)
               return b;
          else
               return calculate(b,r);
     }
     
     public static void main(String guitar[])
     {
          GCDCalculator gcd = new GCDCalculator();
          
          long res1 = gcd.calculate(53,540);
          
          System.out.println(res1);
     }
}
