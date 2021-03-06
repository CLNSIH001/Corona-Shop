package socialDistanceShopSampleSolution;

// GridBlock class to represent a block in the shop.

import java.util.concurrent.Semaphore;

/**
 * class representing blocks for the shop.
 * @author Prof. M.Kuttel
 * @author Sihle Calana
 */

public class GridBlock {
	private boolean isOccupied;
	private final boolean isExit; 
	private final boolean isCheckoutCounter;
	private int [] coords; // the coordinate of the block.
	private int ID;
	private Semaphore mutex;
	
	public static int classCounter=0;

	/**
	 * creats a gridblock
	 * @param exitBlock whether or not this block is exit part of store
	 * @param checkoutBlock whether or not this block in the checkout area
	 * @throws InterruptedException
	 */
	GridBlock(boolean exitBlock, boolean checkoutBlock) throws InterruptedException {
		isExit=exitBlock;
		isCheckoutCounter=checkoutBlock;
		isOccupied= false;
		ID=classCounter;
		classCounter++;
		mutex = new Semaphore(1);
	}


	GridBlock(int x, int y, boolean exitBlock, boolean refreshBlock) throws InterruptedException {
		this(exitBlock,refreshBlock);
		coords = new int [] {x,y};
	}
	
	//getter
	public  int getX() {return coords[0];}  
	
	//getter
	public  int getY() {return coords[1];}

	/**
	 * for customer to move to a block
	 * @return boolean
	 * @throws InterruptedException
	 */
	public boolean get() throws InterruptedException {
		mutex.acquire();
		isOccupied=true;
		return true;
	}

	/**
	 * for customer to leave a block
	 */
	public  void release() {
		isOccupied =false;
		mutex.release();
	}
	
	//getter
	public boolean occupied() {
		return isOccupied;
	}
	
	//getter
	public  boolean isExit() {
		return isExit;	
	}

	//getter
	public  boolean isCheckoutCounter() {
		return isCheckoutCounter;
	}
	
	//getter
	public int getID() {return this.ID;}
}
