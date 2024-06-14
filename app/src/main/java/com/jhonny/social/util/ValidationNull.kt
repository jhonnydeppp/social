package com.jhonny.social.util

fun Int?.orEmpty(): Int = this ?: 0

fun Long?.orEmpty(): Long = this ?: 0

fun Boolean?.orEmpty(): Boolean = this ?: false

fun Double?.orEmpty(): Double = this ?: 0.0

fun Float?.orEmpty(): Float = this ?: 0F

/** Validate that all arguments are false or true, this function eliminate && and reduce code
 * and suppress the warning ComplexCondition **/
fun isFalse(vararg formatArgs: Boolean) = formatArgs.all { !it }
fun isTrue(vararg formatArgs: Boolean) = formatArgs.all { it }

inline fun <reified T> List<T>?.orEmpty(): List<T> = this ?: listOf<T>()

inline fun <reified T> T?.ifNotNull(crossinline block: (T) -> Unit) {
    if (this != null) {
        block(this)
    }
}
