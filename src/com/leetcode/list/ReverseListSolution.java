package com.leetcode.list;

/**
 * source : bytedance
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * Created By Andrew-Geng on 2020/5/14 1:03 下午
 */
public class ReverseListSolution {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head;
        ListNode next = head.next;

        head.next = null;

        while (next != null) {
            head = next; // head执行原先的head.next
            next = head.next; // next指向head.next.next

            // 此时实现head.next的反转，指向head
            head.next = pre; // head.next执行head，完成了head.next的反转
            pre = head; // pre执行head.next
        }

        return head;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode second = head.next;
        ListNode reverseHead = reverseList1(second);

        second.next = head;
        head.next = null;
        return reverseHead;
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
