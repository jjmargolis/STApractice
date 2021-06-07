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
public class Exercise6 
{
    public static void main(String[] args)
    {
        
        Network network = new Network("SiouxFalls");
        
        
        Zone[] zones = network.getZones();
        Node[] nodes = network.getNodes();
        Link[] links = network.getLinks();
        
        
        for(int i = 0; i < links.length; i++)
        {
            links[i].setFlow(1021 + i*500);
        }
        
        Path path = new Path();
        if(links.length > 13)
        {
            path.add(links[0]);
            path.add(links[3]);
            path.add(links[14]);
            path.add(links[12]);
        }

        System.out.println(path.isConnected());
        System.out.println(path.getTravelTime());        
        
        
        Path path2 = new Path();
        
        if(links.length > 4)
        {
            for(int i = 0; i < 4; i++)
            {
                path2.add(links[i]);
            }
        }
        
        System.out.println(path2.isConnected());
        System.out.println(path2.getTravelTime());   
        
        System.out.println(path.getSource()+" "+path.getDest());
        System.out.println(path2.getSource()+" "+path2.getDest());
        
        if(nodes.length > 0)
        {
            network.dijkstras(nodes[0]);

            for(int i = 0; i < nodes.length; i++)
            {
                System.out.println(nodes[i]+" "+nodes[i].cost+" "+nodes[i].predecessor);
                System.out.println(network.trace(nodes[0], nodes[i]));
            }

            network.dijkstras(nodes[0]);

            for(int i = 0; i < nodes.length; i++)
            {
                System.out.println("Path from "+nodes[0]+" to "+nodes[i]+": "+network.trace(nodes[0], nodes[i]));
            }
        }
        

        autograde();
    }
    
