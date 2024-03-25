package com.ccg.golddigger.ui

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/25 12:24
 */
object StringSplitExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = "1234567890abcdefghijk"
        val output = StringBuilder()
        var i = 0
        while (i < input.length) {
            if (i + 5 <= input.length) {
                output.append(input.substring(i, i + 5)).append("-")
            } else {
                output.append(input.substring(i))
            }
            i += 5
        }
        var result = output.toString()
        if (result.endsWith("-")) {
            result = result.substring(0, result.length - 1)
        }
        println(result)
    }
}
