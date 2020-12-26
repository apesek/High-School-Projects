import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Board extends JPanel implements Runnable, MouseListener, MouseMotionListener,  ActionListener, KeyListener
{
    public static int WIDTH, HEIGHT;
    public Thread game;
    private Point mouseLocation;
    public Player player;
    private Tools tool;
    public ArrayList<Object> objects;
    boolean death;
    public int count;
    int immunityCounter;
    JLabel gameOverText;
    public Board(int width, int height)
    {
        //Creates JFrame dimentions
        this.setLayout( null);
        WIDTH = width; HEIGHT = height;
        this.setPreferredSize( new Dimension( WIDTH, HEIGHT ) ); 
        this.setBackground( new Color(0, 0, 0));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        gameOverText = new JLabel();
        this.add(gameOverText);
        gameOverText.setBounds(300, 200, 400, 400);
        gameOverText.setFont(new Font("Arial", Font.BOLD, 48));
        gameOverText.setForeground(new Color(0, 0, 0));
        gameOverText.setText("GAME OVER");
        gameOverText.setVisible(false);
        
        //Creates Game Thread
        game = new Thread(this);
        this.setFocusable(true);
        this.setVisible(true);
        player = new Player(100, 30);
        tool=new Tools();
        objects=new ArrayList<Object>();
        death=false;
        count=0;
        game.start();
        immunityCounter=0;
    }
    
    public void run ()
    {
        boolean running = true;
        //Each 167 ticks = 1 score
        //1-2: 0-100; 0-16,700
        while(running)
        {
            double inc = (count/16700)+1;
            
            int rand1 = (int)(Math.random()*(200/inc));
            int rand2 = (int)(Math.random()*(200/inc));
            int rand3 = (int)(Math.random()*(200/inc));
            int rand4 = (int)(Math.random()*(200/inc));
            if(rand1==0)
            {
                objects.add(new Object(0, (int)(Math.random()*9)*50 + 210, 0));
            }
            if(rand2==0)
            {
                objects.add(new Object(1000, (int)(Math.random()*9)*50 + 210, 180));
            }
            if(rand3==0)
            {
                objects.add(new Object((int)(Math.random()*9)*50 + 310, 0, 270));
            }
            if(rand4==0)
            {
                objects.add(new Object((int)(Math.random()*9)*50 + 310, 800, 90));
            }
            for(Object o: objects)
            {
                o.move(); 
                this.repaint();
            }
            for(int i=0; i<objects.size(); i++)
            {
                if(objects.get(i).getX() <= player.getX()+player.getS() && objects.get(i).getX()>=player.getX())
                {
                    if(objects.get(i).getY()<=player.getY()+player.getS() && objects.get(i).getY()>=player.getY())
                    {
                        if(objects.get(i).immunity())
                        {
                            player.setImmunity(true);
                            immunityCounter = 1000;
                            objects.remove(i);
                            i--;
                        }
                        if(objects.get(i).healable())
                        {
                            player.damage(-5);
                            objects.remove(i);
                            i--;
                        }
                        else
                        {
                            if(player.immune())
                            {
                                objects.remove(i);
                                i--;
                            }
                            else
                            {
                                player.damage(5);
                                objects.remove(i);
                                i--;
                            }
                        }
                    }
                }
                else if(objects.get(i).getX()<0 || objects.get(i).getX()>1000 || objects.get(i).getY()<0 || objects.get(i).getY()>800)
                {
                    objects.remove(i);
                    i--;
                }
                
            }
            this.repaint();
            tool.pause(5);
            
            if(player.getH()<=0)
            {
                death=true;
                while(true)
                {this.repaint();}
            }
            
            if(immunityCounter>0)
            {
                player.setImmunity(true);
                immunityCounter--;
            }
            else
            {
                player.setImmunity(false);
            }
            
            count++;
        }
            
    }
    
    public void mousePressed( MouseEvent event )
    {}
    
    public void mouseReleased( MouseEvent event )
    {}
    
    public void mouseClicked( MouseEvent event )
    {}
    
    public void mouseEntered( MouseEvent event )
    {}
    
    public void mouseExited( MouseEvent event )
    {}

    public void mouseDragged( MouseEvent e )
    {}
    
    public void mouseMoved( MouseEvent e )
    {}
    
    public void keyTyped( KeyEvent event )
    {}

    public void keyPressed( KeyEvent event )
    {}

    public void keyReleased( KeyEvent event )
    {
        if(event.getKeyCode() == KeyEvent.VK_RIGHT)
        {player.move(0);}
        else if(event.getKeyCode() == KeyEvent.VK_UP)
        {player.move(90);}
        else if(event.getKeyCode() == KeyEvent.VK_LEFT)
        {player.move(180);}
        else if(event.getKeyCode() == KeyEvent.VK_DOWN)
        {player.move(270);}
        else if(event.getKeyCode() == KeyEvent.VK_D)
        {player.move(0);}
        else if(event.getKeyCode() == KeyEvent.VK_W)
        {player.move(90);}
        else if(event.getKeyCode() == KeyEvent.VK_A)
        {player.move(180);}
        else if(event.getKeyCode() == KeyEvent.VK_S)
        {player.move(270);}
    }

    public void actionPerformed( ActionEvent event )
    {}
    
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page );
        for(int i=300; i<=700; i+=50)
        {
            for(int j=200; j<=600; j+=50)
            {
                page.setColor(Color.WHITE);
                page.fillRect(i-7, j-7, player.getS()+14, player.getS()+14);
                page.setColor(Color.BLACK);
                page.fillRect(i-5, j-5, player.getS()+10, player.getS()+10);
            }
        }
        page.setColor(Color.RED);
        if(player.immune())
        {page.setColor(Color.YELLOW);}
        page.fillRect(player.getX(), player.getY(), player.getS(), player.getS());
        page.setColor(Color.WHITE);
        for(Object o: objects)
        {
            if(o.healable())
            {
                page.setColor(Color.GREEN);
            }
            else if(o.immunity())
            {
                page.setColor(Color.YELLOW);
            }
            page.fillOval(o.getX(), o.getY(), 10, 10);
            page.setColor(Color.WHITE);
        }
        page.setColor(Color.GREEN);
        page.fillRect(450, 660, 100, 20);
        page.setColor(Color.RED);
        page.fillRect(550 - (100-player.getH()), 660, (100-player.getH()), 20);
        page.setColor(Color.WHITE);
        page.drawString("Score: "+(count/(1000/6)), 100, 100);
        if(death)
        {
            int fScore= count/(1000/6);
            page.setColor(Color.RED);
            page.fillRect(0, 0, 1000, 800);
            gameOverText.setVisible(true);
            page.setColor(Color.BLACK);
            page.drawString("Final Score: "+ fScore, 100, 100);
           
        }
    }

}