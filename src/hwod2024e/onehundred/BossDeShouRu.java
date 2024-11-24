package hwod2024e.onehundred;


import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Scanner;


/**
 * 题目名称：boss的收入
 * 题目类型：逻辑分析，拓扑排序(有向无环图DAG)
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD287
 */


/*
输入样例:
5
1 0 100
2 0 199
3 0 200
4 0 200
5 0 200

输出样例:
0 120

**/
public class BossDeShouRu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        // 孩子父亲表
        HashMap<Integer, Integer> childId_fatherId = new HashMap<>();

        // 孩子收入表
        HashMap<Integer, Integer> childId_income = new HashMap<>();

        // 节点入度表
        HashMap<Integer, Integer> nodeId_inDegree = new HashMap<>();

        // 初始化
        for (int i = 0; i < n; i++) {
            int childId = sc.nextInt();
            int fatherId = sc.nextInt();
            int childIncome = sc.nextInt();

            childId_fatherId.put(childId, fatherId);

            childId_income.put(childId, childIncome);
            // 只有一个顶级节点，不会出现在childId里面
            childId_income.putIfAbsent(fatherId, 0);

            nodeId_inDegree.putIfAbsent(childId, 0);
            nodeId_inDegree.put(fatherId, nodeId_inDegree.getOrDefault(fatherId, 0) + 1);
        }


        // 创建队列
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // 把度为0的节点全部入队
        for (Integer id : nodeId_inDegree.keySet()) {
            if (nodeId_inDegree.get(id) == 0) {
                queue.add(id);
            }
        }


        int bossId = 0;
        // 当队列不为空时，然后将这些节点出队，把收入给父节点，当父节点的度为0入队
        while (!queue.isEmpty()) {
            // 移除节点，并获取，赋值给childId和ans
            int childId = bossId = queue.pollFirst();

            // 最后出队的是不一定是顶级节点，也肯定是叶子节点，出去之后，父节点进来
            // 在孩子节点里面找不到的肯定是顶级节点
            if (childId_fatherId.containsKey(childId)) {

                // 获取父节点id
                Integer fatherId = childId_fatherId.get(childId);

                // 更新父节点收入
                childId_income.put(fatherId, childId_income.get(fatherId) + childId_income.get(childId) / 100 * 15);

                // 父节点入度-1
                nodeId_inDegree.put(fatherId, nodeId_inDegree.get(fatherId) - 1);
                // 如果父节点没有孩子，即父节点入度为零则父节点入队
                if (nodeId_inDegree.get(fatherId) == 0) {
                    queue.addLast(fatherId);
                }
            }
        }
        // 全部出队后
        System.out.println(bossId + " " + childId_income.get(bossId));
    }
}


