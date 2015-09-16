package com.tuacy.wuyunxing.tuacydemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuacy.wuyunxing.tuacydemo.widget.RecyclerItemClickListener;

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
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, onItemClickListener));
		RecyclerViewAdapter adapter = new RecyclerViewAdapter(mTitles);
		mRecyclerView.setAdapter(adapter);
	}

	private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			switch (position) {
				case 0:
					startActivity(new Intent(MainActivity.this, NavigationViewActivity.class));
					break;
				case 1:
					startActivity(new Intent(MainActivity.this, NavigationViewDownActivity.class));
					break;
			}

		}
	};

	private void initTitle(List<String> titles) {
		titles.add("NavigationViewActivity");
		titles.add("NavigationViewDownActivity");
	}


	public static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

		private List<String> mTitles;

		public RecyclerViewAdapter(List<String> titles) {
			mTitles = titles;
		}

		@Override
		public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_title, parent, false);
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
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

		public static class ViewHolder extends RecyclerView.ViewHolder {

			public TextView mTextView = null;

			public ViewHolder(TextView itemView) {
				super(itemView);
				mTextView = itemView;
			}
		}
	}
}

