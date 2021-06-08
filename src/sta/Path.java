/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sta;

import java.util.ArrayList;

/**
 *
 * @author micha
 */
public class Path extends ArrayList<Link>
{
    /**
     * Get the total travel time of the whole path
     * @return double of the travel time
     */
    public double getTravelTime()
    {
        double travelTime = 0;
        for(Link l : this){
            travelTime += l.getTravelTime();
        }
        return travelTime;
    }
    
    /**
     * Checks if the links in the path are connected
     * @return Boolean True if they are connected
     */
    public boolean isConnected()
    {
        Boolean connected = true;
        
        for(int i = 0; i < this.size(); i++){
            if(i+1 < this.size() && this.get(i).getEnd().getId() != this.get(i+1).getStart().getId()){
                connected = false;
            }
        }
        return connected;
    }
    
    /* **********
    Exercise 6(a)
    ********** */
    /**
     * Get the source of the path
     * @return Node of the source, the first node in the list
     */
    public Node getSource()
    {
        return this.get(0).getStart(); //source is first item in the list
    }
    
    /**
     * Reverses the path around, also reverses each link
     * @return The reversed path
     */
    public Path reverse(){
        Path newPath = new Path();
        for(int i = this.size()-1; i >=0; i--){
            Link l = this.get(i);
            newPath.add(l);
        }
        //System.out.println("Original Path: " + this.toString());
        //System.out.println("Reversed Path: " + newPath.toString());
        return newPath;
    }
    
    /**
     * Get the destination of the path
     * @return Node of the destination, the last node in the path
     */
    public Node getDest()
    {
        return this.get(this.size()-1).getEnd();
    }
    
    /* **********
    Exercise 8(a)
    ********** */

    /**
     * Adds the specified flow to the x_star of all the links in the path
     * @param h The new flow to be added
     */
    public void addHstar(double h)
    {
        for(Link l : this){
            l.addXstar(h);
        }
    }
}
