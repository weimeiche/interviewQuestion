package com.navyliu.interviewquestion.single_ton;

import java.util.HashMap;
import java.util.Map;

// 懒汉单例模式中，对于多线程的安全可以通过常见的synchronize等方式实现线程安全，
// 同时，可以通过Java静态内部类的方式实现

/**
 * 其主要原理为：Java中静态内部类可以访问其外部类的成员属性和方法，
 * 同时，静态内部类只有当被调用的时候才开始首次被加载，
 * 利用此特性，可以实现懒汉式，在静态内部类中静态初始化外部类的单一实例即可。
 */
public class SingleTon {

    // 利用静态内部类特性实现外部类的单例
    private static class SingleTonBuilder{
        private static SingleTon singleTon = new SingleTon();
    }

    // 私有化构造方法
    private SingleTon(){}

    public static SingleTon getInstance(){
        return SingleTonBuilder.singleTon;
    }

    public static void main(String[] args){
        SingleTon instance = getInstance();
    }
}
