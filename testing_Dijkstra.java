//Class for testing Dijkstra's algorithm for flight routing (Ongoing)
//Uses priority queue that is absent in BFS
//Part of Final Project - for MOOC on Graph Theory, UCSD on Coursera

//******************************************************************

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DijkstraTester
{
	public static void main(String args[])
    {
        try
        {
            File cityPairs = new File("city_pairs.txt");   //Import files
            File cityxy = new File("city_codes.txt");
            Dijkstra d = new Dijkstra(cityPairs);

            int width = 700;   //Create dimensions for component
            int height = 500;
            mapComponent map = new mapComponent(cityxy, d, width, height);
            map.setPreferredSize(new Dimension(width,height));
            JScrollPane mapPane = new JScrollPane(map);

            JButton find = new JButton("Get path");   //Create button to make path
            find.addActionListener(new DijkstraListener(start, end, map, d, distance));

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(start);
            panel.add(end);
            panel.add(find);
            panel.add(distance);
        }


    catch(FileNotFoundException e)
	{
		System.out.println("Incorrect file name(s).");
	}

	catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Two files are required as command line arguments");
        }

    }

	public static class DijkstraListener implements ActionListener   //Action listener subclass
	{
		private JComboBox start;
		private JComboBox end;
		private mapComponent map;
		private Dijkstra d;
		private JTextArea distance;

		public DijkstraListener(JComboBox s, JComboBox e, mapComponent m, Dijkstra d, JTextArea dist)   //Initialize
		{
			start = s;
			end = e;
			map = m;
			this.d = d;
			distance = dist;
		}

		public void actionPerformed(ActionEvent ae)   //Displays shortest path
		{
		    if(ae.getActionCommand().equals("Get path"))
                {

                    String startCity = (String) start.getSelectedItem();   //Get start and end city
                    String endCity = (String) end.getSelectedItem();

                    if(!startCity.equals(endCity))   //Only display path if going to a different city
                        {
                            d.findPath(startCity);
                            d.setPath(endCity);
                            ArrayList<Vertex> path = d.getPath();

                            map.addPath(path);	//Paint the component
                            map.repaint();

                            String theDistance = String.valueOf(d.getDistance(endCity));   //Display the distance
                            distance.setText(theDistance.substring(0,6) + " Miles");
			}

			else   //No distance needs to be travelled if reached destination
             		{
				distance.setText("00.00 Miles");
				ArrayList<Vertex> path = null;
				map.addPath(path);
				map.repaint();
			}
		}
	}
}

