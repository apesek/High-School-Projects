

public class Interest extends Upgrade
{
    double rate;
    
    /**Constructor for generator object of upgrade class
     * @param i the index of the upgrade
     * @param x the xLoc of the upgrade on the screen
     * @param y the yLoc of the upgrade on the screen
     * @param p the price of the upgrade
     * @param n the name
     * @param d the description
     * @param r the interest rate of the upgrade
     */
    public Interest(int i, int x, int y, int p, String n, String d, double r)
    {
        super(i, x, y, p, n, d);
        rate=r;
    }
    
    public double getInj()
    {
        return rate;
    }
}
