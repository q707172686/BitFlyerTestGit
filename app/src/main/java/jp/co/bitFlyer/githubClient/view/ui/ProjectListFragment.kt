package jp.co.bitFlyer.githubClient.view.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import jp.co.bitFlyer.githubClient.R
import jp.co.bitFlyer.githubClient.databinding.FragmentProjectListBinding
import jp.co.bitFlyer.githubClient.service.model.Project
import jp.co.bitFlyer.githubClient.view.adapter.ProjectAdapter
import jp.co.bitFlyer.githubClient.view.callback.ProjectClickCallback
import jp.co.bitFlyer.githubClient.viewModel.ProjectListViewModel

const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class ProjectListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(ProjectListViewModel::class.java)
    }

    private lateinit var binding: FragmentProjectListBinding
    private val projectAdapter: ProjectAdapter = ProjectAdapter(object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).showDetail(project)
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false) //dataBinding
        binding.apply {
            projectList.adapter = projectAdapter
            isLoading = true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.projectListLiveData.observe(viewLifecycleOwner, Observer { projects ->
            projects?.let {
                binding.isLoading = false
                projectAdapter.setProjectList(it)
            }
        })
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//    }
}
