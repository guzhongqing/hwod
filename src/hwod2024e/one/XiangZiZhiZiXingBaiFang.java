package hwod2024e.one;

import java.util.Scanner;


/**
 * 题目名称：箱子之字形摆放
 * 题目类型：逻辑分析
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD022
 */


/*

输入样例:
ABCDEFG 3

输出样例:
AFG
BE
CD

**/
//蛇形走位
public class XiangZiZhiZiXingBaiFang {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String string = sc.next();
        int n = sc.nextInt();

//        System.out.println(string);
//        System.out.println(n);


        char[][] chars = new char[n][n];

        boolean reverse = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (n * i + j < string.length()) {
                    char c = string.charAt(n * i + j);
                    if (reverse) {
                        chars[n - j - 1][i] = c;
                    } else {
                        chars[j][i] = c;
                    }
                }
            }
            reverse = !reverse;

        }

        // 循环输出二维字符数组，按照要求的格式
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (chars[i][j] != '\0') {  // 只输出已经赋值的字符，避免输出默认的空字符
                    System.out.print(chars[i][j]);
                }
            }
            System.out.println();  // 每一行输出完后换行
            // 结果不对，但是不知道哪不对
        }
    }
}

