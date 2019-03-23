package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.swap;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка Шелла.
 */
public class ShellSorting<T extends Comparable> implements Sorting<T> {
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

        //выбор первоначального размера шага
        int h = 1;
        while (h <= inputList.size() / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for (int marker = h; marker < inputList.size(); marker++) {
                T temp = inputList.get(marker);
                int index = marker;

                while (index > h - 1 && inputList.get(index - h).compareTo(temp) >= 0) {
                    inputList.set(index, inputList.get(index - h));
                    index -= h;
                }
                inputList.set(index, temp);
            }
            //уменьшение шага
            h = (h - 1) / 3;
        }
    }
}
