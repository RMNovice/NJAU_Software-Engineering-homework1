//package org.Alobject;
//
//import java.util.Hashtable;
//import java.util.function.Function;
//
//public interface Algorithm {
//    public void input(Hashtable<String, Object> inputs);
//    public void run();
//    public double[] output();
//}
//class FAlgorithm {
//    public int populationSize;
//    public double[] res;
//    public int maxInterations;
//    public Function<double[],Double> objectiveFunction;
//    void input(Hashtable<String, Object> inputs){
//        objectiveFunction= (Function<double[], Double>) inputs.get("target");
//        populationSize= (int) inputs.get("Size");
//        maxInterations= (int) inputs.get("MaxIter");
//
//    }
//
//}