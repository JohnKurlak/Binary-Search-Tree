/**
 * 	Programmer:			John Kurlak
 * 	Project:			BST
 * 	Last Modified:		9.24.2010
 * 	Purpose:			To implement as BST in Java
 */

/**
 * This class represents a Generic Binary Tree that implements insert, find, and
 * delete operations.
 *
 * @author	John Kurlak
 * @date	9.24.2010
 * @param	<T>		Any generic object that implements Comparable
 */
public class BST<T extends Comparable<? super T>>
{
	/**
	 * This class represents a single node in our Binary Tree.
	 *
	 * @author	John Kurlak
	 * @date	9.24.2010
	 */
	public class BinaryNode
	{
		// Local variables
		public T element;			// The data in the node
		BinaryNode left;	// Pointer to the left child
		BinaryNode right;	// Pointer to the right child

		/**
		 * This constructor initializes a childless binary node.
		 *
		 * @param	elem	Any generic object that implements Comparable
		 * @post	A childless binary node is initialized
		 */
		public BinaryNode(T elem)
		{
			element = elem;
			left = null;
			right = null;
		}

		/**
		 * This constructor initializes a binary node with children.
		 *
		 * @param	elem	Any generic object that implements Comparable
		 * @param	lt		The left node to which this node points
		 * @param	rt		The right node to which this node points
		 * @post	A binary node with two children is initialized
		 */
		public BinaryNode(T elem, BinaryNode lt, BinaryNode rt)
		{
			element = elem;
			left = lt;
			right = rt;
		}
	}

	// Local variables
	public BinaryNode root;					// Pointer to root node, if present
	private boolean removalSuccesful;	// Set to true when remove() succeeds

	/**
	 * This constructor initializes an empty BST.  An empty BST contains no
	 * nodes.
	 *
	 * @post	An empty tree is initialized
	 */
	public BST()
	{
		root = null;
	}

	/**
	 * This method determines if the BST is empty.
	 *
	 * @return	Returns true if the BST contains no nodes
	 */
	public boolean isEmpty()
	{
		return (root == null);
	}

	/**
	 * This method attempts to find an element in the BST.
	 *
	 * @param	elem	The element to find
	 * @return	Returns a pointer to the matching data element or null if no
	 * 			matching element exists in the BST
	 */
	public T find(T elem)
	{
		BinaryNode found = find(root, elem);
		return (found == null) ? null : found.element;
	}

	/**
	 * This method attempts to find an element in the BST.
	 *
	 * @param	start	The node from which to start the search
	 * @param	elem	The element to find
	 * @return	Returns a pointer to the matching data element or null if no
	 * 			matching element exists in the BST
	 */
	public BinaryNode find(BinaryNode start, T elem)
	{
		// If the element isn't found, return null
		if (start == null)
		{
			return null;
		}

		// Compare the current node's element to the element we're looking for
		int comparison = start.element.compareTo(elem);

		// If we should look down the left tree
		if (comparison > 0)
		{
			return find(start.left, elem);
		}
		// If we should look down the right tree
		else if (comparison < 0)
		{
			return find(start.right, elem);
		}
		// If we've found it
		else
		{
			return start;
		}
	}

	/**
	 * This method inserts an element into the BST, unless it is already stored.
	 *
	 * @param	elem	The element to insert into the BST
	 * @return	Returns true if the insertion is performed and false otherwise
	 * @post	The element will be inserted into the BST
	 */
	public boolean insert(T elem)
	{
		return insert(root, elem);
	}

	/**
	 * This method inserts an element into the BST, unless it is already stored.
	 *
	 * @param	start	The node from which to start the insertion
	 * @param	elem	The element to insert into the BST
	 * @return	Returns true if the insertion is performed and false otherwise
	 */
	public boolean insert(BinaryNode start, T elem)
	{
		// We've reached the point of insertion
		if (start == null)
		{
			// Insert our element into a new node
			root = new BinaryNode(elem, null, null);
			return true;
		}

		// Compare the current node's element to the element we're looking to
		// delete
		int comparison = start.element.compareTo(elem);

		// If are insertion will happen somewhere down the left tree
		if (comparison > 0)
		{
			// If we've reached the point of insertion
			if (start.left == null)
			{
				// Insert our element into a new node
				start.left = new BinaryNode(elem, null, null);
				return true;
			}

			// Otherwise, keep traversing until we reach the point of insertion
			return insert(start.left, elem);
		}
		// If are insertion will happen somewhere down the right tree
		else if (comparison < 0)
		{
			// If we've reached the point of insertion
			if (start.right == null)
			{
				// Insert our element into a new node
				start.right = new BinaryNode(elem, null, null);
				return true;
			}

			// Otherwise, keep traversing until we reach the point of insertion
			return insert(start.right, elem);
		}
		// If the element is already in the tree
		else
		{
			// Don't insert the element
			return false;
		}
	}

	/**
	 * This method deletes an element from the BST, if it is present.
	 *
	 * @param	elem	The element to delete from the BST
	 * @return	Returns true if the deletion is performed and false otherwise
	 * @post	The element will be deleted from the BST
	 */
	public boolean remove(T elem)
	{
		removalSuccesful = true;
		root = remove(root, elem);
		return removalSuccesful;
	}

