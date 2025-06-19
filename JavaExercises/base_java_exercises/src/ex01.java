public class ex01
{
    public  void    PrintEvens( )
    {
        int count = 0;
        for (int i = 20; i >= 0; i--)
        {
            if (i % 2 == 0 && count < 10)
            {
                System.out.println(i);
                count++;
            }
        }
    }
}
