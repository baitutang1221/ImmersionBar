package tingting.immersion.homemodule.fragments

import tingting.immersion.homemodule.R

/**
 * 互动主页
 */
class InteractionPageFragment : BaseFragment() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_interaction_page
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