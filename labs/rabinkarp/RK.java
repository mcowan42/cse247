package rabinkarp;

public class RK {

	private final int m;
	private final char[] window;
	private int x=0;
	private int rollingHash;

	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) {
		this.m = m;
		this.window = new char[m];

		this.rollingHash = 0;
	}

	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */
	public int nextCh(char d) {

		// IMPORTANT! 
		// You must write your pseudocode in comments first,
		// then write the code, leaving your pseudocode comments in place.
		// Code without pseudocode comments will receive NO CREDIT!

		// Read the lab write up for what do to here. When you are done you will
		// return the rollingHash.

		// FIXME

		//MY PSEUDOCODE
		//create an array of length m
		//x=0							//initialize the position counter
		//c=array[x]					//identify the char that is about to fall out
		//get new char d
		//array[x]=d					//put new char in array
		//execute the rolling hash function
		//x=x+1 mod m					//increment the position counter, wrap around if necessary
		
		int c;
		c=window[x];
		window[x]=d;
		rollingHash=Math.floorMod((((rollingHash*31)%511)-((exponent(31,m,511))*(c%511))+d%511),511);
		x=(x+1)%m;
		
		return this.rollingHash;
	}

	/**
	 * Compute exponent without overflowing.
	 * Use this instead of Math.pow()
	 * Example: exponent(31, m, 511)
	 */
	private static int exponent(int a, int n, int p) {
		if (n==1) {
			return a;
		} else {
			return (exponent(a, n-1, p) * a) % p;
		}
	}
}
