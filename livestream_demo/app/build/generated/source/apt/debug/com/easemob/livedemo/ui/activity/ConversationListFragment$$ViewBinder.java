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

public class ConversationListFragment$$ViewBinder<T extends ConversationListFragment> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624209, "field 'conversationListView'");
    target.conversationListView = finder.castView(view, 2131624209, "field 'conversationListView'");
    view = finder.findRequiredView(source, 2131624078, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624078, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624010, "field 'titleView'");
    target.titleView = finder.castView(view, 2131624010, "field 'titleView'");
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

  protected static class InnerUnbinder<T extends ConversationListFragment> implements Unbinder {
    private T target;

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
      target.conversationListView = null;
      target.toolbar = null;
      target.titleView = null;
      view2131623989.setOnClickListener(null);
    }
  }
}
