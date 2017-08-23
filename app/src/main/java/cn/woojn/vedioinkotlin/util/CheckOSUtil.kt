package cn.woojn.vedioinkotlin.util

import android.os.Build
import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*

/**
 * Created by wujun on 17-8-22.
 */
private val KEY_EMUI_VERSION_CODE = "ro.build.version.emui"
private val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
private val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
private val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"

fun isFlyme(): Boolean {
    try {
        val method = Build::class.java.getMethod("hasSmartBar")
        return method != null
    } catch (e: Exception) {
        return false
    }

}

fun isEMUI(): Boolean {
    return isPropertiesExist(KEY_EMUI_VERSION_CODE)
}

fun isMIUI(): Boolean {
    return isPropertiesExist(KEY_MIUI_VERSION_CODE, KEY_MIUI_VERSION_NAME, KEY_MIUI_INTERNAL_STORAGE)
}

private fun isPropertiesExist(vararg keys: String): Boolean {
    if (keys == null || keys.size == 0) {
        return false
    }
    try {
        val properties = BuildProperties.newInstance()
        for (key in keys) {
            val value = properties.getProperty(key)
            if (value != null)
                return true
        }
        return false
    } catch (e: IOException) {
        return false
    }

}

private class BuildProperties @Throws(IOException::class)
private constructor() {

    private val properties: Properties

    init {
        properties = Properties()
        // 读取系统配置信息build.prop类
        properties.load(FileInputStream(File(Environment.getRootDirectory(), "build.prop")))
    }

    fun containsKey(key: Any): Boolean {
        return properties.containsKey(key)
    }

    fun containsValue(value: Any): Boolean {
        return properties.containsValue(value)
    }

    fun entrySet(): MutableSet<MutableMap.MutableEntry<Any, Any>> {
        return properties.entries
    }

    fun getProperty(name: String): String? {
        return properties.getProperty(name)
    }

    fun getProperty(name: String, defaultValue: String): String {
        return properties.getProperty(name, defaultValue)
    }

    val isEmpty: Boolean
        get() = properties.isEmpty

    fun keys(): Enumeration<Any> {
        return properties.keys()
    }

    fun keySet(): MutableSet<Any> {
        return properties.keys
    }

    fun size(): Int {
        return properties.size
    }

    fun values(): Collection<Any> {
        return properties.values
    }

    companion object {

        @Throws(IOException::class)
        fun newInstance(): BuildProperties {
            return BuildProperties()
        }
    }
}