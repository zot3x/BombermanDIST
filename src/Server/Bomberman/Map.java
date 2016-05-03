package Server.Bomberman;

import Server.Bomberman.Player;

import java.util.ArrayList;
import java.util.Random;

public class Map {

	private int[][] grid;
	private int width, height;
	private int numberOfPlayers;
	private Player[] players;
	private static Random random = new Random(System.currentTimeMillis());
	
	private int maxBombs = 10;
	private int maxRange = 10;
	private int maxExplosions;
	private int timeToLive = 20; // test for proper timeToLive value
	
	private Bomb[][] bombs;
	private Explosion[] explosions;
	private Bonus[][] bonusGrid;
	
	public Map(int width, int height, int numberOfPlayers){
		grid = new int[height][width];
		this.width = width;
		this.height = height;
		this.numberOfPlayers = numberOfPlayers;
		
		maxExplosions = maxBombs*numberOfPlayers;
		
		explosions = new Explosion[maxExplosions];
		bombs = new Bomb[numberOfPlayers][maxBombs];
		bonusGrid = new Bonus[height][width];
		
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				grid[j][i] = 0;
			}
		}
		
		initialisePlayers(numberOfPlayers);
		
		spawnBlockades(width, height);
		
		spawnCrates(width, height);
		
	}
	
	/**  
	 * 
	 * @return the playerID of the winner.
	 * -1 = false &
	 *  0 = TIE
	 */
	public int checkPlayersAlive() {// returns the playerID of the person who won. when it returns 0 it results in a tie.
		int playersAlive = 0;
		int playerWon = 0;
		int i;
		for (i = 0; i < numberOfPlayers; i++) {	//checks how many is alive
			if (players[i].isAlive()) {
				playersAlive++;
				playerWon = i+1;
			}
		}
		if (((playersAlive == 1) | (playersAlive == 0))) { //if playersAlive == 0 -> TIE
			if(playerWon == 1){
			 //do something here
			}if(playerWon == 2){
				//do something here
				}
			if(playerWon == 3){
				//do something here
					}
			if(playerWon == 4){
				//do something here
					}
			if(playersAlive == 1){
				return playerWon;
			}
			else return 0; //TIE
		}
		return -1;
	}
	
	public void movePlayer(int playerID, int direction) { // move the player on map
		int currentX, currentY;
		if (!players[playerID].isHasMoved()) {
			if (direction == 1) { // LEFT
				currentX = players[playerID].getX();
				if (validMove(currentX - 1, players[playerID].getY())) {
					grid[players[playerID].getY()][players[playerID].getX()] = 0;
					players[playerID].setX(players[playerID].getX()-1);
					checkBonus(playerID);
				}
			}
			if (direction == 2) { // RIGHT
				currentX = players[playerID].getX();
				if (validMove(currentX + 1, players[playerID].getY()) == true) {
					grid[players[playerID].getY()][players[playerID].getX()] = 0;
					players[playerID].setX(players[playerID].getX()+1);
					checkBonus(playerID);
				}
			}
			if (direction == 3) { // UP
				currentY = players[playerID].getY();
				if (validMove(players[playerID].getX(), currentY - 1)) {
					grid[players[playerID].getY()][players[playerID].getX()] = 0;
					players[playerID].setY(players[playerID].getY()-1);
					checkBonus(playerID);
				}
			}
			if (direction == 4) { // DOWN
				currentY = players[playerID].getY();
				if (validMove(players[playerID].getX(), currentY + 1)) {
					grid[players[playerID].getY()][players[playerID].getX()] = 0;
					players[playerID].setY(players[playerID].getY()+1);
					checkBonus(playerID);
				}
			}
		}
	}
	
	public void setBomb(int playerID) { //set bombs
		int x = players[playerID].getX();
		int y = players[playerID].getY();
		
		int bombsLaid = players[playerID].getBombsLaid();
		if (bombsLaid < players[playerID].getBombs()) {
			if (validBombPlacement(x, y)) {
				for(int i =0; i < players[playerID].getBombs(); i++){
					if(bombs[playerID][i] != null){
					//	if(bombs[playerID][i].getBelongTo() == -1){
							bombs[playerID][i] = new Bomb(x, y, players[playerID].getRange(), playerID, false, timeToLive);
							players[playerID].setBombsLaid(players[playerID].getBombsLaid()+1);
							break;
						//}
					}
				}
			}
		}
	}
	
	private void initialisePlayers(int numberOfPlayers){
		
		players = new Player[numberOfPlayers];
		
		int[][] playerPositions = {
			{0,0}, {width-1,0}, {0 , height-1}, {width-1, height-1}	
		};
		
		for(int i = 0; i < numberOfPlayers; i++){
			players[i] = new Player(playerPositions[i][0], playerPositions[i][1], 1, 1, 1, i+1);
		}
	}
	
	private void spawnCrates(int width, int height){
		boolean boxSpawned;
		int randomY, randomX;
		int cratesToSpawn = 73; //find way to calculate flexible value or just set a fixed one
		
		for(int i = 0; i < cratesToSpawn; i++){
			boxSpawned = false;
			while(!boxSpawned){
				randomY = random.nextInt(height);
				randomX = random.nextInt(width);
				
				//dont spawn at player locations
				if(!((randomY == 0) | (randomY == height-1) && (randomX == 0) | randomX == 1 | randomX == 2 | randomX == width-1 | randomX == width-2 | randomX == width-3)){
					if(!(randomX == 0 | randomX == width-1 && randomY == 1 | randomY == 2 | randomY == height-2 | randomY == height-3)){
						if((grid[randomY][randomX] == 0)){
							grid[randomY][randomX] = 9;
							boxSpawned = true;
						}
					}
				}
			}
		}
	}
	
	private void spawnBlockades(int width, int height){
			for(int i = 1; i < width-1; i = i+2){
				for(int j = 1; j < height-1; j = j + 2){
					grid[j][i] = 10;
				}
			}
		}
	
	private boolean validMove(int x, int y) { // check if the move is valid
		if(checkBoundaries(x , y)){
			if ((grid[y][x] == 0) | (grid[y][x] == 1)| (grid[y][x] == 2)| (grid[y][x] == 3)| (grid[y][x] == 4) | (grid[y][x] == 6)| (grid[y][x] == 7)) { //validMove if clear, player, bonus.
				return true;
			}
		}
		return false;
	}
	
	private boolean validBombPlacement(int x, int y){ // check if the bomb placement is valid
		if ((grid[y][x] != 8) | (grid[y][x] != 11)) { //not already a bomb there or player+bomb.
			return true;
		}
		else return false;
	}

	private void detonateBomb(int x, int y) {//	detonates the bomb on the given coordinates and thereby initiating the bomb detonation sequence.
		int i, j;
		for (i = 0; i < numberOfPlayers; i++) {
			for (j = 0; j < maxBombs; j++) {
				if (bombs[i][j].getX() == x && bombs[i][j].getY() == y) {
					int bx = bombs[i][j].getX();
					int by = bombs[i][j].getY();
					int br = bombs[i][j].getRange();
					int bbt = bombs[i][j].getBelongTo();
					bombs[i][j].setX(-1);
					bombs[i][j].setY(-1);
					bombs[i][j].setRange(-1);
					players[bombs[i][j].getBelongTo()].setBombsLaid(players[bombs[i][j].getBelongTo()].getBombsLaid()-1);
					bombs[i][j].setBelongTo(-1);
					bombs[i][j].setTimeToLive(-1);
					calculateExplosion(bx , by , br , bbt);
				}
			}
		}
	}
	private void detonateCrate(int x, int y) {//	removes the crate on the given coordinates and decides if a bonus should spawn
		grid[y][x] = 0;
		int randomValue = random.nextInt(20);
		if (randomValue > 9){
			if (randomValue >= 14)	
				bonusGrid[y][x] = new Bonus("Range", x, y);
			else {
				bonusGrid[y][x] = new Bonus("Bomb", x, y);
			}
		}
	}
	private void calculateExplosion(int x, int y, int range, int belongTo) {//	calculates the impact of the explosion and calls the appropriate functions to e.g. remove the crates hit.
		int explosionNr = -1;
		int k, r, direction;
		for (k = 0; k < maxExplosions; k++) {	//find explosion
			if (explosions[k].getBelongTo() == -1) {
				explosionNr = k;
				break;
			}
		}
		
		int[] length = {0,0,0,0};
		
		explosions[explosionNr].setBelongTo(belongTo); //set explosion  info
		explosions[explosionNr].setX(x);
		explosions[explosionNr].setY(y);
		explosions[explosionNr].setLength(length);
		explosions[explosionNr].setHasExploded(false);
		int currentX = x; 	//calculating length each direction
		int currentY = y;
		int spot = grid[currentY][currentX];
		if((spot == 1) | (spot == 2)| (spot == 3)| (spot == 4)| (spot == 11)){ // hit player
			killPlayer(currentX, currentY);
		}
		for	(direction = 0; direction < 4; direction++){		//Do appropriate actions for the 4 directions
					currentX = x;
					currentY = y;
			for (r = 0; r < range; r++) {
				if(direction == 0){ // // LEFT
					currentX = currentX - 1;
				} else	if(direction == 1){ // RIGHT
					currentX = currentX + 1;
				} else if(direction == 2){ // UP
					currentY = currentY - 1;
				} else	if(direction == 3){ // DOWN
					currentY = currentY + 1;
				}
				if((checkBoundaries(currentX, currentY))){
					spot = grid[currentY][currentX];
					if (spot == 0){ //clear
						length[direction] = length[direction]++;
					}
					if (spot == 1 || spot ==2|| spot ==3 || spot ==4){ //hit player
						length[direction] = length[direction]++;
						killPlayer(currentX, currentY);
					}
					if (spot == 8){ // hit bomb
						length[direction] = length[direction]++;
						detonateBomb(currentX, currentY);
					}
					if (spot == 11){ //hit player + bomb
						length[direction] = length[direction]++;
						killPlayer(currentX, currentY);
						detonateBomb(currentX, currentY);
					}
					if (spot == 5) //fire
						length[direction] = length[direction]++;
					if (spot == 6 || spot == 7){ //hit bonus
						length[direction] = length[direction]++;
						bonusGrid[currentY][currentX] = null;
					}
					if (spot == 9) {// hit crate
						length[direction] = length[direction]++;
						detonateCrate(currentX, currentY);
						break;
					}
					if (spot == 10){ //hit pillar or border
						break;
					}
				}
			}
		}
		explosions[explosionNr].setLength(length);
	}
	private boolean checkBoundaries(int x, int y) { // check boundaries for game
		if ((x < 0) | (x > width-1)) {
			return false;
		}
		if ((y < 0) | (y > height-1)) {
			return false;
		}
		return true;
	}
	private void killPlayer(int x, int y) {//	kills the player residing on the given coordinates.
		int i;
		for (i = 0; i < numberOfPlayers; i++){
			if(players[i].getX() == x && players[i].getY() == y){
				players[i].setAlive(false);
				grid[players[i].getY()][players[i].getX()] = 0;
				players[i].setX(-1);
				players[i].setY(-1);
			}
		}
	}
	
	private void checkBombs(){//	Checks for all deployed bombs whether they are ready to explode and if so detonates the bomb. Otherwise decreases their time to live.
		int i, j;
		for(i = 0; i < numberOfPlayers; i++){
			for (j = 0; j < maxBombs; j++){
				if(bombs[i][j] != null){
				if (bombs[i][j].getTimeToLive() == 0){
					detonateBomb(bombs[i][j].getX() , bombs[i][j].getY());
				}
				else bombs[i][j].setTimeToLive(bombs[i][j].getTimeToLive()-1);
			}
			}
		}
	}
	private void checkBonus(int playerID) {//	Checks if the given player has landed on a bonus, if so; give the player the bonus and remove the bonus from the grid.
		int x = players[playerID].getX();
		int y = players[playerID].getY();
		if (bonusGrid[y][x] != null) {
			if (bonusGrid[y][x].getType().equalsIgnoreCase("Range")) {
				int range = players[playerID].getRange(); 
				if (range < maxRange)
					players[playerID].setRange(++range);
			}
			if (bonusGrid[y][x].getType().equalsIgnoreCase("Bomb")) {
				int bombs = players[playerID].getBombs();
				if (bombs < maxBombs)
					players[playerID].setBombs(++bombs);
			}
			bonusGrid[y][x] = null;
		}
	}
	
	//updates the state of the map - game loop
	public void update(){
		drawPlayers();
		drawBombs();
		checkBombs();
		drawBonus();
		drawExplosions();
		//drawMap();
	}
	
	//draw
	private void drawBonus() { // draw bonus on the grid
		int i, j;

		for (i = 0; i < width; i++) {
			for (j = 0; j < height; j++) {
				if (bonusGrid[j][i] != null){
				
				if (bonusGrid[j][i].getType().equalsIgnoreCase("Range")) {// if there is a bonus
					grid[j][i] = 6;
				}else if(bonusGrid[j][i].getType().equalsIgnoreCase("Bomb")){
					grid[j][i] = 7;
				}
				}
			}
		}
	}
	private void drawBombs() { // draw bombs on the grid
		int i, j;
		int x, y;

		for (i = 0; i < numberOfPlayers; i++) {
			for (j = 0; j < maxBombs; j++) {
				if(bombs[i][j] != null){
					//if(bombs[i][j].getTimeToLive() == 1){
				
					//	if (bombs[i][j].getX() != -1) {// if there is a bomb
							x = bombs[i][j].getX();
							y = bombs[i][j].getY();
							if(grid[y][x] == 1||grid[y][x] == 2||grid[y][x] == 3||grid[y][x] == 4)
								grid[y][x] = 11;
							if(grid[y][x] == 0)
								grid[y][x] = 8;
				//}
				//}
			}
		}}
	}
	private void drawPlayers() { //draw players to the grid
		int i;
		for (i = 0; i < numberOfPlayers; i++){
			if(players[i].isAlive()){
			grid[players[i].getY()][players[i].getX()] = i+1;
			if(players[i].isHasMoved())
			players[i].setHasMoved(false);
			}
		}
	}
	private void drawExplosions() { // draw explosions to the grid
		int currentX, currentY;
		int i, j, k, l, m;
		int fireOrFade;
			for (i = 0; i < 40; i++){
				if(explosions[i] != null){
					fireOrFade = 5;
					if (explosions[i].getBelongTo() != -1){
						if(explosions[i].isHasExploded() == false){
							fireOrFade = 5;
						}	else fireOrFade = 0;
					}
				
					int[] length = explosions[i].getLength();
				// start explosion at center
				grid[explosions[i].getY()][explosions[i].getX()] = fireOrFade; // fire
				currentX = explosions[i].getX();	//expand in the 4 directions
				for (j = 0; j < length[0]; j++){ // LEFT
					currentX = currentX - 1;
					grid[explosions[i].getY()][currentX] = fireOrFade;
				}
				currentX = explosions[i].getX();
				for (k = 0; k < length[1]; k++){ // RIGHT
					currentX = currentX + 1;
					grid[explosions[i].getY()][currentX] = fireOrFade;
				}
				currentY = explosions[i].getY();
				for (l = 0; l < length[2]; l++){ // UP
					currentY = currentY - 1;
					grid[currentY][explosions[i].getX()] = fireOrFade;
				}
				currentY = explosions[i].getY();
				for (m = 0; m < length[3]; m++){ // DOWN
					currentY = currentY + 1;
					grid[currentY][explosions[i].getX()] = fireOrFade;
				}
				if(fireOrFade == 5){
					explosions[i].setHasExploded(true);
				}
				else if(fireOrFade == 0){
					explosions[i].setX(-1);
					explosions[i].setY(-1);
					explosions[i].setBelongTo(-1);
					explosions[i].setHasExploded(false);
					length = new int[] {-1,-1,-1,-1};
					explosions[i].setLength(length);
					explosions[i].setTimeToLive(-1);
				}
				
			}
		}
	}
	
	
	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
}
