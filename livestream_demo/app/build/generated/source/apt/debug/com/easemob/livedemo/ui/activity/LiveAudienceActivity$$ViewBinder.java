// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.Object;
import java.lang.Override;

public class LiveAudienceActivity$$ViewBinder<T extends LiveAudienceActivity> extends LiveBaseActivity$$ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = (InnerUnbinder) super.bind(finder, target, source);
    View view;
    view = finder.findRequiredView(source, 2131624080, "field 'loadingLayout'");
    target.loadingLayout = finder.castView(view, 2131624080, "field 'loadingLayout'");
    view = finder.findRequiredView(source, 2131624081, "field 'progressBar'");
    target.progressBar = finder.castView(view, 2131624081, "field 'progressBar'");
    view = finder.findRequiredView(source, 2131624082, "field 'loadingText'");
    target.loadingText = finder.castView(view, 2131624082, "field 'loadingText'");
    view = finder.findRequiredView(source, 2131624076, "field 'coverView'");
    target.coverView = finder.castView(view, 2131624076, "field 'coverView'");
    view = finder.findRequiredView(source, 2131624229, "method 'close'");
    unbinder.view2131624229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.close();
      }
    });
    view = finder.findRequiredView(source, 2131624225, "method 'Praise'");
    unbinder.view2131624225 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.Praise();
      }
    });
    return unbinder;
  }

  @Override
  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LiveAudienceActivity> extends LiveBaseActivity$$ViewBinder.InnerUnbinder<T> {
    View view2131624229;

    View view2131624225;

    protected InnerUnbinder(T target) {
      super(target);
    }

    @Override
    protected void unbind(T target) {
      super.unbind(target);
      target.loadingLayout = null;
      target.progressBar = null;
      target.loadingText = null;
      target.coverView = null;
      view2131624229.setOnClickListener(null);
      view2131624225.setOnClickListener(null);
    }
  }
}
