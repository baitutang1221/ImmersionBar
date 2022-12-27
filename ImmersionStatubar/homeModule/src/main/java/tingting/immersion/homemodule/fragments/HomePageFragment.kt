package tingting.immersion.homemodule.fragments

import tingting.immersion.homemodule.R


/**
 * 首页
 */
class HomePageFragment : BaseFragment() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_home_page
    }

    override fun initView() {
        super.initView()

    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(!hidden){

        }
    }


}