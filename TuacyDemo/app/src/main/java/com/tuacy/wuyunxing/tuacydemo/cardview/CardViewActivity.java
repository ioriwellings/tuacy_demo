package com.tuacy.wuyunxing.tuacydemo.cardview;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.tuacy.wuyunxing.library.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.tuacy.wuyunxing.tuacydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tuacy
 * @date: 2015/9/17 16:17
 * @version: V1.0
 */
public class CardViewActivity extends ActionBarActivity {

	private List<Actor> actors = new ArrayList<Actor>();
	private RecyclerView mRecyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardview);
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mRecyclerView.getContext()).build());
		actors.add(new Actor("aaa", "p1"));
		actors.add(new Actor("bbb", "p2"));
		actors.add(new Actor("ccc", "p3"));
		mRecyclerView.setAdapter(new CardViewAdapter(this, actors));
	}


}
