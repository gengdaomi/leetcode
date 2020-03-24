package com.leetcode.cache;

import java.util.HashMap;

/**
 * Created by Andrew-Geng 2020/3/24-3:41 下午
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // 返回  1
 * cache.put(3, 3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4, 4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */
public class LRUCacheSolution {
    private int capacity;
    private HashMap<Integer, Node> caches;
    private Node first;
    private Node last;

    public LRUCacheSolution(int capacity) {
        this.capacity = capacity;
        caches = new HashMap<>(capacity);
    }

    public void put(int key, int value) {
        Node node = caches.get(key);
        // 首先得先判断是否存在元素
        if (node == null) {
            // 如果不存在，先判断容量
            if (capacity <= caches.size()) {
                // 不够，先移除最后一个
                removeLast();
            }
            node = new Node();
            node.key = key;
        }
        node.value = value;
        moveNodeToFirst(node);
        caches.put(key, node);
    }

    private void removeLast() {
        if (last != null) {
            caches.remove(last.key);
            // 最后
            last = last.pre;
            if (last != null) {
                last.next = null;
            } else {
                first = null;
            }
        }
    }

    private void moveNodeToFirst(Node node) {
        if (node == first || node == null) return;
        // 先连接
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (node == last) {
            last = last.pre;
        }
        if (last == null || first == null) {
            last = first = node;
            return;
        }
        node.next = first;
        first.pre = node;
        first = node;
        node.pre = null;
    }

    public int get(int key) {
        Node node = caches.get(key);
        if (node == null) return -1;
        moveNodeToFirst(node);
        return node.value;
    }

    class Node {
        Node next;
        Node pre;
        int key;
        int value;
    }

}

