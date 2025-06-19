public class ex04
{
    public  void    DrawHashtags( )
    {
        for (int i = 1; i <= 6; i++)
        {
            for (int j = 0; j < i; j++)
                System.out.print("#");
            if (i != 6)
                System.out.print("\n");
        }
    }
}
