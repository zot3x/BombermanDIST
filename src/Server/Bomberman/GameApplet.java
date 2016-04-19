package Server.Bomberman;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameApplet extends JApplet { 

//Define constants for the game
static final String TITLE = "Bomberman";
static final int WIDTH = 11;   // number of columns (in cells) - width
static final int HEIGHT = 13;      // number of rows (in cells) - height
static final int CELL_SIZE = 15; // Size of a cell (in pixels)
static final int CANVAS_WIDTH = 600;    // width and height of the game screen
static final int CANVAS_HEIGHT = 800;   // make sure to set it in the HTML also
static final int UPDATE_RATE = 1;    // number of game updates per second
static final long UPDATE_PERIOD = 1000000000L / UPDATE_RATE;  // nanoseconds
Timer timer;
int numberOfPlayers = 4; //eventually get this as input and not fixed
int gameWinner;


//Enumeration for the states of the game.
public enum gameState {
	INITIALIZED, PLAYING, PAUSED, GAMEOVER, DESTROYED
}

private gameState state;

//Define instance variables for the game objects
private Map map;

//Handle for the custom drawing panel
private GameCanvas canvas;

//Constructor to initialize the UI components and game objects
public GameApplet() {
// Initialize the game objects
gameInit();

// UI components
canvas = new GameCanvas();
// set the size of the applet in HTML, not the content pane!
canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
this.setContentPane(canvas);

// Other UI components such as button, score board, if any.
// ......

}

//All the game related codes here
//Initialize all the game objects, run only once in the constructor of the main class.
public void gameInit() {
// ......
map = new Map(WIDTH, HEIGHT, numberOfPlayers);
state = gameState.INITIALIZED;
gameStart();
}

//Shutdown the game, clean up code that runs only once.
public void gameShutdown() {
// ......
destroy();
state = gameState.DESTROYED;
}

@Override
public void destroy() {
 timer.stop();
}

public void gameStart() {
     gameLoop();
}

//Run the game loop here.
private void gameLoop() {
	
state = gameState.PLAYING;

// Game loop
ActionListener al = new ActionListener() {

   long beginTime, timeTaken, timeLeft;

   public void actionPerformed(ActionEvent ae) {
      beginTime = System.nanoTime();
      if(state == gameState.PAUSED){
    	  //set pause screen
      }
      
      if (state == gameState.GAMEOVER) {
          //break;  // break the loop to finish the current play
          //DO SOMETHING HERE
      }
      if (state == gameState.PLAYING) {
         // Update the state and position of all the game objects,
         // detect collisions and provide responses.
         gameUpdate();
      }
      // Refresh the display
      repaint();
      // Delay timer to provide the necessary delay to meet the target rate
      timeTaken = System.nanoTime() - beginTime;
      timeLeft = (UPDATE_PERIOD - timeTaken) / 1000000L;  // in milliseconds
      if (timeLeft < 10) timeLeft = 10;   // set a minimum
  }
};
timer = new Timer(40,al);
timer.start();
}

//Update the state and position of all the game objects,
//detect collisions and provide responses.
public void gameUpdate() {
	map.update();
	
	if(map.checkPlayersAlive() != -1){
		 gameWinner = map.checkPlayersAlive();
		if(gameWinner == 0){
			//tie 
		}
		
		state = gameState.GAMEOVER;
		
	}
	
}

//Refresh the display. Called back via repaint(), which invoke the paintComponent().
private void gameDraw(Graphics2D g2d) {
switch (state) {
  case INITIALIZED:
 //DRAW INITIAL STUFF
	  
 break;
  case PLAYING:
 //DRAW PLAYING STUFF
	 int[][] grid = map.getGrid();
		
	 for(int i = 0; i < (map.getHeight()); i++){
		for(int j = 0; j < (map.getWidth()); j++){
			System.out.print(grid[i][j] + " ");
			if(j == map.getWidth()-1){
				System.out.println();
			}
		}
	 }
			
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	  
 break;
case PAUSED:
 //DRAW PAUSE SCREEN
 break;
case GAMEOVER:
 //DRAW GAMEOVER SCREEN
 break;
case DESTROYED:
//DRAW MAIN MENU MAYBE?
break;
}

}

//Process a key-pressed event. Update the current state.
public void gameKeyPressed(int keyCode) {
switch (keyCode) {
  case KeyEvent.VK_UP:
	 map.movePlayer(1, 3);
     break;
  case KeyEvent.VK_DOWN:
	 map.movePlayer(1, 4);
     break;
  case KeyEvent.VK_LEFT:
	 map.movePlayer(1, 1);
     break;
  case KeyEvent.VK_RIGHT:
	 map.movePlayer(1, 2);
     break;
  case KeyEvent.VK_SPACE:
	 map.setBomb(1);
	  break;
}
}

//Process a key-released event.
public void gameKeyReleased(int keyCode) {  }

//Process a key-typed event.
public void gameKeyTyped(char keyChar) {  }

//Other methods
//......

//Custom drawing panel, written as an inner class.
@SuppressWarnings("serial")
class GameCanvas extends JPanel implements KeyListener {
// Constructor
public GameCanvas() {
  setFocusable(true);  // so that can receive key-events
  requestFocus();
  addKeyListener(this);
}

// Override paintComponent to do custom drawing.
// Called back by repaint().
@Override
public void paintComponent(Graphics g) {
  Graphics2D g2d = (Graphics2D)g;
  super.paintComponent(g2d);   // paint background
  setBackground(Color.BLACK);  // may use an image for background

  // Draw the game objects
  gameDraw(g2d);
}

// KeyEvent handlers
@Override
public void keyPressed(KeyEvent e) {
  gameKeyPressed(e.getKeyCode());
}

@Override
public void keyReleased(KeyEvent e) {
  gameKeyReleased(e.getKeyCode());
}

@Override
public void keyTyped(KeyEvent e) {
  gameKeyTyped(e.getKeyChar());
}
}
}