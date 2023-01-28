
//Daniil Radchanka 7079901
//Anastasia Moskalenko 7015595
public class Node<T> {
	private T value;
	private int key;
	private Node<T> next;
	private Node<T> prev;

	public Node(int key, T value) {
		this(key, value, null, null);
	}

	public Node(int key, T value, Node<T> prev, Node<T> next) {
		this.key = key;
		this.value = value;
		this.next = next;
		this.prev = prev;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	public T getValue() {
		return value;
	}

	public int getKey() {
		return key;
	}

	public Node<T> getNext() {
		return next;
	}

	public Node<T> getPrev() {
		return prev;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Node<?>))
			return false;
		Node<?> other = (Node<?>) obj;

		return Integer.compare(this.key, other.key) == 0 && this.value.equals(other.value);
	}

	@Override
	public String toString() {
		return "[" + value + ", " + key + "]";
	}
}
