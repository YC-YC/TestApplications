// Generated code from Butter Knife. Do not modify!
package com.example.testviewcontroller.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HouseDescViewController$$ViewBinder<T extends com.example.testviewcontroller.view.HouseDescViewController> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230721, "field 'mTvDesc'");
    target.mTvDesc = finder.castView(view, 2131230721, "field 'mTvDesc'");
  }

  @Override public void unbind(T target) {
    target.mTvDesc = null;
  }
}
