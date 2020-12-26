
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    int health, size;
    int xPos, yPos;
    boolean immune;
    public Player(int h, int s)
    {
        health = h; size = s;
        xPos=500; yPos=400;
        immune=false;
    }
    
    /* Boundries are:
       X: 250 - 750
       Y: 150 - 650
       */
    public void move(int dir)
    {
        if(dir==0 && xPos<700) //Right Bound
        {xPos+=50;}
        else if(dir==90 && yPos>200) //Top Bound
        {yPos-=50;}
        else if(dir==180 && xPos>300) //LeftBound
        {xPos-=50;}
        else if(dir==270 && yPos<600) //Bottom Bound
        {yPos+=50;}
    }
    
    public void setImmunity(boolean i)
    {
        immune = i;
    }
    
    public boolean immune()
    {
        return immune;
    }
    
    public int getX()
    {
        return xPos;
    }
    
    public int getY()
    {
        return yPos;
    }
    
    public int getH()
    {
        return health;
    }
    
    public int getS()
    {
        return size;
    }
    
    public void damage(int d)
    {
        health=health-d;
    }
}
