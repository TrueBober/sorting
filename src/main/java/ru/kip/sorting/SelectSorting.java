package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.swap;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка выбором.
 * @see <a href='https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%8B%D0%B1%D0%BE%D1%80%D0%BE%D0%BC'>Wikipedia</a>
 */
public class SelectSorting<T extends Comparable<? super T>> implements Sorting<T> {
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

            //начиная от i элемента и до конца коллекции поиск минимального элемента
            for (int j = i + 1; j < inputList.size(); j++) {
                T jElement = inputList.get(j);
                int compareResult = minElement.compareTo(jElement);
                if (reverse && compareResult < 0) {
                    minIndex = j;
                    minElement = inputList.get(j);
                } else if (!reverse && compareResult > 0) {
                    minIndex = j;
                    minElement = inputList.get(j);
                }
            }
            //если индекс минимального элемента, начиная от i-го, не равен i - менять элементы местами
            if (i != minIndex) {
                swap(inputList, i, minIndex);
            }
        }
    }
}
