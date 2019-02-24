package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.swap;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка выбором.
 */
public class SelectSorting<T extends Comparable> implements Sorting<T> {
    private static final Logger log = getLogger(SelectSorting.class);

    @Override
    public void sort(List<T> inputList) {
        sort(inputList, false);
    }

    @Override
    public void reverse(List<T> inputList) {
        sort(inputList, true);
    }

    public void sort(List<T> inputList, boolean reverse) {
        //игнорировать null
        if (Objects.isNull(inputList)) {
            log.warn("Null list");
            return;
        }

        //игнорировать сортировку, если размер листа меньше 2-х
        if (inputList.size() < 2) {
            return;
        }

        log.debug("Sort list with {} elements", inputList.size());

        for (int i = 0; i < inputList.size(); i++) {
            //индекс минимального элемента
            int minIndex = i;
            //значение минимального элемента
            T minElement = inputList.get(i);

            for (int j = i + 1; j < inputList.size(); j++) {
                int sortResult = minElement.compareTo(inputList.get(j));
                if (reverse && sortResult < 0) {
                    minIndex = j;
                    minElement = inputList.get(j);
                } else if (!reverse && sortResult > 0) {
                    minIndex = j;
                    minElement = inputList.get(j);
                }
            }

            if (i != minIndex) {
                swap(inputList, i, minIndex);
            }
        }
    }
}
