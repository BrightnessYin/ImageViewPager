package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public class ImageBrowseActivity extends SwipeBackActivity implements BGASwipeBackHelper.Delegate {

    @BindView(R.id.view_pager)
    HackyViewPager mViewPager;
    @BindView(R.id.swipe_layout)
    SwipeBackLayout1 mSwipeBackLayout;
    @BindView(R.id.page_text)
    TextView mTvImageSize;
    @BindView(R.id.rl_imagebrowse)
    RelativeLayout mRlImageBrowse;
    private String[] imageUrls = {"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg/",
            "http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg/",
            "http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg/",
            "http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg/",
            "http://app.infunpw.com/commons/images/cinema/cinema_films/3779.jpg/",
            "http://app.infunpw.com/commons/images/cinema/cinema_films/3825.jpg/"};
    private int selectedIndex;
    protected BGASwipeBackHelper mSwipeBackHelper;
    private static final String TAG = "ImageBrowseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browse);
//        setDragEdge(SwipeBackLayout.DragEdge.LEFT);
        ButterKnife.bind(this);

        mRlImageBrowse.getBackground().setAlpha(255);
        mSwipeBackLayout.setDragDirectMode(SwipeBackLayout1.DragDirectMode.VERTICAL);
        mSwipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout1.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                mRlImageBrowse.getBackground().setAlpha(255 - (int) Math.ceil(255 * fractionAnchor));
            }
        });

        if (imageUrls == null) return;

        mViewPager.setAdapter(new ViewPagerAdapter(imageUrls));
        mViewPager.setCurrentItem(selectedIndex);

        if (selectedIndex == 0) {
            mSwipeBackHelper.setSwipeBackEnable(true);
        } else {
            mSwipeBackHelper.setSwipeBackEnable(false);
        }

        if (imageUrls.length > 1) {
            mTvImageSize.setText(selectedIndex + 1 + " / " + imageUrls.length);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mTvImageSize.setText(position + 1 + " / " + imageUrls.length);
                    if (position == 0) {
                        mSwipeBackHelper.setSwipeBackEnable(true);
                    } else {
                        mSwipeBackHelper.setSwipeBackEnable(false);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }

    public static void launch(Activity context) {
        Intent intent = new Intent(context, ImageBrowseActivity.class);
//        intent.putExtra("urls", urls);
//        intent.putExtra("selectedIndex", selectedIndex);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(false);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutCancel() {

    }

    @Override
    public void onSwipeBackLayoutExecuted() {

    }

    private class ViewPagerAdapter extends PagerAdapter {
        private PhotoView mPhotoView;
        private ProgressBar mProgressBar;
        private String[] imageUrls = {"http://app.infunpw.com/commons/images/cinema/cinema_films/3823.jpg/",
                "http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg/",
                "http://app.infunpw.com/commons/images/cinema/cinema_films/3566.jpg/",
                "http://app.infunpw.com/commons/images/cinema/cinema_films/3757.jpg/",
                "http://app.infunpw.com/commons/images/cinema/cinema_films/3779.jpg/",
                "http://app.infunpw.com/commons/images/cinema/cinema_films/3825.jpg/"};

        private ViewPagerAdapter(String[] imageUrls) {
            this.imageUrls = imageUrls;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(ImageBrowseActivity.this).inflate(
                    R.layout.loadimage, null);
            mPhotoView = (PhotoView) view.findViewById(R.id.photoview);
            mProgressBar = (ProgressBar) view.findViewById(R.id.loading);
            mProgressBar.setVisibility(View.GONE);
            mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView imageView, float v, float v1) {
                    finish();
                }
            });
            ImageLoaderUtil.LoadImage(ImageBrowseActivity.this, imageUrls[position],
                    new DrawableImageViewTarget(mPhotoView) {
                        @Override
                        public void setDrawable(Drawable drawable) {
                            super.setDrawable(drawable);
                            Log.i(TAG, "setDrawable: ");
                            // mProgressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadStarted(@Nullable Drawable placeholder) {
                            super.onLoadStarted(placeholder);
                            Log.i(TAG, "onLoadStarted: ");
                            mProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            Log.i(TAG, "onLoadFailed: ");
                            mProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            super.onLoadCleared(placeholder);
                            Log.i(TAG, "onLoadCleared: ");
                            mProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            super.onResourceReady(resource, transition);
                            Log.i(TAG, "onResourceReady: ");
                            mProgressBar.setVisibility(View.GONE);

                        }
                    });
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return imageUrls.length > 0 ? imageUrls.length : 0;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
