import java.awt.*;
import javax.swing.*;

public class Dice
{
    private int xLoc;
    private int yLoc;
    private int roll;
    
    /**Constructor for dice object
     * @param x the x location of the top left corner of the dice on the screen
     * @param y the y location of the top left corner of the dice on the screen
     */
    public Dice( int x, int y)
    {
        xLoc=x;
        yLoc=y;
        roll=0;
    }
    
    /**Generates a random number between 1 and 6 for the dice roll.
     */
    public void rollDice()
    {
        roll=Tools.random(6)+1;
    }
    
    /**Generates a random number betwee 1 and 6 for the dice roll
     * with an increased chance of rolling 4, 5, or 6.
     */
    public void rollUpgradedDice()
    {
        roll=Tools.random(9)+1;
        if(roll==7)
        {roll=4;}
        if(roll==8)
        {roll=5;}
        if(roll==9)
        {roll=6;}
    }
    
    /**Gets the dice roll curently associated with the dice object
     * @retun roll
     */
    public int getRoll()
    {
        return roll;
    }
    
    /**Resets the roll back to 0
     */
    public void resetRoll()
    {
        roll=0;
    }
    
    public void draw( Graphics page )
    {
        page.setColor(Color.BLACK);
        page.fillRect(xLoc, yLoc, 100, 100);
        page.setColor(Color.WHITE);
        page.fillRect(xLoc+3, yLoc+3, 94, 94);
        page.setColor(Color.BLACK);
        if( roll>=2 && roll<=6)//Fills circles @ top-left and bottom-right; if roll is 2, 3, 4, 5, or 6
        {
            page.fillOval(xLoc+17, yLoc+17, 16, 16);
            page.fillOval(xLoc+67, yLoc+67, 16, 16);
        }
        if( roll>=4 && roll<=6)//Fills circles @ top-right and bottom-left; if roll is 4, 5, or 6
        {
            page.fillOval(xLoc+67, yLoc+17, 16, 16);
            page.fillOval(xLoc+17, yLoc+67, 16, 16);
        }
        if( roll==6 )//Fills circles @ middle-left and middle-right; if roll is 6
        {
            page.fillOval(xLoc+17, yLoc+42, 16, 16);
            page.fillOval(xLoc+67, yLoc+42, 16, 16);
        }
        if( roll==1 || roll==3 || roll==5 )//Fills circle @ middle-middle, if roll is 1, 3, or 5
        {
            page.fillOval(xLoc+42, yLoc+42, 16, 16);
        }
    }
}
