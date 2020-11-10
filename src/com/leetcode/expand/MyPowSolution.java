package com.leetcode.expand;

/**
 * Created By Andrew-Geng on 2020/11/10 4:30 下午
 *
 * 求解a的n次方
 */
public class MyPowSolution {

    /**
     * 传统做法 时间复杂度O(n)
     * @param a
     * @param n
     * @return
     */
    public Integer pow(Integer a,Integer n) {
        int res = 1;
        for(int i = 1; i <= n; i++) {
            res *= a;
        }
        return res;
    }

    /**
     * 推荐做法
     *
     * 快速幂思想
     * 
     * 求斐波那契数列的第n项，我们就可以通过快速幂将复杂度降到O(logn)，
     * @param a
     * @param n
     * @return
     */
    public Integer pow_1(Integer a,Integer n) {
        int res = 1;
        while(n > 0) {
            if(n % 2 == 1) res *= a;
            n >>= 1;
            if(n > 0) a *= a;
        }
        return res;
    }

    public static void main(String[] args){
        System.out.println(new MyPowSolution().pow_1(2,8));
    }
}
