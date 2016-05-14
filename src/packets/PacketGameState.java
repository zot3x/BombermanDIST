package packets;

public class PacketGameState extends Packet {

	short[] gamersX = new short[4];
	short[] gamersY = new short[4];
	byte[] gamersID = new byte[4];
	byte[] gamersAlive = new byte[4];
	short[] bombX = new short[4];
	short[] bombY = new short[4];
	byte[] bombLaid = new byte[4];
	short[] fireX = new short[20];
	short[] fireY = new short[20];
	byte[] fireMode = new byte[20];
	byte[] randSprunget = new byte[105];
	
	public PacketGameState(byte[] data) {
		super(9);
		String[] dataArray = readData(data).split(",");
		int arrcounter = 0;
		int randcounter = 0;
		for(int i = 0; i<dataArray.length; i++){
			if(i<22){
				this.gamersX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;
				this.gamersY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.gamersID[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.gamersAlive[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.bombX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombLaid[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				for (int j = 0; j < 5; j++) {
					this.fireX[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireY[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireMode[j] = (byte) Byte.parseByte(dataArray[i]);
					i++;
				}
				i--;
				arrcounter++;
			}
			else if (i>21 && i <44){
				this.gamersX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;
				this.gamersY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.gamersID[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.gamersAlive[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.bombX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombLaid[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				for (int j = 5; j < 10; j++) {
					this.fireX[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireY[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireMode[j] = (byte) Byte.parseByte(dataArray[i]);
					i++;
				}
				i--;
				arrcounter++;
				
			}
			else if(i>43 && i<66){
				this.gamersX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;
				this.gamersY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.gamersID[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.gamersAlive[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.bombX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombLaid[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				for (int j = 10; j < 15; j++) {
					this.fireX[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireY[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireMode[j] = (byte) Byte.parseByte(dataArray[i]);
					i++;
				}
				i--;
				arrcounter++;
				
			}
			else if(i>65 && i<88){
				this.gamersX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;
				this.gamersY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.gamersID[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.gamersAlive[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				this.bombX[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombY[arrcounter] = (short) Short.parseShort(dataArray[i]);
				i++;

				this.bombLaid[arrcounter] = (byte) Byte.parseByte(dataArray[i]);
				i++;

				for (int j = 15; j < 20; j++) {
					this.fireX[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireY[j] = (short) Short.parseShort(dataArray[i]);
					i++;
					this.fireMode[j] = (byte) Byte.parseByte(dataArray[i]);
					i++;
				}
				i--;
				arrcounter++;
				
			}
			else if (i> 87){
				this.randSprunget[randcounter] = (byte) Byte.parseByte(dataArray[i]);
				randcounter++;
			}
			
			
		}
		
		
		
		
		
//		
//		for (int i = 0; i < dataArray.length; i++) {
//			int counter = i;
//			if (i < gamersX.length + gamersY.length + gamersID.length + gamersAlive.length + bombX.length + bombY.length
//					+ bombLaid.length + fireX.length + fireY.length + fireMode.length) {
//				System.out.println("Gamer X = " + dataArray[counter] + " " + counter);
//				this.gamersX[arrcounter] = (short) Short.parseShort(dataArray[counter]);
//				counter++;
//				this.gamersY[arrcounter] = (short) Short.parseShort(dataArray[counter]);
//				counter++;
//				this.gamersID[arrcounter] = (byte) Byte.parseByte(dataArray[counter]);
//				counter++;
//				this.gamersAlive[arrcounter] = (byte) Byte.parseByte(dataArray[counter]);
//				counter++;
//				this.bombX[arrcounter] = (short) Short.parseShort(dataArray[counter]);
//				counter++;
//				this.bombY[arrcounter] = (short) Short.parseShort(dataArray[counter]);
//				counter++;
//				this.bombLaid[arrcounter] = (byte) Byte.parseByte(dataArray[counter]);
//				counter++;
//				for (int j = 0; j < 5; j++) {
//					this.fireX[j] = (short) Short.parseShort(dataArray[counter]);
//					counter++;
//					this.fireY[j] = (short) Short.parseShort(dataArray[counter]);
//					counter++;
//					this.fireMode[j] = (byte) Byte.parseByte(dataArray[counter]);
//					counter++;
//				}
//
//			} else {
//				this.randSprunget[randcounter] = (byte) Byte.parseByte(dataArray[counter]);
//				counter++;
//				randcounter++;
//
//			}
//			arrcounter++;
//			i = i + counter-1;
//		}
		

	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public byte[] getBombLaid() {
		return bombLaid;
	}
	public short[] getBombX() {
		return bombX;
	}public short[] getBombY() {
		return bombY;
	}
	public byte[] getFireMode() {
		return fireMode;
	}
	public short[] getFireX() {
		return fireX;
	}
	public short[] getFireY() {
		return fireY;
	}
	public byte[] getGamersAlive() {
		return gamersAlive;
	}
	public byte[] getGamersID() {
		return gamersID;
	}
	public short[] getGamersX() {
		return gamersX;
	}
	public short[] getGamersY() {
		return gamersY;
	}
	public byte[] getRandSprunget() {
		return randSprunget;
	}

	private String[] readGamestate(byte[] data){
		String[] gameState = new String[200];
		
		String gameStates = new String(data, 0 , data.length);
		
		return gameState;
	}
}
