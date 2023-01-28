package jp.co.bitFlyer.githubClient.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.bitFlyer.githubClient.R
import jp.co.bitFlyer.githubClient.service.model.Project

/**
 * MainActivity
 * @author shin
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ユーザ情報画面に遷移
        if (savedInstanceState == null) {
            val userFragment = UserFragment() //一覧のFragment
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, userFragment, TAG_OF_USER_INFO_FRAGMENT)
                .commit()
        }
    }

    /**
     * 一覧画面に遷移
     */
    fun showList() {
        val projectListFragment = ProjectListFragment() //一覧のFragment
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("list")
            .replace(R.id.fragment_container, projectListFragment, TAG_OF_PROJECT_LIST_FRAGMENT)
            .commit()
    }

    /**
     * 詳細画面に遷移
     */
    fun showDetail(project: Project) {
        val projectFragment = ProjectFragment.forProject(project.name) //詳細のFragment
        supportFragmentManager
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container, projectFragment, null)
                .commit()
    }


}
