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
    private HashMap<Node, Double> demand;
    private boolean isThru;
    /* **********
    Exercise 4(a)
    ********** */
    public Zone(int id)
    {
        super(id);
        demand = new HashMap<>();
        isThru = true;
    }
    
    
    
    /* **********
    Exercise 4(b)
    ********** */
    public void addDemand(Node s, double d)
    {
        //System.out.println("Adding demand of " + d + " to node " + s.toString());
        if(demand.get(s) == null || demand.get(s) == 0){
            demand.put(s, d);
        } else {
            double dem = demand.get(s);
            demand.put(s, d+dem);
        }
    }
    
    /**
     * Get demand of this particular node
     * @param s the node under investigation
     * @return d The demand of the zone, 0 if null
     */
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
    /**
     * Productions is the sum of all the demand to this zone
     * @return Productions
     */
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
