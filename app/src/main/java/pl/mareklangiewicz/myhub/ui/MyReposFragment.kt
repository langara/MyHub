package pl.mareklangiewicz.myhub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.mh_fragment_my_repos.*
import kotlinx.android.synthetic.main.mh_notes.view.*
import pl.mareklangiewicz.myfragments.MyFragment
import pl.mareklangiewicz.myhub.MHApplication
import pl.mareklangiewicz.myhub.MyReposPresenter
import pl.mareklangiewicz.myhub.R
import javax.inject.Inject

class MyReposFragment : MyFragment() {

    // TODO LATER: local search on ToolBar
    // TODO SOMEDAY: local menu with sorting options?

    @Inject lateinit var presenter: MyReposPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity.application as MHApplication).component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflateHeader(R.layout.mh_notes)
        return inflater.inflate(R.layout.mh_fragment_my_repos, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val v = AMyReposView(
                activity as MHActivity,
                mh_fmr_pb_progress,
                mh_fmr_tv_status,
                mh_fmr_rv_repos,
                header!!.mh_n_rv_notes
        )
        presenter.view = v
    }

    override fun onDestroyView() {
        presenter.view = null
        super.onDestroyView()
    }
}
