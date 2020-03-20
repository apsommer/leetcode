/*
Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

Note:

1 <= preorder.length <= 100
The values of preorder are distinct.

*/

// each node in BST has a value, left child, and right child
public class TreeNode {

    int val;

    TreeNode(int x) {
        val = x;
    }

    TreeNode left;
    TreeNode right;
}

class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {

        // establish root node as the first element in passed array
        TreeNode root = new TreeNode(preorder[0]);

        // loop through the remaining elements
        for (int i = 1; i < preorder.length; i++) {

            // get current value and instantiate a new node
            int val = preorder[i];
            TreeNode node = new TreeNode(val);

            // start search at the root
            TreeNode parent = root;

            while (true) {

                // move left
                if (node.val < parent.val) {

                    // place node
                    if (parent.left == null) {

                        parent.left = node;
                        parent = node;
                        break;

                    // traverse again
                    } else {

                        parent = parent.left;
                        continue;
                    }

                // move right
                } else {

                    // place node
                    if (parent.right == null) {

                        parent.right = node;
                        parent = node;
                        break;

                    // traverse again
                    } else {

                        parent = parent.right;
                        continue;
                    }
                }
            }
        }

        return root;
    }
}
