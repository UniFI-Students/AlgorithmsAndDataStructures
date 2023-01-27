import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> {
	private Node<T> head;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	
	public Node<T> getFirstNode() 
			throws NoSuchElementException{
		if (head == null) throw new NoSuchElementException();
		return head;
	}
	
	public Node<T> getLastNode() 
			throws NoSuchElementException {
		if (head == null) throw new NoSuchElementException();
		return head.getPrev();
	}
	
	public Node<T> getNodeByNormalizedIndex(int index)
			throws NoSuchElementException, IndexOutOfBoundsException{
		return getNode(index - 1);
	}
	
	public boolean deleteNodeByKey(int key) {
		List<Node<T>> foundNodes = findNodesByKey(key);
		if (foundNodes == null) return false;
		
		for(Node<T> node:foundNodes) {
			deleteNode(node);
		}
		return true;
	}
	
	public boolean insertNode(int key, T value) 
			throws IllegalArgumentException {
		if (key < 1 || 
			key > 1000000) 
			throw new IllegalArgumentException("Key must be in range from 1 to 10^6(inclusive)");
		Node<T> newNode = new Node<T>(key, value);
		
		if (head != null) {
			Node<T> curr = head;
			
			do {
				if (curr.equals(newNode)) {
					return false;
				}
				curr = curr.getNext();
			} while(curr!=head);
		}
		
		insertNodeAfter(newNode, 0);
		return true;
	}
	
	@Override
	public String toString() {
		Node<T> curr = head;
		StringBuilder str = new StringBuilder();
		while(curr.getNext() != head) {
			str.append(curr);
			str.append(", ");
		}
		str.append(curr);
		return str.toString();
	}
	
	public String toSortedByKeyString() {
		// TODO: Implement this method
		throw new UnsupportedOperationException();
	}
		
	public String findNodeAsString(int key) {
		List<Node<T>> foundNodes = findNodesByKey(key);
		if (foundNodes == null) return "";
			
		StringBuilder str = new StringBuilder();
		for(Node<T> node:foundNodes) {
			str.append(node);
			str.append(", ");
		}
		return str.substring(0, str.length() - 2);
		
	}
	
	private List<Node<T>> findNodesByKey(int key) {
		if (head == null) return null;
		Node<T> prevNearestNode = head;
		Node<T> nextNearestNode = head;
		int prevNearestNodePathLength = 0;
		int nextNearestNodePathLength = 0;
		
		while (prevNearestNodePathLength < count &&
			   prevNearestNode.getKey() != key) {
			prevNearestNode = prevNearestNode.getPrev();
			++prevNearestNodePathLength;
		}
		if (prevNearestNodePathLength == count) return null;
		
		while (nextNearestNodePathLength < count - prevNearestNodePathLength &&
				nextNearestNode.getKey() != key) {
			nextNearestNode = nextNearestNode.getNext();
			++nextNearestNodePathLength;
		}
		if (prevNearestNodePathLength == count - nextNearestNodePathLength) return Arrays.asList(prevNearestNode);
		if (prevNearestNodePathLength == nextNearestNodePathLength) return Arrays.asList(prevNearestNode, nextNearestNode);
		if (prevNearestNodePathLength < nextNearestNodePathLength) return Arrays.asList(prevNearestNode);
		else return Arrays.asList(nextNearestNode);
	}
	
	private void insertNodeAfter(Node<T> node, int index) {
		try {
			Node<T> nodeInIndex = getNode(index);
			node.setPrev(nodeInIndex);
			node.setNext(nodeInIndex.getNext());
			
			nodeInIndex.getNext().setPrev(node);
			nodeInIndex.setNext(node);
		}
		catch(IndexOutOfBoundsException exc) {
			head = node;
			head.setPrev(head);
			head.setNext(head);
		}
		finally {
			++count;
		}
	}
	
	private void deleteNode(Node<T> node) {
		--count;
		if (count == 0) {
			head = null;
			return;
		}
		
		Node<T> prevNode = node.getPrev();
		Node<T> nextNode = node.getNext();
		
		nextNode.setPrev(prevNode);
		prevNode.setNext(nextNode);
		
		if (node == head) 
			head = head.getNext();
	}
	
	private Node<T> getNode(int index) 
			throws IndexOutOfBoundsException {
		if (index < 0 || count == 0) throw new IndexOutOfBoundsException();
		index %= count;
		Node<T> curr = head;
		if (index <= count / 2) 
			while (index-- > 0) curr = curr.getNext();
		else {
			index = count - index;
			while (index-- > 0) curr = curr.getPrev();
		}
		return curr;
	}
}
