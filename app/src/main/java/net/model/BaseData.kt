package net.model

open class BaseData<T> {
    var status: Int = 0
    var msg: String = ""
    var data: T? = null
}