import java.util.Iterator;
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          Program 2: Train Hub
//FILE:             TrainHub.java
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
 * This class represents a train hub and provides the common operations
 * needed for managing the incoming and outgoing trains.
 *
 * It has a LinkedList of Train as a member variable and manages it.
 *
 * COMPLETE THIS CLASS AND HAND IN THIS FILE
 *
 * @see LinkedList
 * @see Train
 * @see Config
 */
public class TrainHub {

    /** The internal data structure of a hub is a linked list of Trains */
    private LinkedList<Train> trains; // create a list of trains

    /**
     * Constructs and initializes TrainHub object
     */
    public TrainHub(){
        this.trains = new LinkedList<Train>(); //construct the trains list
    }

    /**
     * This method processes the incoming train.
     * It iterates through each of the cargo car of the incoming train.
     * If there is an outgoing train in the train list going to the
     * destination city of the cargo car, then it removes the cargo car
     * from the incoming train and adds the cargo car at the correct location
     * in the outgoing train.  The correct location is to become the first
     * of the matching cargo cars, with the cargo cars in alphabetical order
     * by their cargo name.
     *
     * If there is no train going to the destination city,
     * it creates a new train and adds the cargo to this train.
     *
     * @param train Incoming train (list or cargo cars)
     */
    public void processIncomingTrain(Train train){
    	LinkedListIterator<CargoCar> itrCargo = train.iterator(); // create an iterator to iterate the train
    	while(itrCargo.hasNext()){ // while ther is more cargo
    		CargoCar cargo = itrCargo.next(); // save the cargo
    		
    		if(findTrain(cargo.getDestination()) == null){ // if there is no train with the cargos destination
    			Train newTrain = new Train(cargo.getDestination()); // create a new train
    			newTrain.add(cargo); // add the cargo to the train
    			trains.add(newTrain); //add the train to the list of trains
    			
    			
    		}else{
    			int pos =0; // create a position variable
    			boolean carAdded = false; // create a varaible to see if a car was added
    			Train thisTrain = findTrain(cargo.getDestination()); // get the train with the cargo destination
    			LinkedListIterator<CargoCar> itrc = thisTrain.iterator(); // create an iterator for the train
    			while(itrc.hasNext()&& !carAdded){ // while there is another cargoCar and a car hasnt been added
    				CargoCar cargoCar = itrc.next(); // save the cargoCar
    				if(cargo.getName().compareTo(cargoCar.getName()) <=0){ // compare the names of the cargoCars
    					thisTrain.add(pos,cargo); // add the train in the correct postion
    					carAdded = true; // say a car was added
    				}
    				pos++; // increment the position
    				
    			}
    			if(!carAdded){ // if a car hasnt been added
    				thisTrain.add(cargo); // add the cargo at the end of  the train
    			}
    			
    		}
    	}
        //TODO: implement this method
    }

    /**
     * This method tries to find the train in the list of trains, departing to the given destination city.
     *
     * @param dest Destination city for which train has to be found.
     * @return  Pointer to the train if the train going to the given destination exists. Otherwise returns null.
     */
    public Train findTrain(String dest){
    	LinkedListIterator<Train> itr = trains.iterator(); // create an iterator of the list of trains
    	while(itr.hasNext()){ // while the iterator has next
    		Train train = itr.next();// save the destination of a train
    		if(train.getDestination().equalsIgnoreCase(dest)){ // if the incoming and saved destinations are the same
    			return train; // return the data at the pointer
    		}
    	
    	}
    	return null; // if there is no train return null
        //TODO: implement this method
    }

    /**
     * This method removes the first cargo car going to the given
     * destination city and carrying the given cargo.
     *
     * @param dest Destination city
     * @param name Cargo name
     * @return If there is a train going to the the given destination and is carrying the given cargo,
     * it returns the cargo car. Else it returns null.
     */
    public CargoCar removeCargo(String dest, String name){
    	LinkedListIterator<Train> itr = trains.iterator(); // create an iterator of the list of trains
    	while(itr.hasNext()){ // while there is another train
    		Train train = itr.next(); // save the train to variable train
    		// create an iterator for the cargo
    		String testname = train.getDestination();
    		if(dest.equalsIgnoreCase(testname)){
    			LinkedListIterator<CargoCar> itrC = train.iterator(); 
    		while(itrC.hasNext()){ // while there is another cargo car
    			CargoCar cargo = itrC.next(); // save the next cargocar in cargo
    			String nameTest = cargo.getName(); // also save the name
    			String test = train.getDestination(); // save the destination of the train
    			if(name.equalsIgnoreCase(nameTest)){ //if the names of cargo are the same
    				train.removeCargo(nameTest); // remove the cargo
    				return cargo; // return the cargo removed
    			}
    			
    		
    		}
    		}
    	}
		return null; // if the destinations or names dont match return null
    	
        //TODO: implement this method
    }

