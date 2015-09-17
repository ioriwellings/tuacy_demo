package com.tuacy.wuyunxing.tuacydemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.tuacy.wuyunxing.library.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

	private RecyclerView mRecyclerView;
	private List<String> mTitles = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTitle(mTitles);
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mRecyclerView.getContext()).build());

		RecyclerViewAdapter adapter = new RecyclerViewAdapter(mTitles);
		adapter.setItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {

			@Override
			public void onItemClick(View view, Object o) {
				switch ((String)o) {
					case "NavigationViewActivity":
						startActivity(new Intent(MainActivity.this, NavigationViewActivity.class));
						break;
					case "NavigationViewDownActivity":
						startActivity(new Intent(MainActivity.this, NavigationViewDownActivity.class));
						break;
				}
			}
		});
		mRecyclerView.setAdapter(adapter);
	}

	private void initTitle(List<String> titles) {
		titles.add("NavigationViewActivity");
		titles.add("NavigationViewDownActivity");
	}


	public static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

		public interface OnRecyclerViewItemClickListener<Model> {
			void onItemClick(View view, Model model);
		}

		private List<String> mTitles = null;
		private OnRecyclerViewItemClickListener<String> itemClickListener = null;

		public RecyclerViewAdapter(List<String> titles) {
			mTitles = titles;
		}

		public void setItemClickListener(OnRecyclerViewItemClickListener listener) {
			itemClickListener = listener;
		}

		@Override
		public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_title, parent, false);
			itemView.setOnClickListener(this);
			return new ViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
			holder.itemView.setTag(mTitles.get(position));
			holder.mTextView.setText(mTitles.get(position));
		}

		@Override
		public int getItemCount() {
			return mTitles.size();
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public void onClick(View v) {
			if (null != itemClickListener) {
				itemClickListener.onItemClick(v, (String)v.getTag());
			}
		}

		public static class ViewHolder extends RecyclerView.ViewHolder {

			public TextView mTextView = null;

			public ViewHolder(View itemView) {
				super(itemView);
				mTextView = (TextView)itemView.findViewById(R.id.item);
			}
		}
	}
}

