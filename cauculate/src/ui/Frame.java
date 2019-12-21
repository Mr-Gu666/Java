package ui;

import logic.Calculate;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.*;


public class Frame extends JFrame{
    private JPanel p,pButton,pText;
    private JTextField textInputField;
    // 标志用户按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字
    private boolean firstDigit = true;
    // 计算结果。
    //private String result;
    private double result;
    private String inputText = "";
    private Calculate calculate;

    public Frame(){
        super("Calculator");
        p = new JPanel();
        p.setLayout(new BorderLayout());

        textInputField = new JTextField();
        textInputField.setPreferredSize(new Dimension(375,50));
        pText = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pText.add(textInputField);
        p.add(pText,BorderLayout.NORTH);

        pButton = new JPanel(new GridLayout(5,4));
        initButton();
        p.add(pButton,BorderLayout.CENTER);

        this.add(p);
        this.setSize(400,300);
        this.setLocation(600,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initButton(){
        String[] str = {"Clear","(",")","BKSpace","7","8","9","/","4","5","6","*","1","2","3",
                        "-","0",".","=","+"};
        for(int i=0;i<str.length;i++){
            JButton button = new JButton(str[i]);
            button.addActionListener(new InputListener());
            pButton.add(button);
        }
    }

    private class InputListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand();
            if(actionCommand.equals("Clear")){
                handleClear();
            }else if(actionCommand.equals("BKSpace")){
                handleBKSpace();
            }else{
                handleOther(actionCommand);
            }
        }
    }

    private void handleClear(){
        inputText="";
        textInputField.setText(inputText);
    }

    private void handleBKSpace(){
        inputText = inputText.substring(0,inputText.length()-1);
        textInputField.setText(inputText);
    }

    private void handleOther(String actionCommend){
        if(!actionCommend.equals("=")){
            inputText += actionCommend;
            textInputField.setText(inputText);
        }else{
            //output
            //System.out.println("output");
            calculate = new Calculate(inputText);
            result = calculate.getAnswer();
            //result = Double.toHexString(calculate.getAnswer());
            //System.out.println("result:"+result);
            //textInputField.setText(Double.toString(result));
            //System.out.println(Double.toString(result));
            textInputField.setText(Double.toString(result));
            inputText = "";
        }
    }
    public static void main(String[] args){
        new Frame();
    }
}
