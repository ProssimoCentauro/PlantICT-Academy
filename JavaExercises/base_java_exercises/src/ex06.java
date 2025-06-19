public class ex06
{
    public  void    Fibonacci( )
    {
        long i = 1;
        long j = 1;
        for (int k = 0; k < 100; k++)
        {
            System.out.println(i);
            i = i + j;
            j = i - j;
        }
    }
}
