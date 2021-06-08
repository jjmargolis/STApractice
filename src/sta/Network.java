/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sta;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Collections;
import java.io.FileNotFoundException;

/**
 *
 * @author micha
 */
public class Network 
{
    private Node[] nodes;
    private Link[] links;
    private Zone[] zones;
    
    private int firstThruNode;
    
    public Network(String name)
    {
        System.out.println("NEW NETWORK: " + name);
        int numNodes=0;
        int numLinks=0;
        int numZones=0;
        firstThruNode=0;
        try{
            File myObj = new File("C:/Users/jmarg/Documents/Github/STApractice/data/"+name+"/net.txt");
            Scanner myReader = new Scanner(myObj);
            Boolean end = false;
            while(myReader.hasNextLine() && end == false){
                String data = myReader.nextLine();
                //System.out.println("The next line is: " + data);
                if(!data.contains("<END OF METADATA>")) {
                    //System.out.println("Reading Metadata");
                    if(data.contains("<NUMBER OF ZONES>")){
                        data = data.substring(data.indexOf(">")+1);
                        data = data.trim();
                        numZones=Integer.parseInt(data);
                    }   
                    if(data.contains("<NUMBER OF NODES>")){
                        data = data.substring(data.indexOf(">")+1);
                        data = data.trim();
                        numNodes=Integer.parseInt(data);
                    }
                    if(data.contains("<NUMBER OF LINKS>")){
                        data = data.substring(data.indexOf(">")+1);
                        data = data.trim();
                        numLinks=Integer.parseInt(data);
                     }
                    if(data.contains("<FIRST THRU NODE>")){
                        data = data.substring(data.indexOf(">")+1);
                        data = data.trim();
                        firstThruNode=Integer.parseInt(data);
                     }
                 } else {
                     //System.out.println("End of metadata reached");
                     end = true;
                 }
            }
            //System.out.println("Closing metadata scanner");
            myReader.close(); 
        } catch (FileNotFoundException e){
            System.out.println("An error occurred reading the file for the metadata.");
            e.printStackTrace();
        }
        

        
        System.out.println("numNodes = " + numNodes);
        System.out.println("numLinks = " + numLinks);
        System.out.println("numZones = " + numZones);
        nodes = new Node[numNodes];
        links = new Link[numLinks];
        zones = new Zone[numZones];
        
        
        try
        {
            readNetwork(new File("data/"+name+"/net.txt"));
            readTrips(new File("data/"+name+"/trips.txt"));
        }
        catch(IOException ex)
        {
            ex.printStackTrace(System.err);
        }
    }
    
    
    public Network(Node[] nodes, Link[] links)
    {
        this.nodes = nodes;
        this.links = links;
    }
    
    
    public Link[] getLinks()
    {
        return links;
    }
    
    public Node[] getNodes()
    {
        return nodes;
    }
    
