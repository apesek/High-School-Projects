import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Playboard extends JPanel implements Runnable, MouseListener, MouseMotionListener, ActionListener, KeyListener
{
    public static int WIDTH, HEIGHT;

    public Thread game;

    JTextField betInput;

    private JLabel beginText;
    private JLabel narr;
    public static JLabel upDesc;

    private JButton contButton;
    private JButton startButton;
    private int beginSeqTrak=0;

    public int gamble;
    
    private boolean admin;

    private JButton buy1;
    private JButton buy2;
    private JButton buy3;
    private JButton buy4;
    private JButton buy5;
    private JButton buy6;
    private JButton buy7;
    private JButton buy8;
    
    ImageIcon image1; JLabel label1;
    ImageIcon image2; JLabel label2;
    ImageIcon image3; JLabel label3;
    ImageIcon image4; JLabel label4;
    ImageIcon image5; JLabel label5;
    ImageIcon image6; JLabel label6;
    ImageIcon image7; JLabel label7;
    ImageIcon image8; JLabel label8;

    ImageIcon image;
    JLabel labelPicture;
    
    JLabel disMoney;
    
    boolean autoPlay;
    boolean goodInput;
    boolean richMan;
    boolean richMan0;
    boolean partyMode;
    boolean scrambleMode;

    Dice TopDice;
    Dice BottomDice;

    ArrayList<Upgrade> upgrades;
    
    MoneyPile Money;

    private Point mouseLocation;

    /**Constructor for the Playboard class
     * @param width width of JFrame
     * @param height height of JFrame
     */
    public Playboard(int width, int height)
    {
        //Creates JFrame dimentions
        this.setLayout( null);
        WIDTH = width; HEIGHT = height;
        this.setPreferredSize( new Dimension( WIDTH, HEIGHT ) ); 
        this.setBackground( new Color(0, 0, 0));
        this.addMouseListener(this);

        //Creates space to enter bets
        betInput = new JTextField(10);
        this.add(betInput, BorderLayout.SOUTH);
        betInput.setBounds(430, 600, 140, 25);
        betInput.setVisible(false); betInput.setFocusable(true);

        //Creates the Start and Continue Button for the Beginning of the Game
        contButton = new JButton( "I Haven't Played Before" );
        contButton.setBounds( 400, 385, 200, 40 );
        this.add( contButton );
        contButton.addActionListener(this);
        
        //Creates a button that allows you to skip the tutorial
        startButton = new JButton( "I Have Played Before" );
        startButton.setBounds( 400, 435, 200, 40 );
        this.add( startButton );
        startButton.addActionListener(this);

        //Creates buttons used to buy upgrades
        buy1 = new JButton("Buy"); buy1.setBounds(710, 270, 80, 20);
        this.add(buy1); buy1.addActionListener(this);
        buy2 = new JButton("Buy"); buy2.setBounds(820, 270, 80, 20);
        this.add(buy2); buy2.addActionListener(this);
        buy3 = new JButton("Buy"); buy3.setBounds(710, 380, 80, 20);
        this.add(buy3); buy3.addActionListener(this);
        buy4 = new JButton("Buy"); buy4.setBounds(820, 380, 80, 20);
        this.add(buy4); buy4.addActionListener(this);
        buy5 = new JButton("Buy"); buy5.setBounds(710, 490, 80, 20);
        this.add(buy5); buy5.addActionListener(this);
        buy6 = new JButton("Buy"); buy6.setBounds(820, 490, 80, 20);
        this.add(buy6); buy6.addActionListener(this);
        buy7 = new JButton("Buy"); buy7.setBounds(710, 600, 80, 20);
        this.add(buy7); buy7.addActionListener(this);
        buy8 = new JButton("Buy"); buy8.setBounds(820, 600, 80, 20);
        this.add(buy8); buy8.addActionListener(this);
        buy1.setVisible(false); buy2.setVisible(false);
        buy3.setVisible(false); buy4.setVisible(false);
        buy5.setVisible(false); buy6.setVisible(false);
        buy7.setVisible(false); buy8.setVisible(false);
        buy1.setFocusable(false); buy2.setFocusable(false);
        buy3.setFocusable(false); buy4.setFocusable(false);
        buy5.setFocusable(false); buy6.setFocusable(false);
        buy7.setFocusable(false); buy8.setFocusable(false);

        //Creates the Text at the Beginning of the Game
        beginText = new JLabel();
        this.add( beginText );
        beginText.setBounds( 300, 300, 400, 100);
        beginText.setForeground(new Color(0, 255, 0));
        beginText.setVisible( true );
        beginText.setFont( new Font( "Arial", Font.BOLD, 38 ) );
        beginText.setText("$$$ SEVEN OVER $$$");

        //Creates the Narrative and Instructional Text
        narr = new JLabel();
        this.add( narr );
        narr.setBounds(200, 100, 600, 50);
        narr.setForeground(new Color(0, 50, 0));
        narr.setVisible(false);
        narr.setFont( new Font("Arial", Font.BOLD, 25));

        //Creates description for upgrades
        upDesc = new JLabel();
        this.add( upDesc );
        upDesc.setBounds(700, 550, 250, 200);
        upDesc.setForeground(new Color(150, 50, 150));
        upDesc.setVisible(false);
        upDesc.setFocusable(true);
        upDesc.setFont( new Font("Arial", Font.BOLD, 15));
        upDesc.setText(" ");

        //Creates JLabel to Display the Player's Money
        disMoney = new JLabel();
        this.add( disMoney );
        disMoney.setBounds(400, 275, 300, 20);
        disMoney.setForeground(new Color(0, 150, 0));
        disMoney.setVisible(false);
        disMoney.setFont( new Font( "Arial", Font.BOLD, 25));

        //Creates Money Pile Picture
        image = new ImageIcon("MoneyPile.jpeg");
        labelPicture = new JLabel();
        labelPicture.setIcon(image);
        this.add(labelPicture);
        labelPicture.setBounds(400, 100, 800, 600);
        labelPicture.setVisible(false);
        
        //Creates Pictures for the upgrades
        image1 = new ImageIcon("iPhone.jpeg");//iPhone
        label1 = new JLabel();
        label1.setIcon(image1);
        this.add(label1);
        label1.setBounds(710,210,80,80);
        label1.setVisible(false);
        image2 = new ImageIcon("Bank.jpeg");//Bank Account
        label2 = new JLabel();
        label2.setIcon(image2);
        this.add(label2);
        label2.setBounds(820,210,80,80);
        label2.setVisible(false);
        image3 = new ImageIcon("Insurance.png");//Insurance
        label3 = new JLabel();
        label3.setIcon(image3);
        this.add(label3);
        label3.setBounds(710,320,80,80);
        label3.setVisible(false);
        image4 = new ImageIcon("Assistant.jpeg");//Assistant
        label4 = new JLabel();
        label4.setIcon(image4);
        this.add(label4);
        label4.setBounds(820,320,80,80);
        label4.setVisible(false);
        image5 = new ImageIcon("Printer.jpeg");//Money Printer
        label5 = new JLabel();
        label5.setIcon(image5);
        this.add(label5);
        label5.setBounds(710,430,80,80);
        label5.setVisible(false);
        image6 = new ImageIcon("Oil.jpeg");//Oil Indusry
        label6 = new JLabel();
        label6.setIcon(image6);
        this.add(label6);
        label6.setBounds(820,430,80,80);
        label6.setVisible(false);
        image7 = new ImageIcon("Gov.jpeg");//Somalian Gov
        label7 = new JLabel();
        label7.setIcon(image7);
        this.add(label7);
        label7.setBounds(710,540,80,80);
        label7.setVisible(false);
        image8 = new ImageIcon("Africa.jpeg");//African Econ
        label8 = new JLabel();
        label8.setIcon(image8);
        this.add(label8);
        label8.setBounds(820,540,80,80);
        label8.setVisible(false);

        //Creates Dice
        TopDice = new Dice(100, 300);
        BottomDice = new Dice(100, 425);

        //Creates Upgrades
        upgrades = new ArrayList<Upgrade>();
        upgrades.add( new Generator(0, 700, 200, 100, "iPhone", "<html>iPhone:$100<br>Gives you $20 per turn.</html>", 20));
        upgrades.add( new Interest(1, 810, 200, 338, "Bank Account", "<html>Bank Account:$338<br>Gain 15% of your money every turn</html>", 0.15));
        upgrades.add( new Insurance(2, 700, 310, 765, "Insurance", "<html>Insurance:$765<br>2nd chance on dice rolls avaliable every 5 turns.</html>"));
        upgrades.add( new Assistant(3, 810, 310, 1468, "Assistant", "<html>Assistant:$1,468<br>Greater chance of rolling 7 over.</html>"));
        upgrades.add( new Generator(4, 700, 420, 4659, "Money Printer", "<html>Money Printer:$4,659<br>Gives you $2,000 per turn.</html>", 2000));
        upgrades.add( new Generator(5, 810, 420, 20480, "Oil Industry", "<html>Oil Industry:20,480<br>Gives you 10,000 per turn.</html>", 10000));
        upgrades.add( new Interest(6, 700, 530, 75405, "Somalian Gov.", "<html>Somalian Government:$75,405<br>Gain 35% of your money back every turn.</html>", 0.35));
        upgrades.add( new Generator(7, 810, 530, 319434, "Africa Econ.", "<html>Africa's Economy:$319,434<br>Gives you $100,000 per turn.</html>", 100000));

        //Sets initial gamble to 0
        gamble=0;
        
        autoPlay=false;
        goodInput=false;
        richMan=false;
        richMan0=true;
        partyMode=false;
        scrambleMode=false;
        
        //Sets admin access to be false initially
        admin=false;
        
        //Creates MoneyPile
        Money = new MoneyPile(50);

        //Implements listeners to this
        this.addMouseListener(this);
        betInput.addKeyListener(this);
        this.addMouseMotionListener(this);

        //Creates Game Thread
        game = new Thread(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    /**This is the run method
     */
    public void run()
    {
        gamble=0; //Set initially to 0
        disMoney.setText("$"+Money.getMoney()); //Sets the JLabel to display the player's money
        this.repaint();
        boolean initializeInsurance=false; //This is to ensure the correct counting of turns when the insurance upgrade is owned
        int turnCount=-1; //This is a turn counter, it only starts when the insurance upgrade is bought
        
        
        
        /* Since the game can go on forever, ech element of each turn is contained within
         * this while loop. 
         */
        while(true)
        {
            gamble=0; //Resets the gamble to zero at the start of each turn
            goodInput=false;
            if(initializeInsurance)
            {
                turnCount++;
            }
            narr.setText("Please enter your bet in the text box below.");
            while(goodInput==false) //This block of code will repeat until a valid gamble has been entered
            {
                if(gamble>Money.getMoney())
                {
                    narr.setText("<html>You've bet more than what you have,<br>Please re-enter your bet.</html>");
                    
                }
                else if(gamble<=0)
                {}
                else
                {
                    goodInput=true;
                }
                this.repaint();
                if(autoPlay)
                {gamble=1; goodInput=true;}
                Tools.pause(3);
            }

            Money.changeMoney(gamble*-1); //Subtracts the gamble from the player's money
            this.repaint();
            
            /**If the player has bought the upgrade insurance, this will start the turn counter,
             * it will not execute this block of code if the turn counter has already began.
             */
            if(upgrades.get(4).owned() && initializeInsurance==false) 
            {
                initializeInsurance=true;
                turnCount=1;
            }
            
            /**If the assistant upgrade is owned, the dice have a 67% chance of rolling 7+,
             * if not, the chance of rolling 7+ is 50%.
             */
            if(upgrades.get(3).owned())//Assistant Owned, 67% chance 7+
            {
                TopDice.rollUpgradedDice();
                BottomDice.rollUpgradedDice();
            }
            else//Assistant Not Owned, 50% chance of 7+
            {
                TopDice.rollDice(); 
                BottomDice.rollDice();
            }
            
            this.repaint();
            int theRoll =TopDice.getRoll()+BottomDice.getRoll(); //Adds up the two dice rolls
            
            Tools.pause(1500);
            narr.setText("You rolled: "+ theRoll); this.repaint(); //tells user what they rolled
            Tools.pause(1500);
            
            /*If the turn counter is greater than 5, and the dice roll is under 7, then the 
             * dice will be rolled again.
             */
            if(theRoll<7 && initializeInsurance && turnCount>=5) 
            {
                narr.setText("<html>You didn't roll 7-Over, but Insurrance gives you <br> a 2nd roll!</html>");
                Tools.pause(1500);
                
                /**If the assistant upgrade is owned, the dice have a 67% chance of rolling 7+,
                 * if not, the chance of rolling 7+ is 50%.
                 */
                if(upgrades.get(3).owned())//Assistant Owned
                {
                    TopDice.rollUpgradedDice();
                    BottomDice.rollUpgradedDice();
                }   
                else//Assistant Not Owned
                {
                    TopDice.rollDice(); 
                    BottomDice.rollDice();
                }
                theRoll =TopDice.getRoll()+BottomDice.getRoll();
                this.repaint();
                Tools.pause(1000);
                narr.setText("You rolled: "+ theRoll); this.repaint();
                Tools.pause(1500);
                turnCount=0;
            }
            
            /**If the roll is greater then seven, they will get double back what they put in,
             * if not, they have essentialy lost the money they put in.
             */
            if(theRoll>=7)
            {
                narr.setText("Nice Job! You gained: $"+(gamble*2));
                Money.changeMoney(gamble*2);
                disMoney.setText("$"+Money.getMoney());
            }
            else
            {
                narr.setText("Oh No! You lost: $"+gamble);
                disMoney.setText("$"+Money.getMoney());
            }
            TopDice.resetRoll(); BottomDice.resetRoll(); //Resets each dice roll to 0
            this.repaint();
            Tools.pause(1750);
            
            if(richMan0&&upgrades.get(0).owned()&&upgrades.get(1).owned()&&upgrades.get(2).owned()&&upgrades.get(3).owned()&&upgrades.get(4).owned()&&upgrades.get(5).owned()&&upgrades.get(6).owned()&&upgrades.get(7).owned())
            {
                richMan=true;
                richMan0=false;
                narr.setText("Congradulations!!! You are now rich!!!");
                Tools.pause(3000);
                narr.setText("<html>There are some secrets to be discovered,<br>see if you can find them all!</html>");
                Tools.pause(5000);
            }
            
            /*These blocks of code give the player money based on which money generating upgrades they own*/
            if(upgrades.get(0).owned())//iPhone
            {
                Money.changeMoney((int)upgrades.get(0).getInj());
                narr.setText("+$20 from your iPhone!");
                this.repaint();
                Tools.pause(750);
            }
            if(upgrades.get(1).owned())//Bank Account
            {
                Money.changeMoney((int)(Money.getMoney()*0.05));
                narr.setText("+$"+((int)(Money.getMoney()*0.05))+" from your Bank Account!");
                this.repaint();
                Tools.pause(750);
            }
            if(upgrades.get(4).owned())//Money Printer
            {
                Money.changeMoney((int)upgrades.get(4).getInj());
                narr.setText("+$10,000 from your Money Printer!");
                this.repaint();
                Tools.pause(750);
            }
            if(upgrades.get(5).owned())//Oil Industry
            {
                Money.changeMoney((int)upgrades.get(5).getInj());
                narr.setText("+$100,000 from the Oil Industry!");
                this.repaint();
                Tools.pause(750);
            }
            if(upgrades.get(6).owned())//Somalian Gov
            {
                Money.changeMoney((int)(Money.getMoney()*0.15));
                narr.setText("+$"+((int)(Money.getMoney()*0.15))+" from the Somalian Government!");
                this.repaint();
                Tools.pause(750);
            }
            if(upgrades.get(7).owned())//Africa Econ.
            {
                Money.changeMoney((int)upgrades.get(7).getInj());
                narr.setText("+$1,000,000 from Africa's Economy!");
                this.repaint();
                Tools.pause(750);
            }
            
            /**If the player runs out of money, a game over text will be displayed and an
             * infinite loop will keep the program from running until the player closes the game.
             */
            if(Money.getMoney()<=0)
            {
                narr.setText("Oh No! You ran out of money!");
                Tools.pause(1500);
                narr.setText("GAME OVER");
                while(true)
                {}
            }
        }
    }

    /**Returns the JLabel for the upgrade description space
     * @returns JLAbel upDesc
     */
    public static JLabel getUpDesc()
    {
        return upDesc;
    }
    
    
    /*These methods don't have a purpose at the moment, but they may have one later.*/
    public void mousePressed( MouseEvent event ){}
    
    public void mouseReleased( MouseEvent event ){}
    
    public void mouseClicked( MouseEvent event ){}
    
    public void mouseEntered( MouseEvent event ){}
    
    public void mouseExited( MouseEvent event ){}

    /**Returns the point of the mouse's location
     * @returns Point mouse location
     */
    public Point getMouseLocation()
    {
        return mouseLocation;
    }

    public void mouseDragged( MouseEvent e )
    {
        mouseLocation = e.getPoint();
    }
    

    /**This method will test if the mouse location is over one of the upgrades displayed on the interface, the game will
     * display the description of whichever upgrade the user has their mouse on top of.
     * @param e the location of the mouse
     */
    public void mouseMoved( MouseEvent e )
    {
        mouseLocation = e.getPoint();
        if(mouseLocation.getX()>700 && mouseLocation.getX()<800 && mouseLocation.getY()>200 && mouseLocation.getY()<300)
        {
            upgrades.get(0).setDescriptionVar(true);
            this.repaint();
        }
        else if(mouseLocation.getX()>810 && mouseLocation.getX()<910 && mouseLocation.getY()>200 && mouseLocation.getY()<300)
        {
            upgrades.get(1).setDescriptionVar(true);
            this.repaint();
        }
        else if(mouseLocation.getX()>700 && mouseLocation.getX()<800 && mouseLocation.getY()>310 && mouseLocation.getY()<410)
        {
            upgrades.get(2).setDescriptionVar(true);
            this.repaint();
        }
        else if(mouseLocation.getX()>810 && mouseLocation.getX()<910 && mouseLocation.getY()>310 && mouseLocation.getY()<410)
        {
            upgrades.get(3).setDescriptionVar(true);
            this.repaint();
        }
        else if(mouseLocation.getX()>700 && mouseLocation.getX()<800 && mouseLocation.getY()>420 && mouseLocation.getY()<520)
        {
            upgrades.get(4).setDescriptionVar(true);
            this.repaint();
        }
        else if(mouseLocation.getX()>810 && mouseLocation.getX()<910 && mouseLocation.getY()>420 && mouseLocation.getY()<520)
        {
            upgrades.get(5).setDescriptionVar(true);
            this.repaint();
        }
        else if(mouseLocation.getX()>700 && mouseLocation.getX()<800 && mouseLocation.getY()>530 && mouseLocation.getY()<630)
        {
            upgrades.get(6).setDescriptionVar(true);
            this.repaint();
        }
        else if(mouseLocation.getX()>810 && mouseLocation.getX()<910 && mouseLocation.getY()>530 && mouseLocation.getY()<630)
        {
            upgrades.get(7).setDescriptionVar(true);
            this.repaint();
        }
        else
        {
            for(int i=0;i<8;i++)
            {
                upgrades.get(i).setDescriptionVar(false);
            }
            this.repaint(); 
        }

    }

    public void keyTyped( KeyEvent event ){}

    public void keyPressed( KeyEvent event ){}

    public void keyReleased( KeyEvent event )
    {
        if( event.getKeyCode() == KeyEvent.VK_ENTER )
        {
            boolean intInput = true; //Will determines if the input is an integer or something else
            String temp = betInput.getText();
            betInput.setText("");
            //Grants admin access to special inputs in the text field to test gameplay.
            if(temp.equals("admin"))
            {admin=true; intInput=false;}
            /**Tests admin access(or Rich Man), will skip input tests for whats listed here if false
             */
            if(admin||richMan)
            {
                if(temp.equals("autoPlay"))
                {
                    if(autoPlay)
                    {autoPlay=false;}
                    else
                    {autoPlay=true;}
                    intInput=false;
                }
                else if(temp.equals("party"))
                {
                    if(partyMode)
                    {partyMode=false;}
                    else
                    {partyMode=true;}
                    intInput=false;
                }
                else if(temp.equals("scramble"))
                {
                    if(scrambleMode)
                    {scrambleMode=false;}
                    else
                    {scrambleMode=true;}
                    intInput=false;
                }
                else if(temp.equals("give100"))
                {Money.changeMoney(100); intInput=false;}
                else if(temp.equals("millionare"))
                {Money.changeMoney(1000000); intInput=false;}
                else if(temp.equals("billionare"))
                {Money.changeMoney(1000000000); intInput=false;}
                else if(temp.equals("up0true"))
                {upgrades.get(1).setOwnership(true); intInput=false;}
                else if(temp.equals("up1true"))
                {upgrades.get(1).setOwnership(true); intInput=false;}
                else if(temp.equals("up2true"))
                {upgrades.get(2).setOwnership(true); intInput=false;}
                else if(temp.equals("up3true"))
                {upgrades.get(3).setOwnership(true); intInput=false;}
                else if(temp.equals("up4true"))
                {upgrades.get(4).setOwnership(true); intInput=false;}
                else if(temp.equals("up5true")) 
                {upgrades.get(5).setOwnership(true); intInput=false;}
                else if(temp.equals("up6true"))
                {upgrades.get(6).setOwnership(true); intInput=false;}
                else if(temp.equals("up7true"))
                {upgrades.get(7).setOwnership(true); intInput=false;}
                else if(temp.equals("allUpgrades"))
                {
                    for(int i=0; i<8; i++)
                    {
                        upgrades.get(i).setOwnership(true); 
                    }
                    intInput=false;
                }
                
            }
            
            /**If an interger input has no been declared false, this block of code will be executed.
             */
            if(intInput)
            {
                try
                {
                    gamble = Integer.parseInt( temp );
                    
                    
                }
                catch(java.lang.IllegalThreadStateException ex)
                {
                    if(admin=false)
                    {
                        narr.setText("Please enter an actual number");
                        Tools.pause(2000);
                        narr.setText("Please enter your bet in the text box below");
                    }
                }
            }
            
            this.repaint();
        }

    }

    public void actionPerformed( ActionEvent event )
    {
        /**If a player has not played the game before, it will show them a series of descriptions about how
         * the game works, when they have read one page, a button will allow them to continue to the next page, and once
         * they have pressed the continue button on the final page the game will start. If they know how to play, the game will
         * start.
         */
        if( (event.getSource() == contButton ) )
        {
            if(beginSeqTrak==0)
            {
                startButton.setVisible(false);
                startButton.setFocusable(false);
                
                beginText.setBounds( 100, 100, 800, 100);
                beginText.setFont( new Font( "Arial", Font.BOLD, 18 ) );

                contButton.setBounds(100, 200, 150, 30);
                contButton.setText("Continue");

                beginText.setText("Hello player, welcome to Seven-Over.");
                beginSeqTrak++;
            }
            else if(beginSeqTrak==1)
            {
                beginText.setText("In this game, you start with $50.");
                beginSeqTrak++;
            }
            else if(beginSeqTrak==2)
            {
                beginText.setText("<html>You have to then bet money, and if the dice roll is 7 or greater,<br>then you get double your money back.</html>");
                beginSeqTrak++;
            }
            else if(beginSeqTrak==3)
            {
                beginText.setText("If not, then you lose that money that you put in.");
                beginSeqTrak++;
            }
            else if(beginSeqTrak==4)
            {
                beginText.setText("You can also buy upgrades along the way to make it easier to earn money.");
                beginSeqTrak++;
            }
            else if(beginSeqTrak==5)
            {
                beginText.setText("Be careful though, because if you lose all your money, the game ends. Good Luck!");
                beginSeqTrak++;
            }
            else if(beginSeqTrak==6)
            {
                beginSeqTrak=-1;
                beginText.setVisible(false);
                this.setBackground(new Color(5, 250, 5));

                contButton.setVisible(false);
                contButton.setFocusable(false);

                narr.setVisible(true);

                labelPicture.setVisible(true);
                disMoney.setVisible(true);

                upDesc.setVisible(true);

                betInput.setVisible(true);

                buy1.setVisible(true); buy2.setVisible(true);
                buy3.setVisible(true); buy4.setVisible(true);
                buy5.setVisible(true); buy6.setVisible(true);
                buy7.setVisible(true); buy8.setVisible(true);
                buy1.setFocusable(true); buy2.setFocusable(true);
                buy3.setFocusable(true); buy4.setFocusable(true);
                buy5.setFocusable(true); buy6.setFocusable(true);
                buy7.setFocusable(true); buy8.setFocusable(true);
                
                label1.setVisible(true); label2.setVisible(true);
                label3.setVisible(true); label4.setVisible(true);
                label5.setVisible(true); label6.setVisible(true);
                label7.setVisible(true); label8.setVisible(true);

                game.start();

            }
        }
        
        if(event.getSource() == startButton)
        {
            
            beginSeqTrak=-1;
            beginText.setVisible(false);
            this.setBackground(new Color(5, 250, 5));

            contButton.setVisible(false);
            contButton.setFocusable(false);
            startButton.setVisible(false);
            startButton.setFocusable(false);

            narr.setVisible(true);

            labelPicture.setVisible(true);
            disMoney.setVisible(true);
            
            upDesc.setVisible(true);

            betInput.setVisible(true);

            buy1.setVisible(true); buy2.setVisible(true);
            buy3.setVisible(true); buy4.setVisible(true);
            buy5.setVisible(true); buy6.setVisible(true);
            buy7.setVisible(true); buy8.setVisible(true);
            buy1.setFocusable(true); buy2.setFocusable(true);
            buy3.setFocusable(true); buy4.setFocusable(true);
            buy5.setFocusable(true); buy6.setFocusable(true);
            buy7.setFocusable(true); buy8.setFocusable(true);
            
            label1.setVisible(true); label2.setVisible(true);
            label3.setVisible(true); label4.setVisible(true);
            label5.setVisible(true); label6.setVisible(true);
            label7.setVisible(true); label8.setVisible(true);

            game.start();
        }

        
        /*These blocks of code will test if the user has enough money to buy the upgrade corresponding to the 'buy'
         * button they have pressed, if they do, that amount of money will be subtracted from the money pile, and the upgrade will be
         * set 'true' for ownership. If they do not have enough money, the button will not do anything.
         */
        if( event.getSource()==buy1)
        {
            if((long)upgrades.get(0).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(0).getPrice())*-1);
                upgrades.get(0).setOwnership(true);
                buy1.setVisible(false); buy1.setFocusable(false);
            }
        }

        if( event.getSource()==buy2)
        {
            if((long)upgrades.get(1).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(1).getPrice())*-1);
                upgrades.get(1).setOwnership(true);
                buy2.setVisible(false); buy2.setFocusable(false);
            }
        }

        if( event.getSource()==buy3)
        {
            if((long)upgrades.get(2).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(2).getPrice())*-1);
                upgrades.get(2).setOwnership(true);
                buy3.setVisible(false); buy3.setFocusable(false);
            }
        }

        if( event.getSource()==buy4)
        {
            if((long)upgrades.get(3).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(3).getPrice())*-1);
                upgrades.get(3).setOwnership(true);
                buy4.setVisible(false); buy4.setFocusable(false);
            }
        }

        if( event.getSource()==buy5)
        {
            if((long)upgrades.get(4).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(4).getPrice())*-1);
                upgrades.get(4).setOwnership(true);
                buy5.setVisible(false); buy5.setFocusable(false);
            }
        }

        if( event.getSource()==buy6)
        {
            if((long)upgrades.get(5).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(5).getPrice())*-1);
                upgrades.get(5).setOwnership(true);
                buy6.setVisible(false); buy6.setFocusable(false);
            }
        }

        if( event.getSource()==buy7)
        {
            if((long)upgrades.get(6).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(6).getPrice())*-1);
                upgrades.get(6).setOwnership(true);
                buy7.setVisible(false); buy7.setFocusable(false);
            }
        }

        if( event.getSource()==buy8)
        {
            if((long)upgrades.get(7).getPrice()<MoneyPile.getMoney())
            {
                MoneyPile.changeMoney((upgrades.get(7).getPrice())*-1);
                upgrades.get(7).setOwnership(true);
                buy8.setVisible(false); buy8.setFocusable(false);
            }
        }
    }
    
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );
        if(beginSeqTrak==-1)
        {
            TopDice.draw(page);
            BottomDice.draw(page);
            for(Upgrade upgrade:upgrades)
            {
                upgrade.draw(page);
            }
            disMoney.setText("$"+Money.getMoney());
            if(autoPlay)
            {
                page.setColor(Color.BLACK);
                page.drawString( "Game set to auto-play.", 100, 700);
            }
            if(partyMode)
            {
                this.setBackground(new Color(Tools.random(155)+100, Tools.random(155)+100, Tools.random(155)+100));
                narr.setForeground(new Color(Tools.random(100), Tools.random(100), Tools.random(100)));
                upDesc.setForeground(new Color(Tools.random(100), Tools.random(100), Tools.random(100)));
                disMoney.setForeground(new Color(Tools.random(100), Tools.random(100), Tools.random(100)));
            }
            if(scrambleMode)
            {
                narr.setBounds(Tools.random(400), Tools.random(750), 600, 50);
                upDesc.setBounds(Tools.random(750), Tools.random(600), 250, 200);
                disMoney.setBounds(Tools.random(700), Tools.random(780), 300, 20);
                label1.setBounds(Tools.random(920), Tools.random(720),80,80);
                label2.setBounds(Tools.random(920), Tools.random(720),80,80);
                label3.setBounds(Tools.random(920), Tools.random(720),80,80);
                label4.setBounds(Tools.random(920), Tools.random(720),80,80);
                label5.setBounds(Tools.random(920), Tools.random(720),80,80);
                label6.setBounds(Tools.random(920), Tools.random(720),80,80);
                label7.setBounds(Tools.random(920), Tools.random(720),80,80);
                label8.setBounds(Tools.random(920), Tools.random(720),80,80);
            }
        }
    }
}
