package org.example;

import org.Alobject.Algorithm;
import org.Alobject.PSO;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.function.Function;


public class Main {
    public static void main(String[] args){
        Algorithm pso= new PSO();

        Hashtable<String, Object> dict = new Hashtable<>();
        Function<double[], Double> objectiveFunction = x -> {
            double sum = 0.0;
            for (double xi : x) {
                sum += -xi * Math.sin(Math.sqrt(Math.abs(xi)));
            }
            return sum;
        };
        dict.put("p_dim", 2);
        dict.put("target",objectiveFunction);
        dict.put("Size",40);
        dict.put("MaxIter",1000);
        dict.put("Factor1",2.0);
        dict.put("Factor2",2.0);
        dict.put("W_up",1.0);
        dict.put("W_low", 0.0);

        pso.input(dict);
        pso.run();
        double[] res_pso=pso.output();

        System.out.println("PSO");
        System.out.println("    Best solution: (" + res_pso[0]+","+res_pso[1]+")");
        System.out.println("    Best value: " + res_pso[2]);
    }
}