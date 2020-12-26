

public class MoneyPile
{
    public static long money;
    /**Constructor for MoneyPile object, stores money variable
     * @param m starting amount of money (might be able to change later)
     */
    public MoneyPile(int m)
    {
        money=(long)m;
    }
    
    /**Changes the amount of money the player has
     * @param c the amount to change how much money a player has (+# to add money, -# to subtract)
     */
    public static void changeMoney(int c)
    {
        money = money + (long)c;
    }
    
    /**Returns the amount of money a player has
     * @return money
     */
    public static long getMoney()
    {
        return money;
    }
    
    /**Sets an amount of money to the money variable
     * @param m the number money should be changed to.
     */
    public static void setMoney(long m)
    {
        money=m;
    }
}
