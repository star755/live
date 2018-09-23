// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class RegisterActivity$$ViewBinder<T extends RegisterActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624087, "field 'username'");
    target.username = finder.castView(view, 2131624087, "field 'username'");
    view = finder.findRequiredView(source, 2131624088, "field 'password'");
    target.password = finder.castView(view, 2131624088, "field 'password'");
    view = finder.findRequiredView(source, 2131624083, "field 'register'");
    target.register = finder.castView(view, 2131624083, "field 'register'");
    view = finder.findRequiredView(source, 2131624078, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624078, "field 'toolbar'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends RegisterActivity> implements Unbinder {
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
      target.username = null;
      target.password = null;
      target.register = null;
      target.toolbar = null;
    }
  }
}