    /**
     * This method iterates through all the trains in the list
     * and finds the sum of weights of given cargo in all trains.
     *
     * @param name Name of the cargo
     * @return Total weight of given cargo in all departing trains.
     */
    public int getWeight(String name){
    	LinkedListIterator<Train> itr = trains.iterator(); // create a train iterator

    	int totalWeight = 0; // set the total wheight to 0
    	while(itr.hasNext()){ //while there is another train
    		Train train = itr.next(); // get the train
    		LinkedListIterator<CargoCar> itrC = train.iterator(); // create an iterator for that trains cargo
    		while(itrC.hasNext()){//while there is more cargo
    			CargoCar nameTest = itrC.next(); // get the cargo
    		if(name.equalsIgnoreCase(nameTest.getName())){ // if the names are equal
    			totalWeight += nameTest.getWeight(); // add its weight to total weight
    		}
    		
    	}
    	}
    	return totalWeight; //return the total weight
        //TODO: implement this method
    }

    /**
     * This method is used to depart the train to the given destination.
     * To depart the train, one needs to delete the train from the list.
     *
     * @param dest Destination city for which corresponding train has to be departed/deleted.
     * @return True if train to the given destination city exists. If not, then return false.
     */
    public boolean departTrain(String dest){
    	LinkedListIterator<Train> itr = trains.iterator(); //create an iterator to iterate through the trains
    	boolean condition = false; // create a condition that is true when the destinations match
    	int pos = 0; // create a position counter
    	while(itr.hasNext()&& !condition){ // while there is another train or the destinations didnt match
    		Train train = itr.next(); // save the current train
    		if(train.getDestination().equalsIgnoreCase(dest)){ //test to see if the names match
    			condition = true; // if they do set the condition to true
    	    	trains.remove(pos); // remove the train at the position -1 postion
    		}
    		pos++; // increase the position counter
    		}

    return condition; //return if there was a train removed
        //TODO: implement this method
    }
    /**
     * This method deletes all the trains.
     *
     * @return True if there was at least one train to delete. False if there was no train to delete.
     */
    public boolean departAllTrains(){
    	boolean oneToRemove = false; // create a variable that is true if there is a train to remove
    	if(!trains.isEmpty()){ // aslong as the trains list has a train
    		oneToRemove = true; // say there is a train to remove
    		while(!trains.isEmpty()){ // while the trains has more trains
    		trains.remove(0); // remove one
    		}
    	}
    	return oneToRemove; // return whether there was a train to remove
        //TODO: implement this method
    }

    /**
     * Display the specific train for a destination.
     *
     * @param dest Destination city for the train the to be displayed.
     * @return True if train to the given destination city exists. If not, then return false.
     */
    public boolean displayTrain(String dest){
    LinkedListIterator<Train> itr = trains.iterator(); // create an iterator for the list of trains
    boolean condition = false; // create a condition variable
    while(itr.hasNext()){ // while there is another train
    	Train train = itr.next(); // save the current train
    	if(dest.equalsIgnoreCase(train.getDestination())){ // if the destinations match
    		condition =true; // set the condtion to true
    		System.out.print("(" + train.getDestination() + ")"); // print of teh trains destination
    		LinkedListIterator<CargoCar> itrC = train.iterator(); // create an iterator for the cargo in the train
    		while(itrC.hasNext()){ // while there is more cargo
    			CargoCar currC = itrC.next(); // save the current cargo
    			System.out.print("->" + currC.getName() + ":" + currC.getWeight()); // print out the info of the cargo
    			
    	}
    		System.out.println(); // format for the next line
    }
    		
    }
    return condition; // return whether the destinations matched
        //TODO: implement this method
    }

    /**
     * This method is used to display all the departing trains in the train hub.
     * Train should be displayed in the specified format.
     *
     * @return True if there is at least one train to print. False if there is no train to print.
     */
    public boolean displayAllTrains(){
    	if(trains.isEmpty()) return false; // if there is no trains to display return false
    	LinkedListIterator<Train> itr = trains.iterator(); // create an iterator for the trains list
    	while(itr.hasNext()){ // while there is another train
    		Train currT = itr.next(); // save the current train
    		System.out.print("(" + currT.getDestination() + ")"); // print out the trains destination
    		LinkedListIterator<CargoCar> itrC = currT.iterator(); // create an iterator for the cargo
    		while(itrC.hasNext()){ // while there is more cargo
    			CargoCar currC = itrC.next(); // save the current cargo
    			System.out.print("->" + currC.getName() + ":" + currC.getWeight()); // print out the data of cargo
    			
    		}
    		System.out.println(); // format for the next line
    		
    	
    	}
    	
    	return true; // return true if there are trains
        //TODO: implement this method
    }

