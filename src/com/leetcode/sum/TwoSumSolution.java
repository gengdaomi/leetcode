package com.leetcode.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by ayue on 下午4:26 2018/6/27
 * <p>
 * * Input: numbers={2, 7, 11, 15}, target=9
 * * Output: index1=1, index2=2
 * *
 * * 题目大意
 * * 给定一个整数数组，找出其中两个数满足相加等于你指定的目标数字。
 * * 要求：这个函数twoSum必须要返回能够相加等于目标数字的两个数的索引，且index1必须要小于index2。
 * * 请注意一点，你返回的结果（包括index1和index2）都不是基于0开始的。你可以假设每一个输入肯定只有一个结果。
 * <p>
 * 算符合结果的下标位置
 */
public class TwoSumSolution {

    private static class Node implements Comparable<Node> {
        public int idx;

        public int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return -1;
            }
            return this.val - o.val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", val=" + val +
                    '}';
        }
    }

    /**
     * <pre>
     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     *
     * 题目大意
     * 给定一个整数数组，找出其中两个数满足相加等于你指定的目标数字。
     * 要求：这个函数twoSum必须要返回能够相加等于目标数字的两个数的索引，且index1必须要小于index2。
     * 请注意一点，你返回的结果（包括index1和index2）都不是基于0开始的。你可以假设每一个输入肯定只有一个结果。
     *
     * 解题思路
     * 创建一个辅助类数组，对辅助类进行排序，使用两个指针，开始时分别指向数组的两端，看这两个下标对应的值是否
     * 等于目标值，如果等于就从辅助类中找出记录的下标，构造好返回结果，返回。如果大于就让右边的下标向左移，
     * 进入下一次匹配，如果小于就让左边的下标向右移动，进入下一次匹配，直到所有的数据都处理完
     * </pre>
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] solution_1(int[] nums, int target) {
        int[] result = {0, 0}; //结果索引的集合

        List<Node> nodes = IntStream.range(0, nums.length)
                .mapToObj(i -> new Node(i, nums[i]))
                .sorted()
                .collect(Collectors.toList());

        int i = 0;
        int j = nodes.size() - 1;

        while (i < j) {
            if (nodes.get(i).getVal() + nodes.get(j).getVal() < target) {
                i++;
            } else if (nodes.get(i).getVal() + nodes.get(j).getVal() > target) {
                j--;
            } else { //相等的情况下
                if (nodes.get(i).getIdx() > nodes.get(j).getIdx()) {
                    result[0] = nodes.get(j).getIdx() + 1;
                    result[1] = nodes.get(i).getIdx() + 1;
                }

                if (nodes.get(i).getIdx() < nodes.get(j).getIdx()) {
                    result[0] = nodes.get(i).getIdx() + 1;
                    result[1] = nodes.get(j).getIdx() + 1;
                }
                break;
            }
        }

        return result;
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * <p>
     * <p>
     * 暴力解决方法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] solution_2(int[] nums, int target) {
        int[] result = {0, 0}; //结果索引的集合

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    public int[] solution_3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];

            if (map.containsKey(x) && map.get(x) != i) {
                return new int[]{i, map.get(x)};
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] params = new int[]{11, 7, 2, 15};
        System.out.println(Arrays.toString(new TwoSumSolution().solution_3(params, 9)));
    }
}