	/**
	 * This method deletes an element from the BST, if it is present.
	 *
	 * @param	start	The node from which to start the deletion
	 * @param	elem	The element to delete from the BST
	 * @return	Returns true if the deletion is performed and false otherwise
	 * @post	The element will be deleted from the BST
	 */
	public BinaryNode remove(BinaryNode start, T elem)
	{
		// If the element we want to delete wasn't found
		if (start == null)
		{
			// Go back up the recursive loop, but let our object know that the
			// element we wanted to delete wasn't found
			removalSuccesful = false;
			return null;
		}

		// Compare the current node's element to the element we're looking for
		int comparison = start.element.compareTo(elem);

		// If the deletion will happen somewhere down the left tree
		if (comparison > 0)
		{
			// Attempt to delete down the left tree
			start.left = remove(start.left, elem);
		}
		// If the deletion will happen somewhere down the right tree
		else if (comparison < 0)
		{
			// Attempt to delete down the right tree
			start.right = remove(start.right, elem);
		}
		// If we are at the element we want to delete
		else
		{
			// If the node we want to delete has two children
			if (start.left != null && start.right != null)
			{
				// Back up pointers
				BinaryNode left = start.left;
				BinaryNode right = start.right;

				// Replace the current element with the smallest element in the
				// right subtree
				start = removeMin(start.right, start);

				// Back up pointer
				BinaryNode minRight = start.right;

				// Fix pointers
				start.left = left;
				start.right = right;

				// We need to fix start.right if it points to the node we just
				// moved
				if (start.right.element == start.element)
				{
					start.right = minRight;
				}
			}
			// If the node we want to delete is a leaf
			else if (start.left == null && start.right == null)
			{
				// Delete the current node from the tree
				start = null;
			}
			// If the node we want to delete just has a left child
			else if (start.left != null)
			{
				start = start.left;
			}
			// If the node we want to delete just has a right child
			else
			{
				start = start.right;
			}
		}

		return start;
	}

	/**
	 * This method removes the minimum node that comes after the given starting
	 * node.
	 *
	 * @param	start	The node from which to start the removal process
	 * @param	parent	The parent node of "start"
	 * @return	Returns a BinaryNode with:
	 * 				element:	The element of the removed node
	 * 				left:		null
	 * 				right:		The right subtree of the deleted node
	 */
	public BinaryNode removeMin(BinaryNode start, BinaryNode parent)
	{
		// If there is nothing to traverse or remove
		if (start == null)
		{
			return null;
		}

		// If we've found the minimum node
		if (start.left == null)
		{
			// Save the important values from the node
			//T elem = start.element;
			//BinaryNode deletedRight = start.right;

			// Rewire nodes
			if (parent != root)
			{
				parent.left = start.right; //deletedRight;
			}

			//start = null;

			// Return a new node that follows this method's specifications
			return start;
		}

		// Recurse until we get to the minimum node
		return removeMin(start.left, start);
	}

	/**
	 * This method returns the tree to an empty state.
	 *
	 * @post	The tree will be empty
	 */
	public void clear()
	{
		root = null;
	}

	/**
	 * This method determines the equality of two BSTs.
	 *
	 * @return	Returns true if the given BST has the same physical structure
	 * 			as the current BST
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object other)
	{
		// Make sure a BST was passed in
		if (other instanceof BST)
		{
			// Attempt to determine the equality of the two BSTs
			try
			{
				BST<T> compare = (BST<T>) other;
				return equals(root, compare.root);
			}
			// In case the user passes a BST filled with different kind of
			// elements
			catch (Exception e)
			{
				return false;
			}
		}

		return false;
	}

	/**
	 * This method determines the equality of two BSTs.
	 *
	 * @param	start1		The node on which the comparing will begin
	 * @param	start2		The node against which the comparing will take begin
	 * @return	Returns true if the given BST has the same physical structure
	 * 			as the current BST
	 */
	public boolean equals(BinaryNode start1, BinaryNode start2)
	{
		// If we've reached the end of each tree without any differences
		if (start1 == null && start2 == null)
		{
			// They are the same
			return true;
		}
		// If we've reached the end of one tree but not the other
		else if (start1 == null || start2 == null)
		{
			// They are different
			return false;
		}

		// Determine whether the left subtrees are equivalent
		boolean leftSame = equals(start1.left, start2.left);

		// Determine whether the current elements are equivalent
		boolean currentSame = start1.element.equals(start2.element);

		// Determine whether the right subtrees are equivalent
		boolean rightSame = equals(start1.right, start2.right);

		// Return true if everything is equivalent
		return (leftSame && currentSame && rightSame);
	}

	/**
	 * This method prints the BST.
	 */
	public void print()
	{
		print(root);
	}

	/**
	 * This heper method prints the BST rooted at the given start node.
	 *
	 * @param start	The node from which to root the printing proceedure.
	 */
	public void print(BinaryNode start)
	{
		if (start != null)
		{
			print(start.left);
			System.out.println(start.element);
			print(start.right);
		}
	}
}