package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {

    @Test
    public void testBubbleSort() {
        int[] numbers = {2, 20, 56, 1, 32, 17, 0, 102};
        Bubble.sort(numbers);

        int[] expectedNumbers = {0, 1, 2, 17, 20, 32, 56, 102};
        Assert.assertArrayEquals(numbers, expectedNumbers);
    }
}
