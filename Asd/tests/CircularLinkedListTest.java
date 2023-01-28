
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CircularLinkedListTest {

	private CircularLinkedList<Integer> list;

	@Before
	public void setUp() throws Exception {
		list = new CircularLinkedList<Integer>(new MergeSortingManager());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findNodeAsStringInEmptyList_ReturnsEmptyString() {
		assertEquals("", list.findNodeAsString(1));
	}

	@Test
	public void findNodeAsStringWithIncorrectKey_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> list.findNodeAsString(0));
		assertThrows(IllegalArgumentException.class, () -> list.findNodeAsString(1000001));
	}

	@Test
	public void findNodeAsStringWithNotExistingKey_ReturnsEmptyString() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		assertEquals("", list.findNodeAsString(3));
	}

	@Test
	public void findNodeAsStringWithEquivalentDistanceToHead_FoundsOneCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		assertEquals("[0, 2]", list.findNodeAsString(2));
	}

	@Test
	public void findNodeAsString_FoundsOneFromNextCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(4, 1);
		list.insertNode(5, 0);
		list.insertNode(4, 2);
		assertEquals("[2, 4]", list.findNodeAsString(4));
	}

	@Test
	public void findNodeAsString_FoundsOneFromPrevCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(4, 1);
		list.insertNode(3, 0);
		list.insertNode(4, 2);
		list.insertNode(5, 0);
		assertEquals("[1, 4]", list.findNodeAsString(4));
	}

	@Test
	public void findNodeAsString_FoundsTwoCorrectly() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(2, 1);
		assertEquals("[0, 2], [1, 2]", list.findNodeAsString(2));
	}

	@Test
	public void insertNodeWithAlreadyExistingData_ReturnsFalse() {
		list.insertNode(1, 0);
		boolean result = list.insertNode(1, 0);
		assertFalse(result);
	}

	@Test
	public void insertIncorrectKey0_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> list.insertNode(0, null));
	}

	@Test
	public void insertIncorrectKeyAbove10InPower6_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> list.insertNode(1000001, null));
	}

	@Test
	public void insertNode_InsertsCorrectlyAfterHeadAndBeforeNextNodeOfTheHead() {
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
	public void getNodeByNormalizedIndexInEmptyList_ThrowsIndexOutOfBoundsException() {
		assertThrows(IndexOutOfBoundsException.class, () -> list.getNodeByNormalizedIndex(1));
	}

	@Test
	public void getNodeByNormalizedIndexWithIncorrectIndex_ThrowsIndexOutOfBoundsException() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);

		assertThrows(IndexOutOfBoundsException.class, () -> list.getNodeByNormalizedIndex(0));
	}

	@Test
	public void getNodeByNormalizedIndex1InListOf3Nodes_ReturnsFirstElement() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);

		assertSame(list.getFirstNode(), list.getNodeByNormalizedIndex(1));
	}

	@Test
	public void getNodeByNormalizedIndex3InListOf3Nodes_ReturnsFirstElement() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);

		assertSame(list.getLastNode(), list.getNodeByNormalizedIndex(3));
	}

	@Test
	public void getNodeByNormalizedIndex5InListOf3Nodes_ReturnsSecondElement() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);

		assertSame(list.getFirstNode().getNext(), list.getNodeByNormalizedIndex(5));
	}

	@Test
	public void getFirstNode_ReturnsHead() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);

		Node<Integer> exp = new Node<Integer>(1, 0);
		Node<Integer> act = list.getFirstNode();

		assertEquals(exp, act);
	}

	@Test
	public void getLastNode_ReturnsPreviousToHead() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(3, 0);

		Node<Integer> exp = new Node<Integer>(2, 0);
		Node<Integer> act = list.getLastNode();

		assertEquals(exp, act);
	}

	@Test
	public void getFirstNodeInEmptyList_ThrowsNoSuchElementException() {
		assertThrows(NoSuchElementException.class, () -> list.getFirstNode());
	}

	@Test
	public void getLastNodeInEmptyList_ThrowsNoSuchElementException() {
		assertThrows(NoSuchElementException.class, () -> list.getLastNode());
	}

	@Test
	public void getCountInEmptyList_Returns0() {
		assertEquals(0, list.getCount());
	}

	@Test
	public void getCountAfterInsert_Returns1() {
		list.insertNode(1, 0);
		assertEquals(1, list.getCount());
	}

	@Test
	public void getCountAfterDeleteInListOf2Nodes_Returns1() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.deleteNodeByKey(1);
		assertEquals(1, list.getCount());
	}

	@Test
	public void getCountAfterDeleteInListOf2Nodes_Returns2() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.deleteNodeByKey(4);
		assertEquals(2, list.getCount());
	}

	@Test
	public void deleteNodeByKeyWithIncorrectKey_ThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> list.deleteNodeByKey(0));
		assertThrows(IllegalArgumentException.class, () -> list.deleteNodeByKey(1000001));
	}

	@Test
	public void deleteNodeByKeyInEmptyList_ReturnsFalse() {
		assertFalse(list.deleteNodeByKey(1));
	}

	@Test
	public void deleteNodeByKeyWithNotExistingOne_ReturnsFalse() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		assertFalse(list.deleteNodeByKey(3));
	}

	@Test
	public void deleteNodeByKeyInListOf1Nodes_ReturnsTrueAndDeletesCorrectly() {
		list.insertNode(2, 0);
		assertTrue(list.deleteNodeByKey(2));
		assertThrows(NoSuchElementException.class, () -> list.getFirstNode());
	}

	@Test
	public void deleteNodeByKey_ReturnsTrueAndDeletesCorrectlyOnly1NodeFromPrev() {
		list.insertNode(2, 0);
		list.insertNode(3, 1);
		list.insertNode(3, 2);
		list.insertNode(4, 0);
		Node<Integer> exp = list.getLastNode();
		assertTrue(list.deleteNodeByKey(3));
		assertNotEquals(exp, list.getLastNode());
	}

	@Test
	public void deleteNodeByKey_ReturnsTrueAndDeletesCorrectlyOnly1NodeFromNext() {
		list.insertNode(2, 0);
		list.insertNode(3, 0);
		list.insertNode(5, 2);
		list.insertNode(5, 1);
		Node<Integer> exp = list.getFirstNode().getNext();
		assertTrue(list.deleteNodeByKey(5));
		assertNotEquals(exp, list.getFirstNode().getNext());
	}

	@Test
	public void deleteNodeByKey_ReturnsTrueAndDeletesCorrectlyOnly2NodeWithEquivalentDistanceToHead() {
		list.insertNode(2, 0);
		list.insertNode(4, 1);
		list.insertNode(4, 2);
		Node<Integer> expPrev = list.getFirstNode().getPrev();
		Node<Integer> expNext = list.getFirstNode().getNext();
		assertTrue(list.deleteNodeByKey(4));
		assertNotEquals(expPrev, list.getFirstNode().getPrev());
		assertNotEquals(expNext, list.getFirstNode().getNext());
	}

	@Test
	public void toString_ReturnsCorrectRepresentationString() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(4, 1);
		list.insertNode(2, 3);
		list.insertNode(4, 2);
		String exp = "[0, 1], [2, 4], [3, 2], [1, 4], [0, 2]";
		String act = list.toString();

		assertEquals(exp, act);
	}

	@Test
	public void toSortedString_ReturnsCorrectRepresentationSortedString() {
		list.insertNode(1, 0);
		list.insertNode(2, 0);
		list.insertNode(4, 1);
		list.insertNode(2, 3);
		list.insertNode(4, 2);

		String exp = "[0, 1], [3, 2], [0, 2], [2, 4], [1, 4]";
		String act = list.toSortedByKeyString();

		assertEquals(exp, act);
	}

}
