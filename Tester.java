package avl_trees;

public class Tester {

	public static void main(String[] args) {
		AVL<Integer> tree = new AVL<>();
		Integer[] array = {43, 18, 22, 9, 21, 6, 8, 20, 63, 50, 62, 51};
		for(int i = 0; i < array.length; i++) {
			tree.add(array[i]);
		}
		
		tree.postOrderTransversal();

	}

}
