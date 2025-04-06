import java.util.*;

public class LinkTreeLevel4 {
    private TreeNodeLevel4 root;

    public LinkTreeLevel4(TreeNodeLevel4 root) {
        this.root = root;
    }

    public TreeNodeLevel4 getRoot() {
        return root;
    }

    public TreeIteratorLevel4 getIterator() {
        return new TreeIteratorLevel4(root);
    }
}
