package com.rmh.rhoffman.cbtvelocity;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Class to control the scrolling animations on the FAB.
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

	public ScrollAwareFABBehavior(Context context, AttributeSet attrs){
		super();
	}

	@Override
	public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild,
	                                   View target, int nestedScrollAxes){
		return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
				super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
	}

	@Override
	public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target,
	                           int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed){
		super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

		if(dyConsumed > 0 && child.getVisibility() == View.VISIBLE){
			animateOut(child);
		} else if(dyConsumed < 0 && child.getVisibility() != View.VISIBLE){
			animateIn(child);
		}
	}

	private void animateOut(final FloatingActionButton button){
		if(Build.VERSION.SDK_INT >= 14){
			ViewCompat.animate(button).alpha(0.0f).setListener(new ViewPropertyAnimatorListener(){
				@Override
				public void onAnimationStart(View view){

				}

				@Override
				public void onAnimationEnd(View view){
					view.setVisibility(View.INVISIBLE);
				}

				@Override
				public void onAnimationCancel(View view){

				}
			}).start();
		} else {
			Animation a = AnimationUtils.loadAnimation(App.getContext(), R.anim.slide_down);
			a.setAnimationListener(new Animation.AnimationListener(){
				@Override
				public void onAnimationStart(Animation animation){

				}

				@Override
				public void onAnimationEnd(Animation animation){
					button.setVisibility(View.INVISIBLE);
				}

				@Override
				public void onAnimationRepeat(Animation animation){

				}
			});
			button.startAnimation(a);
		}
	}

	private void animateIn(FloatingActionButton button){
		button.setVisibility(View.VISIBLE);
		if(Build.VERSION.SDK_INT >= 14){
			ViewCompat.animate(button).alpha(1.0f).start();
		} else {
			Animation b = AnimationUtils.loadAnimation(App.getContext(), R.anim.slide_up);
			button.startAnimation(b);
		}
	}
}
