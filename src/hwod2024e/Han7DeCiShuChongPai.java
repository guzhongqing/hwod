package hwod2024e;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;


/**
 * 题目名称：喊7的重排数
 * 题目类型：约瑟夫环
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD229
 */


/*
输入样例:
0 1 0
输出样例:
1 0 0
输入样例:
0 0 0 2 1
输出样例:
0 2 0 1 0

**/
public class Han7DeCiShuChongPai {


    // 7 14 17 21 27 28  ...
    //每次都是按照这个顺序喊 "过" 的
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 参加喊数的人的个数
        int n = 0;
        // 喊到7倍数或含有7的次数
        int totalGo = 0;

        // 读到不能解析成数字的输入才停止，也就是必须要有非数字输入，最后要输入回车结束输入
        while (sc.hasNextInt()) {
            totalGo += sc.nextInt();
            n++;
        }

        int[] array = new int[n];
        int cnt = 1;
        while (totalGo > 0) {
            // 判断当前计数是不是7的倍数或者含有7
            if (cnt % 7 == 0 || String.valueOf(cnt).contains("7")) {
                totalGo--;
                array[(cnt - 1) % n]++;
            }
            cnt++;
        }

        Stream<String> stringStream = Arrays.stream(array).mapToObj(String::valueOf);
        String ans = String.join(" ", stringStream.toArray(String[]::new));
        System.out.println(ans);


    }
}

