package com.kou.mvp.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.kou.mvp.base.ListBaseAdapter;
import com.kou.mvp.base.SuperViewHolder;
import com.kou.mvp.bean.HotBean;

import me.yaowei.mvp.R;

/**
 * Created by kouhengsheng on 2018/7/28.
 */
public class HotRecyclerAdapter extends ListBaseAdapter<HotBean.ListBean> {

	public HotRecyclerAdapter(Context context) {
		super(context);
	}

	@Override
	public int getLayoutId() {
		return R.layout.home1recycleritem;
	}

	@Override
	public void onBindItemHolder(SuperViewHolder holder, int position) {
		HotBean.ListBean listBean = mDataList.get(position);
		TextView text = holder.getView(R.id.recycleritem_txt);
		text.setText(listBean.getName());

		ImageView image =holder.getView(R.id.recycleritem_img);
		image.setImageURI(Uri.parse(listBean.getImgUrl()));
	}
}
