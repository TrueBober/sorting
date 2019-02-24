package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.Collections.swap;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка вставками.
 */
public class InsertSorting<T extends Comparable> implements Sorting<T> {
    private static final Logger log = getLogger(InsertSorting.class);

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

        for (int i = 1; i < inputList.size(); i++) {
            //сначала берется элемент с индексом i
            T current = inputList.get(i);

            //все элементы слева от i по очереди сдвигаются на 1 позицию вправо до тех пор, пока текущий элемент не
            //станет меньше элемента на позиции
            for (int j = i - 1; j >= 0; j--) {
                int compareResult = current.compareTo(inputList.get(j));
                if (reverse && compareResult < 0 || !reverse && compareResult > 0) {
                    break;
                } else {
                    swap(inputList, j, j+ 1);
                }
            }
        }
    }
}
