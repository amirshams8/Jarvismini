package com.jarvismini.automation

import android.view.accessibility.AccessibilityNodeInfo

object NodeFinder {

    private val inputIds = listOf("com.whatsapp:id/entry")
    private val sendIds = listOf("com.whatsapp:id/send")

    fun findInputField(root: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        inputIds.forEach { id ->
            val nodes = safeFindById(root, id)
            if (nodes.isNotEmpty()) return nodes.first()
        }

        return findNodeByClass(root, "android.widget.EditText")
    }

    fun findSendButton(root: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        sendIds.forEach { id ->
            val nodes = safeFindById(root, id)
            if (nodes.isNotEmpty()) return nodes.first()
        }

        return findNodeByClass(root, "android.widget.ImageButton")
    }

    private fun safeFindById(node: AccessibilityNodeInfo, id: String): List<AccessibilityNodeInfo> {
        return try {
            node.findAccessibilityNodeInfosByViewId(id) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun findNodeByClass(node: AccessibilityNodeInfo, cls: String): AccessibilityNodeInfo? {
        if (node.className == cls) return node

        for (i in 0 until node.childCount) {
            val child = node.getChild(i) ?: continue
            val found = findNodeByClass(child, cls)
            if (found != null) return found
        }
        return null
    }
}
