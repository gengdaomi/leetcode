package com.leetcode.palindrome;

/**
 * Created By Andrew-Geng on 2020/6/2 2:36 下午
 * 判断一个数字 是否是回文数
 */
public class CheckPalindromeSolution {

    public static void main(String[] args) {

    }

    /**
     * 依次比较数字的头和尾，如果相等，去掉头和尾，再进行比较
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        int a = x, h = 1;

        if (a < 0) return false;

        while (a / h >= 10) {
            h = h * 10;
        }

        while (a > 0) {
            if (a / h != a % 10) {
                return false;
            }

            a = a % h;
            a = a / 10;
            h = h / 100;
        }

        return true;
    }

    /**
     * 将数字反转，只需要判断反转前后是否相等即可 但是需要注意，如果数字过大可能导致溢出的问题
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        int a = x, r = 0;

        if (x < 0) {
            return false;
        }

        while (a > 0) {
            r = r * 10 + a % 10;
            a = a / 10;
        }

        return r == x;
    }
}
