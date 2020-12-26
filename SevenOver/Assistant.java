public class Assistant extends Upgrade
{
    /**Constructor for generator object of upgrade class
     * @param i the index of the upgrade
     * @param x the xLoc of the upgrade on the screen
     * @param y the yLoc of the upgrade on the screen
     * @param p the price of the upgrade
     * @param n the name
     * @param d the description
     */
    public Assistant(int i, int x, int y, int p, String n, String d)
    {
        super(i, x, y, p, n, d);
    }
    
    public double getInj()
    {
        return 0;
    }
}
