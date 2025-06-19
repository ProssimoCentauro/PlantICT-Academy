public class ex05
{
    public  void    DrawCycle( )
    {
        for (int k = 1; k <= 6; k++)
        {
            for (int i = 1; i <= k; i++)
                System.out.print(i);
            for (int i = 0; i < 3; i++)
                System.out.print(" ");
            for (int i = 6 - k + 1; i >= 1; i--)
                System.out.print(i);
            if (k != 6)
                System.out.println();
        }
    }
}
