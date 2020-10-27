package org.binpo.study.java8.sort;

import java.util.Arrays;

/**
 * 归并排序的代码实现学习
 * 资料：/Users/yinhao.zhang/OneDrive/学习资料/01-数据结构与算法之美/03-基础篇 (38讲)/12丨排序（下）：归并排序_快速排序.pdf
 */
public class MegreSortTest {


    public static void main(String[] args) {
        int[] a = new int[]{3, 7, 7, 1, 9, 6, 5, 2, 5, 4, 7};

        MegreSortTest megreSortTest = new MegreSortTest();

        megreSortTest.sort(a);

    }


    private void sort(int[] a) {
        int r = a.length - 1;
        merge_sort_c(a, 0, r);
        System.out.println(Arrays.toString(a));
    }


    private void merge_sort_c(int[] a, int p, int r) {
        if (p >= r) return;
        //中间值
        int q = (p + r) / 2;
        merge_sort_c(a, p, q);

        merge_sort_c(a, q + 1, r);

        meger(a, p, q, q + 1, r);

    }


    private void meger(int[] a, int part_a_start, int part_a_end, int part_b_start, int part_b_end) {
        //需要排序
        int[] tmp = new int[part_b_end - part_a_start + 1];
        int i = part_a_start;
        int j = part_b_start;
        int k = 0;
        while (i <= part_a_end && j <= part_b_end) {
            if (a[i] <= a[j]) {
                tmp[k] = a[i];
                i++;
            } else {
                tmp[k] = a[j];
                j++;
            }
            k++;
        }
        //剩下的数据

        int start = i;
        int end = part_a_end;
        if(i>part_a_end){
            start = j;
            end = part_b_end;
        }
        while (start <=end){
            tmp[k++] = a[start++];
        }

        for (int m = 0; m < tmp.length; m++) {
            a[part_a_start + m] = tmp[m];
        }
    }


}
