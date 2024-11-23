package hwod2024e.twohundred;


import java.util.*;

/**
 * 题目名称：中文分词模拟器
 * 题目类型：逻辑分析
 * 题目地址：https://hydro.ac/d/HWOD2023/p/OD229
 */


/*
输入样例:
ilovechina
i,love,china,ch,na,ve,lo,this,is,the,word

输出样例:
i,love,china

**/
public class ZhongWenFenCiMoNiQi {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] sentences = sc.nextLine().split("[,.;]");
        String[] words = sc.next().split("[,.;]");
//        System.out.println(string);
//        System.out.println(Arrays.toString(word));


        // 创建句子分组，单词分组，结果集合变量
        // 因为需要移除句子分组的第一个句子，即删除队头元素用，用LinkedList
        // 因为需要判断句子前面部分是不是在单词中含有，用HashSet
        LinkedList<String> queue = new LinkedList<>(Arrays.asList(sentences));
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
        ArrayList<String> ans = new ArrayList<>();


        // 当队列长度为0是，代表全部句子分词完毕
        while (!queue.isEmpty()) {
            // 移除句子分组的句子，对每个句子分词
            String sentence = queue.removeFirst();
            int length = sentence.length();
            // 每个句子从右边移动指针，每移动一次，判断左边是否匹配，匹配则从句子中移除这个单词，并放到结果中
            // 当这个句子长度为零时，代表该句子分词结束
            while (length > 0) {
                String fragment = sentence.substring(0, length);
                if (wordSet.contains(fragment)) {
                    ans.add(fragment);
                    // 把剩下的部分作为一个句子，重新加到队列队首，重复分词操作
                    // sentence.substring(length)正好是最后一个单词，即length==sentence.length()会返回一个""长度为0的字符串，加到队列中，导致出错，
                    // sentence是完整的一个单词，就不需要重新入队了
                    if (length < sentence.length()) {
                        queue.addFirst(sentence.substring(length));
                    }
                    // 分出来一个词之后，结束此次分词，接着下一次
                    break;
                }
                // 没有分出词来，移动右指针
                length--;
            }
            // 若右指针到0，且句子长度不为零，代表没有单词可以分，则把第一个字母加到结果中，剩下的会句子在分词
            if (length == 0) {
                ans.add(sentence.charAt(0) + "");
                // 判断句子长度，是否还有要分词，把剩下的重新入队首
                if (sentence.length() > 1) {
                    queue.addFirst(sentence.substring(1));
                }
            }
        }
        System.out.println(String.join(",", ans));


    }
}


