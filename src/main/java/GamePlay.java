import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private int[] yoshinakeXLength = new int[750];
    private int[] yoshinakeYLength = new int[750];

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private int yoshinakeLength = 3;

    private Timer timer;
    private int delay = 100;
    private ImageIcon yoshinakeImage;

    private ImageIcon titleImage;

    private int moves = 0;

    public GamePlay() {

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint (Graphics g) {

        if (moves == 0) {

            yoshinakeXLength[2] = 50;
            yoshinakeXLength[1] = 75;
            yoshinakeXLength[0] = 100;

            yoshinakeYLength[2] = 100;
            yoshinakeYLength[1] = 100;
            yoshinakeYLength[0] = 100;
        }

        // image border
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);

        // title image
        titleImage = new ImageIcon("assets/yoshisnaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //gameplay border
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 577);

        // gameplay background
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        rightMouth = new ImageIcon("assets/rightmouth.png");
        rightMouth.paintIcon(this, g, yoshinakeXLength[0], yoshinakeYLength[0]);

        for (int i=0; i < yoshinakeLength; i++) {

            if (i ==0 && right) {
                rightMouth = new ImageIcon("assets/rightmouth.png");
                rightMouth.paintIcon(this, g, yoshinakeXLength[i], yoshinakeYLength[i]);
            }if (i ==0 && left) {
                leftMouth = new ImageIcon("assets/leftmouth.png");
                leftMouth.paintIcon(this, g, yoshinakeXLength[i], yoshinakeYLength[i]);
            }if (i ==0 && up) {
                upMouth = new ImageIcon("assets/upmouth.png");
                upMouth.paintIcon(this, g, yoshinakeXLength[i], yoshinakeYLength[i]);
            }if (i ==0 && down) {
                downMouth = new ImageIcon("assets/downmouth.png");
                downMouth.paintIcon(this, g, yoshinakeXLength[i], yoshinakeYLength[i]);
            }if (i !=0) {
                yoshinakeImage = new ImageIcon("assets/yoshinakeimage.png");
                yoshinakeImage.paintIcon(this, g, yoshinakeXLength[i], yoshinakeYLength[i]);
            }
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if (right) {

            for (int j = yoshinakeLength-1; j >= 0; j--) {

                yoshinakeYLength[j+1] = yoshinakeYLength[j];

            }for (int j = yoshinakeLength; j >= 0; j--) {

                if (j==0) {

                    yoshinakeXLength[j] = yoshinakeXLength[j] + 25;

                }else {

                    yoshinakeXLength[j] = yoshinakeXLength[j-1];

                }if (yoshinakeXLength[j] > 850) {

                    yoshinakeXLength[j] = 25;
                }
            }
        }

        repaint();

        if (left) {

            for (int j = yoshinakeLength-1; j >= 0; j--) {

                yoshinakeYLength[j+1] = yoshinakeYLength[j];

            }for (int j = yoshinakeLength; j >= 0; j--) {

                if (j==0) {

                    yoshinakeXLength[j] = yoshinakeXLength[j] - 25;

                }else {

                    yoshinakeXLength[j] = yoshinakeXLength[j-1];

                }if (yoshinakeXLength[j] < 25) {

                    yoshinakeXLength[j] = 850;
                }
            }
        }

        repaint();

        if (up) {

            for (int j = yoshinakeLength-1; j >= 0; j--) {

                yoshinakeXLength[j+1] = yoshinakeXLength[j];

            }for (int j = yoshinakeLength; j >= 0; j--) {

                if (j==0) {

                    yoshinakeYLength[j] = yoshinakeYLength[j] - 25;

                }else {

                    yoshinakeYLength[j] = yoshinakeYLength[j-1];

                }if (yoshinakeYLength[j] < 75) {

                    yoshinakeYLength[j] = 625;
                }
            }
        }

        repaint();

        if (down) {

            for (int j = yoshinakeLength-1; j >= 0; j--) {

                yoshinakeXLength[j+1] = yoshinakeXLength[j];

            }for (int j = yoshinakeLength; j >= 0; j--) {

                if (j==0) {

                    yoshinakeYLength[j] = yoshinakeYLength[j] + 25;

                }else {

                    yoshinakeYLength[j] = yoshinakeYLength[j-1];

                }if (yoshinakeYLength[j] > 625) {

                    yoshinakeYLength[j] = 75;
                }


            }
        }

        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {

            moves++;
            right = true;
            if (!left) {

                right = true;

            }else {

                right = false;
                left = true;

            }
            up = false;
            down = false;

        }if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {

            moves++;
            left = true;
            if (!right) {

                left = true;

            }else {

                right = true;
                left = false;

            }
            up = false;
            down = false;

        }if (e.getExtendedKeyCode() == KeyEvent.VK_UP) {

            moves++;
            up = true;
            if (!down) {

                up = true;

            }else {

                up = false;
                down = true;

            }
            right = false;
            left = false;

        }if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {

            moves++;
            down = true;
            if (!up) {

                down = true;

            }else {

                up = true;
                down = false;

            }
            right = false;
            left = false;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
