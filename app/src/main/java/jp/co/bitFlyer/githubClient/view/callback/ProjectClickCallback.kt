package jp.co.bitFlyer.githubClient.view.callback

import jp.co.bitFlyer.githubClient.service.model.Project

/**
 * @link onClick(Project project) 詳細画面に移動
 */
interface ProjectClickCallback {
    fun onClick(project: Project)
}
