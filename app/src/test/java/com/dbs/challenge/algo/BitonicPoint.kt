package com.dbs.challenge.algo

import org.junit.Test

class BitonicPoint {
    fun ascendingBinarySearch(
        arr: IntArray, low: Int,
        high: Int, key: Int
    ): Int {
        var low = low
        var high = high
        while (low <= high) {
            val mid = low + (high - low) / 2
            if (arr[mid] == key) {
                return mid
            }
            if (arr[mid] > key) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return -1
    }


    fun descendingBinarySearch(
        arr: IntArray, low: Int,
        high: Int, key: Int
    ): Int {
        var low = low
        var high = high
        while (low <= high) {
            val mid = low + (high - low) / 2
            if (arr[mid] == key) {
                return mid
            }
            if (arr[mid] < key) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        return -1
    }


    fun findBitonicPoint(arr: IntArray, n: Int, l: Int, r: Int): Int {
        val mid: Int = (r + l) / 2
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return mid
        } else {
            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                findBitonicPoint(arr, n, mid, r)
            } else {
                if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    findBitonicPoint(arr, n, l, mid)
                }
            }
        }
        return mid
    }

    fun searchBitonic(arr: IntArray, n: Int, key: Int, index: Int): Int {
        return if (key > arr[index]) {
            -1
        } else if (key == arr[index]) {
            index
        } else {
            val temp = ascendingBinarySearch(arr, 0, index - 1, key)
            if (temp != -1) {
                temp
            } else descendingBinarySearch(arr, index + 1, n - 1, key)

            // Search in right of k
        }
    }

    @Test
    fun testBitonic() {
        val arr = intArrayOf(-3, 9, 8, 20, 17, 5, 1)
        val key = 20
        val n = arr.size
        val r = n - 1
        val l = 0
        val index: Int
        index = findBitonicPoint(arr, n, l, r)

        val x = searchBitonic(arr, n, key, index)

        if (x == -1) {
            println("Element Not Found")
        } else {
            println("Element Found at index $x")
        }
    }

}