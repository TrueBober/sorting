package ru.kip.sorting;

import org.junit.jupiter.api.BeforeEach;

class MergeSortingTest extends SortingTest {
    @BeforeEach
    void setUp() {
        super.setSorting(new MergeSorting());
    }
}
