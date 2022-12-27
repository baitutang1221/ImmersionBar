package tingting.immersion.homemodule.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.widget.LinearLayout
import tingting.immersion.homemodule.R
import tingting.immersion.homemodule.utils.StatusBarUtils2


/**
 * 我的
 */
class MyPageFragment : BaseFragment() {

    private lateinit var header_view:LinearLayout

    override fun setLayoutId(): Int {
        return R.layout.fragment_mime_page
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        super.initView()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){ //5.0+
            header_view = findActivityViewById(R.id.my_header_view)
            header_view.setPadding(0, StatusBarUtils2.getStatusBarHeight(activity),0,0)
        }

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden){

        }
    }

}