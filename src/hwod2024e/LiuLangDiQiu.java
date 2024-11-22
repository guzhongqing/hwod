package hwod2024e;

import java.util.Scanner;

/**
 * 题目名称：流浪地球
 * 题目类型：逻辑模拟
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD445
 */


//
public class LiuLangDiQiu {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int cnt = 0, ans = 0;
        while (n != 0) {
            n--;
            ans++;
            cnt++;
            if (cnt == 3) {
                cnt = 0;
                n++;
            }
        }
        System.out.println(ans);
        scan.close();
    }
}