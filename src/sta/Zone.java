/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sta;

import java.util.HashMap;

/**
 *
 * @author micha
 */
public class Zone extends Node
{
    private HashMap<Node, Double> demand = new HashMap<Node, Double>();
    private boolean isThru;
    /* **********
    Exercise 4(a)
    ********** */
    public Zone(int id)
    {
        super(id);
    }
    
    
    
    /* **********
    Exercise 4(b)
    ********** */
    public void addDemand(Node s, double d)
    {
        demand.put(s, d);
    }
    
    public double getDemand(Node s)
    {
        if(demand.get(s) == null){
            return 0;
        } else {
            return demand.get(s);
        }
    }
    
    
    /* **********
    Exercise 4(c)
    ********** */
    public double getProductions()
    {
        double dem = 0;
        for(Node i : demand.keySet()){
            dem += getDemand(i);
        }
        return dem;
    }
    
    
    
    /* **********
    Exercise 4(d)
    ********** */
    public boolean isThruNode()
    {
        // fill this in
        return isThru;
    }
    
    public void setThruNode(boolean thru)
    {
        this.isThru=thru;
    }
}
