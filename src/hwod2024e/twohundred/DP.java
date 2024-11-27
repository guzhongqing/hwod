package hwod2024e.twohundred;


import java.util.Arrays;
import java.util.Scanner;


public class DP {
    static int maxValue = 0;
    static int selectedValue = 0;
    static int selectedWeight = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int capacity = sc.nextInt();

        sc.nextLine();
        int[] weights = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::new).toArray();
        int[] values = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::new).toArray();


//        System.out.println(n);
//        System.out.println(capacity);
//        System.out.println(Arrays.toString(weights));
//        System.out.println(Arrays.toString(values));
        backTracking(weights, values, 0, capacity);
        System.out.println(maxValue);

    }


    // 回溯算法

    public static void backTracking(int[] weights, int[] values, int index, int weightLimit) {
        if (index == weights.length) return;

        for (int i = index; i < weights.length; i++) {
            if (selectedWeight + weights[i] > weightLimit) continue;
            selectedWeight += weights[i];
            selectedValue += values[i];
            // 存储可能得最大值
            maxValue = Math.max(selectedValue, maxValue);

            backTracking(weights, values, index + 1, weightLimit);
            // 回撤
            selectedWeight -= weights[i];
            selectedValue -= values[i];


        }
    }


}



