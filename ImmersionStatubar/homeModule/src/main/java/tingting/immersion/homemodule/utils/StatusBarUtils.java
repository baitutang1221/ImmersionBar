package tingting.immersion.homemodule.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class StatusBarUtils {

    /**
     * 沉浸式状态栏
     * 同 com.tingtingfm.cbb.ui.activity.CbbActivity#fullScreen(boolean)
     */
    public static void useImmersiveStatus(Activity activity, int colorId){
        Window window = activity.getWindow();
        WindowManager.LayoutParams attr = window.getAttributes();
        attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.setAttributes(attr);
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        }
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    public static void initBarComp(Activity activity, int backgroundColor, boolean isBlackSystemUIColor){

        /*
         * Android 5.0（API 21）以上版本：
         * 在Android 5.0的时候，加入了一个重要的属性和方法 android:statusBarColor （对应方法为 setStatusBarColor），
         * 通过这个方法我们就可以轻松实现沉浸式。也就是说，从Android5.0开始，系统才真正的支持沉浸式。
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置了FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,表明会Window负责系统bar的background 绘制，绘制透明背景的系统bar（状态栏和导航栏），然后用getStatusBarColor()和getNavigationBarColor()的颜色填充相应的区域。这就是Android 5.0 以上实现沉浸式导航栏的原理
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //清除 FLAG_TRANSLUCENT_STATUS flag
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(backgroundColor));
        }

        /*
         * Android4.4（API 19） - Android 5.0（API 21)：
         * 通过FLAG_TRANSLUCENT_STATUS设置状态栏为透明并且为全屏模式，
         * 然后通过添加一个与StatusBar 一样大小的View，将View 的 background 设置为我们想要的颜色，从而来实现沉浸式。
         */
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            int count = decorView.getChildCount();
            if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
                decorView.getChildAt(count - 1).setBackgroundColor(activity.getResources().getColor(backgroundColor));
            } else {
                StatusBarView statusView = createStatusBarView(activity, backgroundColor);
                decorView.addView(statusView);
            }

            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }

        //Android 6.0 + 实现状态栏字体颜色
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(isBlackSystemUIColor){
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }

    }

    public static void setStatusBarContentBlackColor(Activity activity, boolean flag){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(flag){
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }else {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

    private static StatusBarView createStatusBarView(Activity activity, int color) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(activity.getResources().getColor(color));
        return statusBarView;
    }

    public static class StatusBarView extends View {

        public StatusBarView(Context context) {
            super(context);
        }

        public StatusBarView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }

        public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public StatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }
    }

}
