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
			 * @param viewHolder �϶���ViewHolder
			 * @param target Ŀ��λ�õ�ViewHolder
			 * @return
			 */
			@Override
			public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
				int fromPosition = viewHolder.getAdapterPosition();//�õ��϶�ViewHolder��position
				int toPosition = target.getAdapterPosition();//�õ�Ŀ��ViewHolder��position
				if (fromPosition < toPosition) {
					//�ֱ���м����е�item��λ�����½���
					for (int i = fromPosition; i < toPosition; i++) {
						Collections.swap(mDatas, i, i + 1);
					}
				} else {
					for (int i = fromPosition; i > toPosition; i--) {
						Collections.swap(mDatas, i, i - 1);
					}
				}
				mAdapter.notifyItemMoved(fromPosition, toPosition);
				//����true��ʾִ���϶�
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
					//���һ���ʱ�ı�Item��͸����
					final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
					viewHolder.itemView.setAlpha(alpha);
					viewHolder.itemView.setTranslationX(dX);
				}
			}

			@Override
			public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
				super.onSelectedChanged(viewHolder, actionState);
				//��ѡ��Itemʱ�����ø÷�������д�˷�������ʵ��ѡ��ʱ���һЩ�����߼�
			}

			@Override
			public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
				super.clearView(recyclerView, viewHolder);
				//�������Ѿ�������ʱ����ø÷�������д�˷�������ʵ�ָֻ�Item�ĳ�ʼ״̬
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
