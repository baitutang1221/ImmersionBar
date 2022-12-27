package tingting.immersion.homemodule.fragments

import tingting.immersion.homemodule.R

/**
 * 广播主页
 */
class BroadcastRadioPageFragment : BaseFragment() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_broadcast_radio_page
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