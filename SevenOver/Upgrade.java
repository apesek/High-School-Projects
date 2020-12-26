import java.awt.*;
import javax.swing.*;

public abstract class Upgrade
{
    private int xLoc, yLoc;
    public int price;
    public String name;
    public String description;
    public boolean upgradeOwned;
    boolean showDescription;
    
    /**Constructor for generator object of upgrade class
     * @param i the index of the upgrade
     * @param x the xLoc of the upgrade on the screen
     * @param y the yLoc of the upgrade on the screen
     * @param p the price of the upgrade
     * @param n the name
     * @param d the description
     */
    public Upgrade(int i, int x, int y, int p, String n, String d)
    {
        
        xLoc=x; yLoc=y;
        price=p;
        name=n;
        description=d;
        upgradeOwned=false;
        showDescription=false;
        
    }
    
    public int getPrice()
    {
        return price;
    }
    
    public void setOwnership(boolean s)
    {
        upgradeOwned=s;
    }
    
    public boolean owned()
    {
        return upgradeOwned;
    }
    
    public void setDescriptionVar(boolean set)
    {
        showDescription=set;
    }
    
    public abstract double getInj();
    
    public void draw( Graphics page )
    {
        page.setColor(new Color(0, 100, 0));
        if(upgradeOwned)
        {page.setColor(new Color(100, 100, 0));}
        page.fillRect(xLoc, yLoc, 100, 100);
        page.setColor(new Color(0, 200, 0));
        if(upgradeOwned)
        {page.setColor(new Color(200, 200, 0));}
        page.fillRect(xLoc+2, yLoc+2, 96, 96);
        page.setColor(Color.BLACK);
        page.drawString(name, xLoc+3, yLoc+12);
        if(showDescription==true)
        {
            Playboard.getUpDesc().setText(description);
        }
    }
}
