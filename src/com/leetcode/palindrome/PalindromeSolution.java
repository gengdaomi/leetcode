package com.leetcode.palindrome;

/**
 * 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * <p>
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * <p>
 * 解题思路：
 */
public class PalindromeSolution {

    private int low;

    private int maxLength;

    public static void main(String[] args) {
        String s=  "babad";

        System.out.println(new PalindromeSolution().longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        int len = s.length();

        if (len < 2) {
            return s;
        }

        for (int i = 0; i < len - 1; i++) {//遍历整个数组，寻找回文子串最中间的字符，如果回文子串是奇数长度，那么最中间有一个字符；否则有两个字符

            extendPalindrome(s, i, i);  //extendPalindrome方法就是以遍历到的当前字符为中心向左右两边扩展

            extendPalindrome(s, i, i + 1); //assume even length.
        }
        return s.substring(low, low + maxLength);
    }


    /**
     * @param s
     * @param j
     * @param k
     */
    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;//如果满足括号里的条件，则继续向两边扩展
            k++;
        }

        if (maxLength < k - j - 1) {//更新最大长度的值
            low = j + 1;
            maxLength = k - j - 1;
        }
    }
}
