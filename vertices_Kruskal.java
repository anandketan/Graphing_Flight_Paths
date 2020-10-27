//Class of vertices used in Kruskal's algorithm
// Each isolated vertex is a separate component of the minimum span
//Part of Final Project - for MOOC on Graph Theory, UCSD on Coursera

//******************************************************************

import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>
{
	private ArrayList<Vertex> adjacencyList;   //Adjacent vertices
	private ArrayList<Double> distances;   //Distances to vertices
	private boolean known;
	private String name;   //City name
	private Vertex path;   //Previous node for Dijkstra's algorithm
	private double dist;   //Distance to path node
	private int number;   //Integer label for Kruskal's algorithm
	private int x;
	private int y;

	public Vertex()   //Constructor for no city name
	{
		adjacencyList = new ArrayList<Vertex>();
		distances = new ArrayList<Double>();
		known = false;
		dist = -1;
		path = null;
	}

	public Vertex(String n)   //Constructor for city name
	{
		name = n;
		adjacencyList = new ArrayList<Vertex>();
		distances = new ArrayList<Double>();
		known = false;
		dist = -1;
		path = null;
	}

	public int compareTo(Vertex other)   //Vertex must be comparable
	{
		if(this.dist<other.dist)
			return -1;
		if(this.dist>other.dist)
			return 1;
		else
			return 0;
	}

	public void addList(Vertex v, double d)   //Add another adjacent vertex
	{
		adjacencyList.add(v);
		distances.add(d);
	}

	public void setX(int x)   //X-coordinate methods
	{
		this.x=x;
	}
	public int getX(){
		return x;
	}

	public void setY(int y)   //Y-coordinate methods
	{
		this.y=y;
	}
	public int getY()
	{
		return y;
	}

	public double getDist()   //Distance to previous node in the Dijkstra path
	{
		return dist;
	}

	public void setDist(double d)
	{
		dist = d;
	}

	public Vertex getPath()   //Previous node in the Dijkstra path
	{
		return path;
	}

	public void setPath(Vertex v)
	{
		path = v;
	}

	public void setKnown(boolean x)   //Known field for Dijkstra's algorithm
	{
		known = x;
	}

	public boolean getKnown()
	{
		return known;
	}

	public ArrayList<Vertex> getList()   //Accessor for adjacency list
	{
		return adjacencyList;
	}

	public ArrayList<Double> getDistances()  //Accessor for distances to vertices
	{
		return distances;
	}

	public String getName()   //Accessor for city name
	{
		return name;
	}

	public void setNumber(int n)   //Field for Kruskal's algorithm
	{
		number = n;
	}

	public int getNumber()
	{
		return number;
	}
}
