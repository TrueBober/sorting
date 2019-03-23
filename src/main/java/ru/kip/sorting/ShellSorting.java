package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.swap;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка Шелла.
 */
public class ShellSorting<T extends Comparable<? super T>> implements Sorting<T> {
    private static final Logger log = getLogger(ShellSorting.class);

    @Override
    public void sort(List<T> inputList) {
        sort(inputList, false);
    }

    @Override
    public void reverse(List<T> inputList) {
        sort(inputList, true);
    }

    public void sort(List<T> inputList, boolean reverse) {
        if (Objects.isNull(inputList)) {
            log.warn("Null list");
            return;
        }
        if (inputList.size() < 2) {
            return;
        }

        log.debug("Sort list with {} elements", inputList.size());

        //выбор первоначального размера шага
        int h = initH(inputList);

        do {
            for (int i = h; i < inputList.size(); i++) {
                T current = inputList.get(i);
                int j = i;

                while (j > h - 1 && compare(current, inputList.get(j - h), reverse)) {
                    inputList.set(j, inputList.get(j - h));
                    j -= h;
                }

                inputList.set(j, current);
            }

            //уменьшение шага
            h = (h - 1) / 3;
        }
        while (h > 0);
    }

    private boolean compare(T current, T hElement, boolean reverse) {
        int compareResult = hElement.compareTo(current);
        return reverse ? compareResult <= 0 : compareResult >= 0;
    }

    private int initH(List<T> inputList) {
        int h = 1;
        while (h <= inputList.size() / 3) {
            h = h * 3 + 1;
        }
        return h;
    }
}
