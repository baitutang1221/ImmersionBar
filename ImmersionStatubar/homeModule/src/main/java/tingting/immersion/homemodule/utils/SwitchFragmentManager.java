package tingting.immersion.homemodule.utils;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * 切换碎片管理器
 * Created by liming on 2018/6/4.
 *
 * @author liming
 */

public class SwitchFragmentManager {
//    private static final String TAG = TTLog.makeLogTag(SwitchFragmentManager.class);

    //切换欺骗管理对象
    private static volatile SwitchFragmentManager manager = null;

    /**
     * 初始化方法
     *
     * @return
     */
    public static SwitchFragmentManager getInstance() {
        SwitchFragmentManager cache = manager;
        if (cache == null) {
            synchronized (SwitchFragmentManager.class) {
                cache = manager;
                if (cache == null) {
                    cache = new SwitchFragmentManager();
                    manager = cache;
                }
            }
        }
        return cache;
    }

    /**
     * 显示碎片
     *
     * @param manager
     * @param resID
     * @param clazz
     * @param tag
     */
    public void showFragment(@NonNull FragmentManager manager, @NonNull int resID,
                             @NonNull Class<? extends Fragment> clazz, @NonNull String tag) {
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = manager.findFragmentByTag(tag);
        try {
            if (fragment == null) {
                fragment = clazz.newInstance();
                transaction.add(resID, fragment, tag);
            }
            transaction.show(fragment);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏单个碎片
     *
     * @param manager
     */
    public void hideFragments(@NonNull FragmentManager manager, @NonNull String tag) {
        FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment != null) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    /**
     * 隐藏碎片
     *
     * @param manager
     */
    public void hideAllFragments(@NonNull FragmentManager manager, @NonNull Class<? extends Fragment> clazz) {
        FragmentTransaction transaction = manager.beginTransaction();

        List<Fragment> fragments = manager.getFragments();
        if (fragments != null) {
            for (Fragment f : fragments) {
                if (f.getClass().getSimpleName().equals(clazz.getSimpleName())) {
//                    TTLog.e(TAG, clazz.getSimpleName() + ":::::" + f.getClass().getSimpleName());
                    continue;
                }
                if (f != null) {
                    transaction.hide(f);
                }
            }
        }
        transaction.commit();
    }

    /**
     * 替换碎片
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                          @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, tag);
        transaction.commit();
    }

    /**
     * 添加碎片
     *
     * @param fragmentManager
     * @param fragment
     * @param tag
     */
    public void addFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commit();
    }

    /**
     * 显示碎片
     *
     * @param fragmentManager
     * @param fragment
     */
    public void showFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }
}
