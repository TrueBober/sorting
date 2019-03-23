package ru.kip.sorting;

import java.util.*;

/**
 * Интерфейс сортировки.
 */
public interface Sorting<T extends Comparable> {

    /**
     * Отсортировать список (по возрастанию).
     */
    void sort(List<T> inputList);

    /**
     * Отсортировать список (по убыванию).
     */
    void reverse(List<T> inputList);

    /**
     * Скопировать список и отсортировать.
     * @param inputList коллекция элементов.
     * @param reverse сортировать в обратном порядке.
     */
    default List<T> copyAndSort(Collection<T> inputList, boolean reverse) {
        if (Objects.isNull(inputList)) {
            return null;
        }
        if (inputList.isEmpty()) {
            return List.of();
        }

        //подготовить копию элементов
        List<T> result = new ArrayList<>(inputList);

        //сортировка
        if(reverse) {
            reverse(result);
        } else {
            sort(result);
        }

        return result;
    }
}
