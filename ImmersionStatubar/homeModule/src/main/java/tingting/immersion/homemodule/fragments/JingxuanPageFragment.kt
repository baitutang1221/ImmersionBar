package tingting.immersion.homemodule.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.widget.ScrollView
import android.widget.Toast
import androidx.annotation.RequiresApi
import tingting.immersion.homemodule.R
import tingting.immersion.homemodule.utils.StatusBarUtils2


/**
 * 精选
 */
class JingxuanPageFragment : BaseFragment() {

    private lateinit var scrollView :ScrollView

    private var flag : Boolean = false
    private var flag2 : Boolean = false

    override fun setLayoutId(): Int {
        return R.layout.fragment_jingxuan_page
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        super.initView()

        scrollView = findActivityViewById(R.id.scrollView)

        scrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY > oldScrollY && scrollY > 360){//向上滑
                flag2 = false
                if(!flag){
                    StatusBarUtils2.with(activity)
                        .setColor(resources.getColor(R.color.white))
                        .init(StatusBarUtils2.COLOR_FLAG)
                    Toast.makeText(activity,"状态栏文字颜色变了呢", Toast.LENGTH_SHORT).show()
                    flag = true
                }
            }
            if(scrollY < oldScrollY && scrollY < 360){ //向下滑
                flag = false
                if(!flag2){
                    StatusBarUtils2.with(activity).init(StatusBarUtils2.DEFAULT_FLAG)
                    Toast.makeText(activity,"状态栏文字颜色变回来了", Toast.LENGTH_SHORT).show()
                    flag2 = true
                }
            }
        }
    }

}