package com.softmiracle.githubmvp.presentation.adapters;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.softmiracle.githubmvp.R;
import com.softmiracle.githubmvp.common.SempraApplication;
import com.softmiracle.githubmvp.data.models.User;
import com.softmiracle.githubmvp.utils.listeners.OnItemClickListener;

import java.util.List;

/**
 * Created by Admin on 26.06.2017.
 */

public class UserList extends BaseQuickAdapter<User, BaseViewHolder> {

	private com.softmiracle.githubmvp.utils.listeners.OnItemClickListener<User> mListener;

	public UserList(@Nullable List<User> data, com.softmiracle.githubmvp.utils.listeners.OnItemClickListener<User> listener) {
		super(R.layout.user_item_list, data);
		this.mListener = listener;

	}

	public void restoreData() {
		if (mData != null) {
			mData = null;
		}
	}

	@Override
	protected void convert(final BaseViewHolder holder, final User item) {
		holder.setText(R.id.tv_username_user_list, item.getLogin());
		Glide.with(SempraApplication.getInstance()).load(item.getAvatar()).into((ImageView) holder.getView(R.id.avatar_user_list));
		holder.getView(R.id.cardview_user_list).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mListener != null) {
					mListener.onItemClick(mData.get(holder.getAdapterPosition()));
				}
			}
		});

	}
}
