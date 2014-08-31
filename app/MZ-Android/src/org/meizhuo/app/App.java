package org.meizhuo.app;

import butterknife.ButterKnife;
import android.app.Application;

public class App extends Application {

	@Override public void onCreate() {
		ButterKnife.setDebug(true);
	}
}