    public static void autograde()
    {
        Autograde auto = new Autograde();
        
        
        
        Network network = new Network("SiouxFalls");
        
        
        Zone[] zones = network.getZones();
        Node[] nodes = network.getNodes();
        Link[] links = network.getLinks();
        
        int[][] flowdata = new int[][]{
        new int[]{0, 1, 2},
        new int[]{1, 1, 3},
        new int[]{2, 2, 1},
        new int[]{3, 2, 6},
        new int[]{4, 3, 1},
        new int[]{5, 3, 4},
        new int[]{6, 3, 12},
        new int[]{7, 4, 3},
        new int[]{8, 4, 5},
        new int[]{9, 4, 11},
        new int[]{10, 5, 4},
        new int[]{11, 5, 6},
        new int[]{12, 5, 9},
        new int[]{13, 6, 2},
        new int[]{14, 6, 5},
        new int[]{15, 6, 8},
        new int[]{16, 7, 8},
        new int[]{17, 7, 18},
        new int[]{18, 8, 6},
        new int[]{19, 8, 7},
        new int[]{20, 8, 9},
        new int[]{21, 8, 16},
        new int[]{22, 9, 5},
        new int[]{23, 9, 8},
        new int[]{24, 9, 10},
        new int[]{25, 10, 9},
        new int[]{26, 10, 11},
        new int[]{27, 10, 15},
        new int[]{28, 10, 16},
        new int[]{29, 10, 17},
        new int[]{30, 11, 4},
        new int[]{31, 11, 10},
        new int[]{32, 11, 12},
        new int[]{33, 11, 14},
        new int[]{34, 12, 3},
        new int[]{35, 12, 11},
        new int[]{36, 12, 13},
        new int[]{37, 13, 12},
        new int[]{38, 13, 24},
        new int[]{39, 14, 11},
        new int[]{40, 14, 15},
        new int[]{41, 14, 23},
        new int[]{42, 15, 10},
        new int[]{43, 15, 14},
        new int[]{44, 15, 19},
        new int[]{45, 15, 22},
        new int[]{46, 16, 8},
        new int[]{47, 16, 10},
        new int[]{48, 16, 17},
        new int[]{49, 16, 18},
        new int[]{50, 17, 10},
        new int[]{51, 17, 16},
        new int[]{52, 17, 19},
        new int[]{53, 18, 7},
        new int[]{54, 18, 16},
        new int[]{55, 18, 20},
        new int[]{56, 19, 15},
        new int[]{57, 19, 17},
        new int[]{58, 19, 20},
        new int[]{59, 20, 18},
        new int[]{60, 20, 19},
        new int[]{61, 20, 21},
        new int[]{62, 20, 22},
        new int[]{63, 21, 20},
        new int[]{64, 21, 22},
        new int[]{65, 21, 24},
        new int[]{66, 22, 15},
        new int[]{67, 22, 20},
        new int[]{68, 22, 21},
        new int[]{69, 22, 23},
        new int[]{70, 23, 14},
        new int[]{71, 23, 22},
        new int[]{72, 23, 24},
        new int[]{73, 24, 13},
        new int[]{74, 24, 21},
        new int[]{75, 24, 23}};
        
        
        for(int r = 0; r < flowdata.length; r++)
        {
            int i = flowdata[r][0];
            int startid = flowdata[r][1];
            int endid = flowdata[r][2];
            
            Node start = network.findNode(startid);
            Node end = network.findNode(endid);
            
            if(start != null && end != null)
            {
                Link link = network.findLink(start, end);
                
                if(link != null)
                {
                    link.setFlow(1021 + i*500);
                }
            }
        }
        /*
        for(int i = 0; i < links.length; i++)
        {
            System.out.println("new int[]{"+i+", "+links[i].getStart()+", "+links[i].getEnd()+"},");
        }
        
        for(int i = 0; i < links.length; i++)
        {
            links[i].setFlow(1021 + i*500);
        }
        */
        Path path = new Path();
        
        if(links.length > 12)
        {
            path.add(links[0]);
            path.add(links[3]);
            path.add(links[14]);
            path.add(links[12]);
        }
        
        auto.test(path.isConnected() == true);
                
        
        
        Path path2 = new Path();
        
        if(links.length > 4)
        {
            for(int i = 0; i < 4; i++)
            {
                path2.add(links[i]);
            }
        }
        
        auto.test(path2.isConnected() == false);
        
        auto.flush("Link.isConnected()");
        

        if(path.size() > 0)
        {
            auto.test(path.getSource().getId() == 1);
        }
        if(path2.size() > 0)
        {
            auto.test(path2.getSource().getId() == 1);
        }
        
        auto.flush("Path.getSource()");
        
        if(path.size() > 0)
        {
            auto.test(path.getDest().getId() == 9);
        }
        
        if(path2.size() > 0)
        {
            auto.test(path2.getDest().getId() == 6);
        }
        
        auto.flush("Path.getDest()");
        
        
        
        auto.test(Math.abs(path.getTravelTime() - 24.37569064685568) < 0.01);
        auto.test(Math.abs(path2.getTravelTime() - 21.050172255908606) < 0.01);    
        
        
        auto.flush("Link.getTravelTime()");
        
        
        for(Node n : nodes)
        {
            n.cost = 1;
            n.predecessor = n;
        }
        
        if(nodes.length > 20)
        {
            network.dijkstras(nodes[20]);
        
            auto.test(nodes[20].predecessor == null);
        }
        
        for(int i = 1; i < 24; i++)
        {
            if(i < nodes.length)
            {
                if(i == 20)
                {
                    auto.test(nodes[i].cost == 0);
                }
                else
                {
                    auto.test(nodes[i].cost > 1);
                }
            }
            else
            {
                auto.test(false);
            }
        }
        
        for(Node n : nodes)
        {
            n.cost = 1;
            n.predecessor = n;
        }

        if(nodes.length > 10)
        {
            network.dijkstras(nodes[10]);

            auto.test(nodes[10].predecessor == null);
        }
        
        for(int i = 1; i < 24; i++)
        {
            if(i < nodes.length)
            {
                if(i == 10)
                {
                    auto.test(nodes[i].cost == 0);
                }
                else
                {
                    auto.test(nodes[i].cost > 1);
                }
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("Dijkstra's initialization");
        
        
        if(nodes.length > 0)
        {
            network.dijkstras(nodes[0]);
        }
        
        double[] costs = new double[]{0.0, 6.000002173366477, 4.000010704018527, 8.001086579821319, 10.002993278827729, 11.05012818657351, 20.254815898571675, 
            15.796757080158615, 15.185238922340416, 18.530190870067695, 15.441219860476464, 8.000533540987554, 11.131431652920096, 119.42992460379457, 
            25.730656873570418, 27.11253935123187, 138.53485409204677, 22.26303321972979, 31.539301160373306, 27.58642669899411, 650.1485820323414, 44.95245518013293, 
            342.23125672182744, 158.61247346764176};
        
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(Math.abs(costs[i] - nodes[i].cost) < 0.01);
            }
            else
            {
                auto.test(false);
            }
        }
        
        if(nodes.length > 10)
        {
            network.dijkstras(nodes[10]);
        }
        
        costs = new double[]{30.561574457187128, 35.335356588859646, 26.561407873336385, 22.558483478099397, 20.554540759663677, 26.36459203655937, 
            35.569279748557534, 31.111220930144476, 13.988425749509492, 10.587359148869036, 0.0, 30.56193071030541, 33.69282882223795, 103.98870474331811, 
            17.787825152371756, 42.42700320121773, 130.5920223708481, 37.57749706971565, 23.59646943917464, 42.900890548979966, 642.2057503111428, 
            37.00962345893427, 326.790036861351, 181.1738706369596};
        
        
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(Math.abs(costs[i] - nodes[i].cost) < 0.01);
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("Dijkstra's cost labels");
        
        
        
        if(nodes.length > 0)
        {
            network.dijkstras(nodes[0]);
        }
        
        int[] preds = new int[]{-1, 1, 1, 3, 4, 2, 8, 6, 5, 9, 4, 3, 12, 11, 10, 18, 10, 7, 15, 18, 22, 15, 14, 13};
        
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(nodes[i].predecessor == network.findNode(preds[i]));
            }
            else
            {
                auto.test(false);
            }
        }
        
        preds = new int[]{3, 6, 4, 5, 9, 5, 8, 6, 10, 11, -1, 3, 12, 11, 10, 18, 10, 7, 15, 18, 22, 15, 14, 13};
        
        if(nodes.length > 10)
        {
            network.dijkstras(nodes[10]);
        }
        
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(nodes[i].predecessor == network.findNode(preds[i]));
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("Dijkstra's predecessor labels");
        
        
        String[] traces = new String[]{"[]", "[(1, 2)]", "[(1, 3)]", "[(1, 3), (3, 4)]", "[(1, 3), (3, 4), (4, 5)]", 
            "[(1, 2), (2, 6)]", "[(1, 2), (2, 6), (6, 8), (8, 7)]", "[(1, 2), (2, 6), (6, 8)]", "[(1, 3), (3, 4), (4, 5), (5, 9)]", 
            "[(1, 3), (3, 4), (4, 5), (5, 9), (9, 10)]", "[(1, 3), (3, 4), (4, 11)]", "[(1, 3), (3, 12)]", "[(1, 3), (3, 12), (12, 13)]", 
            "[(1, 3), (3, 4), (4, 11), (11, 14)]", "[(1, 3), (3, 4), (4, 5), (5, 9), (9, 10), (10, 15)]", "[(1, 2), (2, 6), (6, 8), (8, 7), (7, 18), (18, 16)]", 
            "[(1, 3), (3, 4), (4, 5), (5, 9), (9, 10), (10, 17)]", "[(1, 2), (2, 6), (6, 8), (8, 7), (7, 18)]", 
            "[(1, 3), (3, 4), (4, 5), (5, 9), (9, 10), (10, 15), (15, 19)]", "[(1, 2), (2, 6), (6, 8), (8, 7), (7, 18), (18, 20)]", 
            "[(1, 3), (3, 4), (4, 5), (5, 9), (9, 10), (10, 15), (15, 22), (22, 21)]", "[(1, 3), (3, 4), (4, 5), (5, 9), (9, 10), (10, 15), (15, 22)]", 
            "[(1, 3), (3, 4), (4, 11), (11, 14), (14, 23)]", "[(1, 3), (3, 12), (12, 13), (13, 24)]"};
        

        if(nodes.length > 0)
        {
            network.dijkstras(nodes[0]);
        }
       
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(network.trace(nodes[0], nodes[i]).toString().equals(traces[i]));
            }
            else
            {
                auto.test(false);
            }
        }
        
        traces = new String[]{"[(11, 10), (10, 9), (9, 5), (5, 4), (4, 3), (3, 1)]", "[(11, 10), (10, 9), (9, 5), (5, 6), (6, 2)]", 
            "[(11, 10), (10, 9), (9, 5), (5, 4), (4, 3)]", "[(11, 10), (10, 9), (9, 5), (5, 4)]", "[(11, 10), (10, 9), (9, 5)]", 
            "[(11, 10), (10, 9), (9, 5), (5, 6)]", "[(11, 10), (10, 9), (9, 5), (5, 6), (6, 8), (8, 7)]", "[(11, 10), (10, 9), (9, 5), (5, 6), (6, 8)]", 
            "[(11, 10), (10, 9)]", "[(11, 10)]", "[]", "[(11, 10), (10, 9), (9, 5), (5, 4), (4, 3), (3, 12)]", "[(11, 10), (10, 9), (9, 5), (5, 4), (4, 3), (3, 12), (12, 13)]", 
            "[(11, 14)]", "[(11, 10), (10, 15)]", "[(11, 10), (10, 9), (9, 5), (5, 6), (6, 8), (8, 7), (7, 18), (18, 16)]", "[(11, 10), (10, 17)]", 
            "[(11, 10), (10, 9), (9, 5), (5, 6), (6, 8), (8, 7), (7, 18)]", "[(11, 10), (10, 15), (15, 19)]", "[(11, 10), (10, 9), (9, 5), (5, 6), (6, 8), (8, 7), (7, 18), (18, 20)]", 
            "[(11, 10), (10, 15), (15, 22), (22, 21)]", "[(11, 10), (10, 15), (15, 22)]", "[(11, 14), (14, 23)]", 
            "[(11, 10), (10, 9), (9, 5), (5, 4), (4, 3), (3, 12), (12, 13), (13, 24)]"};
        
        
        if(nodes.length > 10)
        {
            network.dijkstras(nodes[10]);
        }
        
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(network.trace(nodes[10], nodes[i]).toString().equals(traces[i]));
            }
            else
            {
                auto.test(false);
            }
        }
        
        auto.flush("trace() after Dijkstra's");
        
        
        
        costs = new double[]{0.0, 6.000002173366477, 4.000010704018527, 8.001086579821319, 10.002993278827729, 11.05012818657351, 
            20.254815898571675, 15.796757080158615, 15.185238922340416, 18.530190870067695, 15.441219860476464, 8.000533540987554, 11.131431652920096, 
            119.42992460379457, 25.730656873570418, 27.11253935123187, 138.53485409204677, 22.26303321972979, 31.539301160373306, 27.58642669899411, 
            650.1485820323414, 44.95245518013293, 342.23125672182744, 158.61247346764176};
        
        if(nodes.length > 0)
        {
            network.dijkstras(nodes[0]);
        }
       
        
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(Math.abs(network.trace(nodes[0], nodes[i]).getTravelTime() - costs[i]) < 0.01);
            }
        }
        
        
        costs = new double[]{30.561574457187128, 35.335356588859646, 26.561407873336385, 22.558483478099397, 20.554540759663677, 26.36459203655937, 
            35.569279748557534, 31.111220930144476, 13.988425749509492, 10.587359148869036, 0.0, 30.56193071030541, 33.69282882223795, 
            103.98870474331811, 17.787825152371756, 42.42700320121773, 130.5920223708481, 37.57749706971565, 23.59646943917464, 42.900890548979966, 
            642.2057503111428, 37.00962345893427, 326.790036861351, 181.1738706369596};
        
        if(nodes.length > 10)
        {
            network.dijkstras(nodes[10]);
        }
        
        
        for(int i = 0; i < 24; i++)
        {
            if(i < nodes.length)
            {
                auto.test(Math.abs(network.trace(nodes[10], nodes[i]).getTravelTime() - costs[i]) < 0.01);
            }
        }
        
        auto.flush("costs of paths found by trace()");
        
        auto.end();
    }
}
