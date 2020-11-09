package com.leetcode.sum;

import java.util.*;

/**
 * Created By Andrew-Geng on 2020/11/9 11:38 下午
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class ThreeSumSolution {

    /**
     * 不排序
     * <p>
     * 这个题目的难点之一，就是要求输出的三元组不重复。如果想要在不对原始数据进行排序的情况下解这个题目，就要考虑分情况将结果集分为一个完备集合组，即所有情况之间没有交集，且合起来构成完整的结果集。
     * 不排序下的分情况讨论：
     * <p>
     * 三元组中包含重复元素，例如 {2, 2, -4}，{0, 0, 0} 这些情况。
     * 两正一负，例如 {1, 2, -3} 这种。
     * 两负一正，例如 {-1, -2, 3} 这种。
     * 一正一负，例如 {-1, 1, 0} 这种。
     * 其中第四种情况可以合并到第二种，第三种中的任意一种情况中去
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;
        ArrayList<Integer> l0 = new ArrayList<>(nums.length);//存所有去重后的负数
        ArrayList<Integer> g0 = new ArrayList<>(nums.length);//存所有去重后的正数和0
        HashMap<Integer, Integer> hashMap = new HashMap<>();//存所有的数

        for (int i = 0; i < nums.length; ++i) {
            if (hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], hashMap.get(nums[i]) + 1);
            } else if (nums[i] < 0) {
                l0.add(nums[i]);
                hashMap.put(nums[i], 1);
            } else {
                g0.add(nums[i]);
                hashMap.put(nums[i], 1);
            }
        }
        //处理包含重复元素的情况
        for (Integer e : hashMap.keySet()) {
            if (hashMap.get(e) > 1) {
                int target = -2 * e;
                if ((e != 0 && hashMap.containsKey(target)) || (e == 0 && hashMap.get(0) > 2)) {
                    res.add(Arrays.asList(e, e, target));
                }
            }
        }
        //两负一正的情况
        for (int i = 0; i < l0.size(); ++i) {
            for (int j = i + 1; j < l0.size(); ++j) {
                int e1 = l0.get(i), e2 = l0.get(j);
                int target = -(e1 + e2);
                if (target != e1 && target != e2 && hashMap.containsKey(target)) {
                    res.add(Arrays.asList(e1, e2, target));
                }
            }
        }
        //两正一负或一正一负
        for (int i = 0; i < g0.size(); ++i) {
            for (int j = i + 1; j < g0.size(); ++j) {
                int e1 = g0.get(i), e2 = g0.get(j);
                int target = -(e1 + e2);
                if (target != e1 && target != e2 && hashMap.containsKey(target)) {
                    res.add(Arrays.asList(e1, e2, target));
                }
            }
        }
        return res;
    }


    /**
     * 先简单提一下，有序数组的两数和问题。这个问题是假设数组有序，然后寻找数组中和等于某个特定值的两个数。
     * 这个问题可以使用双指针算法求解，假设数组按升序排序，使用两个指针分别指向数组的第一个元素和最后一个元素，如果两个指针指向的两个元素和小于给定值，则前指针后移，增大两元素和；相反的，如果大于给定值则后指针前移，减小两元素和。而如果等于的话，解就找到了，如果直至两指针相遇还没找到，则无解。
     *
     * @param a
     * @param target
     * @return
     */
    public static int[] twoSum(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (true) {
            if (target == a[i] + a[j]) return new int[]{i, j};
            if (a[i] + a[j] < target) ++i;
            else --j;
            if (i == j) return null;
        }
    }

    /**
     * 本题需要找到三个和为 0 的元素，对数组排序后，遍历数组，以当前元素的相反数为两数和，然后在当前元素后的所有元素范围内使用双指针算法寻找另两个元素。
     *
     * @param num
     * @return
     */
    public List<List<Integer>> threeSum_1(int[] num) {
        Arrays.sort(num);//排序
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length - 2; ++i) {
            if (i > 0 && num[i] == num[i - 1]) continue; //避免重复三元组
            int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
            while (lo < hi) {//有序数组找特定和的两元素，双指针算法
                if (num[lo] + num[hi] == sum) {
                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
                    while (lo < hi && num[lo] == num[lo + 1]) lo++;//避免重复三元组
                    while (lo < hi && num[hi] == num[hi - 1]) hi--;//避免重复三元组
                    lo++;
                    hi--;
                } else if (num[lo] + num[hi] < sum) lo++;
                else hi--;
            }
        }
        return res;
    }

    /**
     * 最优解法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_3(int[] nums) {
        if (nums.length < 3) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int negSize = 0, posSize = 0;
        int zeroSize = 0;
        for (int v : nums) {
            if (v < minValue) minValue = v;
            if (v > maxValue) maxValue = v;
            if (v > 0) posSize++;
            else if (v < 0) negSize++;
            else zeroSize++;
        }
        if (zeroSize >= 3) res.add(Arrays.asList(0, 0, 0));//输出 三个 0 的情况
        if (negSize == 0 || posSize == 0) return res;
        //此时minValue一定为负数，maxValue一定为正数
        //如果maxValue > -2*minValue，那么大于 -2*minValue的元素肯定不会是答案，可以排除掉，所以更新maxValue
        if (minValue * 2 + maxValue > 0) maxValue = -minValue * 2;
            //同理更新minValue
        else if (maxValue * 2 + minValue < 0) minValue = -maxValue * 2;
        //自己构建一个hashmap，值表示元素重复次数，注意java数组默认值为 0
        int[] map = new int[maxValue - minValue + 1];
        int[] negs = new int[negSize];
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        for (int v : nums) {
            if (v >= minValue && v <= maxValue) {//只保留在[minValue,maxValue]区间内的元素
                if (map[v - minValue]++ == 0) {//计数加去重
                    if (v > 0) poses[posSize++] = v;//poses数组存所有去重后的正值
                    else if (v < 0) negs[negSize++] = v;//negs数组存所有去重后的负值
                }
            }
        }
        //正负数两数组排序
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int basej = 0;
        for (int i = negSize - 1; i >= 0; i--) {//负数数组从后往前遍历
            int nv = negs[i];//nv为当前负数值
            //minp = -nv/2，相当于三元组中另外两元素的平均值，即为另两个元素中较小值的上界，较大值的下界
            int minp = (-nv) >>> 1;
            //定位到正数数组中值刚好小于平均值的元素（这个地方应该还可以优化为使用二分查找）
            while (basej < posSize && poses[basej] < minp) basej++;
            for (int j = basej; j < posSize; j++) {//正数数组从大于等于平均值的元素开始遍历
                int pv = poses[j];//pv 为当前正数值
                int cv = 0 - nv - pv;//cv 为要寻找的另一个值
                //目标值 cv 应该在 [nv,pv] 当中
                //如果不限制cv<=pv，当nv为奇数时，有可能会重复输出
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        if (map[nv - minValue] > 1)//两个相同的负数和一个正数的情况
                            res.add(Arrays.asList(nv, nv, pv));
                    } else if (cv == pv) {
                        if (map[pv - minValue] > 1)//两个相同的正数和一个负数的情况
                            res.add(Arrays.asList(nv, pv, pv));
                    } else {
                        if (map[cv - minValue] > 0)//三个不同元素的情况
                            res.add(Arrays.asList(nv, cv, pv));
                    }
                } else if (cv < nv) break;//如果 cv 小于 nv了，表明这种情况会在后面寻找，为避免重复输出，跳出循环
            }
        }

        return res;
    }
}
