package ecassign;

import java.util.Scanner;
	import java.io.*;
	import org.graphstream.graph.Graph;
	import org.graphstream.graph.Node;
	import org.graphstream.graph.implementations.SingleGraph;
		
public class AssignmentX2 {
	

		//sets graph characteristics
		static String styleSheet =
		         "node{" +
		             "fill-color: cyan, magenta;"+
		             "fill-mode: gradient-diagonal1;"+
		             "size: 30px;"+
		             "text-size: 30;"+ "text-alignment: center;"+ "text-color: blue;" +
		             "}";

		
		public static void main(String[] args) {
			String line;
			Node edge1 = null;
			String edge;
			
			try(
				Scanner in = new Scanner(new File("graph.txt"));
				Scanner user = new Scanner(System.in);
		       ){
				
				//creates new graph object
				Graph g = new SingleGraph("Assignment4");
				
				//runs until no more lines in the file
				while(in.hasNext()) {
					line = in.nextLine();
					System.out.println(line);
					String a[] = line.split(" ");
				
					//goes through each line in the file
					for(int i = 0; i < a.length; i++) {
						
						//checks to see if node is already created in graph
						if(g.getNode(a[i])==null) {
							g.addNode(a[i]);
						}
						
						//first int on line is the first node on the edge
						if(i == 0) {
							edge1 = g.getNode(a[i]);
						}
						//concats the name and creates the edge
						else {
							edge = edge1.getId() + a[i];
							g.addEdge( edge , edge1, g.getNode(a[i]));	
						}	
					}//end of for loop
				}//end of while loop. creating graph complete.
				
				//creates graph
				for(Node n: g) {
		            g.getNode(n.getId()).setAttribute("ui.label", n.getId());
		        }
					
				System.setProperty("org.graphstream.ui", "swing");
				g.setAttribute("ui.stylesheet", styleSheet);
				
				//displays graph
		        g.display();
				
		        
				}//end of try
				//catches if graph.txt isn't on computer
				catch(FileNotFoundException fnf){
		            System.out.println("Could not find file.");
				}
		

			}
}
