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
    
    
    /* **********
    Exercise 3(b)
    ********** */
    
    
    public Node(){}
    
    public Node(int id)
    {
        // fill this in
    }
    
    public int getId()
    {
        // fill this in
        return 0;
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
        return "";
    }
    
    
    
    
    /* **********
    Exercise 3(d)
    ********** */
    public ArrayList<Link> getOutgoing()
    {
        // fill this in
        return null;
    }
    
    public void addOutgoingLink(Link l)
    {
        // fill this in
    }
    
    
    
    
    
    
    protected double cost;
    protected Node predecessor;
}
