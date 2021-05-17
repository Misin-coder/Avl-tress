package avl_trees;


public class AVL<T> {

	private class Node<T> {
		T data;
		Node<T> left, right, parent;

		public Node(T data) {
			this.data = data;
			left = right = null;
		}
	}

	Node<T> root;
	int currentSize;

	public AVL() {

		root = null;
		currentSize = 0;
	}

	public void add(T obj) {
		Node<T> newNode = new Node<T>(obj);

		if (root == null) {
			root = newNode;
			currentSize++;
			return;
		}

		add(root, newNode);
		checkBalance(newNode);

	}

	private void checkBalance(Node<T> newNode) {

		if (height(newNode.left) - height(newNode.right) > 1 || height(newNode.left) - height(newNode.right) < -1) {

			rebalance(newNode);
		}
		if (newNode.parent == null) {
			return;
		}
		checkBalance(newNode.parent);

	}

	private void rebalance(Node<T> newNode) {
		if (height(newNode.left) - height(newNode.right) > 1) {
			// left heavy
			if (height(newNode.left.left) > height(newNode.left.right)) {
				newNode = rightRotate(newNode);
			} else {
				newNode = leftRightRotate(newNode);
			}
		} else {
			if (height(newNode.right.right) > height(newNode.right.left)) {
				newNode = leftRotate(newNode);
			} else {
				newNode = rightLeftRotate(newNode);
			}
		}
		if (newNode.parent == null) {
			root = newNode;
		}
	}

	private Node<T> rightLeftRotate(Node<T> node) {

		node.right = rightRotate(node.right);
		return leftRotate(node);
	}

	private Node<T> rightRotate(Node<T> node) {
		Node<T> temp = node.left ;
		node.left = temp.right;
		if (temp.right != null) {
			temp.right.parent = node;
		}
		temp.parent = node.parent;
		// setting the parent of node it can be null, left parent or right parent

		if (node.parent == null) {
			this.root = temp;
		} else if (node == node.parent.left) {
			node.parent.left = temp;
		} else {
			node.parent.right = temp;
		}
		temp.right = node;
		node.parent = temp;

		return temp;
	}

	private Node<T> leftRightRotate(Node<T> node) {

		node.left = leftRotate(node.left); // you donot need node.left bcoz parent thing is set by us in left method
		return rightRotate(node);
		
	}

	private Node<T> leftRotate(Node<T> node) {

		Node<T> temp = node.right;
		node.right = temp.left;
		if (temp.left != null) {
			temp.left.parent = node;
		}
		temp.parent = node.parent;

		// setting the parent of node it can be null, left parent or right parent

		if (node.parent == null) {
			this.root = temp;
		} else if (node == node.parent.left) {
			node.parent.left = temp;
		} else {
			node.parent.right = temp;
		}

		temp.left = node;
		node.parent = temp;
		return temp;

	}

	private int height(Node<T> node) {

		if (node == null) {
			return -1;
		}

		int leftHeight = height(node.left);
		int rightHeight = height(node.right);

		return Math.max(leftHeight, rightHeight) + 1;

	}

	private void add(Node<T> current, Node<T> newNode) {

		if (((Comparable<T>) newNode.data).compareTo(current.data) < 0) {
			if (current.left == null) {
				current.left = newNode;
				newNode.parent = current;
				currentSize++;
				return;
			}
			add(current.left, newNode);
		}

		else {
			if (current.right == null) {
				current.right = newNode;
				newNode.parent = current;
				currentSize++;
				return;
			}
			add(current.right, newNode);
		}
	}
	public void postOrderTransversal() {
		postOrder(root);
	}

	private void postOrder(Node<T> node) {
		if(node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + "	");
	}

}
