

public class Tools
{
    public void pause(int p)
    {
        try
            {
                Thread.sleep( p );
            }catch( InterruptedException ex ){}
    }
}
