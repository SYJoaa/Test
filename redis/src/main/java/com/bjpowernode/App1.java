package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class App1 {
    public static void main(String[] args) {
        JedisPool pool = null;
        try {
            pool = RedisUtils.open("127.0.0.1", 6379);
            Jedis jedis = pool.getResource();
            jedis.flushAll();
            jedis.set("str1", "aaa");
            String str1 = jedis.get("str1");
            System.out.println(str1);
        } catch (Exception e) {
            e.printStackTrace();
            ;
        } finally {
            RedisUtils.close();
        }
    }
}

class MyDemo {
    private static ThreadLocal<String> tl = new ThreadLocal<>();
    private String content;

    private String getContent() {
        return tl.get();
    }
    private void setContent(String content) {
        tl.set(content);
    }
    public static void main(String[] args) {
        MyDemo demo = new MyDemo();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("-----------------------");
                    System.out.println(Thread.currentThread().getName() + "--->" + demo.getContent());
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
