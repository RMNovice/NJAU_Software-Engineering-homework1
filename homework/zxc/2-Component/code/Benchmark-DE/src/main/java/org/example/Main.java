package org.example;

import org.Alobject.Algorithm;
import org.Alobject.DE;
import java.util.Hashtable;
import java.util.function.Function;

public class Main {
    public static void main(String[] args){
        Algorithm de= new DE();

        Hashtable<String, Object> dict = new Hashtable<>();
        Function<double[], Double> objectiveFunction = x -> {
            double sum = 0.0;
            for (double xi : x) {
                sum += -xi * Math.sin(Math.sqrt(Math.abs(xi)));
            }
            return sum;
        };
        dict.put("target",objectiveFunction);
        dict.put("Size",50);
        dict.put("Factor",0.8);
        dict.put("MaxIter",1000);
        dict.put("lowB",new double[]{-513.0, -513.0});
        dict.put("upB", new double[]{513.0, 513.0});

        de.input(dict);
        de.run();
        double[] res_de=de.output();

        System.out.println("DE");
        System.out.println("    Best solution: (" + res_de[0]+","+res_de[1]+")");
        System.out.println("    Best value: " + res_de[2]);

    }
}