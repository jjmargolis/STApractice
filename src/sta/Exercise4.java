/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sta;

/**
 *
 * @author micha
 */
public class Exercise4 
{
    public static void main(String[] args)
    {
        
        Zone n1 = new Zone(1);
        Zone n2 = new Zone(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        
        
        System.out.println(n1.getId());
        System.out.println(n2.getId());
        
        n1.addDemand(n2, 15);
        n1.addDemand(n3, 20);
        n1.addDemand(n3, 9);
        n2.addDemand(n3, 19);
        n2.addDemand(n4, 23);
        
        System.out.println(n1.getDemand(n2));
        System.out.println(n1.getDemand(n3));
        System.out.println(n1.getDemand(n4));
        System.out.println(n2.getDemand(n1));
        System.out.println(n2.getDemand(n3));
        System.out.println(n2.getDemand(n4));
        
        System.out.println(n1.getProductions());
        System.out.println(n2.getProductions());
        
        n1.setThruNode(false);
        
        System.out.println(n1.isThruNode());
        System.out.println(n2.isThruNode());
        
        
        autograde();
    }
    
    
    public static void autograde()
    {
        Autograde auto = new Autograde();
        
        
        Zone n1 = new Zone(1);
        Zone n2 = new Zone(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        
        auto.test(n1.getId() == 1);
        auto.test(n2.getId() == 2);
        
        auto.flush("Zone constructor");
        
        n1.addDemand(n2, 15);
        n1.addDemand(n3, 20);
        n1.addDemand(n3, 9);
        n2.addDemand(n3, 19);
        n2.addDemand(n4, 23);
        
        /*
        System.out.println("test 1 " + n1.getDemand(n2) + "should be " + 15);
        System.out.println("test 2 " + n1.getDemand(n3) + "should be " + 29);
        System.out.println("test 3 " + n1.getDemand(n4) + "should be " + 0);
        System.out.println("test 4 " + n2.getDemand(n1) + "should be " + 0);
        System.out.println("test 5 " + n2.getDemand(n3) + "should be " + 19);
        System.out.println("test 6 " + n2.getDemand(n4) + "should be " + 23);
        */

        auto.test(n1.getDemand(n2) == 15);
        auto.test(n1.getDemand(n3) == 29);
        auto.test(n1.getDemand(n4) == 0);
        auto.test(n2.getDemand(n1) == 0);
        auto.test(n2.getDemand(n3) == 19);
        auto.test(n2.getDemand(n4) == 23);
        
        auto.flush("Zone.addDemand() and Zone.getDemand()");
        
        
        auto.test(n1.getProductions() == 44);
        auto.test(n2.getProductions() == 42);
        
        auto.flush("getProductions()");
        
        n1.setThruNode(false);
        
        auto.test(n1.isThruNode() == false);
        auto.test(n2.isThruNode() == true);
        
        auto.flush("Zone.isThruNode()");
        
        auto.end();
    }
}
