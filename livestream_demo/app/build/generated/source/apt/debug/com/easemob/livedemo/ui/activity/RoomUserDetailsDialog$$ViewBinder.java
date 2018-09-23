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

public class RoomUserDetailsDialog$$ViewBinder<T extends RoomUserDetailsDialog> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624213, "field 'usernameView'");
    target.usernameView = finder.castView(view, 2131624213, "field 'usernameView'");
    view = finder.findRequiredView(source, 2131624219, "field 'setAdminButton' and method 'setToAdmin'");
    target.setAdminButton = finder.castView(view, 2131624219, "field 'setAdminButton'");
    unbinder.view2131624219 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setToAdmin();
      }
    });
    view = finder.findRequiredView(source, 2131624215, "field 'managementLayout'");
    target.managementLayout = finder.castView(view, 2131624215, "field 'managementLayout'");
    view = finder.findRequiredView(source, 2131624216, "method 'mute'");
    unbinder.view2131624216 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.mute();
      }
    });
    view = finder.findRequiredView(source, 2131624217, "method 'addToBlacklist'");
    unbinder.view2131624217 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addToBlacklist();
      }
    });
    view = finder.findRequiredView(source, 2131624218, "method 'kickMember'");
    unbinder.view2131624218 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.kickMember();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends RoomUserDetailsDialog> implements Unbinder {
    private T target;

    View view2131624219;

    View view2131624216;

    View view2131624217;

    View view2131624218;

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
      target.usernameView = null;
      view2131624219.setOnClickListener(null);
      target.setAdminButton = null;
      target.managementLayout = null;
      view2131624216.setOnClickListener(null);
      view2131624217.setOnClickListener(null);
      view2131624218.setOnClickListener(null);
    }
  }
}
