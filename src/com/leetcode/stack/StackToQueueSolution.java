package com.leetcode.stack;

import java.util.Stack;

/**
 * Created By Andrew-Geng on 2020/11/7 10:05 下午
 * 两个栈实现一个队列
 *
 * 插入数据：将数据插入到栈1中，检查栈2是否为空，如果为空则将栈1中的数据全部弹出到栈2当中，此时栈2再弹出数据会保持“先进先出”的效果，可以理解为类似负负得正的效果，即两次“后进先出”得到“先进先出”；
 *
 * 弹出数据：如果栈2不为空，则弹出栈2数据，否则弹出栈1数据，两个栈都为空时会抛出NoSuchElementException
 *
 */
public class StackToQueueSolution<T> {

    private Stack<T> stack1;
    private Stack<T> stack2;

    public StackToQueueSolution() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(T t) {
        stack1.push(t);
        if(stack2.empty()) {
            while (!stack1.empty()) {
                stack2.add(stack1.pop());
            }
        }
    }

    public T pop() {
        if(!stack2.empty()) {
            return stack2.pop();
        } else {
            return stack1.pop();
        }
    }

    public boolean isEmpty() {
        return stack1.empty() && stack2.empty();
    }

}
