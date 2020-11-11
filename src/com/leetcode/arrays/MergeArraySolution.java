package com.leetcode.arrays;

/**
 * Created By Andrew-Geng on 2020/11/10 9:02 下午
 *
 * 合并两个有序的数组
 * 限定语言：C++、Java
 * 给出两个有序的整数数组 和 ，请将数组 合并到数组 中，变成一个有序的数组
 * 注意：
 * 可以假设 数组有足够的空间存放 数组的元素， 和 中初始的元素数目分别为 和
 *
 *
 *
 */
public class MergeArraySolution {

    public static void main(String[] args){

    }

    public void merge(int A[], int m, int B[], int n) {
        int i=m-1;
        int j=n-1;

        int size=m+n-1;

        while(i>=0 && j>=0){
            A[size--]=A[i]>B[j]?A[i--]:B[j--];
        }
        while(j>=0){
            A[size--]=B[j--];
        }
    }
}
