// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class LiveListFragment$PhotoViewHolder$$ViewBinder<T extends LiveListFragment.PhotoViewHolder> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624230, "field 'imageView'");
    target.imageView = finder.castView(view, 2131624230, "field 'imageView'");
    view = finder.findRequiredView(source, 2131624231, "field 'anchor'");
    target.anchor = finder.castView(view, 2131624231, "field 'anchor'");
    view = finder.findRequiredView(source, 2131624227, "field 'audienceNum'");
    target.audienceNum = finder.castView(view, 2131624227, "field 'audienceNum'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LiveListFragment.PhotoViewHolder> implements Unbinder {
    private T target;

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
      target.anchor = null;
      target.audienceNum = null;
    }
  }
}
