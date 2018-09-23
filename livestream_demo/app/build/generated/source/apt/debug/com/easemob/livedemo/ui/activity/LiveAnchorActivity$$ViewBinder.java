// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.Object;
import java.lang.Override;

public class LiveAnchorActivity$$ViewBinder<T extends LiveAnchorActivity> extends LiveBaseActivity$$ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = (InnerUnbinder) super.bind(finder, target, source);
    View view;
    view = finder.findRequiredView(source, 2131624069, "field 'cameraView'");
    target.cameraView = finder.castView(view, 2131624069, "field 'cameraView'");
    view = finder.findRequiredView(source, 2131624070, "field 'countdownView'");
    target.countdownView = finder.castView(view, 2131624070, "field 'countdownView'");
    view = finder.findRequiredView(source, 2131624075, "field 'liveEndLayout'");
    target.liveEndLayout = finder.castView(view, 2131624075, "field 'liveEndLayout'");
    view = finder.findRequiredView(source, 2131624076, "field 'coverImage'");
    target.coverImage = finder.castView(view, 2131624076, "field 'coverImage'");
    view = finder.findRequiredView(source, 2131624068, "field 'liveContainer'");
    target.liveContainer = finder.castView(view, 2131624068, "field 'liveContainer'");
    view = finder.findRequiredView(source, 2131624224, "method 'switchCamera'");
    unbinder.view2131624224 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.switchCamera();
      }
    });
    view = finder.findRequiredView(source, 2131624229, "method 'closeLive'");
    unbinder.view2131624229 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.closeLive();
      }
    });
    return unbinder;
  }

  @Override
  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LiveAnchorActivity> extends LiveBaseActivity$$ViewBinder.InnerUnbinder<T> {
    View view2131624224;

    View view2131624229;

    protected InnerUnbinder(T target) {
      super(target);
    }

    @Override
    protected void unbind(T target) {
      super.unbind(target);
      target.cameraView = null;
      target.countdownView = null;
      target.liveEndLayout = null;
      target.coverImage = null;
      target.liveContainer = null;
      view2131624224.setOnClickListener(null);
      view2131624229.setOnClickListener(null);
    }
  }
}
