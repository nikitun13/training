package by.training.branching.service.impl;

import by.training.branching.service.ServiceException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class DivisorServiceImplTest {

    private DivisorServiceImpl service;

    @DataProvider(name = "positiveDataForFindDivisibleNumbers")
    public static Object[][] createPositiveDataForFindDivisibleNumbers() {
        return new Object[][]{
                {new Object[]{3, List.of(3, 6, 9)}, List.of(3, 6, 9)},
                {new Object[]{1, List.of(3, 6, 9)}, List.of(3, 6, 9)},
                {new Object[]{5, List.of(3, 6, 9)}, Collections.emptyList()},
                {new Object[]{2, List.of(-2, 0, 2, 5, 6, -9)}, List.of(-2, 0, 2, 6)},
                {new Object[]{-2, List.of(-2, 0, 2, 5, 6, -9)}, List.of(-2, 0, 2, 6)},
                {new Object[]{-2, List.of(-1, -2, -3)}, List.of(-2)},
                {new Object[]{2, List.of(-2, -4, 0, 2, 18)}, List.of(-2, -4, 0, 2, 18)},
                {new Object[]{2, List.of(-21, -5, 5, 3, 17)}, Collections.emptyList()},
                {new Object[]{1, Collections.emptyList()}, Collections.emptyList()},
                {new Object[]{-1, Collections.emptyList()}, Collections.emptyList()},
                {new Object[]{5, Collections.emptyList()}, Collections.emptyList()},
                {new Object[]{1, List.of(-21, -5, 5, 3, 17)}, List.of(-21, -5, 5, 3, 17)},
                {new Object[]{-1, List.of(-21, -5, 5, 3, 17)}, List.of(-21, -5, 5, 3, 17)},
                {new Object[]{-5, List.of(-15, -5, -5, -3, -17)}, List.of(-15, -5, -5)},
                {new Object[]{5, List.of(-15, -5, -5, -3, -17)}, List.of(-15, -5, -5)},
                {new Object[]{1, List.of(1, 1, 1, 1, -1, 1, 1, 1)}, List.of(1, 1, 1, 1, -1, 1, 1, 1)}
        };
    }

    @DataProvider(name = "negativeDataForFindDivisibleNumbers")
    public static Object[][] createNegativeDataForFindDivisibleNumbers() {
        return new Object[][]{
                {0, List.of(3, 6, 9)},
                {0, List.of(3, 6, 9)},
                {0, List.of(3, 6, 9)},
                {0, List.of(-2, 0, 2, 5, 6, -9)},
                {0, List.of(-2, 0, 2, 5, 6, -9)},
                {0, List.of(-1, -2, -3)},
                {0, List.of(-2, -4, 0, 2, 18)},
                {0, List.of(-21, -5, 5, 3, 17)},
                {0, List.of(-21, -5, 5, 3, 17)},
                {0, List.of(-21, -5, 5, 3, 17)},
                {0, List.of(-15, -5, -5, -3, -17)},
                {0, List.of(1, 1, 1, 1, -1, 1, 1, 1)},
                {0, Collections.emptyList()},
        };
    }

    @DataProvider(name = "nullDataInsideTheListForFindDivisibleNumbers")
    public static Object[][] createNullDataInsideTheListForFindDivisibleNumbers() {
        return new Object[][]{
                {3, new Integer[]{3, null, 9}},
                {3, new Integer[]{null}},
                {5, new Integer[]{null, null, null}},
                {-1, new Integer[]{3, 6, null}},
                {32, new Integer[]{null, 0, 2, 5, 6, null}},
                {18, new Integer[]{-2, null, 2, 5, null, -9}},
                {-123, new Integer[]{-1, null, null}},
                {-4, new Integer[]{-2, -4, null, 2, 18}},
                {-5, new Integer[]{-21, -5, 5, null, 17}},
                {13, new Integer[]{-21, -5, 5, 3, null}},
                {-45, new Integer[]{-21, null, 5, null, 17}}
        };
    }

    @DataProvider(name = "positiveDataForFindAllNumbersDivisibleByTheirDigits")
    public static Object[][] createPositiveDataForFindAllNumbersDivisibleByTheirDigits() {
        return new Object[][]{
                {1, List.of(1)},
                {2, List.of(1, 2)},
                {5, List.of(1, 2, 3, 4, 5)},
                {9, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)},
                {10, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)},
                {11, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11)},
                {12, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12)},
                {13, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12)},
                {14, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12)},
                {15, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15)},
                {20, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15)},
                {25, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22, 24)},
                {30, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22, 24)},
                {40, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22, 24, 33, 36)},
                {50, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22, 24, 33, 36, 44, 48)}
        };
    }

    @DataProvider(name = "negativeDataForFindAllNumbersDivisibleByTheirDigits")
    public static Object[][] createNegativeDataForFindAllNumbersDivisibleByTheirDigits() {
        return new Object[][]{
                {0, null},
                {-1, null},
                {-15, null},
                {-50, null}
        };
    }

    @BeforeClass
    public void setUp() {
        service = new DivisorServiceImpl();
    }

    @Test(description = "test positive scenario for findDivisibleNumbers method",
            dataProvider = "positiveDataForFindDivisibleNumbers")
    @SuppressWarnings("unchecked")
    public void testPositiveScenarioFindDivisibleNumbers(Object[] input, List<Integer> expected) {
        int divisor = (int) input[0];
        List<Integer> numbers = (List<Integer>) input[1];
        List<Integer> actual = service.findDivisibleNumbers(divisor, numbers);
        assertEquals(actual, expected,
                String.format("of %s numbers, only %s are divisible by %d.",
                        numbers, expected, divisor
                )
        );
    }

    @Test(description = "test negative scenario for findDivisibleNumbers method",
            dataProvider = "negativeDataForFindDivisibleNumbers",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioFindDivisibleNumbers(int divisor, List<Integer> numbers) {
        service.findDivisibleNumbers(divisor, numbers);
        fail(String.format("Must throw %s for %d divisor and %s numbers on input.",
                        ServiceException.class.getName(), divisor, numbers
                )
        );
    }

    @Test(description = "test list is null scenario for findDivisibleNumbers method",
            expectedExceptions = NullPointerException.class)
    public void testListIsNullScenarioFindDivisibleNumbers() {
        service.findDivisibleNumbers(1, null);
        fail(String.format("Must throw %s for null list on input.",
                        NullPointerException.class.getName()
                )
        );
    }

    @Test(description = "test null inside the list scenario for findDivisibleNumbers method",
            dataProvider = "nullDataInsideTheListForFindDivisibleNumbers",
            expectedExceptions = NullPointerException.class)
    public void testNullInsideTheListScenarioFindDivisibleNumbers(int divisor, Integer[] array) {
        List<Integer> numbers = Arrays.asList(array);
        service.findDivisibleNumbers(divisor, numbers);
        fail(String.format("Must throw %s for null inside the list %s.",
                        NullPointerException.class.getName(), numbers
                )
        );
    }

    @Test(description = "test positive scenario for findAllNumbersDivisibleByTheirDigits method",
            dataProvider = "positiveDataForFindAllNumbersDivisibleByTheirDigits")
    public void testPositiveScenarioFindAllNumbersDivisibleByTheirDigits(int bound, List<Integer> expected) {
        List<Integer> actual = service.findAllNumbersDivisibleByTheirDigits(bound);
        assertEquals(actual, expected,
                String.format("must be %s for bound %d", expected, bound)
        );
    }

    @Test(description = "test negative scenario for findAllNumbersDivisibleByTheirDigits method",
            dataProvider = "negativeDataForFindAllNumbersDivisibleByTheirDigits",
            expectedExceptions = ServiceException.class)
    public void testNegativeScenarioFindAllNumbersDivisibleByTheirDigits(int bound, Object stub) {
        service.findAllNumbersDivisibleByTheirDigits(bound);
        fail(String.format("Must throw %s for bound %d.",
                        ServiceException.class.getName(), bound
                )
        );
    }

    @AfterClass
    public void tearDown() {
        service = null;
    }
}