    public Zone[] getZones()
    {
        return zones;
    }
    
    
    
    
    public void readNetwork(File netFile) throws IOException
    {

        /* **********
        Exercise 5(b)
        ********** */
        for(int i = 0; i < zones.length; i++){
            zones[i] = new Zone(i+1);//ids start at 1, array index starts at 0
            if(i+1 < firstThruNode){
                zones[i].setThruNode(false);
            }
        }
        //do not construct new nodes, use existing zones
        for(int i = 0; i < nodes.length; i++){
            if(i < zones.length){
                nodes[i] = zones[i]; //ids start at 1, array index starts at 0
            } else {
                nodes[i] = new Node(i+1);//ids start at 1, array index starts at 0
            }    
        }        
        
        
        /* **********
        Exercise 5(c)
        ********** */
        Boolean isData = false;
        int i = 0;
        try{
            Scanner myReader = new Scanner(netFile);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                if(isData && i < links.length){
                    //System.out.println("Reading line " + i + ":" + data);
                    String[] columns = data.split("	");

                    int start = Integer.parseInt(columns[1])-1;//minus one because nodes start at 0
                    int end = Integer.parseInt(columns[2])-1;
                    double C = Double.parseDouble(columns[3]);
                    double t_ff = Double.parseDouble(columns[5]);
                    double alpha = Double.parseDouble(columns[6]);
                    double beta= Double.parseDouble(columns[7]);
                    links[i] = new Link(nodes[start], nodes[end], t_ff, C, alpha, beta);
                    nodes[start].addOutgoingLink(links[i]);
                    i++;
                }
                
                if(data.contains("Capacity")){
                    isData=true;
                }
            }
            myReader.close();
        } catch (IOException ex){
            ex.printStackTrace(System.err);
        }
    }
    
    /**
     * Reads the trips of the network from a file
     * @param tripsFile the String of the name of the network in the data file
     * @throws IOException if there is a problem reading the file
     */
    public void readTrips(File tripsFile) throws IOException
    {

        /* **********
        Exercise 5(d)
        ********** */
        
        Boolean isData = false;
        try{
            Scanner myReader = new Scanner(tripsFile);
                while(myReader.hasNextLine()){
                    String data = myReader.nextLine();
                    if(data.contains("Origin")){
                        Boolean thisZone = true;
                        data = data.substring(6);
                        data = data.trim();
                        //System.out.println("Looking at Origin " + data);
                        int currZone = Integer.parseInt(data);
                        while(thisZone){
                            //System.out.println("NEXT IS " + myReader.next());
                            String s = myReader.next();
                            int nodeNum = Integer.parseInt(s);   
                            //System.out.println("Set nodeNum to " + s);
                            s = myReader.next();
                            //System.out.println("This is a colon " + s);
                            s = myReader.next();
                            s = s.replace(";", "");
                            //System.out.println("Set demand to " + s);
                            double demand = Double.parseDouble(s);
                            
                            //System.out.println("Adding demand of " + demand + " to node " + nodeNum);
                            zones[currZone-1].addDemand(nodes[nodeNum-1], demand);
                            if (myReader.hasNext("Origin") || !(myReader.hasNext())){
                                //System.out.println("Leaving origin " + currZone);
                                thisZone = false;
                            }
                        }
                    }
                    
                    if(data.contains("END OF METADATA")){
                        isData = true;
                    }
                }
        } catch (IOException ex){
            ex.printStackTrace(System.err);
        }
    }
    
    /* **********
    Exercise 5(e)
    ********** */
    
    /**
     * Finds a node given the node ID
     * @param id
     * @return the node, null if node not found
     */
    public Node findNode(int id)
    {
        for (Node node : nodes) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }
    
    /**
     * Finds the link between two nodes
     * @param i Starting node
     * @param j Ending node
     * @return The link that connects the two nodes, null if one cannot be found
     */
    public Link findLink(Node i, Node j)
    {
        // fill this in
        for(Link l : links){
            if(l.getStart() == i && l.getEnd() == j){
                return l;
            }
        }
        return null;
    }
    
    
    /**
     * Shortest path algorithm, finds the shortest paths between all the nodes
     * Sets the cost and predecessor labels of the shortest path on all the links in the network from node r
     * @param r The starting node
     */
    public void dijkstras(Node r)
    {
        /* **********
        Exercise 6(b)
        ********** */
        //c is cost, N are nodes, A are links, 
        //Q is the hashset of all unsettled nodes
        //u is an instance variable node
        //t_uv is getTravelTime()
        HashSet<Node> Q = new HashSet<Node>();
        for(int i = 0; i < nodes.length; i++){
           nodes[i].cost = Double.MAX_VALUE;
           nodes[i].predecessor=null;
        }
    
        nodes[r.getId()-1].cost=0;
        Q.add(r);
        
        /* **********
        Exercise 6(c)
        ********** */
        while(!Q.isEmpty()){
            Node u = null;
            double smallc = Double.MAX_VALUE;
            for(Node j : Q){ //find node in Q with min cost
                //System.out.println("Looking at node " + j.toString() + " with cost " + j.cost);
                if(j.cost < smallc){
                    smallc = j.cost;
                    u = j;
                    //System.out.println("Current node is " + u.toString());
                }
            }
            Q.remove(u);
            //onto line 10, looping through all the outgoing links
            /*System.out.println("Outoing links of " + u.toString() + ":");
            for(Link v : u.getOutgoing()){
                System.out.print(v.toString() + " ");
            }
            System.out.println("");*/
            
            
            for(Link v : u.getOutgoing()){
                double alt = u.cost + v.getTravelTime();
                if(alt < v.getEnd().cost){
                    //System.out.println("Route from " + u.toString() + " to " + v.getEnd().toString() + " is cheaper than");
                    v.getEnd().cost=alt;
                    v.getEnd().predecessor=u;
                    //System.out.println("adding node " + v.getEnd().toString() + " to Q");
                    Q.add(v.getEnd());
                }
            }
        }
    }
    
    
    /* **********
    Exercise 6(d)
    ********** */
    public Path trace(Node r, Node s)
    {
        //System.out.println("Finding path from " + r.toString() + " to " + s.toString());
        if(s == null || r == null){
            System.out.println("Starting or ending point is null");
            return null;
        }
        Node n = s;
        Path pi = new Path();
        while(!(n==r)){
            //System.out.println("Finding link from " + n.predecessor.toString() + " to " + n.toString());
            if(n.predecessor != null){
                pi.add(findLink(n.predecessor, n));
                n=n.predecessor;
            } else {
                //System.out.println(n.toString() + "'s predeccessor is null, canceling loop early");
                n=r;
            }
        }
        pi = pi.reverse();
        return pi;
    }
    
    
    /* **********
    Exercise 7
    ********** */
    /**
     * Total System Travel Time, the total amount of travel time across all the links in the network. 
     * Sum of flow * travel time on all links
     * @return double TSTT
     */
    public double getTSTT()
    {
        double TSTT = 0;
        for(Link l : links){
            TSTT += (l.getFlow()*l.getTravelTime());
        }
        return TSTT;
    }
    
    /**
     * Shortest Path Travel Time
     * sum of the time on the shortest path times its demand
     * mu_rs = min travel time from r to s
     * d_rs = total demand from r to s
     * @return double SPTT the shortest path travel time of the network
     */
    public double getSPTT()
    {
        double SPTT = 0;
        for(Zone z : zones){
            double maxTravelTime = Double.MAX_VALUE;
            double mu_rs = 0;
            for(Link l : z.){
                if(l.getTravelTime() < maxTravelTime){
                    maxTravelTime = l.getTravelTime();
                    mu_rs = l.getTravelTime();
                }
            }
            SPTT += (z.getProductions()*mu_rs);
        }
        return SPTT;
    }
    
    /**
     * Get the total amount of trips in the network from the zone
     * @return double Number of trips
     */
    public double getTotalTrips()
    {
        double trips = 0;
        for(Zone z : zones){
            trips += z.getProductions();
        }
        return trips;
    }

    /**
     * Average Excess Cost
     * (Total System Travel Time - Shortest Path Travel Time)/Sum of all demand
     * @return AEC
     */
    public double getAEC()
    {
        // fill this in
        return 0.0;
    }
    
    
    
    
    
    
    
    
    
    
    /* **********
    Exercise 8(b)
    ********** */
    public double calculateStepsize(int iteration)
    {
        // fill this in
        return 0.0;
    }

    public void calculateNewX(double stepsize)
    {
        // fill this in
    }
    
    
    /* **********
    Exercise 8(c)
    ********** */
    public void calculateAON()
    {
        // fill this in
    }
    
    
    /* **********
    Exercise 8(d)
    ********** */
    public void msa(int max_iteration)
    {
        System.out.println("Iteration\tAEC");
        
        // fill this in
    }
    
    
    
    
    
    
}
