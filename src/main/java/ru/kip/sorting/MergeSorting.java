package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка слиянием.
 */
public class MergeSorting<T extends Comparable<? super T>> implements Sorting<T> {
    private static final Logger log = getLogger(MergeSorting.class);

    @Override
    public void sort(List<T> inputList) {
        sort(inputList, false);
    }

    @Override
    public void reverse(List<T> inputList) {
        sort(inputList, true);
    }

    public void sort(List<T> inputList, boolean reverse) {
        if(inputList.size() < 2) {
            return;
        }

        int median = inputList.size() / 2;
        List<T> sublistA = inputList.subList(0, median);
        List<T> sublistB = inputList.subList(median, inputList.size());
        sort(sublistA, reverse);
        sort(sublistB, reverse);
        List<T> resultList = inputList.subList(0, inputList.size());
        resultList.clear();
        merge(sublistA, sublistB, resultList, reverse);
    }

    private void merge(List<T> a, List<T> b, List<T> result, boolean reverse) {
        int i, aMarker = 0, bMarker = 0;

        for (i = 0; i < a.size() && i < b.size(); i++) {
            int compareResult = a.get(aMarker).compareTo(b.get(bMarker));
            if (compareResult < 0 && reverse || compareResult > 0 && !reverse) {
                result.add(b.get(bMarker++));
            } else {
                result.add(a.get(aMarker++));
            }
        }

        if (a.size() - i > 0) {
            result.addAll(a.subList(i, a.size() - 1));
        } else if (b.size() - i > 0) {
            result.addAll(b.subList(i, b.size() - 1));
        }
    }
}
