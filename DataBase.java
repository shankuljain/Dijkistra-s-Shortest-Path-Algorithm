/* This class is responsible for reading the data from the file.
 * File contains the data of a directed graph in arrayList representation.
 * ________________________
 * @author : shankul jain
 * date : 7 july 2014
 */

import java.io.*;
import java.util.*;

class DataBase{
	
/* Constructor : DataBase(String filename) */
/** 
 *Creates a new Database of a graph and initializes it using the 
 *data in the specified file. The constructor throws an error exception
 *if file is not found or an error occurs as the file is being read.
 */
	public DataBase(String filename) throws IOException{
		BufferedReader rd = new BufferedReader(new FileReader(filename));
		while(true){
			String line = rd.readLine();
			if(line == null) break;
			Vertex v = new Vertex(line);
			inventory.put(v.getMyVertexNo(),v);
		}
	}
	
	
/* Method : Vertex getVertex(int vertexNo) */
/** this method returns the object of the class Vertex
 * stored in ArrayList<Vertex>
 */
	public Vertex getVertex(int vertexNo){
		return inventory.get(vertexNo);
	}
	
	
/* Method : removeVertex(Vertex v) */
/** when a vertex is found with the help of dijkstra's greedy score
 *  this vertex is to be removed from the list of adjectVertices of everyvertex
 *  stored in database.
 */
	public void removeVertex(int vertexNo){
		for(Vertex w : inventory.values()){
			w.remove(vertexNo);
		}
	}
	
	/********************************************************************************
	 * Although for this particular text file an arraylist can be used and accessed *
	 * by index. but there may exist some otherfiles that has some vertex v which   *
	 * does not have an edge with v as tail. so to extend the program to work with  *
	 * any .txt file HashMap is used and data can be accessed by its key that is the*
	 * vertex no. of that veretex.												   *
	 ********************************************************************************/
	
	/* private instance variable */
	private Map<Integer,Vertex> inventory = new HashMap<Integer,Vertex>();
}