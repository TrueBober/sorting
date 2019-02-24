package ru.kip.sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public interface Sorting<T extends Comparable> {

    void sort(List<T> inputList);

    default List<T> copyAndSort(List<T> inputList) {
        //игнорировать null
        if (Objects.isNull(inputList)) {
            return null;
        }

        List<T> result = new LinkedList<>(inputList);
        sort(result);

        return result;
    }

}
