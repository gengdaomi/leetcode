package com.leetcode.list;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * <p>
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * Created By Andrew-Geng on 2020/5/14 2:46 下午
 */
public class MergeSortSolution {

    public static void main(String[] args) {

    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }

        // 使用快慢指针查找中间结点
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            if (fast == null){// 让slow少走一步，结点数目均匀
                break;
            }

            slow = slow.next;
        }


        ListNode right = slow.next;
        slow.next = null; //注意断链

        ListNode left = sortList(head);
        right = sortList(right);

        return mergeTwoList(left, right);
    }

    /**
     * 通过归并排序 合并两个子序列
     *
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode result = null;

        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            result = l1;
            l1.next = mergeTwoList(l1.next, l2);
        } else {
            result = l2;
            l2.next = mergeTwoList(l1, l2.next);
        }

        return result;
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
