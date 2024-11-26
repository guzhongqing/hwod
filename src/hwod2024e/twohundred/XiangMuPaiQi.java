package hwod2024e.twohundred;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目名称：项目排期
 * 题目类型：二分法 + 回溯算法
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD403
 */


/*
输入样例:
6 2 7 7 9 3 2 1 3 11 4
2

输出样例:
28

**/
public class XiangMuPaiQi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(" ");
        Integer[] balls = Arrays.stream(split).map(Integer::parseInt).toArray(Integer[]::new);
        int n = sc.nextInt();

//        System.out.println(Arrays.toString(balls));

        // 降序
        Arrays.sort(balls, (a, b) -> b - a);


        // 二分确定可能得桶大小

        // 有多少个球，就有多少个桶
        int min = balls[0];
        int max = Arrays.stream(balls).reduce(Integer::sum).get();

        int ans = max;
        // 取二分值，对这个大小进行回溯，看满不满足完成
        // 若完成就缩小二分范围右边界max=mid-1，不能就扩大二分左边界min=mid+1
        // 这里可以取到min==max，此时ans就是边界值
        while (min <= max) {
            int mid = (min + max) / 2;

            // 每次mid都是一种新可能，buckets都是新数组
            if (backTracking(balls, new int[n], 0, mid)) {
                // 若 true 代表此时的mid可以满足条件，先赋值给ans
                ans = mid;
                // 再移动max
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        // 直到二分到一个最小可能的值
        System.out.println(ans);
    }


    // 判断m个球正好有m个容量为n的桶
    public static boolean backTracking(Integer[] balls, int[] buckets, int index, int limit) {
        // 指针到球数组最后，代表结束
        if (index == balls.length) return true;
        int selected = balls[index];
        for (int i = 0; i < buckets.length; i++) {
            // 剪枝优化，如果下面没有return ture,才会回退状态，此时bucket的容量相同，则还是不会返回ture，可以直接跳过
            if (i > 0 && buckets[i] == (buckets[i - 1])) continue;

            if (selected + buckets[i] <= limit) {
                buckets[i] += selected;
                if (backTracking(balls, buckets, index + 1, limit)) return true;
                buckets[i] -= selected;
            }
        }
        return false;
    }
}

