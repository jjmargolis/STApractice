/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sta;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class Node 
{
    
    private int id;
    private ArrayList<Link> outGoing = new ArrayList<Link>();    
    /* **********
    Exercise 3(b)
    ********** */
    
    
    public Node(){}
    
    public Node(int id)
    {
        this.id=id;
    }
    
    public int getId()
    {
        return id;
    }
    
    
    
    
    
    public boolean isThruNode()
    {
        return true;
    }
    
    
    public int hashCode()
    {
        return getId();
    }
    
    
    
    /* **********
    Exercise 3(c)
    ********** */
    public String toString()
    {
        // fill this in
        
        return String.valueOf(id);
    }
    
    
    
    
    /* **********
    Exercise 3(d)
    ********** */
    public ArrayList<Link> getOutgoing()
    {
        return this.outGoing;
    }
    
    public void addOutgoingLink(Link l)
    {
        this.outGoing.add(l);
    }
    
    
    
    
    
    
    protected double cost;
    protected Node predecessor;
}
