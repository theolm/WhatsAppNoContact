package dev.theolm.wwc.ui.tile

import android.content.Intent
import android.service.quicksettings.TileService
import dev.theolm.wwc.ui.main.MainActivity

class ChatTile : TileService() {
    override fun onClick() {
        super.onClick()
        Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }.also {
            startActivityAndCollapse(it)
        }
    }
}
