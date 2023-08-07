import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Myframe extends JFrame implements ActionListener{
    JLabel lable1,lable2,lable3,lable4,lable;
    JTextField t1,t2,t3;
    JRadioButton male,female;
    JTextArea screen;
    JButton submit;
    double height,weight,bmi,bmr;
    String gen="male";
    int age;
    Font font = new Font("Verdana", Font.BOLD, 30);

    Myframe(){
        setTitle("BMI & BMR Calculator");
        setSize(750,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        lable = new JLabel("BMI & BMR Calculator");
        lable.setBounds(30,20,700,60);
        lable.setFont(new Font("Verdana", Font.PLAIN, 60));
        c.add(lable);

        lable1 = new JLabel("Gender");
        lable1.setBounds(20,110,100,20);
        c.add(lable1);
        male = new JRadioButton("male");
        female = new JRadioButton("female");
        male.setBounds(130,110,100,20);
        female.setBounds(260,110,100,20);
        male.setSelected(true);
        c.add(male);
        c.add(female);

        ButtonGroup gen = new ButtonGroup();
        gen.add(male);
        gen.add(female);

        lable2 =new JLabel("Weight");
        lable2.setBounds(20,160,100,20);
        c.add(lable2);
        t1 = new JTextField();
        t1.setBounds(130,160,100,20);
        t1.setText("(weight in kg)");
        t1.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent event){
                if(t1.getText().equals("(weight in kg)")){
                    t1.setText("");
                }

            }
            public void focusLost(FocusEvent event){
                    if(t1.getText().equals("")){
                            t1.setText("(weight in kg)");
                    }
            }

        });
        c.add(t1);

        lable3 =new JLabel("Height");
        lable3.setBounds(20,210,100,20);
        c.add(lable3);
        t2 = new JTextField();
        t2.setBounds(130,210,100,20);
        t2.setText("(height in meter)");
        t2.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent event){
                if(t2.getText().equals("(height in meter)")){
                    t2.setText("");
                }
            }
            public void focusLost(FocusEvent event){
                    if(t2.getText().equals("")){
                            t2.setText("(height in meter)");
                    }
            }
        });
        c.add(t2);

        lable4 =new JLabel("Age");
        lable4.setBounds(20,260,100,20);
        c.add(lable4);
        t3 = new JTextField();
        t3.setBounds(130,260,100,20);
        t3.setText("(age in years)");
        t3.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent event){
                if(t3.getText().equals("(age in years)")){
                    t3.setText("");
                }
            }
            public void focusLost(FocusEvent event){
                    if(t3.getText().equals("")){
                            t3.setText("(age in years)");
                    }
            }
        });
        c.add(t3);

        submit = new JButton("Submit");
        submit.setBounds(150,310,80,20);
        c.add(submit);

        submit.addActionListener(this);

        screen = new JTextArea();
        screen.setBounds(360,150,300,150);
        screen.setFont(font);
        screen.setEnabled(false);
    
        c.add(screen);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        try {
            Socket s = new Socket("localhost",7778);
        Scanner sc1 = new Scanner(s.getInputStream()); //accepting sothing from user

        if(female.isSelected()){
            this.gen = "female";
        }
        PrintStream p = new PrintStream(s.getOutputStream());
        p.println(this.gen);

        double weight;
        weight= Double.parseDouble(t1.getText());
        p.println(weight);

        double height;
        height= Double.parseDouble(t2.getText());
        p.println(height);

        int age;
        age= Integer.parseInt(t3.getText());
        p.println(age);
        double bmi=sc1.nextDouble();
        double bmr=sc1.nextDouble();
        screen.setText("BMI: "+String.format("%.2f",bmi)+"\n\nBMR: "+String.format("%.4f",bmr));
        s.close();
    
        } 
        catch (IOException e1) {
            e1.printStackTrace();
        }     

    }
}

public class Client1 {
    public static void main(String[] args){
        Myframe frame = new Myframe();
    }   
}

