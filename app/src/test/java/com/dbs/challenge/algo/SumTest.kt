package com.dbs.challenge.algo

import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class SumTest {
    fun threeSum(nums: IntArray): List<List<Int>>? {
        Arrays.sort(nums)
        val result: ArrayList<List<Int>> = ArrayList()
        for (i in nums.indices) {
            var j = i + 1
            var k = nums.size - 1
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue
            }
            while (j < k) {
                if (k < nums.size - 1 && nums[k] == nums[k + 1]) {
                    k--
                    continue
                }
                if (nums[i] + nums[j] + nums[k] > 0) {
                    k--
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++
                } else {
                    val l: ArrayList<Int> = ArrayList()
                    l.add(nums[i])
                    l.add(nums[j])
                    l.add(nums[k])
                    result.add(l)
                    j++
                    k--
                }
            }
        }
        return result
    }

    @Test
    fun test3Sum() {
        val arrayToTest = intArrayOf(-1, 0, 1, 2, -1, -4)
        println(threeSum(arrayToTest))
    }
}