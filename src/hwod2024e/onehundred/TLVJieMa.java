package hwod2024e.onehundred;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * 题目名称：TLV解码
 * 题目类型：字符串，数组，集合
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD250
 */


/*
输入样例:
31
32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC

输出样例:
32 33



**/
public class TLVJieMa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        String[] stream = sc.nextLine().split(" ");

        int point = 0;
        while (point < stream.length) {
            String tag = stream[point++];
            String s1 = stream[point++];
            String s2 = stream[point++];

            // 小端序16进制解析成10进制数
            int len = Integer.parseInt(s2 + s1, 16);

            // 如果相等代表已经找到目标tag，直接输出，让后结束程序
            if (target.equals(tag)) {
                ArrayList<String> ans = new ArrayList<>(Arrays.asList(stream).subList(point, point + len));
                System.out.println(String.join(" ", ans));
                break;
            }
            point += len;
        }
    }
}

