import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          Program 2: Train Hub
//FILE:             TrainGenerator.java
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
 * This class provide methods for generating a Train.
 * 
 * COMPLETE THIS CLASS and HAND IN THIS FILE
 * 
 * @see Config
 */
public class TrainGenerator {
	
	/**
	 * Get a new train generated randomly.
	 * The constant variables for determining how many cargo, 
	 * what cargo and how heavy are in {@link Config}.
	 * 
	 * @return a train generated randomly
	 */
	public static Train getIncomingTrain(){
		Train incomingTrain = new Train("TrainHub"); // create a train with destination of TrainHub
		
		Random rand = new Random(System.currentTimeMillis());

		// How many freight cars
		int cartNum = rand.nextInt(Config.MAX_CART_NUM - Config.MIN_CART_NUM + 1) + Config.MIN_CART_NUM;

		for(int i=0;i<cartNum;i++){
			// What kind of cargo
			int loadIndex = rand.nextInt(Config.CARGO_ARRAY.length);
			String load = Config.CARGO_ARRAY[loadIndex];

			// How heavy
			int weight = rand.nextInt(Config.MAX_WEIGHT - Config.MIN_WEIGHT + 1) + Config.MIN_WEIGHT;

			// Where to
			int destIndex = rand.nextInt(Config.DEST_ARRAY.length);
			String dest = Config.DEST_ARRAY[destIndex];
			
			incomingTrain.add(new CargoCar(load, weight, dest));
		}
		
		return incomingTrain;
	}
	
	/**
	 * Get a new train generated from a file.
	 * Files for generating a train must have the format as follow
	 * <p>
	 * {destination},{cargo},{weight}<br>
	 * {destination},{cargo},{weight}<br>
	 * ...
	 * <p>
	 * where {destination} is a string among Config.DEST_ARRAY,
	 * {cargo} is a string among Config.CARGO_ARRAY,
	 * and {weight} is a string for any positive integer.
	 * <p>
	 * Ignore the line if it is not matched in this format. 
	 * See the sample in/outputs provided in the assignment description to get more details.
	 * 
	 * @param filename train input file name
	 * @return a train generated from a file
	 */
	public static Train getIncomingTrainFromFile(String filename){
		File file = new File(filename); // create a new file to read off of
	Train train = new Train("Train Hub"); // create a new train with destination of TrainHub
		try{ 
			Scanner scnr = new Scanner(file); //try and scan the file
			while(scnr.hasNextLine()){ // loop through as long as there is another line to read
				String trainInfo = scnr.nextLine(); // save line
				String[] trainTest = trainInfo.split(","); // split by comma
				if(trainTest.length == 3){ // if there are three items 
				for(int i =0; i< 3; i++){ // go through them to see if they are empty
					if(trainTest[i] == ""){
						break; // if they are skip that line
					}
		
					trainTest[i] = trainTest[i].trim(); // trim each element
				}
				String dest = trainTest[0]; //save the destination
				String cargo = trainTest[1]; // save the cargo name
				try{
				int weight = Integer.parseInt(trainTest[2]); // save the weight
				CargoCar cargoCar = new CargoCar(cargo,weight,dest); // create a new cargo car
				train.add(cargoCar); // add it to the train
				}catch(NumberFormatException e){
				
					continue;
				}
			
				}
				
				
			}
			scnr.close(); // close scanner
			
		}catch(FileNotFoundException e){
			System.out.println(Config.ERROR_FILE_READ); // if unable to read file throw exception
		}
		//TODO: implement this method
		return train; // return the created train
	}
}
