// Generated code from Butter Knife. Do not modify!
package com.easemob.livedemo.ui.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SearchActivity$$ViewBinder<T extends SearchActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624095, "field 'editText'");
    target.editText = finder.castView(view, 2131624095, "field 'editText'");
    view = finder.findRequiredView(source, 2131624097, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131624097, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131624096, "field 'emptyView'");
    target.emptyView = finder.castView(view, 2131624096, "field 'emptyView'");
    view = finder.findRequiredView(source, 2131624094, "field 'cancelView'");
    target.cancelView = finder.castView(view, 2131624094, "field 'cancelView'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends SearchActivity> implements Unbinder {
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
      target.editText = null;
      target.recyclerView = null;
      target.emptyView = null;
      target.cancelView = null;
    }
  }
}
