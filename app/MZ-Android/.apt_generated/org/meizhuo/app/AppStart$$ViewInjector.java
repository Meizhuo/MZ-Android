// Generated code from Butter Knife. Do not modify!
package org.meizhuo.app;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AppStart$$ViewInjector {
  public static void inject(Finder finder, final org.meizhuo.app.AppStart target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099648, "field 'tv'");
    target.tv = (android.widget.TextView) view;
  }

  public static void reset(org.meizhuo.app.AppStart target) {
    target.tv = null;
  }
}
