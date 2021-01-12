package com.example.matisse.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraySplitUtil<T> {
    /**
     * 按指定大小对列表分组
     * @param list
     * @param splitSize
     * @return
     */
    public List<List<T>> splistList(List<T> list, int splitSize) {
        if (null == list) {
            return null;
        }
        int listSize = list.size();
        List<List<T>> newList = new ArrayList<List<T>>();
        if (listSize < splitSize) {
            newList.add(list);
            return newList;
        }
        int addLength = splitSize;
        int times = listSize / splitSize;
        if (listSize % splitSize != 0) {
            times += 1;
        }
        int start = 0;
        int end = 0;
        int last = times - 1;
        for (int i = 0; i < times; i++) {
            start = i * splitSize;
            if (i < last) {
                end = start + addLength;
            } else {
                end = listSize;
            }
            newList.add(list.subList(start, end));
        }
        return newList;
    }

    /**
     * 按指定大小对数组分组
     * @param array
     * @param splitSize
     * @return
     */
    public List<T[]> splistArray(T[] array, int splitSize) {
        if (null == array) {
            return null;
        }
        int listSize = array.length;
        List<T[]> newList = new ArrayList<T[]>();
        if (listSize < splitSize) {
            newList.add(array);
            return newList;
        }
        int addLength = splitSize;
        int times = listSize / splitSize;
        if (listSize % splitSize != 0) {
            times += 1;
        }
        int start = 0;
        int end = 0;
        int last = times - 1;
        for (int i = 0; i < times; i++) {
            start = i * splitSize;
            if (i < last) {
                end = start + addLength;
            } else {
                end = listSize;
            }
            newList.add(Arrays.copyOfRange(array, start, end));
        }
        return newList;
    }

}
