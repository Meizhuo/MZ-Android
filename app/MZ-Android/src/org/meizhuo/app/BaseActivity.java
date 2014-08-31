package org.meizhuo.app;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;

public class BaseActivity extends Activity {
	@Optional @InjectView(R.id.tv_title) TextView tv_title;

	protected void onCreate(Bundle savedInstanceState, int layoutId) {
		super.onCreate(savedInstanceState);
		setContentView(layoutId);
		ButterKnife.inject(this);
		setAppTitle(getResources().getString(R.string.app_name));
	}

	public void setAppTitle(String title) {
		if (tv_title != null) {
			tv_title.setText(title);
		}
	}

	/**
	 * 
	 * 以一个Class<?> cls 启动一个Activity
	 * 
	 * @param cls
	 */
	public void openActivity(Class<?> cls) {
		this.startActivity(new Intent(this, cls));
	}

	/**
	 * 以一个intent来启动一个Activity
	 * 
	 * @param intent
	 */
	public void openActivity(Intent intent) {
		this.startActivity(intent);
	}

	/**
	 * 封装了Toast，直接toast（String content）
	 * 
	 * @param content
	 *            content of your want to Toast
	 */
	public void toast(String content) {
		Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 关闭Activity
	 */
	public void closeActivity() {
		this.finish();
	}

	/**
	 * 获得这个Activity的Context
	 * 
	 * @return
	 */
	public Context getContext() {
		return this;
	}

	/**
	 * 调试
	 * 
	 * @param content
	 */
	public void debug(String content) {
		Log.i("debug", this.getClass().getName() + ":" + content);
	}

	/**
	 * Get intent extra
	 * 
	 * @param name
	 * @return serializable
	 */
	@SuppressWarnings("unchecked") protected <V extends Serializable> V getSerializableExtra(
			final String name) {
		return (V) getIntent().getSerializableExtra(name);
	}

	/**
	 * Get intent extra
	 * 
	 * @param name
	 * @return int -1 if not exist!
	 */
	protected int getIntExtra(final String name) {
		return getIntent().getIntExtra(name, -1);
	}

	/**
	 * Get intent extra
	 * 
	 * @param name
	 * @return string
	 */
	protected String getStringExtra(final String name) {
		return getIntent().getStringExtra(name);
	}

	/**
	 * Get intent extra
	 * 
	 * @param name
	 * @return string array
	 */
	protected String[] getStringArrayExtra(final String name) {
		return getIntent().getStringArrayExtra(name);
	}

	@Override protected void onDestroy() {
		super.onDestroy();
	}

	@Override protected void onPause() {
		super.onPause();
	}

	@Override protected void onStop() {
		super.onStop();
	}

	@Override protected void onResume() {
		super.onResume();

	}

	@Optional @OnClick(R.id.btn_title_back) public void back() {
		if (this.findViewById(R.id.tv_title).getVisibility() != View.INVISIBLE) {
			closeActivity();
		}
	}
}
