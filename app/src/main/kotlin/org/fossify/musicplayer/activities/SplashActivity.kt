package org.fossify.musicplayer.activities

import android.content.Intent
import org.fossify.commons.activities.BaseSplashActivities
import org.fossify.commons.activities.BaseSplashActivity

class SplashActivity : BaseSplashActivities() {
    override fun initActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
