package hwod2024e.onehundred;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


/**
 * 题目名称：智能成绩表
 * 题目类型：动态条件排序
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD268
 */


/*
输入样例:
3 2
yuwen shuxue
fangfang 95 90
xiaohua 88 95
minmin 100 82
shuxue

输出样例:
xiaohua fangfang minmin


**/
public class ZhiNengChengJiBiao {
    static class Student {
        String name;
        int[] score;

        public Student(String name, int[] score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", score=" + Arrays.toString(score) +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int studentN = sc.nextInt();
        int subjectM = sc.nextInt();


        // hash存储学科名称和顺序号
        HashMap<String, Integer> subjectIdxMap = new HashMap<>();

        // 存储学科
        for (int i = 0; i < subjectM; i++) {
            subjectIdxMap.put(sc.next(), i);
        }
//        System.out.println(subjectIdxMap);

        // 创建学生列表
        ArrayList<Student> students = new ArrayList<>();

        // 遍历学生和成绩，存储
        for (int i = 0; i < studentN; i++) {
            String name = sc.next();

            int[] score = new int[subjectM + 1];
            int scoreSum = 0;
            for (int j = 0; j < subjectM; j++) {
                score[j] = sc.nextInt();
                scoreSum += score[j];
            }
            // 成绩的最后一列放总分
            score[subjectM] = scoreSum;
            // 把名称和成绩放到学生类中，并放到列表中
            students.add(new Student(name, score));
        }


        // 输入最后排序的学科，没有该学科，用默认总分，最后一列

        Integer idx = subjectIdxMap.getOrDefault(sc.next(), subjectM);
        // 学生列表排序，判断学科分数，相同按学生名称字典序，不相同，按分数降序
        students.sort((a, b) -> a.score[idx] == b.score[idx]
                ? a.name.compareTo(b.name)
                : b.score[idx] - a.score[idx]);

//        System.out.println(students);

        System.out.println(String.join(" ", students.stream().map(student -> student.name).toArray(String[]::new)));
    }


}


