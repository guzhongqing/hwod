package hwod2024e.onehundred;


import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;


/**
 * 题目名称：字符串变换最小字符串
 * 题目类型：字符串，数组，集合
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD268
 */


/*
输入样例:
abcdef
输出样例:
abcdef

输入样例:
bcdefa
输出样例:
acdefb


**/
public class ZiFuChuanBianHuan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        System.out.println(getResult(str));


    }

    public static String getResult(String str) {
        // 把str变成char[]
        char[] strChar = str.toCharArray();

        char[] minStr = str.toCharArray();
        // 按字典序排序出最小字符串
        Arrays.sort(minStr);
//        System.out.println(minStr);

        // 判断相等，直接返回
        boolean equal = str.equals(String.valueOf(minStr));
        if (equal) return str;

        // 输入字符串和最小字符对比，第一个不一样的字符就是要替换的字符，从最后往前找到该位置最小字符串的字符，把这两个进行交换
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != minStr[i]) {

                // 找到最后的索引
                int lastIndex = str.lastIndexOf(minStr[i]);
                // 交换
                char temp = strChar[i];
                strChar[i] = strChar[lastIndex];
                strChar[lastIndex] = temp;

                // 只交换一次
                break;
            }
        }
        return new String(strChar);
    }
}


