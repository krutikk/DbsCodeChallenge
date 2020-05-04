package com.dbs.challenge.algo

import org.junit.Test

class EggDrop {

    var map: MutableMap<Int, Int> = HashMap()
    fun eggDrop(n: Int, k: Int): Int {

        if (k == 1 || k == 0) return k

        if (n == 1) return k

        var min = Int.MAX_VALUE
        var res: Int
        var x = 1
        while (x <= k) {
            res = Math.max(
                eggDrop(n - 1, x - 1),
                eggDrop(n, k - x)
            )
            if (res < min) min = res
            x++
        }


        return min + 1
    }

    @Test
    fun testEggDrop() {
        val n = 2 // no of eggs
        val k = 10 // floor
        print(eggDrop(2, 10))
    }
}