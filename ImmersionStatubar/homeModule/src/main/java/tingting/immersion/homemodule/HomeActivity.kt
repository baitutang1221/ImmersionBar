package tingting.immersion.homemodule

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import tingting.immersion.homemodule.fragments.*
import tingting.immersion.homemodule.utils.StatusBarUtils
import tingting.immersion.homemodule.utils.StatusBarUtils2
import tingting.immersion.homemodule.utils.SwitchFragmentManager

/**
 * 主页
 */
class HomeActivity : AppCompatActivity() , View.OnClickListener {

    //碎片承载控件
    private lateinit var flHomePage: FrameLayout
    private lateinit var flBroadcast: FrameLayout
    private lateinit var flInteraction: FrameLayout
    private lateinit var flJingxuan: FrameLayout
    private lateinit var flMine: FrameLayout

    //底部导航父组件
    private lateinit var llHomePage: LinearLayout
    private lateinit var llBroadcasting: LinearLayout
    private lateinit var llInteraction: LinearLayout
    private lateinit var llJingxuan: RelativeLayout
    private lateinit var rlMy: RelativeLayout

    //底部导航文本
    private lateinit var tvHomePage: TextView
    private lateinit var tvBroadcast: TextView
    private lateinit var tvInteraction: TextView
    private lateinit var tvMy: TextView
    private lateinit var tvJingxuan: TextView

    //底部导航图片
    private lateinit var ivBroadcast: ImageView
    private lateinit var ivMy: ImageView
    private lateinit var ivHomePage: ImageView
    private lateinit var ivInteraction: ImageView
    private lateinit var ivJingxuan: ImageView

    protected var switchFragmentManager: SwitchFragmentManager? = null

    //碎片实体对象
    private lateinit var homePageFragment: HomePageFragment
    private lateinit var broadcastPageFragment: BroadcastRadioPageFragment
    private lateinit var interactionPageFragment: InteractionPageFragment
    private lateinit var myPageFragment: MyPageFragment
    private lateinit var jingxuanPageFragment: JingxuanPageFragment

    private val HOME_TAG : String = "HOME_TAG"
    private val BROADCAST_TAG : String = "BROADCAST_TAG"
    private val INTERACTION_TAG : String = "INTERACTION_TAG"
    private val MY_TAG : String = "MY_TAG"
    private val JINGXUAN_TAG : String = "JINGXUAN_TAG"