    /**
     * Move all cargo cars that match the cargo name from a
     * source (src) train to a destination (dst) train.
     *
     * The matching cargo cars are added before the first cargo car
     * with a name match in the destination train.
     *
     * All matching cargo is to be moved as one chain of nodes and inserted
     * into the destination train's chain of nodes before the first cargo matched node.
     *
     * PRECONDITION: there is a source train and a destination train,
     * and the source train of nodes has at least one node with
     * matching cargo.  We will not test other conditions.
     *
     * NOTE: This method requires direct access to the chain of nodes of a
     * Train object.  Therefore, the Train class has a method in addition to
     * ListADT methods so that you can get direct access to header node
     * of the train's chain of nodes.
     *
     * @param src a reference to a Train that contains at least one node with cargo.
     *
     * @param dst a reference to an existing Train.  The destination is the
     * train that will have the cargo added to it.  If the destination chain
     * does not have any matching cargo, add the chain at its correct location
     * alphabetically.  Otherwise, add the chain of cargo nodes before the
     * first matching cargo node.
     *
     * @param cargoName The name of cargo to be moved from one chain to another.
     */
    public static void moveMultipleCargoCars(Train srcTrain, Train dstTrain, String cargoName) {
        // TODO Implement this method last.  It is not needed for other parts of program
    	
        // get references to train header nodes
        // get references to train header nodes
        Listnode<CargoCar> srcHeader, dstHeader, prev, curr;
        srcHeader = srcTrain.getHeaderNode();
        dstHeader = dstTrain.getHeaderNode();

        Listnode<CargoCar> first_prev = null, first = null, last = null;
        boolean hasFound = false;

        // 1. Find references to the node BEFORE the first matching cargo node
        //    and a reference to the last node with matching cargo.
        curr = srcHeader;
        if(curr.getNext().getData().getName().equalsIgnoreCase(cargoName)){// check to see if cargoname matches
        	first_prev = srcHeader; // if it does the postion before is the header node
        	first = curr.getNext(); // and the first is the first item in the list
        	hasFound = true;
        }
        while(curr.getNext() !=null && !hasFound){
        	if(curr.getNext().getData().getName().equalsIgnoreCase(cargoName)){ // check to see if the names are equal
        		first_prev = curr; // if they are the current position of curr is the item before
        		first = curr.getNext(); // the first item with name is the next one
        		hasFound = true;	
        	}
        	curr = curr.getNext(); //increment pointer
        }
        hasFound = false;
        curr = first; // set the curr to first
        if(curr.getNext() == null){ //if the next item is null
        	last = null; // last is null
        }else{
        while(curr.getNext() != null && !hasFound){ // loop while there are more items
        	if(curr.getNext().getData().getName().equalsIgnoreCase(cargoName)){ // check teh name
        		curr = curr.getNext(); // increment if same name
        	}else{
        		hasFound = true; 
        		last = curr; //the last item is at curr position
        	}
        }
        }
        curr = srcHeader;
        boolean removed = false;
        while(curr.getNext() != null && !removed){ //loop as long as there are items
   
        	if(curr.equals(first_prev)){ // if curr is the same as the item before
        		Listnode<CargoCar> lastNew = last.getNext(); //teh last is the next item
        		first_prev.setNext(lastNew); 
        		removed = true;
        	}
        	curr = curr.getNext(); //increment counter
        }
       curr = dstHeader;
       boolean carAdded = false;
       Listnode<CargoCar> lastDst;
       while(curr.getNext() != null && !carAdded){ // while there is a next item
        if(curr.getNext().getData().getName().compareTo(first.getData().getName()) >=0){ // compare the names of the cargoCars
        	lastDst = curr.getNext().getNext(); // the start of the dst list
        	curr.getNext().setNext(first); //add the transfered items
        	last.setNext(lastDst); // relink the end of the list
			carAdded = true; // say a car was added
		}
        curr = curr.getNext(); //increment pointer

		}
       if(!carAdded){
    	   curr = dstHeader; 
    	  lastDst = curr.getNext(); //get the beginning of dst list
           curr.setNext(first);  // add the items
           last.setNext(lastDst); // relink the end of list
    	   }
           
    
            // NOTE : We know we can find this cargo,
            //        so we are not going to deal with other exceptions here.

       }



        // 2. Remove from matching chain of nodes from src Train
        //    by linking node before match to node after matching chain




        // 3-1. Find reference to first matching cargo in dst Train


                // 3-2. If found, insert them before cargo found in dst


            // 3-3. If no matching cargo, add at correct location in dst
    }

