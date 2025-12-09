package com.jarvismini.automation

import android.accessibilityservice.AccessibilityService
import android.os.Bundle
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

object AutomationEngine {

    private var lastReplyTime = 0L
    private const val COOLDOWN_MS = 8000L

    fun handleEvent(service: AccessibilityService, event: AccessibilityEvent) {
        val root = service.rootInActiveWindow ?: return

        val input = NodeFinder.findInputField(root)
        val sendBtn = NodeFinder.findSendButton(root)

        // Prevent spam sending
        if (System.currentTimeMillis() - lastReplyTime < COOLDOWN_MS) return

        if (input != null && sendBtn != null) {

            val reply = SmartAutoReply.generateReply("Hello")

            // FOCUS FIRST
            input.performAction(AccessibilityNodeInfo.ACTION_FOCUS)

            // SET TEXT
            val bundle = Bundle()
            bundle.putCharSequence(
                AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                reply
            )
            input.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, bundle)

            // PRESS SEND
            sendBtn.performAction(AccessibilityNodeInfo.ACTION_CLICK)

            lastReplyTime = System.currentTimeMillis()

            Log.i("JarvisMini", "Auto replied successfully.")
        }
    }
}
