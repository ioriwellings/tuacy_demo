package com.tuacy.wuyunxing.tuacydemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author: tuacy
 * @date: 2015/9/16 14:28
 * @version: V1.0
 */
public class BaseFragment extends Fragment {

	protected static final String FRAGMENT_NAME = "name";

	protected String mFragmentName = null;

	public BaseFragment() {
		// Required empty public constructor
	}

	public static BaseFragment newInstance(String name) {
		BaseFragment fragment = new BaseFragment();
		Bundle args = new Bundle();
		args.putString(FRAGMENT_NAME, name);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mFragmentName = getArguments().getString(FRAGMENT_NAME);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		TextView view = new TextView(getActivity());
		view.setText(mFragmentName);
		view.setGravity(Gravity.CENTER);
		view.setTextSize(24);
		return view;
	}
}
