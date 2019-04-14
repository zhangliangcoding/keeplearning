###前言
1.8版本的ConcurrentHashMap较1.7有很大的改动，1.7是使用分段锁来进行并发控制，使用Segment进行分段，每个Segment继承ReentrantLock
1.8之后再Map的每个同加锁，当当前桶没有数据时进行cas设置第一个值，后续在进行链表追加的时候，使用synchronize对桶中的第一个数据加锁
使锁的粒度更细，支持更高并发

###基本参数
    // 表的最大容量
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    // 默认表的大小
    private static final int DEFAULT_CAPACITY = 16;
    // 最大数组大小
    static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    // 默认并发数
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    // 装载因子
    private static final float LOAD_FACTOR = 0.75f;
    // 转化为红黑树的阈值
    static final int TREEIFY_THRESHOLD = 8;
    // 由红黑树转化为链表的阈值
    static final int UNTREEIFY_THRESHOLD = 6;
    // 转化为红黑树的表的最小容量
    static final int MIN_TREEIFY_CAPACITY = 64;
    // 每次进行转移的最小值
    private static final int MIN_TRANSFER_STRIDE = 16;
    // 生成sizeCtl所使用的bit位数
    private static int RESIZE_STAMP_BITS = 16;
    // 进行扩容所允许的最大线程数
    private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;
    // 记录sizeCtl中的大小所需要进行的偏移位数
    private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;    
    // 一系列的标识
    static final int MOVED     = -1; // hash for forwarding nodes
    static final int TREEBIN   = -2; // hash for roots of trees
    static final int RESERVED  = -3; // hash for transient reservations
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    // 
    /** Number of CPUS, to place bounds on some sizings */
    // 获取可用的CPU个数
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    // 表
    transient volatile Node<K,V>[] table;
    // 下一个表
    private transient volatile Node<K,V>[] nextTable;
    //
    /**
     * Base counter value, used mainly when there is no contention,
     * but also as a fallback during table initialization
     * races. Updated via CAS.
     */
    // 基本计数
    private transient volatile long baseCount;
    //
    /**
     * Table initialization and resizing control.  When negative, the
     * table is being initialized or resized: -1 for initialization,
     * else -(1 + the number of active resizing threads).  Otherwise,
     * when table is null, holds the initial table size to use upon
     * creation, or 0 for default. After initialization, holds the
     * next element count value upon which to resize the table.
     */
    // 对表初始化和扩容控制
    private transient volatile int sizeCtl;
    
    /**
     * The next table index (plus one) to split while resizing.
     */
    // 扩容下另一个表的索引
    private transient volatile int transferIndex;

    /**
     * Spinlock (locked via CAS) used when resizing and/or creating CounterCells.
     */
    // 旋转锁
    private transient volatile int cellsBusy;

    /**
     * Table of counter cells. When non-null, size is a power of 2.
     */
    // counterCell表
    private transient volatile CounterCell[] counterCells;    
###关键点
- 在设置初始化容量时，会根据输入的容量initialCapacity大小，找到最接近的initialCapacity的2的n次幂，入输入15，初始容量为16，
具体是通过initialCapacity无符号右移实现
- Node节点和1.7之前的区别是value和next设置成了volatile类型
- putVal 
    1.判断key和value是否为空，为空则抛出异常
    2.计算key的hash值，判断table是否为空，为空进行初始化tab
    3.根据hash值定位到桶，如果桶内为空，进行cas设置数据，如果不为空进行第4步
    4.判断当前桶是否是MOVED状态，在扩容过程中会标记为MOVED，进行transfer操作，否则进行5操作
    5.桶内第一个元素不为空，对第一个元素加锁同步，循环链表下面的数据，如果key相同，进行更新，如果不同，在最后追加当前元素
    6.若达到树形化阈值，进行树形化操作转成红黑树  
- get和put类似，也是通过计算key的hashcode来定位到具体的桶，然后进行遍历链表

