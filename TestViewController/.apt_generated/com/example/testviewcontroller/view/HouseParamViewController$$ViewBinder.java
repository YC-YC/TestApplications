// Generated code from Butter Knife. Do not modify!
package com.example.testviewcontroller.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HouseParamViewController$$ViewBinder<T extends com.example.testviewcontroller.view.HouseParamViewController> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230725, "field 'mTvParamThree'");
    target.mTvParamThree = finder.castView(view, 2131230725, "field 'mTvParamThree'");
    view = finder.findRequiredView(source, 2131230724, "field 'mTvParamTwo'");
    target.mTvParamTwo = finder.castView(view, 2131230724, "field 'mTvParamTwo'");
    view = finder.findRequiredView(source, 2131230728, "field 'mTvParamSix'");
    target.mTvParamSix = finder.castView(view, 2131230728, "field 'mTvParamSix'");
    view = finder.findRequiredView(source, 2131230723, "field 'mTvParamOne'");
    target.mTvParamOne = finder.castView(view, 2131230723, "field 'mTvParamOne'");
    view = finder.findRequiredView(source, 2131230727, "field 'mTvParamFive'");
    target.mTvParamFive = finder.castView(view, 2131230727, "field 'mTvParamFive'");
    view = finder.findRequiredView(source, 2131230726, "field 'mTvParamFour'");
    target.mTvParamFour = finder.castView(view, 2131230726, "field 'mTvParamFour'");
  }

  @Override public void unbind(T target) {
    target.mTvParamThree = null;
    target.mTvParamTwo = null;
    target.mTvParamSix = null;
    target.mTvParamOne = null;
    target.mTvParamFive = null;
    target.mTvParamFour = null;
  }
}
