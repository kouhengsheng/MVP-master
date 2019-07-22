package com.kou.mvp.bean;

/**
 * Created by kouhengsheng on 2018/7/11.
 */
public class Slider {

	/**
	 * id : 1
	 * imgUrl : http://7mno4h.com2.z0.glb.qiniucdn.com/5608f3b5Nc8d90151.jpg
	 * name : 音箱狂欢
	 * type : 1
	 */

	private int id;
	private String imgUrl;
	private String name;
	private int    type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Slider{" +
				"id=" + id +
				", imgUrl='" + imgUrl + '\'' +
				", name='" + name + '\'' +
				", type=" + type +
				'}';
	}
}
