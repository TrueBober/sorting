package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка слиянием.
 */
public class MergeSorting<T extends Comparable<? super T>> implements Sorting<T> {
    private static final Logger log = getLogger(MergeSorting.class);

    @Override
    public void sort(List<T> inputList) {
        //игнорировать null
        if (Objects.isNull(inputList)) {
            log.warn("Null list");
            return;
        }

        //игнорировать сортировку, если размер листа меньше 2-х
        if (inputList.size() < 2) {
            return;
        }
        List<T> result = new LinkedList<>(inputList);
        sort(inputList, false, result, 0, inputList.size());
    }

    @Override
    public void reverse(List<T> inputList) {
        //игнорировать null
        if (Objects.isNull(inputList)) {
            log.warn("Null list");
            return;
        }

        //игнорировать сортировку, если размер листа меньше 2-х
        if (inputList.size() < 2) {
            return;
        }

        List<T> result = new LinkedList<>(inputList);
        sort(inputList, true, result, 0, inputList.size());
    }

    public void sort(List<T> inputList, boolean reverse, List<T> result, int position, int elements) {
        if (elements <= 1) {
            return;
        }

        int median = elements / 2;
        sort(inputList, reverse, result, position, median);
        sort(inputList, reverse, result, position + median, elements - median);
        merge(inputList, reverse, result, position, median, elements);
    }

    protected void merge(List<T> originalArray, boolean reverse, List<T> result, int position, int median, int elements) {
        int n = position, lMarker = position, rMarker = position + median;

        while (lMarker < position + median && rMarker < position + elements) {
            int compareResult = originalArray.get(lMarker).compareTo(originalArray.get(rMarker));
            if (compareResult < 0 && reverse || compareResult > 0 && !reverse) {
                result.set(n++, originalArray.get(rMarker++));
            } else {
                result.set(n++, originalArray.get(lMarker++));
            }
        }

        while (lMarker < position + median) {
            result.set(n++, originalArray.get(lMarker++));
        }

        while (rMarker < position + median) {
            result.set(n++, originalArray.get(rMarker++));
        }

        for (int i = position; i < position + elements; i++) {
            originalArray.set(i, result.get(i));
        }
    }
}
