import java.util.*;

/**
 * An iterator for traversing a tree of {@link TreeNodeLevel4} nodes in level-order (breadth-first).
 * 
 * This iterator uses a queue to visit nodes layer by layer, ensuring all nodes at a given depth are visited before moving to the next level.
 */
public class TreeIteratorLevel4 implements Iterator<TreeNodeLevel4> {
    /**
     * Queue to manage the traversal order of nodes.
     */
    private Queue<TreeNodeLevel4> queue = new LinkedList<>();

    /**
     * It constructs a TreeIteratorLevel4 starting from the given root node.
     *
     * @param root The root node of the tree. If the root is null, the iterator will have no elements.
     */

    public TreeIteratorLevel4(TreeNodeLevel4 root) {
        if (root != null) queue.add(root);
    }

    /**
     * It checks whether there are more nodes to visit in the tree.
     *
     * @return true if there are more nodes remaining; false otherwise.
     */

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /**
     * It returns the next node in the level-order traversal and queues its children for future visits.
     *
     * @return The next {@link TreeNodeLevel4} node in the traversal.
     * @throws NoSuchElementException if there are no more nodes to return.
     */
    @Override
    public TreeNodeLevel4 next() {
        TreeNodeLevel4 current = queue.poll();
        queue.addAll(current.getChildren());
        return current;
    }
}
