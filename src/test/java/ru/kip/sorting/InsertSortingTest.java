package ru.kip.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InsertSortingTest {

    private InsertSorting sort;

    @BeforeEach
    public void setUp() {
        sort = new InsertSorting();
    }

    @Test
    void sort_null_notException() {
        sort.sort(null);
    }

    @Test
    void sort_emptyList_nothingToDo() {
        List<Integer> list = List.of();
        sort.sort(list);
    }

    @Test
    void sort_OK() {
        List<Integer> list = new LinkedList<>(List.of(2, 1, 3));
        sort.sort(list);
        assertEquals(List.of(1, 2, 3), list);
    }

    @Test
    void sort_manyNumbers_OK() {
        int k = 500;
        Random random = new Random();
        List<Integer> elements = new LinkedList<>();

        //заполнение массива случайными числами
        for (int i = 0; i < k; i++, elements.add(random.nextInt())) ;

        //сортировка
        elements = sort.copyAndSort(elements);

        //проверка, что каждый следующий элемент <= предыдущему
        for (int i = 1; i < elements.size(); i++) {
            assertTrue(elements.get(i) >= elements.get(i - 1));
        }
    }

    @Test
    void reverse_OK() {
        int k = 500;
        Random random = new Random();
        List<Integer> elements = new LinkedList<>();

        //заполнение массива случайными числами
        for (int i = 0; i < k; i++, elements.add(random.nextInt())) ;

        //сортировка
        sort.reverse(elements);

        //проверка, что каждый следующий элемент <= предыдущему
        for (int i = 1; i < elements.size(); i++) {
            assertTrue(elements.get(i) <= elements.get(i - 1));
        }
    }
}