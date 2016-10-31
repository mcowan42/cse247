package kwaymergesort;

import timing.Ticker;

public class KWayMergeSort {

	/**
	 * kwaymergesort
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length;
		// System.out.println("K " + K + " n " + n);

		// IMPORTANT! 
		// You must write your pseudocode in comments first,
		// then write the code, leaving your pseudocode comments in place.
		// Code without pseudocode comments will receive NO CREDIT!


		// Tip: check for your base case here
	if (n<=1){
		return input;
	}
		// TODO: Breaking your input into K parts...
		Integer[][] parts = new Integer[K][n/K];
		//Creates an array of arrays with K arrays of size n/K
		// You must write code that performs a K-way merge sort.
		//
		// The web page for this assignment provides more detail.
		//
		// Use the ticker as you normally would, to account for
		// the operations taken to perform the K-way merge sort.

		// FIXME
		
		//MY PSEUDOCODE
		//If n>1
			//Create an array [K][n/K]
			//populate the array
			//n=n/K
			//call kwaymergesort 	//recursive step
		//end
		
		int i, j, p;
		p=0;
		for (i=0; i<K; i++){
			for (j=0; j<n/K; j++){
				parts[i][j]=input[p];
				p++;
			}
		}
		n=n/K;
		for (i=0; i<K; i++){
			parts[i]=kwaymergesort(K, parts[i], ticker);
		}


		// Note: The following statement, and the return statement are correct.
		Integer[] ans = merge(parts, ticker)[0];
		return ans;
	}

	/**
	 * merge K arrays
	 * @param parts
	 * @param ticker
	 * @return
	 */
	public static Integer[][] merge(Integer[][] parts, Ticker ticker) {
		// IMPORTANT! 
		// You must write your pseudocode in comments first,
		// then write the code, leaving your pseudocode comments in place.
		// Code without pseudocode comments will receive NO CREDIT!

		// FIXME

		// Tip: Read the lab write-up for merging.
		// You might want to call mergeTwo from here.
		
		//MY PSEUDOCODE
		//Given parts [i][j]
		//Merge each row with the row below it using mergeTwo
		//Produces a new matrix of size [i/2][j*2]
		//repeat until only one row
		
		int i,j;
		j=parts.length;
		while (j>1){
			for (i=0; i<j;){
				parts[i/2]=mergeTwo(parts[i],parts[i+1],ticker);
				i=i+2;
			}
			j=j/2;
		}
		// Note: this return statement is wrong.
		return parts;
	}
	

	/**
	 * merge two arrays
	 * @param one     array one
	 * @param two     array two
	 * @param ticker
	 * @return
	 */
	public static Integer[] mergeTwo(Integer[] one, Integer[] two, Ticker ticker) {
		Integer[] ans = new Integer[one.length + two.length];
		ticker.tick(ans.length);

		// IMPORTANT! 
		// You must write your pseudocode in comments first,
		// then write the code, leaving your pseudocode comments in place.
		// Code without pseudocode comments will receive NO CREDIT!

		// Tip: Read the lab write-up for merging.
		// FIXME
		
		//MY PSEUDOCODE
		//compare the first entry in one and two
		//put the smaller one into ans
		//increment the index of whichever array you grabbed the entry from
		//repeat until empty
		
		int i,j,k;
		i=0;
		j=0;
		for (k=0; k<(one.length+two.length); k++){
			if (i<one.length && j<two.length && one[i]<two[j]){
				ans[k]=one[i];
				i++;
			}else{
				if(j<two.length){
					ans[k]=two[j];
					j++;
				}else if(i<one.length){
					ans[k]=one[i];
					i++;
				}
			}
		}
		
		return ans;
	}

}
