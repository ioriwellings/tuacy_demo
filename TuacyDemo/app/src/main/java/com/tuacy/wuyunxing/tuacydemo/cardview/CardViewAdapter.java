package com.tuacy.wuyunxing.tuacydemo.cardview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuacy.wuyunxing.tuacydemo.R;

import java.util.List;

/**
 * @author: tuacy
 * @date: 2015/9/17 16:41
 * @version: V1.0
 */
public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

	private List<Actor> actors;

	private Context mContext;

	public CardViewAdapter(Context context, List<Actor> actors) {
		this.mContext = context;
		this.actors = actors;
	}

	@Override
	public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(CardViewAdapter.ViewHolder holder, int position) {
		Actor p = actors.get(position);
		holder.mTextView.setText(p.name);
		holder.mImageView.setImageResource(p.getImageResourceId(mContext));
	}

	@Override
	public int getItemCount() {
		return actors == null ? 0 : actors.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		public TextView mTextView;

		public ImageView mImageView;

		public ViewHolder(View v) {
			super(v);
			mTextView = (TextView) v.findViewById(R.id.name);
			mImageView = (ImageView) v.findViewById(R.id.pic);
		}
	}
}
