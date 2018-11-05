package com.hp.test;

/**
 * 编写一个应用程序，计算并输出一维数组（9.8，12，45，67，23，
 * 1.98，2.55，45）中的最大值、最小值和平均值。
 *
 */
public class Test13Array {
    public static void main(String[] args) {
        double[] value={9.8,12,45,67,23,1.98,2.55,45};
        double  max=0.00;
        double  min=0.00;
        double sum=0;
        for (int i = 0; i <value.length ; i++) {
            sum+=value[i];
        }
        for (int i=0;i<value.length;i++){
            System.out.println(value[i]);

            if (value[i]>max){
                max=value[i];
                min=max;
            }
            if(value[i]<min){
                min=value[i];
            }
        }
        double avg=sum/value.length;
        System.out.println("最大数："+max+"最小数："+min+"平均数："+avg+"总数："+sum);
    }
}
