package logic;

import java.util.ArrayList;
import java.util.Stack;

public class Calculate {
    private String string;
    private ArrayList<String> ops;
    private ArrayList<Double> num;
    //private ArrayList<Character> str_list;
    private Stack<Integer> stack;
    private double answer;

    public Calculate(String string){
        this.string = string;
        answer = 0.0;
        work();
    }

    public double getAnswer() {
        return answer;
    }

    private void work(){
        ArrayList str_list = new ArrayList<>();
        string = "(" + string + ")";
        for(int i=0;i<string.length();i++){
            str_list.add(string.charAt(i));
        }

        stack = new Stack<>();
        for(int i=0;i<str_list.size();i++){
            if(str_list.get(i).equals('(')){
                //stack 里面存的是左括号的位置
                stack.add(i);
            }
            if(str_list.get(i).equals(')')){
                //遇见右括号，pop一个左括号
                int s = stack.pop();

                StringBuilder stringBuilder = new StringBuilder();
                //j 等于 最里层左括号的位置 一直循环到最里层 右括号的位置
                for(int j = s;j <= i;j++){
                    //把最里层的算式放到 stringBuilder 内
                    stringBuilder.append(str_list.get(j));
                }

                int stringBuilder_len = i-s+1;

                for(int k = 0;k<stringBuilder_len;k++){
                    //将此算式从原串中移除 (...)
                    str_list.remove(s);
                }

                //获取括号中的式子
                String strCalc = stringBuilder.substring(1,stringBuilder.length()-1);
                //计算式子结果
                double num = Calc_no_bracket.Result(strCalc);
                //原左括号位置添加得数
                str_list.add(s, num);

                //System.out.println(str_list);
                i = i - stringBuilder_len + 1;
                if(i<-1){
                    break;
                }
            }
        }
        answer = (double)str_list.get(0);
    }

    public static void main(String[] args){
        String str = "(8+2)*5.1-49";
        Calculate calculate = new Calculate(str);
        System.out.println(calculate.getAnswer());
    }
}
