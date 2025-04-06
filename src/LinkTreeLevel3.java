public class LinkTreeLevel3 {
    private TreeNodeLevel3 root;

    public LinkTreeLevel3(String rootUrl) {
        root = new TreeNodeLevel3(rootUrl, 0);
    }

    public TreeNodeLevel3 getRoot() {
        return root;
    }
}
