package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.AWS;

class AWSTest {

	private static final int FILLER_VALUE = Integer.MIN_VALUE;
	private int[] original={1, 2, 3};
	AWS originalAWS;
	
	@BeforeEach
	void setUp() throws Exception {
		 originalAWS = new AWS(this.original);
	}

	@Test
	void testGetValues() {
		int[] expected = {1,2,3};
		AWS aws = new AWS(expected);
		int[] values = aws.getValues();
		for(int i = 0; i < values.length; i ++)
		{
			assertEquals(values[i], expected[i]);
		}
	}

	@Test
	void testSetValues() {
		int[] dummy = {0};
		int[] expected = {1,2,3};
		AWS aws = new AWS(dummy);
		aws.setValues(expected);
		int[] values = aws.getValues();
		for(int i = 0; i < values.length; i ++)
		{
			assertEquals(values[i], expected[i]);
		}
		
	}

	@Test
	void testToString() {
		int[] x = {1,2,3};
		AWS aws = new AWS(x);
		String result = "AWS [values=[1, 2, 3]]";
		assertEquals(aws.toString(), result);
		//fail("Not yet implemented");
	}

	private void assertThat(String string, String result) {
		// TODO Auto-generated method stub
		
	}

	@Test
	void testAWS() {
		int[] expected = {1, 2, 3};
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		x[1] = 100;
		
		int[] actual = aws.getValues();
		assertEquals(actual[0], expected[0]);
		assertEquals(actual[1], expected[1]);
	}
	
	@Test
	void testRemove() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		
		int value = aws.remove(-1);
		int expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		 value = aws.remove(x.length + 10);
		expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		value = aws.remove(0);
		assertEquals(x[0], value);
		
		int[] r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
		value = aws.remove(2);
		assertEquals(r[2], value);
		
		r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
	}
	
	@Test
	void testFillAndExpand() {
		int position = 1;
		int numberOfTimes = 2;
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
		int first = org[0];
 		
		int expected = originalAWS.getValues().length + numberOfTimes;
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		assertEquals(first, result[0]);
		 
		
	
	}
	@Test
	void testFillAndExpandWithNegative() {
		int position = 1;
		int numberOfTimes = -2;
		
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
 		int first = org[0];
		int expected = originalAWS.getValues().length + Math.abs(numberOfTimes);
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		 
		assertEquals(first, result[0]);
	}
	@Test
	void testRemoveBiggerThan() {
		int[] array = {2, 3, 6, 1, 7, 8};
		AWS aws = new AWS(array);
		int threshold = 1;
		int count = 5;
		int expected = FILLER_VALUE;
		int result = aws.removeBiggerThan(threshold);
		assertEquals(count, result);
		int[] values = aws.getValues();
		for (int i = 0; i <  values.length; i++) {
			if (array[i] > threshold)
			{
			assertEquals(values[i], expected);
			}
			else {
			assertEquals(values[i], array[i]);
			}
		}
		
	}
	
	@Test
	void testStepMultiplier() {
		int[] array = {0, 1, 11, 21, 91, 101};
		int[] expected = {0, 2, 44, 2100, 9100, 101};
		AWS aws = new AWS(array);
		int[] result = aws.stepMultiplier();
		for (int i = 0; i <  result.length; i++) {
			assertEquals(expected[i], result[i]);
		}
		
	}
}

