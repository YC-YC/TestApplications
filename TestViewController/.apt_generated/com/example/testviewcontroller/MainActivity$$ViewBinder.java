// Generated code from Butter Knife. Do not modify!
package com.example.testviewcontroller;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.testviewcontroller.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230720, "field 'mLlContainer'");
    target.mLlContainer = finder.castView(view, 2131230720, "field 'mLlContainer'");
    view = finder.findRequiredView(source, 2131230730, "field 'mPb'");
    target.mPb = finder.castView(view, 2131230730, "field 'mPb'");
  }

  @Override public void unbind(T target) {
    target.mLlContainer = null;
    target.mPb = null;
  }
}
