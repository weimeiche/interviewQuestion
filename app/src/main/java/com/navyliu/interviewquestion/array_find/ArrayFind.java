package com.navyliu.interviewquestion.array_find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排列，每一列都按照从上到下的顺序排列。
 * 完成一个函数，输入这样的一个二位数组和一个整数，判断数组中是否包含该整数。
 */
public class ArrayFind extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[][] array = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10},
                {6, 8, 11, 15}
        };
        System.out.println("array[3][3] is:" + arrayFind1(array, 2));
        System.out.println("array[3][3] is:" + arrayFind2(array, 5));
        System.out.println("array[3][3] is:" + arrayFind3(array, 11));
    }

    /**
     * 每一行都按照从左到右递增的顺序排序，把每一行看成有序递增数组
     * 利用二分查找
     * 通过遍历每一行查找答案
     * 时间复杂度mlog（n）
     *
     * @param array
     * @param keyword
     * @return
     */
    private boolean arrayFind3(int[][] array, int keyword) {
        if (array == null || array.length == 0 || (array.length == 1 && array[0].length == 0)) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            int begin = 0;
            int end = array[i].length - 1;
            while (begin <= end) {
                int mid = (begin + end) / 2;
                if (keyword > array[i][mid]) {
                    begin = mid + 1;
                } else if (keyword < array[i][mid]) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 利用二位数组由上到下，由左到右递增的规律，
     * 选取左下角或者右上角的元素a[i][j]与keyword进行比较，
     * 当keyword大于a[i][j]的时候，keyword一定在a[i][j]的右边，
     * 即j++
     * 当keyword小于a[i][j]的时候，keyword一定在a[i][j]的上边，
     * 即i--；
     * 时间复杂度m+n
     *
     * @param array
     * @param keyword
     * @return
     */
    private boolean arrayFind2(int[][] array, int keyword) {
        if (array == null || array.length == 0 || (array.length == 1 && array[0].length == 0)) {
            return false;
        }
        // 左下角
        int i = array.length - 1;
        int j = 0;
        while (i >= 0 && j < array[i].length) {
            if (keyword == array[i][j]) {
                return true;
            } else if (keyword > array[i][j]) {
                j++;
            } else { // keyword < array[i][j])
                i--;
            }
        }
        return false;
    }

    /**
     * 暴力查找
     * 时间复杂度mn
     *
     * @param array
     * @param keyword
     * @return
     */
    private boolean arrayFind1(int[][] array, int keyword) {
        if (array == null || array.length == 0 || (array.length == 1 && array[0].length == 0)) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (keyword == array[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }


}

