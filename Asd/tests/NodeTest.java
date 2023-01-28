import static org.junit.Assert.*;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testEqualsObjectWithNull_ReturnsFalse() {
		Node<Integer> testNode = new Node<Integer>(1, 0);
		boolean act = testNode.equals(null);
		boolean exp = false;

		assertEquals(exp, act);
	}

	@Test
	public void testEqualsObjectWithSameReference_ReturnsTrue() {
		Node<Integer> testNode = new Node<Integer>(1, 0);
		boolean act = testNode.equals(testNode);
		boolean exp = true;

		assertEquals(exp, act);
	}

	@Test
	public void testEqualsObjectWithSameValueKey_ReturnsTrue() {
		Node<Integer> testNodeA = new Node<Integer>(1, 0);
		Node<Integer> testNodeB = new Node<Integer>(1, 0);
		boolean act = testNodeA.equals(testNodeB);
		boolean exp = true;

		assertEquals(exp, act);
	}

	@Test
	public void testEqualsObjectWithSameValueDifferentKey_ReturnsFalse() {
		Node<Integer> testNodeA = new Node<Integer>(1, 0);
		Node<Integer> testNodeB = new Node<Integer>(2, 0);
		boolean act = testNodeA.equals(testNodeB);
		boolean exp = false;

		assertEquals(exp, act);
	}

	@Test
	public void testToString_ReturnsCorrectlyRepresentationString() {
		Node<Integer> act = new Node<Integer>(0, 1);
		String exp = "[1, 0]";

		assertEquals(exp, act.toString());
	}

}
