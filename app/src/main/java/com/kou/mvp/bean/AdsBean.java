package com.kou.mvp.bean;

import java.util.List;

/**
 * Created by kouhengsheng on 2018/11/29.
 */
public class AdsBean {

	/**
	 * code : 200
	 * data : [{"id":65,"introduce":"线上机审,最快25秒到账","logo":"http://www.wuhaojun.com/uploads/thumb/20180918/24fcc8c9-8825-3a7f-821d-1dcef9c59f2e.png","name":"贷贷侠","stars":"5","state":"最快25秒到账，利率低至7折哦！","success_num":2464206,"tags":["机审","25秒"]},{"id":63,"introduce":"无视征信，秒下4000","logo":"http://www.wuhaojun.com/uploads/thumb/20181015/6e851449-9a16-2408-07ae-237abf6784b1.png","name":"叫个钱","stars":"5","state":"仅凭身份证贷款，58s放款，最高贷10万。","success_num":984346,"tags":["无征信","秒放款"]},{"id":61,"introduce":"简单两步申请，急速放款","logo":"http://www.wuhaojun.com/uploads/thumb/20171026/cdc89c7e-0e8d-0bbb-41bc-98702584059d.png","name":"金钱宝","stars":"5","state":"简单2步申请，最快3分钟到账","success_num":467634,"tags":["急速","闪电放款"]},{"id":43,"introduce":"小额急速借款","logo":"http://www.wuhaojun.com/uploads/thumb/20180518/820da326-bb2b-f93c-b96f-e2cdf711581a.png","name":"现金秘书","stars":"5","state":"年轻人的专属钱包","success_num":105823,"tags":["利率低","通过率高"]},{"id":55,"introduce":"注册即送2000元免息卷","logo":"http://www.wuhaojun.com/uploads/thumb/20180525/19612514-b9e3-ff97-06d9-b1c1114ad4e8.png","name":"有米淘","stars":"5","state":"2小时放款，最慢当天下款","success_num":645354,"tags":["急速","免息卷"]},{"id":44,"introduce":"5000-50万额度，随你挑","logo":"http://www.wuhaojun.com/uploads/thumb/20180705/de7faee0-1f0c-8dea-070c-049d71f2c0e3.png","name":"讯秒","stars":"4","state":"简单四步申请，最低可拿5000，最高可拿50万","success_num":105643,"tags":["额度大","下款快"]},{"id":53,"introduce":"随时随地您身边的线上钱包，1分钟到账哦~","logo":"http://www.wuhaojun.com/uploads/thumb/20170829/92136082-5e22-8414-fba9-34dbde9dca0a.png","name":"信用白条","stars":"5","state":"日息0.09%、急速放款、超高额度任你挑","success_num":834009,"tags":["机审","通过率高"]},{"id":39,"introduce":"贷上钱-极速借款神器","logo":"http://www.wuhaojun.com/uploads/thumb/20170815/8f7d7700-0600-b399-4ac5-cab1e6f60030.png","name":"贷上钱","stars":"5","state":"0门槛，3分钟极速审核，88秒现金到账","success_num":108644,"tags":["速度快","费率低"]},{"id":31,"introduce":"随借随还","logo":"http://www.wuhaojun.com/uploads/thumb/20170721/4b6e6616-d538-b171-86a6-000ab8008eff.png","name":"钱急送","stars":"4","state":"3000元内说借就借，随借随还","success_num":117945,"tags":["身份证","银行卡","随借随还"]},{"id":54,"introduce":"线上自动审核，急速放款","logo":"http://www.wuhaojun.com/uploads/thumb/20170901/33fc08a6-347a-fe9f-d503-431ac98f24f2.png","name":"小树时代","stars":"5","state":"无抵押纯线上借款，1小时审核下款。","success_num":56240,"tags":["超低利率","急速放款"]}]
	 * message : ok
	 */

	private String code;
	private String         message;
	private List<DataBean> data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<DataBean> getData() {
		return data;
	}

	public void setData(List<DataBean> data) {
		this.data = data;
	}

	public static class DataBean {
		/**
		 * id : 65
		 * introduce : 线上机审,最快25秒到账
		 * logo : http://www.wuhaojun.com/uploads/thumb/20180918/24fcc8c9-8825-3a7f-821d-1dcef9c59f2e.png
		 * name : 贷贷侠
		 * stars : 5
		 * state : 最快25秒到账，利率低至7折哦！
		 * success_num : 2464206
		 * tags : ["机审","25秒"]
		 */

		private int id;
		private String       introduce;
		private String       logo;
		private String       name;
		private String       stars;
		private String       state;
		private int          success_num;
		private List<String> tags;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getIntroduce() {
			return introduce;
		}

		public void setIntroduce(String introduce) {
			this.introduce = introduce;
		}

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStars() {
			return stars;
		}

		public void setStars(String stars) {
			this.stars = stars;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public int getSuccess_num() {
			return success_num;
		}

		public void setSuccess_num(int success_num) {
			this.success_num = success_num;
		}

		public List<String> getTags() {
			return tags;
		}

		public void setTags(List<String> tags) {
			this.tags = tags;
		}

		@Override
		public String toString() {
			return "DataBean{" +
					"id=" + id +
					", introduce='" + introduce + '\'' +
					", logo='" + logo + '\'' +
					", name='" + name + '\'' +
					", stars='" + stars + '\'' +
					", state='" + state + '\'' +
					", success_num=" + success_num +
					", tags=" + tags +
					'}';
		}
	}

	@Override
	public String toString() {
		return "AdsBean{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}
