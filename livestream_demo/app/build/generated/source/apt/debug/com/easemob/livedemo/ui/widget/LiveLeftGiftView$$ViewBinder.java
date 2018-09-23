// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.widget;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class LiveLeftGiftView$$ViewBinder<T extends LiveLeftGiftView> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624099, "field 'avatar'");
    target.avatar = finder.castView(view, 2131624099, "field 'avatar'");
    view = finder.findRequiredView(source, 2131624142, "field 'name'");
    target.name = finder.castView(view, 2131624142, "field 'name'");
    view = finder.findRequiredView(source, 2131624251, "field 'giftImage'");
    target.giftImage = finder.castView(view, 2131624251, "field 'giftImage'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LiveLeftGiftView> implements Unbinder {
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
      target.avatar = null;
      target.name = null;
      target.giftImage = null;
    }
  }
}
