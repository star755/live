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

public class LiveBaseActivity$$ViewBinder<T extends LiveBaseActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624071, "field 'messageView'");
    target.messageView = finder.castView(view, 2131624071, "field 'messageView'");
    view = finder.findRequiredView(source, 2131624072, "field 'periscopeLayout'");
    target.periscopeLayout = finder.castView(view, 2131624072, "field 'periscopeLayout'");
    view = finder.findRequiredView(source, 2131624073, "field 'bottomBar'");
    target.bottomBar = view;
    view = finder.findRequiredView(source, 2131624228, "field 'horizontalRecyclerView'");
    target.horizontalRecyclerView = finder.castView(view, 2131624228, "field 'horizontalRecyclerView'");
    view = finder.findRequiredView(source, 2131624227, "field 'audienceNumView'");
    target.audienceNumView = finder.castView(view, 2131624227, "field 'audienceNumView'");
    view = finder.findRequiredView(source, 2131624223, "field 'userManagerView' and method 'showUserList'");
    target.userManagerView = finder.castView(view, 2131624223, "field 'userManagerView'");
    unbinder.view2131624223 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showUserList();
      }
    });
    view = finder.findRequiredView(source, 2131624224, "field 'switchCameraView'");
    target.switchCameraView = finder.castView(view, 2131624224, "field 'switchCameraView'");
    view = finder.findRequiredView(source, 2131624225, "field 'likeImageView'");
    target.likeImageView = finder.castView(view, 2131624225, "field 'likeImageView'");
    view = finder.findRequiredView(source, 2131624054, "field 'liveIdView'");
    target.liveIdView = finder.castView(view, 2131624054, "field 'liveIdView'");
    view = finder.findRequiredView(source, 2131624213, "field 'usernameView'");
    target.usernameView = finder.castView(view, 2131624213, "field 'usernameView'");
    view = finder.findRequiredView(source, 2131624222, "method 'onCommentImageClick'");
    unbinder.view2131624222 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCommentImageClick();
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LiveBaseActivity> implements Unbinder {
    private T target;

    View view2131624223;

    View view2131624222;

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
      target.messageView = null;
      target.periscopeLayout = null;
      target.bottomBar = null;
      target.horizontalRecyclerView = null;
      target.audienceNumView = null;
      view2131624223.setOnClickListener(null);
      target.userManagerView = null;
      target.switchCameraView = null;
      target.likeImageView = null;
      target.liveIdView = null;
      target.usernameView = null;
      view2131624222.setOnClickListener(null);
    }
  }
}
