## List

### ArrayList
> 初始10 ，内部数组存储，每次扩容增加capacity的一半，再进行arraycopy，查找效率高，删除插入效率低，需要移动其他元素
### LinkedList
> 初始10 ，每次增加capacity的一半，每一个阶段维护上一节点和下一节点的信息，形成链式结构，peek 查看第一个，pool 查看第一个后移除list，offer 后面追加，push 放在最前，pop 直接移除第一个
> get(index)时，分成两段查询，插入和删除效率高，查询效率低
### Vector
> 类似ArrayList，初始10 ，每次增加capacity的一倍，线程安全，通过在方法级别加synchronized进行加锁,再进行arraycopy

通过Collections.synchronizedCollection 把非线程安全的集合转换成线程安全的集合，在方法上synchronized关键字

##Map

###HashMap
> 由数组加链表组成，数组存储Node元素，每个Node包含 K V hashcode 和nextNode，根据K的hashcode计算出放在哪个位置，无序的，当出现Hash碰撞时，放入同一个位置，形成链表，当链表达到阈值，进行树形化
> 在并发环境下容易形成循环链表，线程不安全
###HashTable
>线程安全，通过sychronized在方法级别实现同步，效率低

###TreeMap
>使用红黑树实现，线程不安全

###LinkedHashMap
>在HashMap基础上，加入一个链表，记录元素的顺序

## Set
> Set 是通过Map来实现的，数据存放在map的key中，value是统一默认的一个Object
### SortedSet

### HashSet 
> 通过HashMap实现

### TreeSet
> 红黑树实现 


## Queue

### 非阻塞
priorityQueue
ConcurrentLinkedQueue
### 阻塞 
ArrayBlockingQueue 数组组成
LinkedBlockingQueue 链接节点组成
PriorityBlockingQueue 优先级
DelayQueue
SynchronousQueue