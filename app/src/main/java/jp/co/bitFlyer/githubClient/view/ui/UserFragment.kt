package jp.co.bitFlyer.githubClient.view.ui

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import jp.co.bitFlyer.githubClient.R
import jp.co.bitFlyer.githubClient.databinding.FragmentUserBinding
import jp.co.bitFlyer.githubClient.viewModel.UserViewModel

const val TAG_OF_USER_INFO_FRAGMENT = "UserInfoFragment"
/**
 * ユーザ情報画面
 * @author shin
 */
class UserFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }
    // 画面レイアウト
    private lateinit var binding: FragmentUserBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false) //dataBinding
        binding.apply {
            userViewModel = viewModel
            isLoading = true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.userData.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.isLoading = false
               // binding.userModel = user
                // 名前
                binding.nameText.text = user.name
//                // メール
                binding.mailText.text = user.email
                // 状態
            }
        })

        // ボタンイベント
        binding.repoListButton.setOnClickListener {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).showList()
            }
        }


    }
}
