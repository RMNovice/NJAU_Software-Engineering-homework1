//package org.Alobject;
//
//import java.util.Hashtable;
//import java.util.Random;
//import java.util.function.Function;
//public class PSO extends FAlgorithm implements Algorithm{
////    private int SWARM_SIZE; // 粒子群规模
////    private int MAX_ITERATION; // 最大迭代次数
////    private double[] res;
////    private Function<double[], Double> fitnessFunction; // 目标函数
//    private double C1; // 学习因子1
//    private double C2 ; // 学习因子2
//    private double W_UPPERBOUND; // 惯性权重上界
//    private double W_LOWERBOUND; // 惯性权重下界
//    private int problemDimension;
//
//
//
//
//    private Particle[] swarm;
//    private double[] pBest; // 个体最优解
//    private double[] gBest; // 全局最优解
//    private Random random;
//
//
//
//    public void input(Hashtable<String, Object> inputs){
////        fitnessFunction=(Function<double[], Double>) inputs.get("target");
////        SWARM_SIZE=(int)inputs.get("Size");
////        MAX_ITERATION=(int) inputs.get("MaxIter");
//        super.input(inputs);
//        C1=(double) inputs.get("Factor1");
//        C2=(double) inputs.get("Factor2");
//        W_UPPERBOUND=(double) inputs.get("W_up");
//        W_LOWERBOUND=(double) inputs.get("W_low");
//        problemDimension= (int) inputs.get("p_dim");
//
//        swarm = new Particle[populationSize];
//        pBest = new double[populationSize];
//        gBest = new double[problemDimension];
//        random = new Random();
//
//        for (int i = 0; i < populationSize; i++) {
//            swarm[i] = new PSO.Particle(problemDimension);
//            pBest[i] = Double.MAX_VALUE;
//        }
//    }
//
//    private double[] solve() {
//        double[] bestPosition = new double[swarm[0].getProblemDimension()];
//
//        for (int i = 0; i < maxInterations; i++) {
//            // 更新每个粒子的速度和位置
//            for (int j = 0; j < populationSize; j++) {
//                PSO.Particle particle = swarm[j];
//                double[] position = particle.getPosition();
//                double[] velocity = particle.getVelocity();
//                double[] pBestPosition = particle.getpBestPosition();
//
//                // 更新速度
//                for (int k = 0; k < position.length; k++) {
//                    double r1 = random.nextDouble();
//                    double r2 = random.nextDouble();
//                    velocity[k] = velocity[k] + C1 * r1 * (pBestPosition[k] - position[k])
//                            + C2 * r2 * (gBest[k] - position[k]);
//
//                    // 限制速度范围
//                    if (velocity[k] > W_UPPERBOUND) {
//                        velocity[k] = W_UPPERBOUND;
//                    } else if (velocity[k] < -W_UPPERBOUND) {
//                        velocity[k] = -W_UPPERBOUND;
//                    }
//                }
//
//                // 更新位置
//                for (int k = 0; k < position.length; k++) {
//                    position[k] = position[k] + velocity[k];
//
//                    // 限制位置范围
//                    if (position[k] > 100) {
//                        position[k] = 100;
//                    } else if (position[k] < -100) {
//                        position[k] = -100;
//                    }
//                }
//
//                // 更新个体最优解
//                double fitness = objectiveFunction.apply(position);
//                if (fitness < pBest[j]) {
//                    pBest[j] = fitness;
//                    System.arraycopy(position, 0, pBestPosition, 0, position.length);
//                }
//
//                // 更新全局最优解
//                if (fitness < objectiveFunction.apply(gBest)) {
//                    System.arraycopy(position, 0, gBest, 0, position.length);
//                }
//            }
//            // 记录全局最优解
//            if (i == 0 || objectiveFunction.apply(gBest) < objectiveFunction.apply(bestPosition)) {
//                System.arraycopy(gBest, 0, bestPosition, 0, gBest.length);
//            }
//        }
//
//        return bestPosition;
//    }
//    private class Particle {
//        private double[] position; // 位置
//        private double[] velocity; // 速度
//        private double[] pBestPosition; // 个体最优解位置
//
//        public Particle(int problemDimension) {
//            position = new double[problemDimension];
//            velocity = new double[problemDimension];
//            pBestPosition = new double[problemDimension];
//
//            // 初始化位置和速度
//            for (int i = 0; i < problemDimension; i++) {
//                position[i] = random.nextDouble() * 200 - 100;
//                velocity[i] = random.nextDouble() * 2 - 1;
//            }
//
//            // 初始化个体最优解位置
//            System.arraycopy(position, 0, pBestPosition, 0, position.length);
//        }
//
//        public double[] getPosition() {
//            return position;
//        }
//
//        public double[] getVelocity() {
//            return velocity;
//        }
//
//        public double[] getpBestPosition() {
//            return pBestPosition;
//        }
//
//        public int getProblemDimension() {
//            return position.length;
//        }
//    }
//
//    public void run(){
//        res=solve();
//    }
//    public double[] output(){
//        double[] outputs = new double[res.length+1];
//        System.arraycopy(res, 0, outputs, 0, res.length);
//        outputs[res.length]=objectiveFunction.apply(res);
//        return outputs;
//    }
//}
//
