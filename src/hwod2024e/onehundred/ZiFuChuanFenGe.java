package hwod2024e.onehundred;


import java.util.*;


/**
 * 题目名称：字符串分割
 * 题目类型：字符串，数组，集合
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD154
 */


/*
输入样例:
3
12abc-abCABc-4aB@

输出样例:
12abc-abc-ABC-4aB-@


**/
public class ZiFuChuanFenGe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        String[] split = sc.next().split("-");
//        System.out.println(Arrays.toString(split));


        StringJoiner ans = new StringJoiner("-");
        ans.add(split[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            sb.append(split[i]);
        }
        // 从sb中每n个字符串截取一段出来，之后指针向后移动n个位置
        for (int i = 0; i < sb.length(); i += n) {

            ans.add(convert(sb.substring(i, Math.min(i + n, sb.length()))));
        }
        System.out.println(ans);
    }


    // 大小写转换
    public static String convert(String s) {
        int lowerCount = 0;
        int upperCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('a' <= c && c <= 'z') lowerCount++;
            if ('A' <= c && c <= 'Z') upperCount++;

        }
        if (lowerCount > upperCount) return s.toLowerCase();
        else if (lowerCount < upperCount) return s.toUpperCase();
        else return s;
    }


}


