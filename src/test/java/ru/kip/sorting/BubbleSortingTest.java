package ru.kip.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortingTest {

    private BubbleSorting bubbleSorting;

    @BeforeEach
    public void setUp() {
        bubbleSorting = new BubbleSorting();
    }

    @Test
    void sort_null_notException() {
        bubbleSorting.sort(null);
    }

    @Test
    void sort_emptyList_nothingToDo() {
        List<Integer> list = List.of();
        bubbleSorting.sort(list);
    }

    @Test
    void sort_OK() {
        List<Integer> list = new LinkedList<>(List.of(2, 1, 3));
        bubbleSorting.sort(list);
        assertEquals(List.of(1, 2, 3), list);
    }

    @RepeatedTest(10)
    @Test
    void sort_manyNumbers_OK() {
        int k = 1000;
        Random random = new Random();
        List<Integer> elements = new LinkedList<>();

        //заполнение массива случайными числами
        for (int i = 0; i < k; i++, elements.add(random.nextInt())) ;

        //сортировка
        bubbleSorting.sort(elements);

        //проверка, что каждый следующий элемент <= предыдущему
        for (int i = 1; i < elements.size(); i++) {
            assertTrue(elements.get(i) >= elements.get(i - 1));
        }
    }

    @Test
    void sort_externalIntervention() {
        int k = 500;
        Random random = new Random();
        List<Integer> elements = new LinkedList<>();

        //заполнение массива случайными числами
        for (int i = 0; i < k; i++, elements.add(random.nextInt())) ;

        //постоянное перемешивание элементов массива в другом потоке
        ScheduledFuture future = Executors.newScheduledThreadPool(5)
                .scheduleWithFixedDelay(() -> Collections.shuffle(elements), 0L, 1L, TimeUnit.MILLISECONDS);

        try {
            //сортировка c ожиданием исключения
            assertThrows(IllegalStateException.class, () -> bubbleSorting.sort(elements));
        } finally {
            future.cancel(true);
        }
    }
}
