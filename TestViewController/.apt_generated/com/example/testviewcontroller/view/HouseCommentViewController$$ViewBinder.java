// Generated code from Butter Knife. Do not modify!
package com.example.testviewcontroller.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HouseCommentViewController$$ViewBinder<T extends com.example.testviewcontroller.view.HouseCommentViewController> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230720, "field 'mLlContainer'");
    target.mLlContainer = finder.castView(view, 2131230720, "field 'mLlContainer'");
  }

  @Override public void unbind(T target) {
    target.mLlContainer = null;
  }
}