    private fun viewIDChangeString(id:Int) :String{
        when(id){
            R.id.ll_bottom_navigation_home_page ->{
                return HOME_TAG
            }
            R.id.ll_bottom_navigation_broadcasting ->{
                return BROADCAST_TAG
            }
            R.id.ll_bottom_navigation_interaction ->{
                return INTERACTION_TAG
            }
            R.id.rl_bottom_navigation_my ->{
                return MY_TAG
            }
            R.id.rl_bottom_navigation_jingxuan ->{
                return JINGXUAN_TAG
            }
            else ->{
                return ""
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        initViews()
        initDatas()
        initConfigure()

    }

    private fun initViews(){
        //碎片容器控件
        flHomePage = findViewById(R.id.fl_fragment_home_page_layout)
        flBroadcast = findViewById(R.id.fl_fragment_broadcast_layout)
        flInteraction = findViewById(R.id.fl_fragment_interaction_layout)
        flJingxuan = findViewById(R.id.fl_fragment_jingxuan_layout)
        flMine = findViewById(R.id.fl_fragment_my_layout)

        llHomePage = findViewById(R.id.ll_bottom_navigation_home_page)
        llBroadcasting = findViewById(R.id.ll_bottom_navigation_broadcasting)
        llInteraction = findViewById(R.id.ll_bottom_navigation_interaction)
        llJingxuan = findViewById(R.id.rl_bottom_navigation_jingxuan)
        rlMy = findViewById(R.id.rl_bottom_navigation_my)

        tvBroadcast = findViewById(R.id.tv_bottom_navigation_broadcasting)
        ivBroadcast = findViewById(R.id.iv_bottom_navigation_broadcasting)
        tvMy = findViewById(R.id.tv_bottom_navigation_my)
        ivMy = findViewById(R.id.iv_bottom_navigation_my)
        ivJingxuan = findViewById(R.id.iv_bottom_navigation_jingxuan)
        tvJingxuan = findViewById(R.id.tv_bottom_navigation_jingxuan)

        tvHomePage = findViewById(R.id.tv_bottom_navigation_home_page)
        ivHomePage = findViewById(R.id.iv_bottom_navigation_home_page)
        tvInteraction = findViewById(R.id.tv_bottom_navigation_interaction)
        ivInteraction = findViewById(R.id.iv_bottom_navigation_interaction)
    }

    private fun initDatas(){

    }

    private fun initConfigure(){
        //声明切换碎片管理器对象
        switchFragmentManager = SwitchFragmentManager.getInstance()

        broadcastPageFragment = BroadcastRadioPageFragment()
        interactionPageFragment = InteractionPageFragment()
        homePageFragment = HomePageFragment()
        myPageFragment = MyPageFragment()
        jingxuanPageFragment = JingxuanPageFragment()

        llBroadcasting.setOnClickListener(this)
        rlMy.setOnClickListener(this)
        llHomePage.setOnClickListener(this)
        llInteraction.setOnClickListener(this)
        llJingxuan.setOnClickListener(this)

        showFragment(HOME_TAG)
        showFragmentLayout(HOME_TAG)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            val tag = viewIDChangeString(v.id)
            showFragment(tag)
            showFragmentLayout(tag)
            updateNavigationView(tag)
        }
    }

    private fun showFragment(tag: String) {

        val flag = 2

        when(tag){
            HOME_TAG ->{
                if(flag == 1){
                    val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
                    if (null == fragment) {
                        switchFragmentManager?.addFragmentInActivity(supportFragmentManager, homePageFragment, R.id.fl_fragment_home_page_layout, tag)
                    }else{
                        fragment.let {
                            switchFragmentManager?.showFragmentInActivity(supportFragmentManager, it)
                        }
                    }
                }
                else{
                    switchFragmentManager?.showFragment(supportFragmentManager, R.id.fl_fragment_home_page_layout, homePageFragment::class.java,
                        tag
                    )
                    switchFragmentManager?.hideFragments(supportFragmentManager, BROADCAST_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, INTERACTION_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, MY_TAG)
                }
            }
            BROADCAST_TAG ->{
                if(flag == 1){
                    val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
                    if (null == fragment) {
                        switchFragmentManager?.addFragmentInActivity(supportFragmentManager, broadcastPageFragment, R.id.fl_fragment_broadcast_layout, tag)
                    }else{
                        fragment.let {
                            switchFragmentManager?.showFragmentInActivity(supportFragmentManager, it)
                        }
                    }
                }
                else{
                    switchFragmentManager?.showFragment(supportFragmentManager, R.id.fl_fragment_broadcast_layout, broadcastPageFragment::class.java,
                        tag
                    )
                    switchFragmentManager?.hideFragments(supportFragmentManager, HOME_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, INTERACTION_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, MY_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, JINGXUAN_TAG)
                }
            }
            INTERACTION_TAG ->{
                if(flag == 1){
                    val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
                    if (null == fragment) {
                        switchFragmentManager?.addFragmentInActivity(supportFragmentManager, interactionPageFragment,  R.id.fl_fragment_interaction_layout, tag)
                    }else{
                        fragment.let {
                            switchFragmentManager?.showFragmentInActivity(supportFragmentManager, it)
                        }
                    }
                }
                else{
                    switchFragmentManager?.showFragment(supportFragmentManager, R.id.fl_fragment_interaction_layout, interactionPageFragment::class.java,
                        tag
                    )
                    switchFragmentManager?.hideFragments(supportFragmentManager, HOME_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, BROADCAST_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, MY_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, JINGXUAN_TAG)
                }
            }
            MY_TAG ->{
                if(flag == 1){
                    val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
                    if (null == fragment) {
                        switchFragmentManager?.addFragmentInActivity(supportFragmentManager, myPageFragment,  R.id.fl_fragment_my_layout, tag)
                    }else{
                        fragment.let {
                            switchFragmentManager?.showFragmentInActivity(supportFragmentManager, it)
                        }
                    }
                }
                else{
                    switchFragmentManager?.showFragment(supportFragmentManager, R.id.fl_fragment_my_layout, myPageFragment::class.java,
                        tag
                    )
                    switchFragmentManager?.hideFragments(supportFragmentManager, HOME_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, INTERACTION_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, BROADCAST_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, JINGXUAN_TAG)
                }
            }
            JINGXUAN_TAG ->{
                if(flag == 1){
                    val fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
                    if (null == fragment) {
                        switchFragmentManager?.addFragmentInActivity(supportFragmentManager, jingxuanPageFragment,  R.id.fl_fragment_jingxuan_layout, tag)
                    }else{
                        fragment.let {
                            switchFragmentManager?.showFragmentInActivity(supportFragmentManager, it)
                        }
                    }
                }
                else{
                    switchFragmentManager?.showFragment(supportFragmentManager, R.id.fl_fragment_jingxuan_layout, jingxuanPageFragment::class.java,
                        tag
                    )
                    switchFragmentManager?.hideFragments(supportFragmentManager, HOME_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, INTERACTION_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, BROADCAST_TAG)
                    switchFragmentManager?.hideFragments(supportFragmentManager, MY_TAG)
                }
            }
        }
    }

    private fun updateNavigationView(tag: String) {
        ivBroadcast.setImageResource(R.mipmap.broadcast_default)
        ivMy.setImageResource(R.mipmap.my_default)
        ivHomePage.setImageResource(R.mipmap.discovery_default)
        ivJingxuan.setImageResource(R.mipmap.my_default)
        ivInteraction.setImageResource(R.mipmap.home_bottom_audio_book_unselect)

        tvBroadcast.setTextColor(Color.parseColor("#756E6C"))
        tvMy.setTextColor(Color.parseColor("#756E6C"))
        tvHomePage.setTextColor(Color.parseColor("#756E6C"))
        tvJingxuan.setTextColor(Color.parseColor("#756E6C"))
        tvInteraction.setTextColor(Color.parseColor("#756E6C"))

        when(tag){
            HOME_TAG ->{
                ivHomePage.setImageResource(R.mipmap.discovery_press)
                tvHomePage.setTextColor(Color.parseColor("#4a90e2"))
            }
            BROADCAST_TAG ->{
                ivBroadcast.setImageResource(R.mipmap.broadcast_press)
                tvBroadcast.setTextColor(Color.parseColor("#4a90e2"))
            }
            INTERACTION_TAG ->{
                ivInteraction.setImageResource(R.mipmap.home_bottom_audio_book_selected)
                tvInteraction.setTextColor(Color.parseColor("#4a90e2"))
            }
            MY_TAG ->{
                ivMy.setImageResource(R.mipmap.my_press)
                tvMy.setTextColor(Color.parseColor("#4a90e2"))
            }
            JINGXUAN_TAG ->{
                ivJingxuan.setImageResource(R.mipmap.my_press)
                tvJingxuan.setTextColor(Color.parseColor("#4a90e2"))
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showFragmentLayout(tag: String) {
        when(tag){
            HOME_TAG ->{
                flHomePage.visibility = View.VISIBLE
                flBroadcast.visibility = View.GONE
                flInteraction.visibility = View.GONE
                flJingxuan.visibility = View.GONE
                flMine.visibility = View.GONE

                StatusBarUtils2.with(this@HomeActivity)
                    .setColor(resources.getColor(R.color.text_green))
                    .init(StatusBarUtils2.COLOR_FLAG)
            }
            BROADCAST_TAG ->{
                flHomePage.visibility = View.GONE
                flBroadcast.visibility = View.VISIBLE
                flInteraction.visibility = View.GONE
                flJingxuan.visibility = View.GONE
                flMine.visibility = View.GONE

                StatusBarUtils2.with(this@HomeActivity)
                    .setColor(resources.getColor(R.color.white))
                    .init(StatusBarUtils2.COLOR_FLAG)

            }
            INTERACTION_TAG ->{
                flHomePage.visibility = View.GONE
                flBroadcast.visibility = View.GONE
                flInteraction.visibility = View.VISIBLE
                flJingxuan.visibility = View.GONE
                flMine.visibility = View.GONE


                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){ //5.0+
                    StatusBarUtils2.with(this@HomeActivity).init(StatusBarUtils2.DEFAULT_FLAG)
                }else{
                    StatusBarUtils2.with(this@HomeActivity)
                        .setColor(resources.getColor(R.color.black))
                        .init(StatusBarUtils2.COLOR_FLAG)
                }

            }
            MY_TAG ->{
                flHomePage.visibility = View.GONE
                flBroadcast.visibility = View.GONE
                flInteraction.visibility = View.GONE
                flJingxuan.visibility = View.GONE
                flMine.visibility = View.VISIBLE

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){ //5.0+
                    StatusBarUtils2.with(this@HomeActivity).init(StatusBarUtils2.DEFAULT_FLAG)
                }else{
                    StatusBarUtils2.with(this@HomeActivity)
                        .setColor(resources.getColor(R.color.black))
                        .init(StatusBarUtils2.COLOR_FLAG)
                }

            }
            JINGXUAN_TAG ->{
                flHomePage.visibility = View.GONE
                flBroadcast.visibility = View.GONE
                flInteraction.visibility = View.GONE
                flMine.visibility = View.GONE
                flJingxuan.visibility = View.VISIBLE

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){ //5.0+
                    StatusBarUtils2.with(this@HomeActivity).init(StatusBarUtils2.DEFAULT_FLAG)
                }else{
                    StatusBarUtils2.with(this@HomeActivity)
                        .setColor(resources.getColor(R.color.black))
                        .init(StatusBarUtils2.COLOR_FLAG)
                }

            }
        }
    }
}