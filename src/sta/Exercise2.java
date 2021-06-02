/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import static sta.Exercise1.autograde;

/**
 *
 * @author micha
 */
public class Exercise2 
{
    public static void main(String[] args)
    {
        Link[] array = new Link[]{
            new Link(null, null, 10, 2580, 0.15, 4),
            new Link(null, null, 6, 1100, 0.15, 4),
            new Link(null, null, 12, 1900, 0.35, 2),
            new Link(null, null, 23, 1280, 0.15, 4)
        };
        
        for(Link l : array)
        {
            l.setFlow(1500);
        }
        
        findCongestedLinks(array);
        
        
        
        
        
        autograde();
    }
    
    /* **********
    Exercise 2(b)
    ********** */
    public static void findCongestedLinks(Link[] array)
    {
        // print travel times of all links. Then for each link, if it has volume/capacity > 1, print yes. Otherwise, print no. Print each on a separate line. 
       for(Link l : array){
            System.out.print("link " + l );
            System.out.print(l.getTravelTime());
            if((l.getFlow()/l.getCapacity()) > 1){
                System.out.println(" yes");
            } else if ((l.getFlow()/l.getCapacity()) <= 1){
                System.out.println(" no");
            }
        }
    }
    
    public static void autograde()
    {
        double[] capacities = new double[]{2580, 1130, 1280, 1900, 1460};
        double[] flows = new double[]{1200, 1300, 1400, 1500, 1600};
        
        Link[] array = new Link[]{
            new Link(null, null, 10, 2580, 0.15, 4),
            new Link(null, null, 11, 1130, 0.15, 4),
            new Link(null, null, 6, 1280, 0.15, 4),
            new Link(null, null, 12, 1900, 0.35, 2),
            new Link(null, null, 13, 1460, 0.15, 4)
        };
        
        for(int i = 0; i < array.length; i++)
        {
            array[i].setFlow(flows[i]);
        }
        

        
        double[] answers = new double[]{10.070200049666536, 13.89030436990636, 7.287996768951416, 14.617728531855956, 15.812568567186961};
        String[] answers2 = new String[]{"no", "yes", "yes", "no", "yes"};
        
        
        Autograde auto = new Autograde();
        
        
        for(int i = 0; i < array.length; i++)
        {
            auto.test(array[i].getCapacity() == capacities[i]);
        }
        
        auto.flush("Link.getCapacity()");
        
        
        
        for(int i = 0; i < array.length; i++)
        {
            auto.test(array[i].getFlow() == flows[i]);
        }
        
        auto.flush("Link.getFlow()");
        
        
        
        try
        {
            PrintStream normalOut = System.out;
            
            File tempFile = new File("temporary_testing.txt");
            PrintStream fileout = new PrintStream(new FileOutputStream(tempFile), true);
            System.setOut(fileout);
            
            findCongestedLinks(array);
            fileout.close();
            
            System.setOut(normalOut);
            
            
            Scanner filein = new Scanner(tempFile);
            
            for(int i = 0; i < array.length; i++)
            {
                String answer1 = "";
                int answer2 = 0;
                
                double answer3 = 0;
                String answer4 = "";
                
                if(filein.hasNext())
                {
                    answer1 = filein.next();
                }
                
                if(filein.hasNextInt())
                {
                    answer2 = filein.nextInt();
                }
                else if(filein.hasNext())
                {
                    filein.next();
                }
                
                
                if(filein.hasNextDouble())
                {
                    answer3 = filein.nextDouble();
                }
                else if(filein.hasNext())
                {
                    filein.next();
                }
                
                if(filein.hasNext())
                {
                    answer4 = filein.next();
                }
                
                auto.test(answer1.equalsIgnoreCase("link"));
                auto.test(answer2 == i+1);
                auto.test(Math.abs(answer3 - answers[i]) < 0.01);
                auto.test(answer4.equalsIgnoreCase(answers2[i]));
            }

            filein.close();
            
            tempFile.delete();
        }
        catch(IOException ex)
        {
            ex.printStackTrace(System.err);
        }
        
        
        auto.flush("findCongestedLinks()");
        auto.end();
        
        
    }
}
