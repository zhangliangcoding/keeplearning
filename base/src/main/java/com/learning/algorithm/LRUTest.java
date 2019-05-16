package com.learning.algorithm;

import java.util.HashMap;

/**
 * @Auther: zhangll
 * @Date: 2019/5/13 16:23
 * @Description:
 */
public class LRUTest<K,V> {


    private HashMap<K,Node> map;
    private int capacity;

    private Node<K, V> head;
    private Node<K, V> tail;

    class Node<K,V>{
        private V value;
        private K key;

        private Node pre;
        private Node next;

        public Node(V value, K key) {
            this.value = value;
            this.key = key;
        }
    }

    public LRUTest() {
        new LRUTest<>(16);
    }

    public LRUTest(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public void set(K k, V v) {
        Node node = map.get(k);
        if (node != null) {
            node.value = v;
            removeNode(node);
        }else {
            node = new Node(k, v);
            if (map.size() >= this.capacity) {
                removeTail();
            }
            map.put(k, node);
        }
        setHead(node);
    }

    public V get(K k) {
        Node node = map.get(k);
        if (node != null) {
            removeNode(node);
            setHead(node);
            return (V) node.value;
        }
        return null;
    }

    private void removeTail() {
        if (tail != null) {
            map.remove(tail.key);
            tail.pre.next = null;
        }
    }

    private void setHead(Node node) {

        if (head != null) {
            node.next = head;
            head.pre = node;
            head = node;
        } else {
            head = node;
            tail = node;
        }


    }

    private void removeNode(Node node) {//从链表中删除node
        if (node.pre != null) {
            node.pre.next = node.next;
        }else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }else {
            tail = node.pre;
        }
        node.next = null;
        node.pre = null;
    }

    public void print(){
        Node node = head;
        if (node != null) {
            do {
                System.out.println(node.value);
                node = node.next;
            } while (node != null);
        }
    }


    public static void main(String[] args) {
        LRUTest<String, String> lruTest = new LRUTest<>(5);
        lruTest.set("1","a");
        lruTest.set("2","b");
        lruTest.set("3","c");
        lruTest.get("2");
        lruTest.set("4","d");
        lruTest.set("5","e");
        lruTest.set("6","f");
        lruTest.set("7","g");
        lruTest.get("3");
        lruTest.print();
    }





}
