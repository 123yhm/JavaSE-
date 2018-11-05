package com.hp.test;

/**
 * 100-1000以内的姐妹素数
 */
public class Test01Prime {

    public static void main(String[] args) {
        boolean flag2=true;
        int psve=0;
        for (int i = 101; i <=999;i+=2) {//循环1000以内的素数
            boolean flag=true;
           for (int j=2;j<i;j++){//素数的条件只能1和它本身以外不再有其他因数
               if (i%j==0){//判断该数是否为素数
                   flag=false;//不为素数跳出往后的循环计算
                   break;
               }
           }
           if(flag){
               if (i-psve==2){//判断相邻两个数相减是否等于2
                   if(flag2){//用标记判断是否输出该数
                       System.out.println("1："+psve);
                       System.out.println("2："+i);
                       flag2=true;
                   }else{
                       flag2=false;
                   }
               }else{
                   psve=i;
               }
           }
        }
    }
}
