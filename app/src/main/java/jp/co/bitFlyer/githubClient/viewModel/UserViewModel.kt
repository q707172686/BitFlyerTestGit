package jp.co.bitFlyer.githubClient.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import jp.co.bitFlyer.githubClient.R
import jp.co.bitFlyer.githubClient.service.model.User
import jp.co.bitFlyer.githubClient.service.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * ユーザ情報ViewModel
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProjectRepository.instance
    var userData: MutableLiveData<User> = MutableLiveData()

    init {
        // ユーザ情報を取得
        getUserInfo()
    }

    /**
     * ユーザ情報取得処理
     */
    private fun getUserInfo() = viewModelScope.launch { //onCleared() のタイミングでキャンセルされる
        try {
            val response = repository.getUserInfo(getApplication<Application>().getString(R.string.github_user_name))
            if (response.isSuccessful) {
                userData.postValue(response.body()) //データを取得したら、LiveDataを更新
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }
}
