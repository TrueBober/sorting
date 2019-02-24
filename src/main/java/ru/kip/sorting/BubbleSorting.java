package ru.kip.sorting;

import org.slf4j.Logger;

import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Пузырьковая сортировка.
 *
 * @param <T>
 */
public class BubbleSorting<T extends Comparable> implements Sorting<T> {
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
        if (Objects.isNull(inputList)) {
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
                int compareResult = inputList.get(i - 1).compareTo(inputList.get(i));

                //если элемент с меньшим индексом имеет большее значение (или меньшее для обратной сортировки), то меняем местами
                if (reverse ? compareResult < 0 : compareResult > 0) {
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

    //поменять элементы местами
    private void swap(List<T> inputList, int a, int b) {
        T tmp = inputList.get(a);
        inputList.set(a, inputList.get(b));
        inputList.set(b, tmp);
    }
}
