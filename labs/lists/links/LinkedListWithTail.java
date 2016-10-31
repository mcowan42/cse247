package lists.links;

import lists.List;

/**
 * Your assignment is to modify this class so it uses a tail reference
 * 
 * @author roncytron and WhoAreYou
 *
 * @param <T>
 */
public class LinkedListWithTail<T> implements List<T> {
	
	private ListNode<T> head;
	private ListNode<T> tail;
	
	public LinkedListWithTail() {
		this.head = null;   // nothing in our list yet
		this.tail = null;
	}

	@Override
	public void addLast(T thing) {
		if (head == null) { // empty list, slide 176
			ListNode<T> p = new ListNode<T>();
			p.value = thing;
			head = p;
			tail = p;
		}
		else {
			ListNode<T> q = new ListNode<T>();
			q.value = thing;
			tail.next = q;
			tail = q;
		}
		
	}

	/**
	 * 
	 * @param n which time, 0 is the first item
	 * @return
	 */
	@Override
	public T getItemAt(int n) {
		ListNode<T> p = head;
		for (int i=0; i < n; ++i) {
			p = p.next;
		}
		return p.value;
	}
	
	public String toString() {
		String ans = "[ ";
		for (ListNode<T> p = head; p != null; p = p.next) {
			ans = ans + p.value + " ";
		}
		ans = ans + "]";
		return ans;
	}
	
	public static void main(String[] args) {
		LinkedListWithTail<String> list = new LinkedListWithTail<String>();
		list.addLast("hello");
		list.addLast("again");
		for (int i=0; i < 10; ++i) {
			list.addLast("String " + i);
			System.out.println(list);
		}
		System.out.println("List is " + list);
		System.out.println("expect again? " + list.getItemAt(1));
	}

}
