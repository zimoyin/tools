package com.github.zimoyin.utils

/**
 *
 * @author : zimo
 * @date : 2024/01/17
 */
class Observer<T : () -> Any> {
    private val hook = HashMap<String, ArrayList<T>>()

    fun add(key: String, event: T) {
        hook[key] = hook[key]?.apply {
            add(event)
        } ?: arrayListOf(event)
    }

    fun remove(key: String, event: T) {
        if (hook[key] != null) {
            hook[key]?.remove(event)
        }
    }

    fun removeAll(key: String) {
        hook[key]?.clear()
    }

    fun invertHook(key: String) {
        hook[key]?.forEach {
            it()
        }
    }
}