package hwod2024e.onehundred;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 题目名称：连续字母长度
 * 题目类型：字符串，数组，集合
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD251
 */


/*
输入样例:
AAAAHHHBBCDHHHH
3

输出样例:
2


**/
public class LianXuZiMuChangDu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int k = sc.nextInt();

        System.out.println(result(str, k));
    }

    private static int result(String str, int k) {
        if (k <= 0) return -1;
        HashMap<Character, Integer> map = new HashMap<>();


        // 定义一个开始
        int index = 0, len = 0;
        int n = str.length();

        while (index < n) {
            char c = str.charAt(index);
            while (index + len < n && c == str.charAt(index + len)) {
                len++;
            }
            if (!map.containsKey(c) || len > map.get(c)) {
                map.put(c, len);
            }
            index += len;
            len = 0;
        }

        // 这里0可以是任意任意，返回数组的时候会内部扩容
        Integer[] arr = map.values().toArray(new Integer[0]);

        // 判断k的范围，不合法直接返回
        if (k > arr.length) return -1;
        // 降序排序
        Arrays.sort(arr, (a, b) -> b - a);
        return arr[k - 1];
    }
}
