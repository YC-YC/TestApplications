// Generated code from Butter Knife. Do not modify!
package com.example.testviewcontroller.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HousePhotoViewController$$ViewBinder<T extends com.example.testviewcontroller.view.HousePhotoViewController> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230722, "field 'mViewPage'");
    target.mViewPage = finder.castView(view, 2131230722, "field 'mViewPage'");
  }

  @Override public void unbind(T target) {
    target.mViewPage = null;
  }
}
