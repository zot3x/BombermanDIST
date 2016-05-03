/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BombeLogik;

import brugerautorisation.transport.soap.Bruger;
import brugerautorisation.transport.soap.Brugeradmin;
import brugerautorisation.transport.soap.BrugeradminImplService;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

public class BombeStart extends Applet implements Runnable, KeyListener {

    static final int HEIGHT = 800;
    static final int WIDTH = 650;
    static final String Title = "Bombeman";
    private final int speed = 2;
    private Image i;
    private Graphics tempI;
    Button logBut = new Button("Login");
    Button HighS = new Button("Se highscore");
    TextField logText = new TextField();
    TextField pass = new TextField();
    Button checkLog = new Button("Login");
    Bruger logIn;
    boolean check = false;
    Label wrongC = new Label("WRONG PASSWORD OR USER, TRY AGAIN");
    Button START = new Button("START NY KAMP");
    private final int playerCount = 4;
    Accounts[] gamers = new Accounts[4];
    walls[] walltop = new walls[17];
    walls[] wallbot = new walls[17];
    walls[] wallleft = new walls[12];
    walls[] wallright = new walls[12];
    walls[] wallsmid = new walls[35];
    int[] startX = new int[4];
    int[] startY = new int[4];
    int dx, dy;
    randomBoxes[] randBox = new randomBoxes[105];
    Point[] bombposA = new Point[130];
    ArrayList<Bombs> laidbombs = new ArrayList<>();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (state == state.PLAYING) {
            Accounts tempA = new Accounts(gamers[0].getX(), gamers[0].getY(), 2);
            boolean goodMove = true;
            switch (e.getKeyCode()) {

                case KeyEvent.VK_A:

                    dx = -speed;
                    tempA.setX(tempA.getX() + dx);

                    for (int i = 0; i < wallsmid.length; i++) {
                        boolean validMove = validMove(tempA, wallsmid[i]);

                        if (validMove == false) {
                            goodMove = false;
                        }

                    }

                    for (int i = 0; i < randBox.length; i++) {
                        boolean validMove = true;
                        if (randBox[i].getSprunger() == false) {
                            validMove = validMoveRand(tempA, randBox[i]);
                        }
                        if (validMove == false) {

                            goodMove = false;
                        }

                    }

                    if (goodMove == true) {

                        if (wallleft[0].getX() + 40 < tempA.getX() + dx) {
                            gamers[0].setX(tempA.getX());
                        }
                    }
                    break;
                case KeyEvent.VK_D:

                    dx = speed;

                    tempA.setX(tempA.getX() + dx);

                    for (int i = 0; i < wallsmid.length; i++) {
                        boolean validMove = validMove(tempA, wallsmid[i]);

                        if (validMove == false) {
                            goodMove = false;
                        }

                    }

                    for (int i = 0; i < randBox.length; i++) {
                        boolean validMove = true;
                        if (randBox[i].getSprunger() == false) {
                            validMove = validMoveRand(tempA, randBox[i]);
                        }
                        if (validMove == false) {

                            goodMove = false;
                        }

                    }

                    if (goodMove == true) {
                        if (wallright[2].getX() > tempA.getX() + dx + 30) {
                            gamers[0].setX(tempA.getX());
                        }
                    }

                    break;
                case KeyEvent.VK_W:
                    dy = -speed;

                    tempA.setY(tempA.getY() + dy);

                    for (int i = 0; i < wallsmid.length; i++) {
                        boolean validMove = validMove(tempA, wallsmid[i]);

                        if (validMove == false) {
                            goodMove = false;
                        }

                    }

                    for (int i = 0; i < randBox.length; i++) {
                        boolean validMove = true;
                        if (randBox[i].getSprunger() == false) {
                            validMove = validMoveRand(tempA, randBox[i]);
                        }
                        if (validMove == false) {

                            goodMove = false;
                        }

                    }

                    if (goodMove == true) {
                        if (gamers[0].getY() > walltop[2].getY() + 40) {
                            gamers[0].setY(tempA.getY());
                        }
                    }

                    break;
                case KeyEvent.VK_S:
                    dy = speed;

                    tempA.setY(tempA.getY() + dy);

                    for (int i = 0; i < wallsmid.length; i++) {
                        boolean validMove = validMove(tempA, wallsmid[i]);

                        if (validMove == false) {
                            goodMove = false;
                        }

                    }

                    for (int i = 0; i < randBox.length; i++) {
                        boolean validMove = true;
                        if (randBox[i].getSprunger() == false) {
                            validMove = validMoveRand(tempA, randBox[i]);
                        }
                        if (validMove == false) {

                            goodMove = false;
                        }

                    }

                    if (goodMove == true) {
                        if (gamers[0].getY() + 30 < wallbot[2].getY()) {
                            gamers[0].setY(tempA.getY());
                        }
                    }
                    
                    break;
                case KeyEvent.VK_SPACE:
                    setBomb(gamers[0]);

                    
                    break;

                default:
                    break;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public enum State {
        MENU, PLAYING, DEATH, LOGIN, HIGHSCORE, LOGGEDIN
    }

    State state;

    @Override
    public void init() {
        setSize(HEIGHT, WIDTH);
        setFocusable(true);
        addKeyListener(this);
        state = state.MENU;
        add(logBut);
        add(HighS);
        add(logText);
        add(pass);
        add(checkLog);
        add(wrongC);
        add(START);

        startX[0] = 80;
        startX[1] = 640;
        startX[2] = 80;
        startX[3] = 640;
        startY[0] = 140;
        startY[1] = 140;
        startY[2] = 540;
        startY[3] = 540;

        setBombpos();

        START.setBounds(500, 400, 100, 100);
        START.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = state.PLAYING;

            }
        });

