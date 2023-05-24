package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Hashtable;

public class Main {
    private static String DoutoStr(double[] doubleArray){
        StringBuilder sb = new StringBuilder(); // 创建字符串构建器
        for (int i = 0; i < doubleArray.length; i++) {
            sb.append(doubleArray[i]); // 将双精度浮点数添加到构建器中
            if (i != doubleArray.length - 1) {
                sb.append(","); // 如果不是最后一个数，则添加逗号
            }
        }
        String str = sb.toString(); // 构建字符串
        return str;
    }
    private static double[] StrtoDoub(String str){
        String[] strArray = str.split(","); // 将字符串按照逗号分割为字符串数组
        double[] doubleArray = new double[strArray.length]; // 创建双精度浮点数数组
        for (int i = 0; i < strArray.length; i++) {
            doubleArray[i] = Double.parseDouble(strArray[i]); // 将字符串转换为双精度浮点数并存入数组
        }
        return doubleArray;
    }
    private static String[] de_test(String urll,int targetID,int Size,double Factor,int MaxIter,double[] lowB,double[] upB) {
        String[] strArray = new String[0];
        try {
            Hashtable<String, Object> dict = new Hashtable<>();
            // 将Function对象作为值放入Hashtable中
            dict.put("target", targetID);
            dict.put("Size", Size);
            dict.put("Factor", Factor);
            dict.put("MaxIter", MaxIter);
            dict.put("lowB", DoutoStr(lowB));
            dict.put("upB", DoutoStr(upB));

            // 使用HttpURLConnection发送POST请求
            URL url = new URL(urll);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(dict);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(json.getBytes("UTF-8"));

            // 使用HttpURLConnection接受返回数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            strArray = null;
            while ((output = reader.readLine()) != null) {
                strArray = output.substring(1, output.length() - 1).split("[,\\[\\]]"); // 将字符串按照逗号分割为字符串数组
                System.out.println("Best position:" + "(" + strArray[0] + "," + strArray[1] + ")");
                System.out.println("Best value:" + strArray[2]);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strArray;
    }
    static class DEVisualizer extends JFrame implements ActionListener {
        private JLabel  labelMaxIter, labelcomboBox,labeltargetID,labelurl,labelResult1,labelResult2,labelSize,labelFactor,labellowB,labelupB;
        private JTextField textMaxIter, texttargetID,texturl,textSize,textFactor,textlowB,textupB;
        private JButton buttonRun;
        private JComboBox<String> comboBox;
        public DEVisualizer() {
            setTitle("DE Algorithm");
            setSize(900, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(10, 2));

            labelurl = new JLabel("URL:");
            texturl = new JTextField("http://10.33.0.92:8080/javaMavenWeb/api/runDE");

            String[] func={"Rastrigin","Sphere","Rosenbrock","Ackley","griewank","schwefel","cigar","eggcrate"};
            labelcomboBox=new JLabel("targetFunction:");
            comboBox=new JComboBox<>(func);
            labelSize = new JLabel("Size:");
            textSize = new JTextField("50");

            labelFactor = new JLabel("Factor:");
            textFactor = new JTextField("0.8");

            labelMaxIter = new JLabel("MaxIter:");
            textMaxIter = new JTextField("1000");

            labellowB = new JLabel("lowB:");
            textlowB = new JTextField("-513.0,-513.0");

            labelupB = new JLabel("upB:");
            textupB = new JTextField("513.0,513.0");


            labelResult1 = new JLabel("Best solution:");
            labelResult2 = new JLabel("Best value:");

            buttonRun = new JButton("Run DE");
            buttonRun.addActionListener(this);
            panel.add(labelurl);
            panel.add(texturl);
            panel.add(labelcomboBox);
            panel.add(comboBox);
            panel.add(labelSize);
            panel.add(textSize);
            panel.add(labelFactor);
            panel.add(textFactor);
            panel.add(labelMaxIter);
            panel.add(textMaxIter);
            panel.add(labelupB);
            panel.add(textupB);
            panel.add(labellowB);
            panel.add(textlowB);
            panel.add(labelResult1);
            panel.add(labelResult2);
            panel.add(buttonRun);

            add(panel);
            setVisible(true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonRun) {
                String url= new String(texturl.getText());
                int targetID=comboBox.getSelectedIndex();
                int Size = Integer.parseInt(textSize.getText());
                int maxIter = Integer.parseInt(textMaxIter.getText());
                double Factor=Double.parseDouble(textFactor.getText());
                String lowB = new String(textlowB.getText());
                String upB = new String(textupB.getText());
                double[] low=StrtoDoub(lowB);
                double[] up=StrtoDoub(upB);
                String[] result=de_test(url,targetID+1,Size,Factor,maxIter,low,up);
                //在界面上显示结果
                labelResult1.setText("Best solution: (" + result[0] + "," + result[1]+")");
                labelResult2.setText("Best value: " + result[2]);
            }
        }
    }
    public static void main(String[] args) {

        new DEVisualizer();
    }
}