package kwaymergesort.test;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import kwaymergesort.KWayMergeSort;
import timing.Ticker;

/**
 * 
 * @author roncytron and timhuber
 *
 */
public class TestMergeSort {

	private Random r = new Random();

	@Test
	public void mergeTwoTest1() {
		mergeTwoHelper(new Integer[]{12, 13, 15, 16}, new Integer[]{17, 18, 19, 20});
	}

	@Test
	public void mergeTwoTest2() {
		mergeTwoHelper(new Integer[]{12, 14, 16, 18}, new Integer[]{13, 15, 17, 19});
	}

	@Test
	public void mergeTwoTest3() {
		mergeTwoHelper(new Integer[]{17, 18, 19, 20}, new Integer[]{12, 13, 15, 16});
	}

	@Test
	public void mergeTwoTest4() {
		mergeTwoHelper(new Integer[]{12, 13, 15, 16}, new Integer[]{14, 15, 19, 20});
	}

	@Test
	public void mergeTwoTest5() {
		mergeTwoHelper(new Integer[]{12, 12, 12, 12}, new Integer[]{1, 12});
	}

	@Test
	public void mergeTwoTest6() {
		mergeTwoHelper(new Integer[]{12, 13, 15, 16}, new Integer[]{2, 3, 4, 20});
	}

	private void mergeTwoHelper(Integer[] one, Integer[] two) {
		Ticker ticker = new Ticker();
		Integer[] result = KWayMergeSort.mergeTwo(one, two, ticker);
		assert result.length == one.length + two.length;
		System.out.println("mergeTwo: " + arrayToString(one) + " + " + arrayToString(two) + " = ");
		System.out.println("            " + arrayToString(result) + "\n");

		int previous = Integer.MIN_VALUE;
		for( int i = 0; i < result.length; i++ ) {
			if (previous <= result[i]) {
				// everything is good
			} else {
				fail("At index " + i + " resulting array is not sorted: " + arrayToString(result));
			}
			previous = result[i];
		}
	}

	@Test
	public void sortTest() {
		for (int i=0; i < 20; ++i) {
			sortOnce();
		}
	}


	private void sortOnce() {
		int p = r.nextInt(4); 
		int K = twoToThe(p+2);
		int n = twoToThe((p+2)*(r.nextInt(3)+1));
		System.out.println(""+K+"-way merge sort of " + n + " integers");
		Ticker t = new Ticker();
		Integer[] input = genInts(n);
		Integer[] gold  = Arrays.copyOf(input, input.length);
		Arrays.sort(gold);
		Integer[] ans = KWayMergeSort.kwaymergesort(K, input, t);
		for (int i=0; i < n; ++i) {
			if (!ans[i].equals(gold[i])) {
				fail("At index " + i + " expected " + gold[i] + " but got " + ans[i]);
			}
		}
	}

	private static int twoToThe(int i) {
		if (i==0) return 1;
		else return 2 * twoToThe(i-1);
	}

	private  Integer[] genInts(int n) {
		Integer[] ans = new Integer[n];
		for (int i=0; i < n; ++i) {
			ans[i] = r.nextInt();
		}
		return ans;
	}

	private String arrayToString(Integer[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < array.length; i++ ) {
			sb.append(array[i]);
			if (i < array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
