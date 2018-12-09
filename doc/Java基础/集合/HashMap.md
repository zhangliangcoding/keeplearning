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
首先简单说明一下Map及AbstractMap
Map作为父接口，定义了基础的接口方法，在1.8版本中，增加了几个default方法
```java
public interface Map<K,V> {
    
    //.........
    
    //default methods since 1.8
    
    /**
    *当获取不到value，设置默认值  
    **/
    default V getOrDefault(Object key, V defaultValue){}
    
    /**
    *当old-value为空时才会更新为new-value，否则不更新 
    **/
    default V putIfAbsent(K key, V value) {}
    
    /**
    * 只有map中包含此键，并且查询出的value值与提供的value值相等且不为空，才会remove返回true
    **/
    default boolean remove(Object key, Object value) {}
    
    /**
    * 查询出的value与oldValue相等，才去替换为newValue
    **/
    default boolean replace(K key, V oldValue, V newValue) {}
    
    /**
    * 只要map中包含此key，就可以替换
    **/
    default V replace(K key, V value) {}
    
    /**
    *当此key对应的curValue为空时，
    * 根据mappingFuncion函数计算key后得到的newValue，存放到map中，并返回newValue（不能为空）
    **/
    default V computeIfAbsent(K key,
                Function<? super K, ? extends V> mappingFunction) {}
                
    /**
    *当此key对应的curValue不为空时，
    * 根据remappingFuncion函数计算key,value后得到的newValue，存放到map中，并返回newValue（不能为空）
    * 如果通过函数计算后的newValue为空，则移除remove(key),注意会remove掉的
    **/            
    default V computeIfPresent(K key,
                BiFunction<? super K, ? super V, ? extends V> remappingFunction) {}
                
    
    /**
    * 无论有没有对应的key好，通过函数计算key和oldValue得到的newValue不为空，就可以put(key,newValue)
    * 如果newValue为空，如果map中存在key且oldValue不为空，则会remove(key)，这个也是会remove的哦
    * computeIfPresent方法必须有这个key和对应的value不为空才可以
    */
    default V compute(K key,
            BiFunction<? super K, ? super V, ? extends V> remappingFunction) {}
    
            
    /**
    * 1.如果oldValue为空，value不为空，直接put(key,value)，如果value为空，直接remove(key)
    * 2.oldValue不为空，则通过函数计算value和oldValue的到newValue,如果newValue不为空直接put(key,newValue),如果newValue为空，remove(key)
    * 一样会remove
    */
    default V merge(K key, V value,
            BiFunction<? super V, ? super V, ? extends V> remappingFunction) {}
            
            

```
上面提到的Map中的defalut methods 可以在以后的编码中，提供很大的方便，具体用法及验证
```java
class Text{
    public static void main(String[] args){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);

        map.forEach((k, v) -> {
            System.out.println("k:" + k + "==>" + "v:" + v);
        });
        map.compute(1, (k, v) -> {
            return v + 100 + k;
        });
        System.out.println("1:"+map.get(1));//1:112

        map.compute(1, (k, v) -> {
            return null;
        });
        System.out.println("还有1吗？->" + map.containsKey(1));//还有1吗？->false 被remove了

        map.computeIfAbsent(2, (v) -> {
           return -2;
        });
        System.out.println("2:"+map.get(2));//2:22

        map.computeIfAbsent(4, (v) -> {
            return 444;
        });
        System.out.println("4:"+map.get(4));//4:444
        map.computeIfAbsent(4, (v) -> {
            return null;
        });
        System.out.println("4:"+map.get(4));//4:444

        map.computeIfPresent(5, (k, v) -> {
            return 55;
        });
        System.out.println("5:"+map.get(5));//5:null
        System.out.println("有5吗?->"+map.containsKey(5));//有5吗?->false

        map.put(5, null);
        map.computeIfPresent(5, (k, v) -> {
            return 55;
        });
        System.out.println("5:"+map.get(5));//5:null
        System.out.println("有5吗?->"+map.containsKey(5));//有5吗?->true

        map.put(5, 5);
        map.computeIfPresent(5, (k, v) -> {
            return 55;
        });
        System.out.println("5:"+map.get(5));//5:55
        System.out.println("有5吗?->"+map.containsKey(5));//有5吗?->true
        
    }    
}
```

通过上面的例子，对新增的方法有了一定了了解，里面涉及了 BiFunction 和Function 两个接口，这也是1.8新增的，
后面会单拿出来一篇文章单独讲解一些类似的接口

AbstractMap 实现了Map接口的抽象类，实现了一些常用的基础的方法，不再赘述


###HashMap

#####一些概念


#####一些属性

    DEFAULT_INITIAL_CAPACITY = 1 << 4; // 初始容量为16

    MAXIMUM_CAPACITY = 1 << 30; //最大容量

    DEFAULT_LOAD_FACTOR = 0.75f;//负载因子 当size为容量的0.75倍是 自动扩容

    TREEIFY_THRESHOLD = 8; 链表结构转成红黑树接口最小元素个数 8 

    UNTREEIFY_THRESHOLD = 6; 红黑树转成链表结构元素个数阈值 6

    MIN_TREEIFY_CAPACITY = 64; 转换红黑树 最小元素个数 64 ，当map的size>64时，符合条件的桶才会进行红黑树转换




    