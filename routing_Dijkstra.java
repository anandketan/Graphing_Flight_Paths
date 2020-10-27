//Planning and optimizing flight routes between cities 
//Using Dijkstra's Algorithm to continuously eliminate longer paths
//Part of Final Project - for MOOC on Graph Theory, UCSD on Coursera

//******************************************************************

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Dijkstra {
	private ArrayList <Vertex> theList;   //The vertices
	private ArrayList <Vertex> path;   //The optimal path
	private File infile;
	private Scanner in;
	private HashMap <String, Vertex> cityList;   //vertex, city-name map

	public Dijkstra (File f) throws FileNotFoundException
	{
		infile = f;
		in = new Scanner(infile);
		cityList = new HashMap<String, Vertex>();
		theList = new ArrayList<Vertex>();
		path = new ArrayList<Vertex>();
		makeGraph();
	}


	public String[] getCities()   //Array of city name strings
	{
		String[] cityNames = new String[cityList.size()];
		Set<String> c = cityList.keySet();
		Iterator<String> cIterator = c.iterator();
		for(int i=0;i<c.size();i++)
        {
			cityNames[i]=cIterator.next();
		}
		return cityNames;
	}

	public int size()   //Number of cities in question
	{
		return cityList.size();
	}

	public HashMap <String, Vertex> getMap()   //Hashmap of city names
	{
		return cityList;
	}

	public ArrayList <Vertex> getList()   //Optimal path
	{
		return theList;
	}

	public void makeGraph()   //Create graph using data
	{
		while(in.hasNextLine())
        {
			String start = " ";   //Import first city
			if(in.hasNext())
				start = in.next();
			else
				break;

			String end = " ";   //Import second city
			if(in.hasNext())
				end = in.next();
			else
				break;

			double distance=0.0;   //Import distance
			if(in.hasNextDouble())
				distance = in.nextDouble();
			else
				break;

			if(cityList.containsKey(start))   //Check if city is already on hashmap
            {
				Vertex v1 = cityList.get(start);

				if(!cityList.containsKey(end))   //If city is not on hashmap, add it
				{
					Vertex x = new Vertex(end);
					cityList.put(end, x);
				}
				Vertex v2 = cityList.get(end);

				v1.addList(v2,distance);   //Add path to both adjacency lists
				v2.addList(v1,distance);
			}


			else if(!cityList.containsKey(start)){
				Vertex v1 = new Vertex(start);

				if(!cityList.containsKey(end))   //If destination not on map, add it
                {
					Vertex x = new Vertex(end);
					cityList.put(end, x);
				}

				Vertex v2 = cityList.get(end);

				v1.addList(v2,distance);
				v2.addList(v1,distance);

				cityList.put(start, v1);  //Add start city to hashmap
			}
		}

		Collection <Vertex> vertices = cityList.values();
		Iterator <Vertex> it = vertices.iterator();

		for(int i = 0; i<vertices.size();i++)
        {
			theList.add(it.next());   //Create the list of vertices from the hashmap
		}
	}

	public void findPath(String city)   //Dijkstra's algorithm
	{
		MyMinHeap <Vertex> heap = new MyMinHeap<Vertex>();

		for(int i = 0; i < theList.size();i++)   //reset vertices
        {
			Vertex v = theList.get(i);
			v.setKnown(false);
			v.setDist(Double.POSITIVE_INFINITY);
			v.setPath(null);
			heap.insert(v);
		}

		Vertex start = cityList.get(city);   //Decrease key of starting vertex
		Vertex x = start;
		start.setDist(0);
		heap.decreaseKey(x, start);

		for(;;)
		{
			Vertex v = heap.deleteMin();   //Get closest vertex
			v.setKnown(true);
			for(int i=0; i<v.getList().size();i++)
            {
				Vertex w = v.getList().get(i);   //Get adjacent vertices
				if(!w.getKnown())
				{
					double cost = v.getDistances().get(i);
					if(cost + v.getDist() < w.getDist())
					{
						Vertex old = w;   //Compare costs
						w.setDist(v.getDist() + cost);
						w.setPath(v);
						heap.decreaseKey(old, w);
					}

					else if(w.getDist() == Double.POSITIVE_INFINITY)
                    {
						Vertex old = w;   //New vertex is found
						w.setDist(cost+v.getDist());
						w.setPath(v);
						heap.decreaseKey(old, w);
                    }
            }
        }

			if(heap.isEmpty())
				break;
		}
	}

	public void setPath(String city)   //Use recursion to get shortest path to city
	{
		path.clear();
		Vertex v = cityList.get(city);
		setPath(v);
	}

	private void setPath(Vertex v)   //Find path shortest recursively
	{
		if(v.getPath() != null)
        {
			setPath(v.getPath());
			path.add(v);
		}
		else
			path.add(v);
	}

	public double getDistance(String city)   //Returns the path distance
	{
		return getDistance(cityList.get(city));
	}

	public double getDistance(Vertex v)
	{
		return v.getDist();
	}

	public ArrayList <Vertex> getPath()   //Returns the path as an arrayList of vertices
	{
		return path;
	}
}
