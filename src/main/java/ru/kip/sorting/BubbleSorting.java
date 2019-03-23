package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;

import static java.util.Collections.swap;
import static java.util.Objects.isNull;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Пузырьковая сортировка.
 *
 * @see <a href='https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%80%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D0%B0_%D0%BF%D1%83%D0%B7%D1%8B%D1%80%D1%8C%D0%BA%D0%BE%D0%BC'>Wikipedia</a>
 */
public class BubbleSorting<T extends Comparable<? super T>> implements Sorting<T> {
    private static final Logger log = getLogger(BubbleSorting.class);

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
        if (isNull(inputList)) {
            log.warn("Null list");
            return;
        }

        //игнорировать сортировку, если размер листа меньше 2-х
        if (inputList.size() < 2) {
            return;
        }

        log.debug("Sort list with {} elements", inputList.size());

        //список отсортирован
        boolean sorted;
        //счетчик кол-ва проходов (чтобы была возможность выйти из цикла, если вдруг по каким-то причинам он стал бесконечный)
        int attempts = inputList.size();
        //индекс последнего отсортированного элемента
        int lastSortedElement = inputList.size();
        int tmpLastSortedElement = 0;

        do {
            sorted = true;
            for (int i = 1; i < inputList.size() && i < lastSortedElement; i++) {
                T previousElement = inputList.get(i - 1);
                T currentElement = inputList.get(i);
                int compareResult = previousElement.compareTo(currentElement);

                //если элемент с меньшим индексом имеет большее значение (или меньшее для обратной сортировки)
                if (reverse ? compareResult < 0 : compareResult > 0) {
                    //поменять элементы местами
                    tmpLastSortedElement = i;
                    swap(inputList, i, i - 1);
                    sorted = false; //пометить, что список не отсортирован
                }
            }
            lastSortedElement = tmpLastSortedElement;
        } while (!sorted && attempts-- >= 0);

        //кол-во проходов не должно превышать кол-во элементов в списке
        if (attempts < 0) {
            throw new IllegalStateException();
        }
    }
}