        START.setVisible(false);
        wrongC.setVisible(false);
        logBut.setVisible(false);
        HighS.setVisible(false);
        logText.setVisible(false);
        pass.setVisible(false);
        checkLog.setVisible(false);

        logText.setBounds(300, 550, 80, 30);
        pass.setBounds(400, 550, 80, 30);
        checkLog.setBounds(360, 600, 60, 30);

        checkLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                check = true;
                BrugeradminImplService service = new BrugeradminImplService();
                Brugeradmin port = service.getBrugeradminImplPort();

                logIn = port.hentBruger(logText.getText(), pass.getText());

                if (logIn.getEfternavn() != null) {
                    state = state.LOGGEDIN;
                }

            }

        });

    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        logBut.setBackground(Color.red);
        HighS.setBackground(Color.red);
        logBut.setBounds(300, 600, 100, 30);
        HighS.setBounds(420, 600, 100, 30);
        HighS.setVisible(true);
        logBut.setVisible(true);
        logBut.addActionListener((ActionEvent e) -> {
            state = state.LOGIN;
            HighS.setVisible(false);
            logBut.setVisible(false);
        });

        HighS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = state.HIGHSCORE;
                HighS.setVisible(false);
                logBut.setVisible(false);

            }
        });

        for (int i = 0; i < playerCount; i++) {
            gamers[i] = new Accounts(startX[i], startY[i], i);
        }
        for (int i = 0; i < 17; i++) {
            walltop[i] = new walls(40 + (i * 40), 100, 40, 40);
            wallbot[i] = new walls(40 + (i * 40), 580, 40, 40);
        }
        for (int i = 0; i < 12; i++) {
            wallleft[i] = new walls(40, 140 + (40 * i), 40, 40);
            wallright[i] = new walls(680, 140 + (40 * i), 40, 40);
        }
        int cW = 0;

        for (int j = 0; j < 5; j++) {
            for (int k = 0; k < 7; k++) {
                wallsmid[cW] = new walls(120 + (k * 80), 180 + (j * 80), 40, 40);
                cW++;
            }

        }
        int cRB = 0;

        for (int i = 0; i < 11; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
                for (int j = 0; j < 11; j++) {
                    randBox[cRB] = new randomBoxes(160 + (j * 40), 140 + (i * 40));
                    cRB++;
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    randBox[cRB] = new randomBoxes(160 + (j * 80), 140 + (i * 40));
                    cRB++;
                }
            }
        }
        for (int i = 0; i < 7; i++) {
            randBox[cRB] = new randomBoxes(startX[0], startY[0] + 80 + (i * 40));
            cRB++;
            randBox[cRB] = new randomBoxes(startX[1], startY[1] + 80 + (i * 40));
            cRB++;

        }

        while (true) {

            for (int i = 0; i < laidbombs.size(); i++) {
                if (laidbombs.get(i).getbombstatus() == true) {
                    laidbombs.get(i).setTimeBomb();
                    laidbombs.get(i).bombBoom(bombposA,randBox);
                }
                if (laidbombs.get(i).getbombstatus() == false) {
                    laidbombs.remove(i);
                    i = i - 1;
                    

                }
            }

            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                Logger.getLogger(BombeStart.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public void stop() {
    }

    /*
    Får billede til at se flydende ud.
     */
    @Override
    public void update(Graphics g) {
        if (i == null) {
            i = createImage(this.getSize().width, this.getSize().height);
            tempI = i.getGraphics();
        }

        tempI.setColor(getBackground());
        tempI.fillRect(0, 0, this.getSize().width, this.getSize().height);

        tempI.setColor(getForeground());
        paint(tempI);

        g.drawImage(i, 0, 0, this);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon im = new ImageIcon("background.png");
        Image BI = im.getImage();
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, BI.getWidth(this), 50);
        g2d.setColor(Color.white);
        String tittle = "BOMBEMAN AF ANWAR,NICKLAS,FAYAD OG HADI - DIST PROJECT";

        g2d.drawString(tittle, 200, 30);

        switch (state) {

            case MENU:

                g2d.drawImage(BI, 0, 50, this);

                break;
            case LOGIN:
                if (check == true) {
                    wrongC.setVisible(true);
                }

                g2d.drawImage(BI, 0, 50, this);

                logText.setVisible(true);
                pass.setVisible(true);
                checkLog.setVisible(true);

                break;
            case PLAYING:
                START.setVisible(false);

                ImageIcon PI = new ImageIcon("C:/Users/AnwarC/Desktop/asphalt.jpg");
                Image BPI = PI.getImage();
                g2d.drawImage(BPI, 0, 50, this);
                for (int i = 0; i < playerCount; i++) {
                    g2d.drawImage(gamers[i].getIcon(), gamers[i].getX(), gamers[i].getY(), this);
                }
                for (int i = 0; i < 17; i++) {
                    g2d.drawImage(walltop[i].getImg(), walltop[i].getX(), walltop[i].getY(), this);
                }
                for (int i = 0; i < 12; i++) {
                    g2d.drawImage(wallleft[i].getImg(), wallleft[i].getX(), wallleft[i].getY(), this);
                }
                for (int i = 0; i < 17; i++) {
                    g2d.drawImage(wallbot[i].getImg(), wallbot[i].getX(), wallbot[i].getY(), this);
                }
                for (int i = 0; i < 12; i++) {
                    g2d.drawImage(wallright[i].getImg(), wallright[i].getX(), wallright[i].getY(), this);
                }
                for (int i = 0; i < wallsmid.length; i++) {
                    g2d.drawImage(wallsmid[i].getImg(), wallsmid[i].getX(), wallsmid[i].getY(), this);
                }
                for (int i = 0; i < randBox.length; i++) {
                    boolean boxSprunget = randBox[i].getSprunger();
                    if (boxSprunget == false) {
                        g2d.drawImage(randBox[i].getImg(), randBox[i].getX(), randBox[i].getY(), this);
                    }
                }
                for (int i = 0; i < laidbombs.size(); i++) {
                    if (laidbombs.get(i).getbombstatus() == true) {
                        int J = laidbombs.get(i).getBombOwner();
                        for(int m = 0; m<gamers[J].getavaBomb();m++){
                        if(gamers[J].getBomber().get(m).getbombstatus() == true)    
                            g2d.drawImage(gamers[J].getBomber().get(m).getImg(), gamers[J].getBomber().get(m).getX(), gamers[J].getBomber().get(m).getY(), this);
                        }
                    }
                }
                
                for(int i = 0; i<gamers.length;i++){
                    for(int j = 0; j<gamers[i].getBomber().size();j++){
                        gamers[i].getBomber().get(j).drawFire(g2d);
                    }
                }

                break;
            case DEATH:
                break;
            case HIGHSCORE:
                break;
            case LOGGEDIN:
                check = false;
                wrongC.setBounds(370, 300, 100, 40);
                logText.setVisible(false);
                pass.setVisible(false);
                checkLog.setVisible(false);
                g2d.setColor(Color.black);
                wrongC.setVisible(false);

                g2d.drawImage(BI, 0, 50, this);
                START.setVisible(true);
                break;

            default:
                break;
        }

    }

    private boolean validMove(Accounts acc, walls w) {
        Rectangle r1 = acc.getBounds1();
        Rectangle r2 = w.getBounds1();

        if (r1.intersects(r2)) {
            return false;
        }

        return true;
    }

    private boolean validMoveRand(Accounts acc, randomBoxes ran) {

        Rectangle r1 = acc.getBounds1();
        Rectangle r2 = ran.getBounds1();

        if (r1.intersects(r2)) {
            return false;
        }

        return true;
    }

    private Point findBombPos(Accounts acc) {
        Point bombpos = new Point();
        int foundPoint = 0;
        int accx = acc.getCentrumX();
        int accy = acc.getCentrumY();

        for (int i = 0; i < bombposA.length; i++) {
            if (accx > bombposA[i].x && accx < bombposA[i].x + 40 && accy > bombposA[i].y && accy < bombposA[i].y + 40) {
                foundPoint = i;
                i = 20000;
            }
        }

        bombpos = bombposA[foundPoint];

        return bombpos;
    }

    private void setBombpos() {
        int pc = 0;
        for (int i = 0; i < 11; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10) {
                for (int j = 0; j < 15; j++) {
                    bombposA[pc] = new Point(startX[0] + (j * 40), startY[0] + (i * 40));
                    pc++;
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    bombposA[pc] = new Point(startX[0] + (j * 80), startY[0] + (i * 40));
                    pc++;
                }
            }

        }
    }

    private void setBomb(Accounts acc) {
        if (acc.getLaidBombs() < acc.getavaBomb()) {
            Point bombpos = findBombPos(acc);
            laidbombs.add(acc.getBomber().get(acc.getLaidBombs()));
            acc.getBomber().get(acc.getLaidBombs()).setX(bombpos.x);
            acc.getBomber().get(acc.getLaidBombs()).setY(bombpos.y);
            acc.getBomber().get(acc.getLaidBombs()).setLaid();
            acc.setLaidBombs();
            
        }

    }

    private void explodeBomb(Accounts acc) {

    }
}
