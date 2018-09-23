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

public class CreateLiveRoomActivity$$ViewBinder<T extends CreateLiveRoomActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624051, "field 'coverView'");
    target.coverView = finder.castView(view, 2131624051, "field 'coverView'");
    view = finder.findRequiredView(source, 2131624052, "field 'hintView'");
    target.hintView = finder.castView(view, 2131624052, "field 'hintView'");
    view = finder.findRequiredView(source, 2131624057, "field 'liveNameView'");
    target.liveNameView = finder.castView(view, 2131624057, "field 'liveNameView'");
    view = finder.findRequiredView(source, 2131624058, "field 'liveDescView'");
    target.liveDescView = finder.castView(view, 2131624058, "field 'liveDescView'");
    view = finder.findRequiredView(source, 2131624050, "method 'setLiveCover'");
    unbinder.view2131624050 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setLiveCover();
      }
    });
    view = finder.findRequiredView(source, 2131624066, "method 'associateRoom'");
    unbinder.view2131624066 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.associateRoom();
      }
    });
    view = finder.findRequiredView(source, 2131624059, "method 'startLive'");
    unbinder.view2131624059 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.startLive();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends CreateLiveRoomActivity> implements Unbinder {
    private T target;

    View view2131624050;

    View view2131624066;

    View view2131624059;

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
      target.coverView = null;
      target.hintView = null;
      target.liveNameView = null;
      target.liveDescView = null;
      view2131624050.setOnClickListener(null);
      view2131624066.setOnClickListener(null);
      view2131624059.setOnClickListener(null);
    }
  }
}
