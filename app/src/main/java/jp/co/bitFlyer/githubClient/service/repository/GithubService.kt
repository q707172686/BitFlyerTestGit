package jp.co.bitFlyer.githubClient.service.repository

import jp.co.bitFlyer.githubClient.service.model.Project
import jp.co.bitFlyer.githubClient.service.model.User

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    //ユーザ情報
    @GET("users/{user}")
    suspend fun getUserInfo(@Path("user") user: String): Response<User>
    //一覧
    @GET("users/{user}/repos")
    suspend fun getProjectList(@Path("user") user: String): Response<List<Project>>

    //詳細
    @GET("/repos/{user}/{reponame}")
    suspend fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Response<Project>
}
