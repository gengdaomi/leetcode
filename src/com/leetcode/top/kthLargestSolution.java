package com.leetcode.top;

/**
 * Created by ayue on 上午9:56 2018/6/28
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
 * <p>
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class kthLargestSolution {

    public static void main(String[] args) {
        int[] integers = new int[]{33, 1, 3, 234, 5, 3435, 32, 34};

        System.out.println(new kthLargestSolution().findKthLargest(integers, 1));
    }


    public int findKthLargest(int[] nums, int k) {
        return sort(nums, nums.length - k, 0, nums.length - 1);
    }

    public int sort(int[] nums, int k, int left, int right) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = left;
        int j = right;

        int p = nums[i];

        while (i < j) {
            while (i < j && p <= nums[j]) {
                j--;
            }

            if (p > nums[j]) {
                nums[i++] = nums[j];
            }

            while (i < j && p >= nums[i]) {
                i++;
            }

            if (p < nums[i]) {
                nums[j--] = nums[i];
            }
        }

        nums[i] = p;

        if (left < i && k < i) {
            return sort(nums, k, left, i - 1);
        }
        if (right > j && k > j) {
            return sort(nums, k, j + 1, right);
        }

        return nums[i];
    }
}
