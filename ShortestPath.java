/* This program implements the dijkstra's Algorithm to solve single source shortest path 
 * problem.
 * 
 * This algorithm uses greedy approach or dijkstra's greedy score.
 * _____________________________________
 * @author : Shankul Jain
 * Date : 08 July 2014
 */


import java.io.*;
import java.util.*;

class ShortestPath{
	
	/* file name */
	private static final String filename = "dijkstraData.txt";		//data of undirected graph
	private static final int nVertices = 200;
	private static final int maxDistance = 1000000;
	
/* Method: Main() */
/**
 * This method creates an object of class database and prompts
 * the user to enter the source vertex. After receiving the input
 * from user, a call is made to calculate shortest path method that
 * calculates the shortest path from the source vertex.
 */
	
/** After calculating the shortest path for each vertex user is
 * prompted to enter the vertex no of the vertex which he/she wants
 * to know the shortest path from source vertex.
 * 
 * To end the program press 0.
 */
	public static void main(String args[]) throws IOException{
		db = new DataBase(filename);
		Scanner in = new Scanner(System.in);
		
		int sourceVertexNo = 1;
		
		String line;
		while(true){
			System.out.print("Enter source vertex no : " );
			line = in.nextLine();
			try{
				sourceVertexNo =  Integer.parseInt(line);
				if(sourceVertexNo>nVertices || sourceVertexNo<1){
					System.out.println("Enter a integer b/w 1 to "+nVertices);
				}
				else{
					break;
				}
			}
			catch(NumberFormatException e){
				System.out.println("Enter a integer b/w 1 to "+nVertices);
			}
		}
		
		calculateShortestPath(sourceVertexNo);
		
		System.out.println("Please enter the vertex no of which you want to see shortest distance");
		System.out.println("if you see shortest distance of any vertex "+ maxDistance +
				" it means that there doen't exist a direct route to that vertex from source vertex");
		System.out.println("To finish the program enter 0");
		
		int vertexNo = 0;
		
		while(true){
			
			while(true){
				System.out.print("enter vertex No : ");
				line = in.nextLine();
				
				try{
					vertexNo = Integer.parseInt(line);
					if(vertexNo>200 || vertexNo <0){
						System.out.println("Enter a integer b/w 1 to "+nVertices);
					}
					else break;
				}
				catch(NumberFormatException e){
					System.out.println("Enter a integer b/w 1 to "+nVertices);
				}
			}
			
			if(vertexNo == 0) break;
			System.out.println(shortestDistance[vertexNo-1]);
		}
		
	}
	
/* Method : calculateShortestpath(Vertex sourceVertex) */
/** This method calculates the shortest path for each and every vertex
 * from the souce vertex. if there is no direct path from the source vertex
 * then the distance is set to be @maxDistance (infinity)
 */
	
/** tailSet is an arrayList of vertex and the greedy score is minimum distance
 *  from vertices in this set as tail.
 */
	
/** once a vertex is found with greed score that is removed from the
 * adjecent vertices list of each vertices present in database so we don't
 * find the same vertex again and again.
 */
	
	
/* 
 * search for the diskstra's greedy score amongst all the vertices
 *  present in tailSet.
 */
		
/******************************************************************************
 * tailSet may contain some null pointer. since there may be some vertex v    *
 * that is not in the database due to the fact that the vertex doesn't show   *
 * up in .txt file because there doesn't exist an edge with vertex v as tail  *
 * in directed graph.To avoid null pointer exception if() statement is used   *
 ******************************************************************************/
	
	private static void calculateShortestPath(int sourceVertexNo){
		
		shortestDistance = new int[nVertices];
		Arrays.fill(shortestDistance, maxDistance);
		shortestDistance[sourceVertexNo-1] = 0;
		tailSet = new ArrayList<Vertex>();
		tailSet.add(db.getVertex(sourceVertexNo));
		db.removeVertex(sourceVertexNo);
		
		
		/** loop continues till the all the vertices are in the tailSet */
		while(tailSet.size()!=shortestDistance.length){
			int shortestPathDistance = 0;
			int minVertexNo = 0;
			
			for(Vertex v : tailSet){
				if(v!=null){
					int minDistance = v.getMinDistance();
					Integer vertexNo = v.getVertexNoAtDistance(minDistance);
					if(vertexNo!=null && (shortestPathDistance == 0 ||shortestDistance[v.getMyVertexNo()-1]+minDistance<shortestPathDistance)){
						minVertexNo = vertexNo;
						shortestPathDistance = shortestDistance[v.getMyVertexNo()-1]+minDistance;
					}	
				}
			}
			
			shortestDistance[minVertexNo-1] = shortestPathDistance;
			db.removeVertex(minVertexNo);
			tailSet.add(db.getVertex(minVertexNo));
		}
	}
	
	/* private ivars */
	private static DataBase db;
	private static int[] shortestDistance;
	private static ArrayList<Vertex> tailSet;
}