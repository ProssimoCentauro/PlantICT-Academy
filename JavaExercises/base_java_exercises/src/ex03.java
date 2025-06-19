public class ex03
{
    public  void    DrawStars( )
    {
        for (int i = 6; i > 0; i--)
        {
            for (int j = 0; j < i; j++)
                System.out.print("*");
            if (i != 0)
                System.out.print("\n");
        }
    }
}
