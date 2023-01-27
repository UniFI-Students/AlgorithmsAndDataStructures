import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CircularLinkedListTest {

	private CircularLinkedList<Integer> list;

	@Before
	public void setUp() throws Exception {
		list = new CircularLinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void FindNodeAsStringInEmptyList_ReturnsEmptyString() {
		assertEquals("", list.findNodeAsString(1));
	}
	
	@Test
	public void FindNodeAsStringWithNotExistingKey_ReturnsEmptyString() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		assertEquals("", list.findNodeAsString(3));
	}
	
	@Test
	public void FindNodeAsStringWithEquivalentDistanceToHead_FoundsOneCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		assertEquals("[0, 2]", list.findNodeAsString(2));
	}
	
	@Test
	public void FindNodeAsString_FoundsOneFromNextCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(4, 1);
		list.insertNode(5, 0);
		list.insertNode(4, 2);
		assertEquals("[2, 4]", list.findNodeAsString(4));
	}
	
	@Test
	public void FindNodeAsString_FoundsOneFromPrevCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(4, 1);
		list.insertNode(3, 0);
		list.insertNode(4, 2);
		list.insertNode(5, 0);
		assertEquals("[1, 4]", list.findNodeAsString(4));
	}
	
	@Test
	public void FindNodeAsString_FoundsTwoCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(2, 1);
		assertEquals("[0, 2], [1, 2]", list.findNodeAsString(2));
	}
	
	@Test
	public void InsertNodeWithRepeatedData_ReturnsFalse() {
		list.insertNode(1, 0);
		boolean result = list.insertNode(1, 0);
		assertFalse(result);
	}
	
	@Test
	public void InsertIncorrectKey0_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> list.insertNode(0, null));
	}

	@Test
	public void InsertIncorrectKeyAbove10InPower6_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, 
				() -> list.insertNode(1000001, null));
	}
	
	@Test
	public void InsertNode_InsertsCorrectlyAfterHeadAndBeforeNextNodeOfTheHead() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		Node<Integer> head = list.getFirstNode();
		Node<Integer> nextToHead = list.getLastNode();
		
		list.insertNode(3, 0);
		Node<Integer> createdNode = list.getNodeByNormalizedIndex(2);
		assertSame(head, createdNode.getPrev());
		assertSame(nextToHead, createdNode.getNext());
		
		assertSame(createdNode, head.getNext());
		assertSame(createdNode, nextToHead.getPrev());
	}
	
	@Test
	public void GetNodeByNormalizedIndex0InListOf3Nodes_ThrowsIndexOutOfBoundsException() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);
		
		assertThrows(IndexOutOfBoundsException.class,() -> list.getNodeByNormalizedIndex(0));
	}
	
	@Test
	public void GetNodeByNormalizedIndex1InListOf3Nodes_ReturnsFirstElement() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);
		
		assertSame(list.getFirstNode(), list.getNodeByNormalizedIndex(1));
	}
	
	@Test
	public void GetNodeByNormalizedIndex3InListOf3Nodes_ReturnsFirstElement() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);
		
		assertSame(list.getLastNode(), list.getNodeByNormalizedIndex(3));
	}
	
	@Test
	public void GetNodeByNormalizedIndex5InListOf3Nodes_ReturnsSecondElement() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);
		
		assertSame(list.getFirstNode().getNext(), list.getNodeByNormalizedIndex(5));
	}
	
	@Test
	public void GetFirstNode_ReturnsHead() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);

		Node<Integer> exp = new Node<Integer>(1, 0);
		Node<Integer> act = list.getFirstNode();
		
		assertEquals(exp, act);
	}
	
	@Test
	public void GetLastNode_ReturnsPreviousToHead() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);

		Node<Integer> exp = new Node<Integer>(2, 0);
		Node<Integer> act = list.getLastNode();
		
		assertEquals(exp, act);
	}
	
	@Test
	public void GetFirstNodeInEmptyList_ThrowsNoSuchElementException() {
		assertThrows(NoSuchElementException.class,()->list.getFirstNode());
	}
	
	@Test
	public void GetLastNodeInEmptyList_ThrowsNoSuchElementException() {
		assertThrows(NoSuchElementException.class,()->list.getLastNode());
	}
	
	
	
}
