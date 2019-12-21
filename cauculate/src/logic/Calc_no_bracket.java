package logic;

import java.util.ArrayList;

public class Calc_no_bracket {
    public static double Result(String str){
        ArrayList<String> ops = getOps(str);
        ArrayList<Double> num = getNum(str);
        double ans = 0.0;
        for(int i = 0;i<ops.size();i++){
            if(ops.get(i).contains("*") || ops.get(i).contains("/")){
                String op = ops.remove(i);
                if(op.equals("*")){
                    double d1 = num.remove(i);
                    double d2 = num.remove(i);
                    ans = d1*d2;
                    num.add(i,ans);
                }
                if(op.equals("/")){
                    double d1 = num.remove(i);
                    double d2 = num.remove(i);
                    ans = d1/d2;
                    num.add(i,ans);
                }
                i--;
            }
        }

        while (ops.size()!=0){
            String op = ops.remove(0);
            //System.out.println("op:"+op);
            double d1 = num.remove(0);
            double d2 = num.remove(0);
            //System.out.println("d1:"+d1);
            //System.out.println("d2:"+d2);
            if(op.equals("+")){
                ans = d1+d2;
                num.add(0,ans);
            }
            if(op.equals("-")){
                ans = d1-d2;
                num.add(0,ans);
            }
        }
        return ans;
    }

    //找到负数
    private static String change(String str){
        //把字符串转化为字符数组
        char[] chars = str.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(i==0 && chars[i] =='-'){
                str = '@' + str.substring(i+1);
            }
            if(chars[i] == '*' && chars[i+1] =='-' || chars[i] =='/' && chars[i+1] =='-'){
                str = str.substring(0,i+1) + '@' + str.substring(i+2);
            }
        }
        return str;
    }

    private static ArrayList getNum(String str){
        //System.out.println("not change"+str);
        str = change(str);
        //System.out.println("changed:"+str);
        ArrayList<Double> list = new ArrayList<>();

        String[] split = str.split("[\\+\\-\\*/]");
        //System.out.println("splited:"+split);
        for(int i=0;i<split.length;i++){
            String s = split[i];
            //System.out.println(s);
            if(s.contains("@")){
                s = "-" + s.substring(1);
                //double t = Double.parseDouble(s);
                //t = -t;
                //list.add(t);
                //continue;
            }
            list.add(Double.parseDouble(s));
        }
        return list;
    }

    private static ArrayList getOps(String str){
        ArrayList<String> list = new ArrayList<>();
        str = change(str);

        String[] split = str.split("[0-9\\.@]");
        for(int i = 0;i<split.length;i++){
            if(split[i].contains("+") || split[i].contains("-")||split[i].contains("*")||split[i].contains("/")){
                list.add(split[i]);
            }
        }
        return list;
    }

    public static void main(String[] args){
        String str = "-3+5.0*-4+9/-3";
        System.out.println(Result(str));
    }
}
