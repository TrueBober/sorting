package ru.kip.sorting;

import org.junit.jupiter.api.BeforeEach;

class InsertSortingTest extends SortingTest {
    @BeforeEach
    void setUp() {
        super.setSorting(new InsertSorting());
    }
}
