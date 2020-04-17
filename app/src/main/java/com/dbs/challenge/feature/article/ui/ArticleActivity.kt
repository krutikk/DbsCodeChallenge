package com.dbs.challenge.feature.article.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dbs.challenge.R


class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container,
                ArticleFragment())
            .commit()

    }

}
