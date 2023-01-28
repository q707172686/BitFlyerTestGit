package jp.co.bitFlyer.githubClient.service.repository

import jp.co.bitFlyer.githubClient.service.model.Project
import jp.co.bitFlyer.githubClient.service.model.User

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val HTTPS_API_GITHUB_URL = "https://api.github.com/"

/**
 * ViewModelに対するデータプロバイダ
 */
class ProjectRepository {

    companion object Factory {
        val instance: ProjectRepository
            @Synchronized get() {
                return ProjectRepository()
            }
    }

    private val retrofit = Retrofit.Builder()
            .baseUrl(HTTPS_API_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val githubService: GithubService = retrofit.create(GithubService::class.java)

    suspend fun getUserInfo(userId: String): Response<User> =
        githubService.getUserInfo(userId)

    suspend fun getProjectList(userId: String): Response<List<Project>> =
        githubService.getProjectList(userId)

    suspend fun getProjectDetails(userID: String, projectName: String): Response<Project> =
        githubService.getProjectDetails(userID, projectName)
}
