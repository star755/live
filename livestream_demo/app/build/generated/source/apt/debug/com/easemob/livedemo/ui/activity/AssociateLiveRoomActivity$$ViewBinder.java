// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class AssociateLiveRoomActivity$$ViewBinder<T extends AssociateLiveRoomActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624063, "field 'loopView'");
    target.loopView = finder.castView(view, 2131624063, "field 'loopView'");
    view = finder.findRequiredView(source, 2131624060, "field 'selectLiveIdLayout'");
    target.selectLiveIdLayout = finder.castView(view, 2131624060, "field 'selectLiveIdLayout'");
    view = finder.findRequiredView(source, 2131624055, "field 'liveIdView'");
    target.liveIdView = finder.castView(view, 2131624055, "field 'liveIdView'");
    view = finder.findRequiredView(source, 2131624059, "field 'startButton' and method 'startLive'");
    target.startButton = finder.castView(view, 2131624059, "field 'startButton'");
    unbinder.view2131624059 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startLive();
      }
    });
    view = finder.findRequiredView(source, 2131624056, "field 'inputLayout'");
    target.inputLayout = finder.castView(view, 2131624056, "field 'inputLayout'");
    view = finder.findRequiredView(source, 2131624057, "field 'liveNameView'");
    target.liveNameView = finder.castView(view, 2131624057, "field 'liveNameView'");
    view = finder.findRequiredView(source, 2131624058, "field 'liveDescView'");
    target.liveDescView = finder.castView(view, 2131624058, "field 'liveDescView'");
    view = finder.findRequiredView(source, 2131624051, "field 'liveCoverView'");
    target.liveCoverView = finder.castView(view, 2131624051, "field 'liveCoverView'");
    view = finder.findRequiredView(source, 2131624052, "field 'hintView'");
    target.hintView = finder.castView(view, 2131624052, "field 'hintView'");
    view = finder.findRequiredView(source, 2131624061, "method 'cancel'");
    unbinder.view2131624061 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cancel();
      }
    });
    view = finder.findRequiredView(source, 2131624062, "method 'save'");
    unbinder.view2131624062 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.save();
      }
    });
    view = finder.findRequiredView(source, 2131624053, "method 'liveIdClicked'");
    unbinder.view2131624053 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.liveIdClicked();
      }
    });
    view = finder.findRequiredView(source, 2131624050, "method 'setLiveCover'");
    unbinder.view2131624050 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setLiveCover();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends AssociateLiveRoomActivity> implements Unbinder {
    private T target;

    View view2131624059;

    View view2131624061;

    View view2131624062;

    View view2131624053;

    View view2131624050;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      target.loopView = null;
      target.selectLiveIdLayout = null;
      target.liveIdView = null;
      view2131624059.setOnClickListener(null);
      target.startButton = null;
      target.inputLayout = null;
      target.liveNameView = null;
      target.liveDescView = null;
      target.liveCoverView = null;
      target.hintView = null;
      view2131624061.setOnClickListener(null);
      view2131624062.setOnClickListener(null);
      view2131624053.setOnClickListener(null);
      view2131624050.setOnClickListener(null);
    }
  }
}
