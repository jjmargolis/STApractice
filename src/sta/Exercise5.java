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
public class Exercise5 
{
    public static void main(String[] args)
    {
        Network network = new Network("Braess");
        
        
        Zone[] zones = network.getZones();
        Node[] nodes = network.getNodes();
        Link[] links = network.getLinks();
        
        for(int i = 0; i < zones.length; i++)
        {   
            System.out.println(zones[i]+" "+(zones[i] == nodes[i])+" "+zones[i].getOutgoing());
        }
        
        for(int i = zones.length; i < nodes.length; i++)
        {
            System.out.println(nodes[i]+" "+nodes[i].getOutgoing());
        }
        
        for(Link l : links)
        {
            System.out.println(l+"\t"+l.getCapacity()+"\t"+l.getTravelTime());
        }
        
        
        for(int r = 0; r < zones.length; r++)
        {
            for(int s = 0; s < zones.length; s++)
            {
                System.out.println(zones[r]+" "+zones[s]+" "+zones[r].getDemand(zones[s]));
            }
        }
        
        
        for(int i = 0; i < nodes.length; i++)
        {
            if(i < zones.length)
            {
                System.out.println(network.findNode(nodes[i].getId()) == zones[i]);
            }
            else
            {
                System.out.println(network.findNode(nodes[i].getId()) == nodes[i]);
            }
        }
        
        System.out.println(network.findNode(100));
        System.out.println(network.findNode(-1));

        
        for(int i = 0; i < links.length; i++)
        {
            System.out.println(network.findLink(network.findNode(links[i].getStart().getId()), network.findNode(links[i].getEnd().getId())) == links[i]);
        }
        
        if(nodes.length > 0)
        {
            System.out.println(network.findLink(null, nodes[0]));
        }
        
        
        
        
        
        autograde();
    }
    
    
    public static void autograde()
    {
        Autograde auto = new Autograde();
        
        Network network = new Network("Braess");
        
        auto.test(network.getNodes().length == 4);
        auto.test(network.getLinks().length == 5);
        auto.test(network.getZones().length == 4);
        
        auto.flush("reading metadata of \"net.txt\"");
        
        
        Zone[] zones = network.getZones();
        Node[] nodes = network.getNodes();
        Link[] links = network.getLinks();
        
        for(int i = 0; i < 4; i++)
        {
            if(i < nodes.length)
            {
                auto.test(zones[i] == nodes[i] && zones[i] != null);
            }
            else
            {
                auto.test(false);
            }
        }
        
        for(int i = zones.length; i< nodes.length; i++)
        {
            auto.test(nodes[i] != null);
        }
        
  
        
        auto.flush("construction of zones and nodes");
        
        
        for(int i = 0; i < 4; i++)
        {
            if(i < nodes.length)
            {
                auto.test(nodes[i].isThruNode() == (i+1 >= 2));
            }
            else
            {
                auto.test(false);
            }
        }
        
        
        auto.flush("isThruNode() parameter");
        
        
        
        for(int i = 0; i< 4; i++)
        {
            if(i < nodes.length)
            {
                auto.test(nodes[i].getId() == i+1);
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("correct ids for nodes");
        
        int starts[] = new int[]{1, 2, 1, 3, 2};
        int ends[] = new int[]{2, 4, 3, 4, 3};
        
        for(int i = 0; i < starts.length; i++)
        {
            if(links.length > i)
            {
                auto.test(links[i].getStart().getId() == starts[i]);
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("start nodes from data file");

        for(int i = 0; i < starts.length; i++)
        {
            if(links.length > i)
            {
                auto.test(links[i].getEnd().getId() == ends[i]);
            }
            else
            {
                auto.test(false);
            }
        }
        
        
        auto.flush("end nodes from data file");
        
        if(nodes.length > 0 && links.length > 0)
        {
            auto.test(nodes[0].getOutgoing().contains(links[0]));
        }
        else
        {
            auto.test(false);
        }
        
        if(nodes.length > 0 && links.length > 2)
        {
            auto.test(nodes[0].getOutgoing().contains(links[2]));
            
        }
        else
        {
            auto.test(false);
            
        }
        
        if(nodes.length > 0)
        {
            auto.test(nodes[0].getOutgoing().size() == 2);
        }
        else
        {
            auto.test(false);
        }
        
        if(nodes.length > 1 && links.length > 1)
        {
            auto.test(nodes[1].getOutgoing().contains(links[1]));
        }
        else
        {
            auto.test(false);
        }
        
        if(nodes.length > 1 && links.length > 4)
        {
            auto.test(nodes[1].getOutgoing().contains(links[4]));
        }
        else
        {
            auto.test(false);
        }
        
        if(nodes.length > 1)
        {
            auto.test(nodes[1].getOutgoing().size() == 2);
        }
        else
        {
            auto.test(false);
        }
        
        if(nodes.length > 2 && links.length > 3)
        {
            auto.test(nodes[2].getOutgoing().contains(links[3]));
        }
        else
        {
            auto.test(false);
        }
        
        if(nodes.length > 2)
        {
            auto.test(nodes[2].getOutgoing().size() == 1);
        }
        else
        {
            auto.test(false);
        }
        
        if(nodes.length > 3)
        {
            auto.test(nodes[3].getOutgoing().size() == 0);
        }
        else
        {
            auto.test(false);
        }
        

        auto.flush("outgoing links");
        
        int[] TTs = new int[]{1, 50, 45, 1, 5};
        double[] capacities = new double[]{100, 1000000, 1000000, 200, 1000000};
        
        for(int i = 0; i < TTs.length; i++)
        {
            if(links.length > i)
            {
                auto.test(links[i].getTravelTime() == TTs[i]);
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("link free flow travel times");
        
        for(int i = 0; i < capacities.length; i++)
        {
            if(links.length > i)
            {
                auto.test(Math.abs(links[i].getCapacity() - capacities[i]) < 0.1);
            }
            else
            {
                auto.test(false);
            }
        }
        
        
        
        auto.flush("link capacities");
        
        for(int r = 0; r < 4; r++)
        {
            for(int s = 0; s < 4; s++)
            {
                if(r < zones.length && s < zones.length)
                {
                    if(r == 0 && s == 3)
                    {
                        auto.test(zones[r].getDemand(zones[s]) == 4000);

                    }
                    else
                    {
                        auto.test(zones[r].getDemand(zones[s]) == 0);
                    }
                }
                else
                {
                    auto.test(false);
                }
            }
        }
        
        auto.flush("trips.txt file");
        
        
        
        network = new Network("SiouxFalls");
        
        
        zones = network.getZones();
        nodes = network.getNodes();
        links = network.getLinks();
        
        int[] outgoing = new int[]{2, 2, 3, 3, 3, 3, 2, 4, 3, 5, 4, 3, 2, 3, 4, 4, 3, 3, 3, 4, 3, 4, 3, 3};
        
        for(int i = 0; i < 4; i++)
        {
            if(i < nodes.length)
            {
                auto.test(nodes[i].getOutgoing().size() == outgoing[i]);
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("outgoing links of Sioux Falls");
        
        
        double[] productions = new double[]{8800.0, 4000.0, 2800.0, 11600.0, 6100.0, 7600.0, 12100.0, 16700.0, 16200.0, 45200.0, 
            22300.0, 13900.0, 14600.0, 14100.0, 21400.0, 26100.0, 23400.0, 4800.0, 12800.0, 18500.0, 11000.0, 24400.0, 14500.0, 7700.0};
        
        for(int i = 0; i < 24; i++)
        {
            if(i < zones.length)
            {
                auto.test(zones[i].getProductions() == productions[i]);
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("productions of Sioux Falls");
        
        
        for(int i = 0; i < 4; i++)
        {
            if(i < nodes.length)
            {
                if(i < zones.length)
                {
                    auto.test(network.findNode(nodes[i].getId()) == zones[i]);
                }
                else
                {
                    auto.test(network.findNode(nodes[i].getId()) == nodes[i]);
                }
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("Network.findNode()");
        
        for(int i = 0; i < links.length; i++)
        {
            auto.test(network.findLink(network.findNode(links[i].getStart().getId()), network.findNode(links[i].getEnd().getId())) == links[i]);
        }
        
        if(nodes.length > 0)
        {
            auto.test(network.findLink(null, nodes[0]) == null);
        }
        else
        {
            auto.test(false);
        }
        
        auto.flush("Network.findLink()");
        
        auto.end();
    }
}
