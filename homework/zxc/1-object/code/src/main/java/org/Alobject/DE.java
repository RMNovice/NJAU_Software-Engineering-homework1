//package org.Alobject;
//
//import java.util.Arrays;
//import java.util.Hashtable;
//import java.util.Random;
//import java.util.function.Function;
//public class DE extends FAlgorithm implements Algorithm{
////    private  Function<double[], Double> objectiveFunction;
////    private  int populationSize;
////    private  int maxIterations;
////    private  double[] res;
//    private static final double crossoverProbability=0.9;
//    private  double scalingFactor;
//
//    private  double[] lowerBounds;
//    private  double[] upperBounds;
//
//    public void input(Hashtable<String, Object> inputs){
//        super.input(inputs);
////        objectiveFunction=(Function<double[], Double>) inputs.get("target");
////        populationSize=(int)inputs.get("Size");
////        maxIterations=(int) inputs.get("MaxIter");
//        scalingFactor=(double) inputs.get("Factor");
//
//        lowerBounds=(double[]) inputs.get("lowB");
//        upperBounds=(double[]) inputs.get("upB");
//    }
//    private double[] optimize() {
//        Random random = new Random();
//        double[][] population = initializePopulation(random);
//        double[] fitnessValues = evaluatePopulation(population);
//        double[] bestSolution = getBestSolution(population, fitnessValues);
//
//        for (int i = 0; i < maxInterations; i++) {
//            double[][] trialPopulation = createTrialPopulation(population, random);
//            double[] trialFitnessValues = evaluatePopulation(trialPopulation);
//
//            for (int j = 0; j < populationSize; j++) {
//                if (trialFitnessValues[j] <= fitnessValues[j]) {
//                    population[j] = trialPopulation[j].clone();
//                    fitnessValues[j] = trialFitnessValues[j];
//
//                    if (trialFitnessValues[j] < objectiveFunction.apply(bestSolution)) {
//                        bestSolution = trialPopulation[j].clone();
//                    }
//                }
//            }
//        }
//
//        return bestSolution;
//    }
//    private double[] getBestSolution(double[][] population, double[] fitnessValues) {
//        int bestIndex = 0;
//        for (int i = 1; i < fitnessValues.length; i++) {
//            if (fitnessValues[i] < fitnessValues[bestIndex]) {
//                bestIndex = i;
//            }
//        }
//        return population[bestIndex];
//    }
//    private double[][] initializePopulation(Random random) {
//        double[][] population = new double[populationSize][lowerBounds.length];
//        for (int i = 0; i < populationSize; i++) {
//            for (int j = 0; j < lowerBounds.length; j++) {
//                population[i][j] = lowerBounds[j] + (upperBounds[j] - lowerBounds[j]) * random.nextDouble();
//            }
//        }
//        return population;
//    }
//    private double[] evaluatePopulation(double[][] population) {
//        double[] fitnessValues = new double[populationSize];
//        for (int i = 0; i < populationSize; i++) {
//            fitnessValues[i] = objectiveFunction.apply(population[i]);
//        }
//        return fitnessValues;
//    }
//    private double[][] createTrialPopulation(double[][] population, Random random) {
//        double[][] trialPopulation = new double[populationSize][lowerBounds.length];
//        for (int i = 0; i < populationSize; i++) {
//            int a = random.nextInt(populationSize);
//            int b = random.nextInt(populationSize);
//            int c = random.nextInt(populationSize);
//            while (a == i || b == i || c == i || a == b || a == c || b == c) {
//                a = random.nextInt(populationSize);
//                b = random.nextInt(populationSize);
//                c = random.nextInt(populationSize);
//            }
//            double[] trialSolution = new double[lowerBounds.length];
//            for (int j = 0; j < lowerBounds.length; j++) {
//                if (random.nextDouble() < crossoverProbability) {
//                    trialSolution[j] = population[c][j] + scalingFactor * (population[a][j] - population[b][j]);
//                    trialSolution[j] = Math.max(lowerBounds[j], Math.min(upperBounds[j], trialSolution[j]));
//                } else {
//                    trialSolution[j] = population[i][j];
//                }
//            }
//            trialPopulation[i] = trialSolution.clone();
//        }
//        return trialPopulation;
//    }
//    public void run(){
//        res=optimize();
//    }
//    public double[] output(){
//        double[] outputs = new double[res.length+1];
//        System.arraycopy(res, 0, outputs, 0, res.length);
//        outputs[res.length]=objectiveFunction.apply(res);
//        return outputs;
//    }
//}
