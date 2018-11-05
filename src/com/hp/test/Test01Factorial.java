package com.hp.test;

/**
 * 输入整数a，输出结果s，其中s 与a的关系是：s=a+aa+aaa+aaaa+aa...a，
 * 最后为 a 个 a。例如 a=2 时，s=2+22=24
 */
public class Test01Factorial {
    public static void main(String[] args) {
        int a=4;
        int sum=0;
        int yhm=0;
        for(int i=0;i<a;i++){
            sum+=a*Math.pow(10,i);
            yhm+=sum;
        }
        System.out.println(yhm);

        //=======================================================================
        int tr=30;
        int hm=1;
        int sum2=0;
        if(tr<100){
            for (int i=tr;i<100;i++){
                sum2+=tr+hm++;
            }
            System.out.println("ooo"+sum2);
        }else{
            System.out.println("输入值不在此范围内");
            return;
        }

       // System.out.println(4+44+444+4444);4936
    }

}
