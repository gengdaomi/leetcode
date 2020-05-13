package com.leetcode.expand;

/**
 * source: bytedancer
 * <p>
 * x 的平方根
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * Created By Andrew-Geng on 2020/5/13 2:38 下午
 */
public class MySqrtSolution {

    public static void main(String[] args) {
        MySqrtSolution mySqrtSolution = new MySqrtSolution();
        int result = mySqrtSolution.mySqrt1(6);

        System.out.println(result);
    }

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1, right = x;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sqrt = x / mid;

            if (sqrt == mid) {
                return mid;
            } else if (sqrt < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

    /**
     * 通过公式 2r=r+x/r ，不断的循环迭代 r的平方=x
     *
     * @param x
     * @return
     */
    public int mySqrt1(int x) {
        long r = x;

        while (r * r > x) {
            r = (r + x / r) / 2;
        }

        return (int) r;
    }
}
