package com.rmh.rhoffman.cbtvelocity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.squareup.picasso.Picasso;

public class CardDetails extends Activity implements ObservableScrollViewCallbacks{

	private static final float MAX_TEXT_SCALE_DELTA = 0.3f;

	private ImageView imageView;
	private View overlay;
	private ObservableScrollView scroll;
	private TextView titleTextView;
	private TextView bodyTextView;
	private int actionBarSize;
	private int flexibleSpaceImageHeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_card_details);

		flexibleSpaceImageHeight = getResources().getDimensionPixelOffset(R.dimen.flexible_space_image_height);
		actionBarSize = 72;

		imageView = (ImageView) findViewById(R.id.image);
		Picasso.with(this)
				.load("https://scontent-ord1-1.xx.fbcdn.net/hphotos-xaf1/v/t1.0-9/561991_162080323915090_640379256_n.jpg?oh=437c0a40f5728cf21d5b5a4a7d88c85f&oe=55E6B8EB")
				.into(imageView);

		overlay = findViewById(R.id.overlay);
		scroll = (ObservableScrollView) findViewById(R.id.scroll);
		scroll.setScrollViewCallbacks(this);
		titleTextView = (TextView) findViewById(R.id.title);
		bodyTextView = (TextView) findViewById(R.id.body);

		ScrollUtils.addOnGlobalLayoutListener(scroll, new Runnable(){
			@Override
			public void run(){
				scroll.scrollTo(0, 1);
				scroll.scrollTo(0, 0);
			}
		});

	}

	@Override
	public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging){
		// Translate overlay and image
		float flexibleRange = flexibleSpaceImageHeight - actionBarSize;
		int minOverlayTransitionY = actionBarSize - overlay.getHeight();
		ViewHelper.setTranslationY(overlay, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
		ViewHelper.setTranslationY(imageView, ScrollUtils.getFloat(-scrollY/2, minOverlayTransitionY, 0));

		// Change alpha of overlay
		ViewHelper.setAlpha(overlay, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

		// Scale title text
		float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
		ViewHelper.setPivotX(titleTextView, 0);
		ViewHelper.setPivotY(titleTextView, 0);
		ViewHelper.setScaleX(titleTextView, scale);
		ViewHelper.setScaleY(titleTextView, scale);

		// Translate title text
		int maxTitleTranslationY = (int) (flexibleSpaceImageHeight - titleTextView.getHeight() * scale);
		int titleTranslationY = maxTitleTranslationY - scrollY;
		ViewHelper.setTranslationY(titleTextView, titleTranslationY);
	}

	@Override
	public void onDownMotionEvent(){

	}

	@Override
	public void onUpOrCancelMotionEvent(ScrollState scrollState){

	}
}
