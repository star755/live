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

public class ChatFragment$$ViewBinder<T extends ChatFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624078, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624078, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624207, "field 'usernameView'");
    target.usernameView = finder.castView(view, 2131624207, "field 'usernameView'");
    view = finder.findRequiredView(source, 2131624129, "field 'messageListView'");
    target.messageListView = finder.castView(view, 2131624129, "field 'messageListView'");
    view = finder.findRequiredView(source, 2131624095, "field 'editText'");
    target.editText = finder.castView(view, 2131624095, "field 'editText'");
    view = finder.findRequiredView(source, 2131624191, "method 'sendMessage'");
    unbinder.view2131624191 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendMessage();
      }
    });
    view = finder.findRequiredView(source, 2131623989, "method 'close'");
    unbinder.view2131623989 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.close();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends ChatFragment> implements Unbinder {
    private T target;

    View view2131624191;

    View view2131623989;

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
      target.toolbar = null;
      target.usernameView = null;
      target.messageListView = null;
      target.editText = null;
      view2131624191.setOnClickListener(null);
      view2131623989.setOnClickListener(null);
    }
  }
}
