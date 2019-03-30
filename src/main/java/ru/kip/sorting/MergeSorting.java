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

        List<T> result = sort(inputList, false);
        inputList.clear();
        inputList.addAll(result);
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

        List<T> result = sort(inputList, true);
        inputList.clear();
        inputList.addAll(result);
    }

    public List<T> sort(List<T> inputList, boolean reverse) {
        if (inputList.size() < 2) {
            return inputList;
        }

        int median = inputList.size() / 2;
        List<T> sublistA = inputList.subList(0, median);
        List<T> sublistB = inputList.subList(median, inputList.size());
        sublistA = sort(sublistA, reverse);
        sublistB = sort(sublistB, reverse);
        return merge(sublistA, sublistB, reverse);
    }

    private List<T> merge(List<T> a, List<T> b, boolean reverse) {
        int i = 0, aMarker = 0, bMarker = 0;
        List<T> result = new LinkedList<>();

        while (aMarker < a.size() && bMarker < b.size() && i++ < a.size() + b.size()) {
            int compareResult = a.get(aMarker).compareTo(b.get(bMarker));
            if (compareResult < 0 && reverse || compareResult > 0 && !reverse) {
                result.add(b.get(bMarker++));
            } else {
                result.add(a.get(aMarker++));
            }
        }

        if (a.size() - aMarker > 0) {
            result.addAll(a.subList(aMarker, a.size()));
        } else if (b.size() - bMarker > 0) {
            result.addAll(b.subList(bMarker, b.size()));
        }

        return result;
    }
}
