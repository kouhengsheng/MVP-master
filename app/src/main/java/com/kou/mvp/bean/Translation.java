package com.kou.mvp.bean;

/**
 * Created by Carson_Ho on 17/3/20.
 */
public class Translation {

    private int status;

    private content content;
    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int    errNo;
    }

	@Override
	public String toString() {
		return "Translation{" +
				"status=" + status +
				", content.from=" + content.from +",content.to="+content.to+",content.vendor="+content.vendor+",content.out="+content.out+",content.errNo="+content.errNo+
				'}';
	}

	//定义 输出返回数据 的方法
    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }
}
