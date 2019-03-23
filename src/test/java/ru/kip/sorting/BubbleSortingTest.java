package ru.kip.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BubbleSortingTest extends SortingTest {
    @BeforeEach
    void setUp() {
        super.setSorting(new BubbleSorting());
    }
}
