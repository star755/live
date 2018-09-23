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

public class ScreenshotDialog$$ViewBinder<T extends ScreenshotDialog> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624112, "field 'imageView'");
    target.imageView = finder.castView(view, 2131624112, "field 'imageView'");
    view = finder.findRequiredView(source, 2131624113, "method 'onShare'");
    unbinder.view2131624113 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onShare();
      }
    });
    view = finder.findRequiredView(source, 2131624094, "method 'onCancel'");
    unbinder.view2131624094 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCancel();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ScreenshotDialog> implements Unbinder {
    private T target;

    View view2131624113;

    View view2131624094;

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
      target.imageView = null;
      view2131624113.setOnClickListener(null);
      view2131624094.setOnClickListener(null);
    }
  }
}
