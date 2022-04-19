package com.kerubyte.common.util

import java.util.regex.Pattern

fun String?.isValidEmail(): Boolean {
    return !this.isNullOrEmpty() && Pattern.matches(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
                ")+", this
    )
}

fun String?.isValidPassword(): Boolean {
    return !this.isNullOrEmpty() && (this.length >= 6)
}