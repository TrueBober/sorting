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

        do {
            sorted = true;
            for (int i = 1; i < inputList.size(); i++) {
                int compareResult = inputList.get(i - 1).compareTo(inputList.get(i));

                //если элемент с меньшим индексом имеет большее значение, то меняем местами
                if (compareResult > 0) {
                    swap(inputList, i, i-1);
                    sorted = false; //пометить, что список не отсортирован
                }
            }
        } while (!sorted && attempts-- >= 0);

        //кол-во проходов не должно превышать кол-во элементов в списке
        if (attempts < 0) {
            throw new IllegalStateException();
        }
    }

    private void swap(List<T> inputList, int a, int b) {
        T tmp = inputList.get(a);
        inputList.set(a, inputList.get(b));
        inputList.set(b, tmp);
    }
}
