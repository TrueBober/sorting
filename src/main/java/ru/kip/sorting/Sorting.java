package ru.kip.sorting;

import java.util.*;

/**
 * Интерфейс сортировки.
 */
public interface Sorting<T extends Comparable> {

    /**
     * Отсортировать список.
     */
    void sort(List<T> inputList);

    /**
     * Обратная сортировка списка.
     */
    void reverse(List<T> inputList);

    /**
     * Скопировать список и отсортировать.
     */
    default List<T> copyAndSort(Collection<T> inputList) {
        //игнорировать null
        if (Objects.isNull(inputList)) {
            return null;
        }

        List<T> result = new ArrayList<>(inputList);
        sort(result);

        return result;
    }

}
