package com.imooc.netty.actual;



import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现一个LRU缓存，当缓存数据达到N之后需要淘汰掉最近使用的数据
 * N小时之内没有被访问的数据也需要淘汰掉
 * @author chenmuchao
 * @date 2018/12/11 18:59
 */
public class LRUAbstractMap extends java.util.AbstractMap {

   // private final static Logger LOGGER = LoggerFactory.getLogger(LRUAbstractMap.class);

    /**
     * 检查是否超期线程
     */
    private ExecutorService checkTimePool;

    /**
     * map最大size
     */
    private final static int MAX_SIZE = 1024;

    /**
     * 缺省大小
     */
    private final static int DEFAULT_ARRAY_SIZE = 1024;

    /**
     * 数组长度
     */
    private int arraySize;

    /**
     * 数组
     */
    private Object[] arrays;

    /**
     * 判断是否停止flag
     */
    private volatile boolean flag = true;

    /**
     * 超时时间
     */
    private final static Long EXPIRE_TIME = 60 * 60 * 1000L;

    /**
     * 整个Map的大小
     */
    private volatile AtomicInteger size;

    public LRUAbstractMap(){
        arraySize = DEFAULT_ARRAY_SIZE;
        arrays = new Object[arraySize];
        //开启一个线程检查最先放入队列的值是否超期
        executeCheckTime();
    }

    /**
     * 开启一个线程检查最先放入队列的值是否超期，设置为守护线程
     */
    private void executeCheckTime() {
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
