package com.kou.utils;

/**
 * Created by kouhengsheng on 2018/12/10.
 */

public class Singleton {

	private Singleton() {}
	private volatile static Singleton instance;//第一层锁：保证变量可见性

	public static Singleton getInstance() {
		if (instance == null) {//第一次判空：无需每次都加锁，提高性能
			synchronized (Singleton.class) {//第二层锁：保证线程同步
				if (instance == null) {//第二次判空:避免多线程同时执行getInstance()产生多个instance对象
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
