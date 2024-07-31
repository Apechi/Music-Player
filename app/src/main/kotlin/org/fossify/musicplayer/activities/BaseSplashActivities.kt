package org.fossify.commons.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import org.fossify.commons.R
import org.fossify.commons.extensions.*
import org.fossify.commons.helpers.SIDELOADING_TRUE
import org.fossify.commons.helpers.SIDELOADING_UNCHECKED

abstract class BaseSplashActivities : AppCompatActivity() {
    abstract fun initActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Force dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        if (baseConfig.appSideloadingStatus == SIDELOADING_UNCHECKED) {
            if (checkAppSideloading()) {
                return
            }
        } else if (baseConfig.appSideloadingStatus == SIDELOADING_TRUE) {
            showSideloadingDialog()
            return
        }

        // Apply fixed dark theme colors
        baseConfig.apply {
            isUsingSharedTheme = false
            textColor = 0xFF2C50CB.toInt() // Text color: #2C50CB
            backgroundColor = 0xFF1E1E1E.toInt() // Background color: #1E1E1E
        }

        // Apply colors directly to the activity window
        window.decorView.setBackgroundColor(baseConfig.backgroundColor)

        if (!baseConfig.isUsingAutoTheme && !baseConfig.isUsingSystemTheme) {
            getSharedTheme {
                if (it != null) {
                    baseConfig.apply {
                        wasSharedThemeForced = true
                        isUsingSharedTheme = true
                        wasSharedThemeEverActivated = true

                        // Apply shared theme colors but keep fixed text and background colors
                        primaryColor = it.primaryColor
                        accentColor = it.accentColor


                    }
                }
                initActivity()
            }
        } else {
            initActivity()
        }
    }
}
