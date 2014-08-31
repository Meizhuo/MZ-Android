package org.meizhuo.app.acty;

import org.meizhuo.app.BaseActivity;
import org.meizhuo.app.R;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;

public class Feedback extends BaseActivity {
	@InjectView(R.id.btn_confirm) View confirm;
	@InjectView(R.id.btn_cancel) View cancel;
	@InjectView(R.id.feedback_et) EditText et;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState,R.layout.acty_feedback);
		setAppTitle("反馈");

	}


	@OnClick(R.id.btn_confirm) public void confirm() {
		Toast.makeText(Feedback.this, "反馈已发送", Toast.LENGTH_SHORT).show();

	}

	@OnClick(R.id.btn_cancel) public void cancel() {
		et.setText("");

	}

}
