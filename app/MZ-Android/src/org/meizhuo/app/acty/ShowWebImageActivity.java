package org.meizhuo.app.acty;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;
import org.meizhuo.view.WaittingDialog;
import org.meizhuo.view.ZoomableImageView;

import android.annotation.SuppressLint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class ShowWebImageActivity extends BaseActivity {
	private static final String TAG = "ShowWebImageActivity";
	private static final int status_start = 0;
	private static final int status_faild = 1;
	private static final int status_finish = 2;
	private TextView imageTextView = null;
	 String imagePath = null;
	private ZoomableImageView imageView = null;
	DisplayHandler h =  new DisplayHandler();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState,R.layout.show_web_img);
		setAppTitle("返回");
		this.imagePath = getIntent().getStringExtra("image");
		Log.i(TAG, ""+ imagePath);
		this.imageTextView = (TextView) findViewById(R.id.show_webimage_imagepath_textview);
		imageTextView.setText(this.imagePath);
		imageView = (ZoomableImageView) findViewById(R.id.show_webimage_imageview);
		display();
		

	
	}
	
	@JavascriptInterface
	private void display(){
		h.sendEmptyMessage(status_start);
		final Message msg = h.obtainMessage();
		new Thread(new Runnable() {
			

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Log.i(TAG, "进入线程了");
//					imageView.setImageBitmap(((BitmapDrawable) ShowWebImageActivity.loadImageFromUrl(ShowWebImageActivity.this.imagePath)).getBitmap());
					Drawable drawable = ShowWebImageActivity.loadImageFromUrl(ShowWebImageActivity.this.imagePath);
					msg.what = status_finish;
					msg.obj = drawable;
					h.sendMessage(msg);
				} catch (IOException e) {
					e.printStackTrace();
					Log.i(TAG, "出错了" + e.getMessage());
					msg.what = status_faild;
					h.sendMessage(msg);
				}
			}
		}).start();
	}
	
	@SuppressLint("HandlerLeak")
	class DisplayHandler extends Handler {
		WaittingDialog dialog;
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case status_start:
					dialog =  new WaittingDialog(ShowWebImageActivity.this);
					dialog.setText("正在加载");
					dialog.show();
				break;
			case status_finish:
				if(dialog.isShowing())
					dialog.dismiss();
				Drawable pic = (Drawable)msg.obj;
				imageView.setImageBitmap(((BitmapDrawable)pic).getBitmap());
				break;
			case status_faild:
					if (dialog.isShowing())
					{
						dialog.setText("网络不给力，请检查您的网络设置");
						dialog.dismiss();
					}
						
				break;
			default:
				break;
			}
		}
	}
	

	public static Drawable loadImageFromUrl(String url) throws IOException {

		URL m = new URL(url);
		InputStream i = (InputStream) m.getContent();
		Drawable d = Drawable.createFromStream(i, "src");
		return d;
	}

}
