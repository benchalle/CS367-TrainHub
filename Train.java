import java.util.Iterator;
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          Program 2: Train Hub
//FILE:             Train.java
//
//TEAM:    Benjamin Challe
//Authors: Benjamin Challe, bchalle@wisc.edu, 002
//
//---------------- OTHER ASSISTANCE CREDITS 
//Persons: none
//
//Online sources: none
////////////////////////////80 columns wide //////////////////////////////////
/**
 * This class represents a train.  It has a destination and a linked list 
 * of CargoCar objects.  It implements Iterable<CargoCar> by returning 
 * a direct access iterator to its linked list of cargo cars. 
 * 
 * Several methods, such as getDestination(), removeCargo() and getWeight(), 
 * are provided to manage a train object. 
 * 
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 * 
 * @see LinkedList
 * @see CargoCar
 */
public class Train implements Iterable<CargoCar> {

	private String destination; // create destination variable
	private LinkedList<CargoCar> train; // create a CargoCar named train
	
	/**
	 * Constructs Train with its destination.
	 * 
	 * @param dest train destination
	 */
	public Train(String dest){
		this.destination = dest; //set this instance of destination to dest
		train = new LinkedList<CargoCar>(); // create a new cargocar add it to train
	}
	
	/**
	 * Get the destination of this train.
	 * 
	 * @return train destination
	 */
	public String getDestination(){
		return this.destination; // return the destination
	}
	
	/**
	 * Set a new destination for this train.
	 * 
	 * @param newDest new train destination
	 */
	public void setDestination(String newDest){
		this.destination = newDest; // set the destination to newDest
	}
	
	/**
	 * Get the total weight of a cargo in this train.
	 * 
	 * @param the name of the cargo to sum
	 * @return total weight of specified cargo in this train
	 */
	public int getWeight(String cargoName){
		LinkedListIterator<CargoCar> itrName = train.iterator(); // create an iterator for cargo name
		LinkedListIterator<CargoCar> itrWeight = train.iterator(); // create an iterator for cargo weight
		while(itrName.hasNext() && itrWeight.hasNext()){ // while both iterators have an item
			String testName = itrName.next().getName(); // set testName to the name of the cargo
			int testWeight = itrWeight.next().getWeight(); // set the weight of the cargo to testWeight
			if(testName.equals(cargoName)){ //test to see if the testName and cargoName are the same
				return testWeight; // if they are return the weight of the cargo
			}
		}
		//TODO: implement this method
		return 0;// return 0 if the cargoName doesnt exist
		
	}
	

	public void add(CargoCar cargoCar) {
		train.add(cargoCar); // add the cargoCar to the train
		//TODO: implement this method
	}

	// add cargo car as specified position 
	public void add(int pos, CargoCar newCargo) {
		train.add(pos, newCargo); // add newCargo to a postion in the train
		//TODO: implement this method		
	}
	
	/**
	 * Remove the first CargoCar from this train which has the same cargo name 
	 * with the argument. If there are multiple CargoCar objects with the same 
	 * name, remove the first one.
	 * 
	 * @param The name of the cargo that you wish to remove.
	 * @return removed CargoCar object if you successfully removed a cargo, 
	 * otherwise null
	 */
	public CargoCar removeCargo(String cargoName){
		LinkedListIterator<CargoCar> itr = train.iterator(); // create an iterator for the train
		int pos = 0; // create a postion variable
		while(itr.hasNext()){ // loop through as long as the train has cargo
		String test = 	itr.next().getName(); // save the name of the cargo
			if(test.equals(cargoName)){  //test if the names are equal
				
				return train.remove(pos); // remove the cargo at that postion and return it
			}
			pos++; // increment the postion counter
		}
		return null; // if the cargoName doesnt exist return null
		
		
		//TODO: implement this method
	}

	public LinkedListIterator<CargoCar> iterator() {
		return train.iterator(); //return an iterator for train
		//TODO: implement this method
	}

	/**
	 * Returns the number of cargo cars on this train.  
	 * 
	 * CAUTION: the number of actual cars on a train can be changed external
	 * to the Train type.  Make sure this returns a current count of the 
	 * cargo cars on this train.  Tip: call a LinkedList method from here
	 * and make sure that the LinkedList method iterates to count cars.
	 * 
	 * @return the number of cargo cars on this train.
	 */
	public int numCargoCars() {
		return train.size(); // return the size of the train
		// TODO implement this method
	}

	/**
	 * Returns a reference to the header node from the linked list of
	 * CargoCar nodes.
	 * 
	 * CAUTION: Returning this node allows other
	 * code to change the contents of this train without
	 * calling train methods.  
	 * 
	 * It is being returned in this program to facilitate our testing
	 * and for moving sub-chains of nodes from one train to another.  
	 * THIS METHOD MAY ONLY BE CALLED BY moveMultipleCargoCars()
	 * of the TrainHub class.
	 * 
	 * @return the header node of the chain of nodes from the linked list.
	 */
	public Listnode<CargoCar> getHeaderNode() {
		return train.getHeaderNode(); // return the head pointer to train
		// TODO implement this method
	}

	/**
	 * Returns Train with a String format as following.
	 * <p>
	 * {ENGINE_START}{destination}{ENGINE_END}{CARGO_LINK}{cargo}:{weight}{CARGO_LINK}{cargo}:{weight}...
	 * <p>
	 * By default, {ENGINE_START} = ( , {ENGINE_END} = ) and {CARGO_LINK} = -> (defined in {@link Config}).
	 * So if you did not modify Config class, it will generate a String with following format.
	 * <p>
	 * ({destination})->{cargo}:{weight}->{cargo}:{weight}...
	 * 
	 * DO NOT EDIT THIS METHOD
	 * 
	 * @return train as a string format
	 */
	@Override
	public String toString(){
		String builtStr = "";
		
		builtStr += Config.ENGINE_START+this.destination+ Config.ENGINE_END;

		LinkedListIterator<CargoCar> itr = train.iterator();
		while(itr.hasNext()){
			CargoCar item = itr.next();
			builtStr += Config.CARGO_LINK + item.getName() +":" + item.getWeight();
		}
		
		return builtStr;
	}

}
