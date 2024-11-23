package hwod2024e.onehundred;

import java.util.Scanner;


/**
 * 题目名称：靠谱的车
 * 题目类型：位运算
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD242/solution
 */


/*
输入样例:
5
输出样例:
4

输入样例:
17
输出样例:
15


**/
public class KaoPuDeChe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(String.valueOf(s.charAt(i)));

            if (num < 4) {
                ans += (int) (num * Math.pow(9, n - i - 1));
            } else {
                ans += (int) ((num - 1) * Math.pow(9, n - i - 1));
            }
        }
        System.out.println(ans);

    }
}

