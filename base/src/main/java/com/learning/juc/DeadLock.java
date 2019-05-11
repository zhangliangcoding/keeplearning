package com.learning.juc;

/**
 * @Auther: zhangll
 * @Date: 2019/5/11 17:18
 * @Description: jps -lvm 查看java进程的pid及相关信息
 * jstack -l pid 查看线程的堆栈信息
 * 死锁堆栈如下
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.112-b15 mixed mode):

"DestroyJavaVM" #14 prio=5 os_prio=0 tid=0x00000000030d3800 nid=0x3ea4 waiting on condition [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"Thread-1" #13 prio=5 os_prio=0 tid=0x000000001fb29000 nid=0x408 waiting for monitor entry [0x000000002036f000]
java.lang.Thread.State: BLOCKED (on object monitor)
at com.learning.juc.DeadLock.method2(DeadLock.java:28)
- waiting to lock <0x000000076b60b7b8> (a java.lang.Object)
- locked <0x000000076b60b7c8> (a java.lang.Object)
at com.learning.juc.DeadLock.lambda$main$1(DeadLock.java:45)
at com.learning.juc.DeadLock$$Lambda$2/1198108795.run(Unknown Source)
at java.lang.Thread.run(Thread.java:745)

Locked ownable synchronizers:
- None

"Thread-0" #12 prio=5 os_prio=0 tid=0x000000001fb28800 nid=0x7a8c waiting for monitor entry [0x000000002026e000]
java.lang.Thread.State: BLOCKED (on object monitor)
at com.learning.juc.DeadLock.method1(DeadLock.java:18)
- waiting to lock <0x000000076b60b7c8> (a java.lang.Object)
- locked <0x000000076b60b7b8> (a java.lang.Object)
at com.learning.juc.DeadLock.lambda$main$0(DeadLock.java:38)
at com.learning.juc.DeadLock$$Lambda$1/1880587981.run(Unknown Source)
at java.lang.Thread.run(Thread.java:745)

Locked ownable synchronizers:
- None

"Service Thread" #11 daemon prio=9 os_prio=0 tid=0x000000001ee27800 nid=0x874 runnable [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"C1 CompilerThread3" #10 daemon prio=9 os_prio=2 tid=0x000000001ed6d000 nid=0x8958 waiting on condition [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"C2 CompilerThread2" #9 daemon prio=9 os_prio=2 tid=0x000000001ed68000 nid=0x1524 waiting on condition [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"C2 CompilerThread1" #8 daemon prio=9 os_prio=2 tid=0x000000001ed66000 nid=0x6f8 waiting on condition [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"C2 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x000000001ed61800 nid=0x3a30 waiting on condition [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x000000001ed5e800 nid=0x6dc0 runnable [0x000000001f36e000]
java.lang.Thread.State: RUNNABLE
at java.net.SocketInputStream.socketRead0(Native Method)
at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
at java.net.SocketInputStream.read(SocketInputStream.java:170)
at java.net.SocketInputStream.read(SocketInputStream.java:141)
at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
- locked <0x000000076b6cf258> (a java.io.InputStreamReader)
at java.io.InputStreamReader.read(InputStreamReader.java:184)
at java.io.BufferedReader.fill(BufferedReader.java:161)
at java.io.BufferedReader.readLine(BufferedReader.java:324)
- locked <0x000000076b6cf258> (a java.io.InputStreamReader)
at java.io.BufferedReader.readLine(BufferedReader.java:389)
at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)

Locked ownable synchronizers:
- None

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x000000001eb9f000 nid=0x46c4 waiting on condition [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x000000001eb9e800 nid=0x8648 runnable [0x0000000000000000]
java.lang.Thread.State: RUNNABLE

Locked ownable synchronizers:
- None

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x000000001eb30800 nid=0x7234 in Object.wait() [0x000000001f00f000]
java.lang.Thread.State: WAITING (on object monitor)
at java.lang.Object.wait(Native Method)
- waiting on <0x000000076b408e98> (a java.lang.ref.ReferenceQueue$Lock)
at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
- locked <0x000000076b408e98> (a java.lang.ref.ReferenceQueue$Lock)
at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

Locked ownable synchronizers:
- None

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x00000000031cb000 nid=0x6208 in Object.wait() [0x000000001eb0f000]
java.lang.Thread.State: WAITING (on object monitor)
at java.lang.Object.wait(Native Method)
- waiting on <0x000000076b406b40> (a java.lang.ref.Reference$Lock)
at java.lang.Object.wait(Object.java:502)
at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
- locked <0x000000076b406b40> (a java.lang.ref.Reference$Lock)
at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

Locked ownable synchronizers:
- None

"VM Thread" os_prio=2 tid=0x000000001cc39000 nid=0x5748 runnable

"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x00000000030e9800 nid=0x5604 runnable

"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x00000000030eb000 nid=0x7304 runnable

"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x00000000030ec800 nid=0x51d8 runnable

"GC task thread#3 (ParallelGC)" os_prio=0 tid=0x00000000030ee000 nid=0x7c90 runnable

"GC task thread#4 (ParallelGC)" os_prio=0 tid=0x00000000030f1800 nid=0x8220 runnable

"GC task thread#5 (ParallelGC)" os_prio=0 tid=0x00000000030f2800 nid=0x5cb4 runnable

"GC task thread#6 (ParallelGC)" os_prio=0 tid=0x00000000030f5800 nid=0x7fa0 runnable

"GC task thread#7 (ParallelGC)" os_prio=0 tid=0x00000000030f7000 nid=0x72e8 runnable

"VM Periodic Task Thread" os_prio=2 tid=0x000000001eefc000 nid=0x7390 waiting on condition

JNI global references: 336


Found one Java-level deadlock:
=============================
"Thread-1":
waiting to lock monitor 0x000000001cc42318 (object 0x000000076b60b7b8, a java.lang.Object),
which is held by "Thread-0"
"Thread-0":
waiting to lock monitor 0x000000001cc43658 (object 0x000000076b60b7c8, a java.lang.Object),
which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
at com.learning.juc.DeadLock.method2(DeadLock.java:28)
- waiting to lock <0x000000076b60b7b8> (a java.lang.Object)
- locked <0x000000076b60b7c8> (a java.lang.Object)
at com.learning.juc.DeadLock.lambda$main$1(DeadLock.java:45)
at com.learning.juc.DeadLock$$Lambda$2/1198108795.run(Unknown Source)
at java.lang.Thread.run(Thread.java:745)
"Thread-0":
at com.learning.juc.DeadLock.method1(DeadLock.java:18)
- waiting to lock <0x000000076b60b7c8> (a java.lang.Object)
- locked <0x000000076b60b7b8> (a java.lang.Object)
at com.learning.juc.DeadLock.lambda$main$0(DeadLock.java:38)
at com.learning.juc.DeadLock$$Lambda$1/1880587981.run(Unknown Source)
at java.lang.Thread.run(Thread.java:745)

Found 1 deadlock.

 */

public class DeadLock {

    static Object o1 = new Object();
    static Object o2 = new Object();

    static void method1() throws InterruptedException {
        synchronized (o1) {
            Thread.sleep(5*1000);
            System.out.println("获取o1的锁");
            synchronized (o2) {
                System.out.println("获取o2的锁");

            }
        }
    }

    static void method2(){
        synchronized (o2) {
            System.out.println("获取o2的锁");
            synchronized (o1) {
                System.out.println("获取o1的锁");

            }
        }
    }


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                DeadLock.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            DeadLock.method2();
        }).start();
    }








}
