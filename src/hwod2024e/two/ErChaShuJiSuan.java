package hwod2024e.two;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 题目名称：二叉树计算
 * 题目类型：二叉树前序，中序遍历
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD373
 */


/*
输入样例:
-3 12 6 8 9 -10 -7
8 12 -3 6 -10 9 -7

输出样例:
0 3 0 7 0 2 0


**/
public class ErChaShuJiSuan {

    // 解决重复节点值的问题
    static HashMap<Integer, ArrayList<Integer>> midOrderMap = new HashMap<>();


    static int[] midOrder;
    static int[] preOrder;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        midOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        preOrder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        System.out.println(Arrays.toString(midOrder));
//        System.out.println(Arrays.toString(preOrder));

        int n = midOrder.length;


        // 把中序数组放到map中
        for (int i = 0; i < n; i++) {
            int num = midOrder[i];
            midOrderMap.putIfAbsent(num, new ArrayList<>());
            // get返回的是map里面arraylist的引用，对返回的list修改，修改的还是map里面的list
            midOrderMap.get(num).add(i);
        }


        // 构建二叉树
        TreeNode root = buildTree(0, n - 1, 0, n - 1);


        // 中序输出结果
        printMidOrder(root);


    }

    public static void printMidOrder(TreeNode root) {
        if (root == null) return;

        printMidOrder(root.left);
        System.out.print(root.childSum + " ");
        printMidOrder(root.right);

    }


    // 通过中序和前序的左右边界还原二叉树
    public static TreeNode buildTree(int midL, int midR, int preL, int preR) {
        // 前序的子数组左边界大于右边界，代表没有该孩子节点了
        if (preL > preR) return null;

        // 创建根节点
        TreeNode root = new TreeNode();

        // 前序遍历肯定能确定当前序列的根节点的值，但是在中序遍历里面无法确定重复值的编号位置
        // 所以需要遍历中序子数组中重复值的编号，判断是否符合根节点的位置，重复值有且只有一个是当前序列的根节点

        int val = preOrder[preL];
        for (Integer idx : midOrderMap.get(val)) {
            // 判断该根节点索引，不在当前中序子数组的范围内直接pass
            if (idx < midL || idx > midR) continue;

            // 判断中序和前序左子树元素是否相等
            int leftChildSize = idx - midL;
            if (notEqual(midL, preL + 1, leftChildSize)) continue;

            // 判断中序和前序右子树元素是否相等
            int rightChildSize = midR - idx;
            if (notEqual(idx + 1, preR - rightChildSize + 1, rightChildSize)) continue;

            // 确定根节点索引位置为idx，以从该结点开始，递归左右孩子
            root.val = val;
            root.left = buildTree(midL, idx - 1, preL + 1, preL + leftChildSize);
            root.right = buildTree(idx + 1, midR, preR - rightChildSize + 1, preR);

            // 计算孩子和
            root.childSum = (root.left == null ? 0 : root.left.val + root.left.childSum)
                    + (root.right == null ? 0 : root.right.val + root.right.childSum);
            // 结束
            break;

        }
        return root;
    }


    //判断子数组元素是否相等，顺序可以不一样
    public static boolean notEqual(int midL, int preL, int size) {
        int[] mid = Arrays.stream(Arrays.copyOfRange(midOrder, midL, midL + size)).sorted().toArray();
        int[] pre = Arrays.stream(Arrays.copyOfRange(preOrder, preL, preL + size)).sorted().toArray();

        for (int i = 0; i < size; i++) {
            if (mid[i] != pre[i]) {
                return true;
            }
        }
        return false;
    }


}

