

public class Generator extends Upgrade
{
    int injection;
    
    /**Constructor for generator object of upgrade class
     * @param i the index of the upgrade
     * @param x the xLoc of the upgrade on the screen
     * @param y the yLoc of the upgrade on the screen
     * @param p the price of the upgrade
     * @param n the name
     * @param d the description
     * @param inj the amount of money that is given to the player every turn
     */
    public Generator(int i, int x, int y, int p, String n, String d, int inj)
    {
        super(i, x, y, p, n, d);
        injection=inj;
    }
    
    public double getInj()
    {
        return injection;
    }
}
