package socialDistanceShopSampleSolution;

import java.util.concurrent.atomic.AtomicInteger;

//class to keep track of people inside and outside and left shop
public class PeopleCounter {
	private AtomicInteger peopleOutSide; //counter for people arrived but not yet in the building
	private AtomicInteger peopleInside; //people inside the shop
	private AtomicInteger peopleLeft; //people left the shop
	private final int maxPeople; //maximum for lockdown rules

	PeopleCounter(int max) {
		peopleOutSide = new AtomicInteger(0);
		peopleInside = new AtomicInteger(0);
		peopleLeft = new AtomicInteger(0);
		maxPeople = max;
	}
		
	//getter
	public int getWaiting() {
		return peopleOutSide.get();
	}

	//getter
	public int getInside() {
		return peopleInside.get();
	}
	
	//getter
	public int getTotal() {
		return (getWaiting()+getInside()+getLeft());
	}

	//getter
	public int getLeft() {
		return peopleLeft.get();
	}
	
	//getter
	public int getMax() {
		return maxPeople;
	}
	
	//getter
	public void personArrived() {
		peopleOutSide.incrementAndGet();
	}
	
	//update counters for a person entering the shop
	public void personEntered() throws InterruptedException{
		peopleOutSide.decrementAndGet();
		peopleInside.incrementAndGet();
	}

	//update counters for a person exiting the shop
	public void personLeft() throws InterruptedException{
		peopleInside.decrementAndGet();
		peopleLeft.incrementAndGet();
	}

	//reset - not really used
	synchronized public void resetScore() {
		peopleInside.getAndSet(0);
		peopleOutSide.getAndSet(0);
		peopleLeft.getAndSet(0);
	}
}
