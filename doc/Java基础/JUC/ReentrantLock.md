ReentrantLock 内部包含一个实现AQS的内部类Sync,还有两个继承Sync的子类，FairSync(公平锁)和NoneFairSync(非公平锁)，通过这两种锁，来实现ReentrantLock的锁机制

首先AQS(AbstractQueuedSynchronizer)
维护一个Node，形成链表
每一个node都包含preNode，nextNode，waitStatus(node的等待状态)，thread，status(锁的状态)
AQS也维护了一个status，描述当前的锁状态，都是通过volatile进行修饰
通过检查此状态，当status>0时，说明此锁在使用中，针对可重入锁，判断锁拥有者是不是当前线程

ReentrantLock lock = new ReentrantLock();
当进行lock.lock()获取锁时，当是公平锁-走FairSync的lock 非公平锁 走NoneFairSync
公平锁是对当前请求获取锁的线程进行入队，放在上面有node组成的链表的末端
非公平锁直接去获取锁，如果获取不到，在进行入队放在链表的末端