package com.hp.test;

import java.util.Scanner;

/**
 * 输入某年某月某日，判断这一天是这一年的第几天。例如，2001 年
 * 3 月 5 日是这一年的第 64 天。
 */
public class Test02Time {
    public static void main(String[] args) {
        boolean flag=true;
        Integer year,month,day;
        int hm,sum = 0;
        Scanner sca=new Scanner(System.in);
        System.out.println("请输入年：");
        year=sca.nextInt();
        System.out.println("请输入月：");
        month=sca.nextInt();
        System.out.println("请输入日：");
        day=sca.nextInt();
        switch(month){
            case 1:sum   +=31;
            case    3:sum   +=31;
            case    5:sum   +=31;
            case    7:sum   +=31;
            case    8:sum   +=31;
            case    10:sum   +=31;
            case    12:sum   +=31;
            case    4:sum +=30;
            case    6:sum +=30;
            case    9:sum +=30;
            case    11:sum +=30;
            default:
                 sum += 29;
        }
        if (year % 4 == 0&&(year % 100 != 0 || year % 400 == 0)){
            sum+=28;
        }else{
            sum+=28;
        }
        System.out.println("总天数为："+sum);

    }
}
