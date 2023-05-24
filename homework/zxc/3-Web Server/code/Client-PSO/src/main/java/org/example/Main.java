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
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
public class Main {
    private static String[] pso_test(String urll,int targetID,int p_dim,int Size,int MaxIter,double c1,double c2,double w_up,double w_low) {
        String[] strArray = new String[0];
        try {
            Hashtable<String, Object> dict = new Hashtable<>();
            // 将Function对象作为值放入Hashtable中
            dict.put("target", targetID);
            dict.put("p_dim", p_dim);
            dict.put("Size", Size);
            dict.put("MaxIter", MaxIter);
            dict.put("Factor1", c1);
            dict.put("Factor2", c2);
            dict.put("W_up", w_up);
            dict.put("W_low", w_low);

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
    static class PSOVisualizer extends JFrame implements ActionListener {
        private JLabel labelSwarmSize, labelMaxIter, labelcomboBox,labelurl,labeltargetID,labelC1, labelC2, labeldim,labelW_up, labelW_low,labelResult1,labelResult2;
        private JTextField textSwarmSize, textMaxIter, texturl,texttargetID,textC1, textC2, textdim,textW_up,textW_low;
        private JButton buttonRun;
        private JFrame targetID;
        private JComboBox<String> comboBox;
        public PSOVisualizer() {
            setTitle("PSO Algorithm");
            setSize(900, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(11, 2));
            labelurl = new JLabel("URL:");
            texturl = new JTextField("http://10.49.74.150:8080/javaMavenWeb/api/runPSO");
            String[] func={"Rastrigin","Sphere","Rosenbrock","Ackley","griewank","schwefel","cigar","eggcrate"};
            labelcomboBox=new JLabel("targetFunction:");
            comboBox=new JComboBox<>(func);

            labeldim = new JLabel("dim:");
            textdim = new JTextField("2");

            labelSwarmSize = new JLabel("Swarm Size:");
            textSwarmSize = new JTextField("50");

            labelMaxIter = new JLabel("Max Iterations:");
            textMaxIter = new JTextField("1000");

            labelC1 = new JLabel("C1:");
            textC1 = new JTextField("2");

            labelC2 = new JLabel("C2:");
            textC2 = new JTextField("2");

            labelW_up = new JLabel("W_up:");
            textW_up = new JTextField("1.0");

            labelW_low = new JLabel("W_low:");
            textW_low = new JTextField("0.0");

            labelResult1 = new JLabel("Best solution:");
            labelResult2 = new JLabel("Best value:");

            buttonRun = new JButton("Run PSO");
            buttonRun.addActionListener(this);
            panel.add(labelurl);
            panel.add(texturl);
            panel.add(labelcomboBox);
            panel.add(comboBox);
            panel.add(labeldim);
            panel.add(textdim);
            panel.add(labelSwarmSize);
            panel.add(textSwarmSize);
            panel.add(labelMaxIter);
            panel.add(textMaxIter);
            panel.add(labelC1);
            panel.add(textC1);
            panel.add(labelC2);
            panel.add(textC2);
            panel.add(labelW_up);
            panel.add(textW_up);
            panel.add(labelW_low);
            panel.add(textW_low);
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
                int p_dim=Integer.parseInt(textdim.getText());
                int swarmSize = Integer.parseInt(textSwarmSize.getText());
                int maxIter = Integer.parseInt(textMaxIter.getText());
                double c1 = Double.parseDouble(textC1.getText());
                double c2 = Double.parseDouble(textC2.getText());
                double w_up = Double.parseDouble(textW_up.getText());
                double w_low = Double.parseDouble(textW_low.getText());

                String[] result=pso_test(url,targetID+1,p_dim,swarmSize,maxIter,c1,c2,w_up,w_low);
                //在界面上显示结果
                labelResult1.setText("Best solution: (" + result[0] + "," + result[1]+")");
                labelResult2.setText("Best value: " + result[2]);
            }
        }
    }
    public static void main(String[] args) {
        new PSOVisualizer();
    }
}