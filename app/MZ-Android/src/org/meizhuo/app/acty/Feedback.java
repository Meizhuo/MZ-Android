package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Feedback extends BaseActivity {
	
	
	@InjectView(R.id.btn_confirm) View confirm;
	@InjectView(R.id.btn_cancel) View cancel;
	@InjectView(R.id.feedback_et) EditText et;
	@InjectView(R.id.feedback_back) Button back;
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.acty_feedback);
		ButterKnife.inject(this);
		
	}
	
	
	@OnClick(R.id.feedback_back)
	public void back()
	{
		closeActivity();
	}
	@OnClick(R.id.btn_confirm)
	public void confirm()
	{
		Toast.makeText(Feedback.this, "反馈已发送", Toast.LENGTH_SHORT).show();
		
	}
	@OnClick(R.id.btn_cancel)
	public void cancel()
	{
		et.setText("");
		
	}

	
	
}
