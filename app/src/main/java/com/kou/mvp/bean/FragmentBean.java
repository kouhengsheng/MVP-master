package com.kou.mvp.bean;

/**
 * Created by kouhengsheng on 2018/7/2.
 */
public class FragmentBean {
	public int drawable;
	public String name;
	public Class fragment;

	public FragmentBean(int drawable, String name, Class fragment) {
		this.drawable = drawable;
		this.name = name;
		this.fragment = fragment;
	}


	public int getDrawable() {
		return drawable;
	}

	public void setDrawable(int drawable) {
		this.drawable = drawable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class getFragment() {
		return fragment;
	}

	public void setFragment(Class fragment) {
		this.fragment = fragment;
	}

	@Override
	public String toString() {
		return "FragmentBean{" +
				"drawable=" + drawable +
				", name='" + name + '\'' +
				", fragment=" + fragment +
				'}';
	}
}
