//import java.util.Hashtable;
//import java.util.function.Function;
//
//import org.Alobject.Algorithm;
//import org.Alobject.DE;
//import org.Alobject.PSO;
//
//public class Main {
//    public static double[] pso(Function<double[], Double> objectiveFunction){
//        Algorithm pso= new PSO();
//        Hashtable<String, Object> dict = new Hashtable<>();
//
//        dict.put("p_dim", 2);
//        dict.put("target",objectiveFunction);
//        dict.put("Size",40);
//
//        dict.put("MaxIter",1000);
//        dict.put("Factor1",2.0);
//        dict.put("Factor2",2.0);
//        dict.put("W_up",1.0);
//        dict.put("W_low", 0.0);
//
//        pso.input(dict);
//        pso.run();
//        double[] res=pso.output();
//        return res;
//    }
//    public static double[] de(Function<double[], Double> objectiveFunction){
//        Algorithm de= new DE();
//        Hashtable<String, Object> dict = new Hashtable<>();
//
//        dict.put("target",objectiveFunction);
//        dict.put("Size",50);
//        dict.put("Factor",0.8);
//        dict.put("MaxIter",1000);
//        dict.put("lowB",new double[]{-513.0, -513.0});
//        dict.put("upB", new double[]{513.0, 513.0});
//        de.input(dict);
//        de.run();
//        double[] res=de.output();
//        return res;
//    }
//    public static void main(String[] args){
////        Function<double[], Double> objectiveFunction = x -> Math.pow(x[0], 2) + Math.pow(x[1], 2)+x[0];
//        Function<double[], Double> objectiveFunction=x->{//schwefel
//                                                                double sum = 0.0;
//                                                                for (double xi : x) {
//                                                                    sum += -xi * Math.sin(Math.sqrt(Math.abs(xi)));
//                                                                }
//                                                                return sum;
//                                                            };
//        double[] res_de=de(objectiveFunction);
//        System.out.println("DE");
//        System.out.println("    Best solution: (" + res_de[0]+","+res_de[1]+")");
//        System.out.println("    Best value: " + res_de[2]);
//        double[] res_pso=pso(objectiveFunction);
//        System.out.println("PSO");
//        System.out.println("    Best solution: (" + res_pso[0]+","+res_pso[1]+")");
//        System.out.println("    Best value: " + res_pso[2]);
//    }
//}
