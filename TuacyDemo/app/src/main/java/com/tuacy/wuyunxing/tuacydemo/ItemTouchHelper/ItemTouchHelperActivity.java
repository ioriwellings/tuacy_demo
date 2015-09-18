package com.tuacy.wuyunxing.tuacydemo.ItemTouchHelper;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuacy.wuyunxing.tuacydemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: tuacy
 * @date: 2015/9/18 10:35
 * @version: V1.0
 */
public class ItemTouchHelperActivity extends AppCompatActivity {

	private List<String>        mDatas;
	private RecyclerView        mRecyclerView;
	private RecyclerViewAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_itemtouchhelper);
		initDatas();
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		mAdapter = new RecyclerViewAdapter(mDatas);
		mRecyclerView.setAdapter(mAdapter);

		ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
																				ItemTouchHelper.RIGHT) {
			/**
			 * @param recyclerView
			 * @param viewHolder 拖动的ViewHolder
			 * @param target 目标位置的ViewHolder
			 * @return
			 */
			@Override
			public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
				int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
				int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
				if (fromPosition < toPosition) {
					//分别把中间所有的item的位置重新交换
					for (int i = fromPosition; i < toPosition; i++) {
						Collections.swap(mDatas, i, i + 1);
					}
				} else {
					for (int i = fromPosition; i > toPosition; i--) {
						Collections.swap(mDatas, i, i - 1);
					}
				}
				mAdapter.notifyItemMoved(fromPosition, toPosition);
				//返回true表示执行拖动
				return true;
			}

			@Override
			public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
				int position = viewHolder.getAdapterPosition();
				mDatas.remove(position);
				mAdapter.notifyItemRemoved(position);
			}

			@Override
			public void onChildDraw(Canvas c,
									RecyclerView recyclerView,
									RecyclerView.ViewHolder viewHolder,
									float dX,
									float dY,
									int actionState,
									boolean isCurrentlyActive) {
				super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
				if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
					//左右滑动时改变Item的透明度
					final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
					viewHolder.itemView.setAlpha(alpha);
					viewHolder.itemView.setTranslationX(dX);
				}
			}

			@Override
			public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
				super.onSelectedChanged(viewHolder, actionState);
				//当选中Item时候会调用该方法，重写此方法可以实现选中时候的一些动画逻辑
			}

			@Override
			public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
				super.clearView(recyclerView, viewHolder);
				//当动画已经结束的时候调用该方法，重写此方法可以实现恢复Item的初始状态
			}

		};
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
		itemTouchHelper.attachToRecyclerView(mRecyclerView);
	}

	private void initDatas() {
		mDatas = new ArrayList<>();
		for (int i = 0; i <= 100; i++) {
			mDatas.add("Number:" + i);
		}
	}

	public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

		private List<String> datas;

		public RecyclerViewAdapter(List<String> datas) {
			this.datas = datas;
		}

		@Override
		public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_itemtouchhelper, parent, false);
			RecyclerViewHolder holder = new RecyclerViewHolder(view);
			return holder;
		}

		@Override
		public void onBindViewHolder(RecyclerViewHolder holder, int position) {
			holder.mTextView.setText(datas.get(position));
		}

		@Override
		public int getItemCount() {
			return datas.size();
		}

		public class RecyclerViewHolder extends RecyclerView.ViewHolder {

			public TextView mTextView;

			public RecyclerViewHolder(View itemView) {
				super(itemView);
				mTextView = (TextView) itemView.findViewById(R.id.tv_content);
			}
		}

	}
}
