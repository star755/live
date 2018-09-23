// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.widget;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class BarrageLayout$$ViewBinder<T extends BarrageLayout> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624249, "field 'container1'");
    target.container1 = finder.castView(view, 2131624249, "field 'container1'");
    view = finder.findRequiredView(source, 2131624250, "field 'container2'");
    target.container2 = finder.castView(view, 2131624250, "field 'container2'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends BarrageLayout> implements Unbinder {
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
      target.container1 = null;
      target.container2 = null;
    }
  }
}
