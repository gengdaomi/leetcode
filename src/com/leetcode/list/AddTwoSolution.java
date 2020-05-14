package com.leetcode.list;

/**
 * source： bytedance
 * 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * Created By Andrew-Geng on 2020/5/14 1:59 下午
 */
public class AddTwoSolution {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode q = l1, p = l2;
        ListNode cur = head;

        int temp = 0;//进位
        while (q != null || p != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;

            int sum = x + y + temp;
            cur.next = new ListNode(sum % 10);
            cur =cur.next;
            temp = sum / 10;

            p = (p != null) ? p.next : null;
            q = (q != null) ? q.next : null;
        }

        if(temp!=0){
            cur.next = new ListNode(temp);
        }

        return head.next;
    }

    class ListNode {

        ListNode next;
        Integer val;

        public ListNode(Integer val) {
            this.val = val;
            this.next = null;
        }
    }
}
