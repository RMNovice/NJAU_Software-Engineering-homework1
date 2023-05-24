package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.Alobject.Algorithm;
import org.Alobject.DE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Hashtable;
import java.util.function.Function;

//@Controller注解：采用注解的方式，可以明确地定义该类为处理请求的Controller类；
@Controller
//@RequestMapping()注解：用于定义一个请求映射，value为请求的url，值为 /helloworld 说明，该请求首页请求，method用以指定该请求类型，一般为get和post；
@RequestMapping("/api")
public class MainController {
    private static double[] StrtoDoub(String str){
        String[] strArray = str.split(","); // 将字符串按照逗号分割为字符串数组
        double[] doubleArray = new double[strArray.length]; // 创建双精度浮点数数组
        for (int i = 0; i < strArray.length; i++) {
            doubleArray[i] = Double.parseDouble(strArray[i]); // 将字符串转换为双精度浮点数并存入数组
        }
        return doubleArray;
    }
    @RequestMapping (value = "/runDE",method = RequestMethod.POST)

    @ResponseBody
    public double[] de( @RequestBody String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Hashtable<String, Object> dict = objectMapper.readValue(json, new TypeReference<Hashtable<String, Object>>() {});
        String tlowB= (String) dict.get("lowB");
        double[] lowB=StrtoDoub(tlowB);
        dict.put("lowB",lowB);

        String tupB= (String) dict.get("upB");
        double[] upB=StrtoDoub(tupB);
        dict.put("upB",upB);

        int targetID=(int) dict.get("target");
        Function<double[], Double> objectiveFunction;
        switch(targetID) {
            case 1 :
                objectiveFunction = x -> {
                                            double sum = 0.0;
                                            int n = x.length;
                                            for (double xi : x) {
                                                sum += xi * xi - 10 * Math.cos(2 * Math.PI * xi);
                                            }
                                            return 10 * n + sum;
                                        };
                break;
            case 2 ://Sphere
                objectiveFunction = x -> {
                                            double sum = 0.0;
                                            for (double xi : x) {
                                                sum += xi * xi;
                                            }
                                            return sum;
                                        };
                break; // 可选
            case 3://Rosenbrock
                objectiveFunction = x -> {
                                            double sum = 0.0;
                                            for (int i = 0; i < x.length - 1; i++) {
                                                double xi = x[i];
                                                double xNext = x[i+1];
                                                sum += 100 * Math.pow(xNext - xi*xi, 2) + Math.pow(xi - 1, 2);
                                            }
                                            return sum;
                                        };
                break; // 可选
            case 4://Ackley
                objectiveFunction = x -> {
                                            double sum1 = 0.0;
                                            double sum2 = 0.0;
                                            int n = x.length;
                                            for (double xi : x) {
                                                sum1 += xi * xi;
                                                sum2 += Math.cos(2 * Math.PI * xi);
                                            }
                                            double term1 = -20 * Math.exp(-0.2 * Math.sqrt(1.0/n * sum1));
                                            double term2 = -1.0/n * sum2;
                                            return term1 + term2 + 20 + Math.E;
                                        };
                break; // 可选
            case 5://griewank
                objectiveFunction = x -> {
                                            double sum1 = 0.0;
                                            double prod1 = 1.0;
                                            for (int i = 0; i < x.length; i++) {
                                                sum1 += x[i]*x[i];
                                                prod1 *= Math.cos(x[i]/Math.sqrt(i+1));
                                            }
                                            return sum1/4000.0 - prod1 + 1.0;
                                        };
                break;
            case 6://schwefel
                objectiveFunction = x -> {
                                            double sum = 0.0;
                                            for (double xi : x) {
                                                sum += -xi * Math.sin(Math.sqrt(Math.abs(xi)));
                                            }
                                            return sum;
                                        };
                break;
            case 7://cigar
                objectiveFunction = x -> {
                                        double sum = x[0] * x[0];
                                        for (int i = 1; i < x.length; i++) {
                                            sum += Math.pow(10, 6) * x[i] * x[i];
                                        }
                                        return sum;
                                    };
                break;
            case 8://eggcrate
                objectiveFunction = x -> {
                                            double sum = 0.0;
                                            for (double xi : x) {
                                                sum += xi * xi + 25.0 * Math.sin(xi);
                                            }
                                            return sum;
                                        };
                break;
            default :
                objectiveFunction = x -> Math.pow(x[0], 2) + Math.pow(x[1], 2)+x[0];
        }

        dict.put("target",objectiveFunction);

        Algorithm de =new DE();
        de.input(dict);
        de.run();
        double[] res=de.output();
        return res;
    }


}
