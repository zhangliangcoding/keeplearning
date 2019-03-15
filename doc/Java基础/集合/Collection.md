## List

### ArrayList
> 初始10 ，每次增加capacity的一半
### LinkedList
> 初始10 ，每次增加capacity的一半，每一个阶段维护上一节点和下一节点的信息，形成链式结构，peek 查看第一个，pool 查看第一个后移除list，offer 后面追加，push 放在最前，pop 直接移除第一个
### Vector
> 初始10 ，每次增加capacity的一半，线程安全，通过在方法级别加synchronized进行加锁

通过Collections.synchronizedCollection 把非线程安全的集合转换成线程安全的集合，在方法上synchronized关键字

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