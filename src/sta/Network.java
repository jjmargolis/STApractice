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
    
    public Network(String name)
    {
        System.out.println("NEW NETWORK NOW");
        int numNodes=0;
        int numLinks=0;
        int numZones=0;
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
                 } else {
                     //System.out.println("End of metadata reached");
                     end = true;
                 }
            }
            System.out.println("Closing metadata scanner");
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
        this.zones = zones;
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
            zones[i] = new Zone(i);
        }
        //do not construct new nodes, use existing zones
        for(int i = 0; i < nodes.length; i++){
            if(i < zones.length){
                nodes[i] = zones[i];
            } else {
                nodes[i] = new Node(i);
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
                        System.out.println("Looking at Origin " + data);
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
                            
                            System.out.println("Adding demand of " + demand + " to node " + nodeNum);
                            zones[currZone-1].addDemand(nodes[nodeNum-1], demand);
                            if (myReader.hasNext("Origin") || !(myReader.hasNext())){
                                System.out.println("Leaving origin " + currZone);
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
    
    public Node findNode(int id)
    {
        // fill this in
        return null;
    }
    
    public Link findLink(Node i, Node j)
    {
        // fill this in
        return null;
    }
    
    
    
    public void dijkstras(Node r)
    {
        /* **********
        Exercise 6(b)
        ********** */
        
        /* **********
        Exercise 6(c)
        ********** */
        
        // fill this in
    }
    
    
    /* **********
    Exercise 6(d)
    ********** */
    public Path trace(Node r, Node s)
    {
        // fill this in
        return null;
    }
    
    
    /* **********
    Exercise 7
    ********** */
    public double getTSTT()
    {
        // fill this in
        return 0.0;
    }
    
    public double getSPTT()
    {
        // fill this in
        return 0.0;
    }
    
    public double getTotalTrips()
    {
        // fill this in
        return 0.0;
    }

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
