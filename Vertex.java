/* This class keeps track of the information of a single vertex  */

/***********************************************************************
 * upgrade this class
 * remove hashmap
 * possibility of error
 * use two arrayList indeed
 * sort
 * better run time
 ***********************************************************************/

import java.util.*;

class Vertex{
	
/* Constructor :Vertex(String line) */
/** constructor gets a string as an argument and calls the 
 * parseLine(String line) method passing the same string.
 * Further this method breaks the line in tokens and saves
 * data in useful way.
 */
	public Vertex(String line){
		parseLine(line);
	}
	
	
/* Method : parseLine(String line) */
/** 
 *  this method breaks down the line using the StringTokenizer
 *  and stores the data in two hashmap (distanceVertexMap and vertexDistanceMap).
 */
	private void parseLine(String line){
		StringTokenizer token = new StringTokenizer(line);
		myVertexNo = Integer.parseInt(token.nextToken());
		while(token.hasMoreTokens()){
			String str = token.nextToken();
			int commaIndex = str.indexOf(",");
			int vertexNo = Integer.parseInt(str.substring(0,commaIndex));
			int distance = Integer.parseInt(str.substring(commaIndex+1));
			distanceVertexMap.put(distance,vertexNo);
			vertexDistanceMap.put(vertexNo,distance);
			
		}
	}
	
	
/* Method: getMyVertexNo() */
/** this method returns the vertex no of current vertex */
	public int getMyVertexNo(){
		return myVertexNo;
	}
	
/* Method: int getMinDistance() */
/** this method returns the min distance amongst all the edges
 *  from this vertex as tail. if there are no edges remaining from this vertex
 *  then the maxDistance is returned.
 */
	public int getMinDistance(){
		int min = maxDistance;
		for(int i : distanceVertexMap.keySet()){
			if(i<min){
				min = i;
			}
		}
		return min;
	}
	
/* Method: getVertexNoAtDistance(int distance) */
/** this method returns the vertexNo of the vertex at the given distance
 *  It may return null if no matches are found 
 */
	public Integer getVertexNoAtDistance(int distance){
		return distanceVertexMap.get(distance);
	}
	
/*Method: remove(int vertexNo) */
/** This method removes the given vertex no from the list of adjecent
 *  vertices list of current vertex.
 */
	public void remove(int vertexNo){
		if(vertexDistanceMap.containsKey(vertexNo)){
			int distance = vertexDistanceMap.get(vertexNo);
			vertexDistanceMap.remove(vertexNo);
			distanceVertexMap.remove(distance);
		}
		
	}
	
/* private instance variables */
	private static final int maxDistance = 1000000;
	private int myVertexNo;
	private Map<Integer,Integer> distanceVertexMap = new HashMap<Integer,Integer>();
	private Map<Integer,Integer> vertexDistanceMap = new HashMap<Integer,Integer>();
}