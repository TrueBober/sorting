package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static java.util.Collections.swap;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Сортировка вставками.
 * @see <a href='https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B2%D1%81%D1%82%D0%B0%D0%B2%D0%BA%D0%B0%D0%BC%D0%B8'>Wikipedia</a>
 */
public class InsertSorting<T extends Comparable<? super T>> implements Sorting<T> {
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
            //станет меньше элемента на позиции (при прямой сортировке)
            for (int j = i - 1; j >= 0; j--) {
                T jElement = inputList.get(j);
                int compareResult = current.compareTo(jElement);
                if (reverse && compareResult < 0 || !reverse && compareResult > 0) {
                    break;
                } else {
                    swap(inputList, j, j+ 1);
                }
            }
        }
    }
}
