package hwod2024e.one;

import java.util.Scanner;


/**
 * 题目名称：最远足迹
 * 题目类型：逻辑分析
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD257
 */


/*
输入样例:
ferg(3,10)a13fdsf3(3,4)f2r3rfasf(5,10)
输出样例:
(5,10)

输入样例:
asfefaweawfaw(0,1)fe
输出样例:
(0,0)


**/
public class ZuiYuanZuJi {
    public static void main(String[] args) {

        String ans = "(0,0)";
        int maxDis = 0;
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
//        System.out.println(string);

        int left = 0;

        // 遍历字符串
        for (int i = 0; i < string.length(); i++) {

            // 判断(),获取左右边界
            if (string.charAt(i) == '(') {
                left = i;
                continue;
            }
            if (string.charAt(i) == ')') {
                // 使用string.substring()截取子串，[)左闭右开
                String[] p = string.substring(left + 1, i).split(",");
                // 判断合法
                if (p[0].startsWith("0") || p[1].startsWith("0")) continue;
                // 解析数字
                int x = Integer.parseInt(p[0]);
                int y = Integer.parseInt(p[1]);

                // 判断数值范围
                if (0 < x && x < 1000 && 0 < y && y < 1000) {
                    // 和最远距离比大小，比最远距离大才修改最远距离和输出结果
                    if (x * x + y * y > maxDis) {
                        maxDis = x * x + y * y;
                        ans = "(" + x + "," + y + ")";
                    }
                }
            }
        }
        System.out.println(ans);
    }
}

