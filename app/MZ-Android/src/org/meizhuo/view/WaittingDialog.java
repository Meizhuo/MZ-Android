package org.meizhuo.view;

import org.meizhuo.app.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class WaittingDialog extends Dialog {

	private TextView tv;
	
	public WaittingDialog(Context context) {
		super(context, R.style.BaseDialog);
		this.setCancelable(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_progress_waitting);
		tv = (TextView) this.findViewById(R.id.dialog_progress_waitting_tv);
	}
	
	public void setText(String text) {
		if (tv != null)
			tv.setText(text);
	}


}
