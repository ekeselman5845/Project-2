import java.util.*;

public class TreeIteratorLevel4 implements Iterator<TreeNodeLevel4> {
    private Queue<TreeNodeLevel4> queue = new LinkedList<>();

    public TreeIteratorLevel4(TreeNodeLevel4 root) {
        if (root != null) queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public TreeNodeLevel4 next() {
        TreeNodeLevel4 current = queue.poll();
        queue.addAll(current.getChildren());
        return current;
    }
}
