package ru.kip.sorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SortingTest {

    private Sorting sorting;

    public void setSorting(Sorting sorting) {
        this.sorting = sorting;
    }

    @Test
    void sort_null_notException() {
        sorting.sort(null);
    }

    @Test
    void sort_emptyList_nothingToDo() {
        List<Integer> list = new ArrayList<>();
        sorting.sort(list);
    }

    @Test
    void sort_OK() {
        List<Integer> list = new LinkedList<>(List.of(2, 1, 3));
        sorting.sort(list);
        assertEquals(List.of(1, 2, 3), list);
    }

    @Test
    void sort_manyNumbers_OK() {
        int k = 10000;
        List<Integer> elements = getRandomNumbers(k);

        //сортировка
        elements = sorting.copyAndSort(elements, false);

        //проверка, что каждый следующий элемент <= предыдущему
        for (int i = 1; i < elements.size(); i++) {
            assertTrue(elements.get(i) >= elements.get(i - 1));
        }
    }

    private List<Integer> getRandomNumbers(int k) {
        Random random = new Random();
        return Stream.iterate(1, n -> n + 1)
                .limit(k)
                .map(n -> random.nextInt())
                .collect(Collectors.toList());
    }

    @Test
    void reverse_OK() {
        int k = 10000;
        List<Integer> elements = getRandomNumbers(k);

        //сортировка
        sorting.reverse(elements);

        //проверка, что каждый следующий элемент <= предыдущему
        for (int i = 1; i < elements.size(); i++) {
            assertTrue(elements.get(i) <= elements.get(i - 1));
        }
    }
}
