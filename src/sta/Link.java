/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sta;

/**
 *
 * @author micha
 * 
 */
public class Link 
{
    
    // the flow on this link
    private double x;
    
    // parameters for travel time calculation. t_ff is the free flow time, C is the capacity, alpha and beta are the calibration parameters in the BPR function
    private double t_ff, C, alpha, beta;
    
    // the start and end nodes of this link. Links are directed.
    private Node start, end;

    //xStar, use for successive averages
    private double x_star;
    
    // construct this Link with the given parameters
    /**
     * 
     * @param start The node the link starts at 
     * @param end The node the link ends at
     * @param t_ff Link travel time/cost
     * @param C Link Capacity
     * @param alpha Constant alpha
     * @param beta Constant Beta
     */
    public Link(Node start, Node end, double t_ff, double C, double alpha, double beta)
    {
        this.start = start;
        this.end = end;
        this.t_ff = t_ff;
        this.C = C;
        this.alpha = alpha;
        this.beta = beta;
        x_star=0;
        /*if(start != null){
            start.addOutgoingLink(this);
        }*/

    }
    
    // updates the flow on this link
    public void setFlow(double x)
    {
        this.x = x;
    }
    
    
    /* **********
    Exercise 1
    ********** */
    public double getTravelTime()
    {
        // fill this in
        double t_ij = t_ff*(1+(alpha*(Math.pow((x/C),beta))));
        
        return t_ij;
    }
    
    
    
    
    /* **********
    Exercise 2(a)
    ********** */
    public double getCapacity()
    {
        return C;
    }
    
    public double getFlow()
    {
        return x;
    }
    
    
    
    public int hashCode()
    {
        return getStart().getId()+getEnd().getId()*10000;
    }
    
    /**
     * Flips the order of the link
     * @return The new reversed link
     */
    public Link reverse(){
        Node s = this.start;
        Node e = this.end;
        return new Link(e, s, t_ff, C, alpha, beta);
    }
    
    
    
    
    
    /* **********
    Exercise 3(a)
    ********** */
    public Node getStart()
    {
        // fill this in
        return start;
    }
    
    public Node getEnd()
    {
        // fill this in
        return end;
    }
    
    
    /* **********
    Exercise 3(c)
    ********** */
    public String toString()
    {
        // fill this in
        return "(" + start.toString() + ", " + end.toString() + ")";
    }
    
    
    /* **********
    Exercise 8(a)
    ********** */
    public void addXstar(double flow)
    {
        x_star+=flow;
    }
    
    /* **********
    Exercise 8(b)
    ********** */

    /**
     * Sets the new flow from lamda, as part of the method of successive averages
     * @param stepsize lamda, the weight
     */
    public void calculateNewX(double stepsize)
    {
        x = ((1-stepsize)*x) + (stepsize*x_star);
        x_star = 0; //because we just implemented x_star into x
    }
}
