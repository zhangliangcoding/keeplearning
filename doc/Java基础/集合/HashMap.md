##HashMap

###HashMap引言
> since jdk1.8


    HashMap作为做常用的KEY-VALUE数据存储工具，在使用是之前，我们要充分了解其内部原理，这样才能更加合理的使用它。
    在本文中，主要从基本概念、实现原理、源码解读、常用HashMap子类及使用场景举例这几个方面来解读。


###基本概念
```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {
    //...
    }
```
首先简单说明一下Map及AbstractMap，Map作为父接口，定义了基础的方法，在1.8版本中，增加了几个default方法


    