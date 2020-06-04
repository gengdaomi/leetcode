package com.leetcode.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * 题目1
 * Created By Andrew-Geng on 2020/6/4 8:55 下午
 */
public class FiveThreadSolution {
    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(1);
    private static Semaphore C = new Semaphore(1);
    private static Semaphore D = new Semaphore(1);
    private static Semaphore E = new Semaphore(1);

    private static Map<String, String> taskMap;
    private static Map<String, Student> studentMap;

    //初始化任务池
    static {
        taskMap = new ConcurrentHashMap<>();
        taskMap.put("A", "a");
        taskMap.put("B", "b");
        taskMap.put("C", "c");
        taskMap.put("D", "d");
        taskMap.put("E", "e");

        studentMap = new ConcurrentHashMap<>();
        studentMap.put("A", new Student("A", A, B));
        studentMap.put("B", new Student("B", B, C));
        studentMap.put("C", new Student("C", C, D));
        studentMap.put("D", new Student("D", D, E));
        studentMap.put("E", new Student("E", E, A));

    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                A.acquire();

                if (taskMap.containsKey("A")) { //找到符合自己的task
                    System.out.println("当前A 执行任务：" + taskMap.get("A"));
                    taskMap.remove("A");
                }

                System.out.println("A 处理完后还剩任务：" + taskMap.values());

                B.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            try {
                B.acquire();

                if (taskMap.containsKey("B")) { //找到符合自己的task
                    System.out.println("当前B 执行任务：" + taskMap.get("B"));
                    taskMap.remove("B");
                }

                System.out.println("B 处理完后还剩任务：" + taskMap.values());

                C.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    static class ThreadC extends Thread {

        @Override
        public void run() {
            try {
                C.acquire();

                if (taskMap.containsKey("C")) { //找到符合自己的task
                    System.out.println("当前C 执行任务：" + taskMap.get("C"));
                    taskMap.remove("C");
                }

                System.out.println("C 处理完后还剩任务：" + taskMap.values());

                D.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadD extends Thread {

        @Override
        public void run() {
            try {
                D.acquire();

                if (taskMap.containsKey("D")) { //找到符合自己的task
                    System.out.println("当前D 执行任务：" + taskMap.get("D"));
                    taskMap.remove("D");
                }

                System.out.println("D 处理完后还剩任务：" + taskMap.values());

                E.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadE extends Thread {

        @Override
        public void run() {
            try {
                E.acquire();
                if (taskMap.containsKey("E")) { //找到符合自己的task
                    System.out.println("当前E 执行任务：" + taskMap.get("E"));
                    taskMap.remove("E");
                }

                System.out.println("E 处理完后还剩任务：" + taskMap.values());
                A.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class StudentThread extends Thread {
        String name;

        public StudentThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                studentMap.get(name).self.acquire();
                if (taskMap.containsKey(name)) { //找到符合自己的task
                    System.out.println(name+" 执行任务：" + taskMap.get(name));
                    taskMap.remove(name);
                }

                System.out.println(name + " 处理完后还剩任务：" + taskMap.values());
                studentMap.get(name).next.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 学生信息
     */
    static class Student {
        Semaphore next;  //下游
        Semaphore self; //自己
        String name;

        public Student(String name, Semaphore self, Semaphore next) {
            this.name = name;
            this.self = self;
            this.next = next;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 开始只有A可以获取
        for(Student student: studentMap.values()){
            if(!student.name.equals("A")){
                student.self.acquire();
            }
        }

//        B.acquire();
//        C.acquire();
//        D.acquire();
//        E.acquire();

        for(Student student: studentMap.values()){
            new FiveThreadSolution.StudentThread(student.name).start();
        }

//        new FiveThreadSolution.ThreadB().start();
//        new FiveThreadSolution.ThreadA().start();
//        new FiveThreadSolution.ThreadC().start();
//        new FiveThreadSolution.ThreadD().start();
//        new FiveThreadSolution.ThreadE().start();

    }
}
