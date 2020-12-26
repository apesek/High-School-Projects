
/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Object
{
    int xPos, yPos, direct;
    boolean healable;
    boolean immunity;
    public Object(int x, int y, int dir)
    {
        xPos=x; yPos=y;
        direct=dir;
        int green = (int)(Math.random()*15);
        healable = (green==0);
        if(!healable)
        {
            int yellow = (int)(Math.random()*30);
            immunity = (yellow==0);
        }
    }
    public int getX()
    {return xPos;}
    public int getY()
    {return yPos;}
    public boolean healable()
    {return healable;}
    public boolean immunity()
    {return immunity;}
    public void move()
    {
        if(direct == 0)
        {
            xPos++;
        }
        else if(direct == 90)
        {
            yPos--;
        }
        else if(direct == 180)
        {
            xPos--;
        }
        else if(direct == 270)
        {
            yPos++;
        }
    }
}
