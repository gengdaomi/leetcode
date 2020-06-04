package com.leetcode.cache;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 题目2
 * Created By Andrew-Geng on 2020/6/4 8:17 下午
 */
public class FileStringSolution {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<>();

        File txtFile = new File("/Users/andrew-geng/Tools/test.txt");
        FileReader reader = new FileReader(txtFile);
        BufferedReader bReader = new BufferedReader(reader);

        String s = "";
        while ((s = bReader.readLine()) != null) {
            if (s != null && s.trim().length() > 0) {  //这个把每行字符串 按空格分隔分开 获取
                String[] strList = s.split(" ");

                for (int i = 0; i < strList.length; i++) {
                    map.put(strList[i], map.get(strList[i]) == null ? 1 : map.get(strList[i]) + 1);
                }

//                Arrays.stream(s.split(" "))
//                        .filter(Objects::nonNull)
//                        .forEach(str -> map.put(str, map.get(str) == null ? 1 : map.get(str) + 1));  //计数
            }
        }

        bReader.close();

        List<StringNode> result = new FileStringSolution().sort(map);
        System.out.println(result);
    }

    /**
     * 基于map数据转换为有序链表  倒序
     *
     * @param map
     * @return
     */
    public List<StringNode> sort(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> keyList = new LinkedList<Map.Entry<String, Integer>>(map.entrySet()); //初始化

        Collections.sort(keyList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o2.getValue().compareTo(o1.getValue()) < 0) {
                    return -1;
                } else if (o2.getValue().compareTo(o1.getValue()) > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });


        List<StringNode> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : keyList) {
            StringNode stringNode = new StringNode(entry.getKey(), entry.getValue());
            result.add(stringNode);
        }

        return result;

//        return map.keySet()
//                .stream()
//                .filter(Objects::nonNull)
//                .map(key -> new StringNode(key, map.get(key)))
//                .sorted(Comparator.comparing(StringNode::getCount).reversed())
//                .collect(Collectors.toList());
    }

    class StringNode {
        int count;
        String val;

        public StringNode(String val, int count) {
            this.count = count;
            this.val = val;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "StringNode{" +
                    "count=" + count +
                    ", val='" + val + '\'' +
                    '}';
        }
    }
}
