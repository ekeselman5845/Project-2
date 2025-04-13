import java.util.ArrayList;
import java.util.List;

/**
 * TreeNodeLevel5 represents a node in the webpage link tree,
 * extended for Level 5 with depth tracking for DOT graph output.
 * Each node contains information about a URL, its title, and its depth in the tree.
 * It also stores a list of child nodes, allowing for the creation of a hierarchical structure.
 */
public class TreeNodeLevel5 {
    private String url;
    private String title;
    private int depth;
    private List<TreeNodeLevel5> children;

    /**
     * Constructs a TreeNodeLevel5 with the specified URL, title, and depth.
     *
     * @param url   the URL associated with this node
     * @param title the title of the webpage
     * @param depth the depth level of this node in the tree
     */
    public TreeNodeLevel5(String url, String title, int depth) {
        this.url = url;
        this.title = title;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    /**
     * Adds a child node to this tree node.
     *
     * @param child the child TreeNodeLevel5 to be added
     */
    public void addChild(TreeNodeLevel5 child) {
        children.add(child);
    }

    /**
     * Returns the list of child nodes for this tree node.
     *
     * @return a List of TreeNodeLevel5 representing the children
     */
    public List<TreeNodeLevel5> getChildren() {
        return children;
    }

     /**
     * Returns the URL associated with this tree node.
     *
     * @return the URL of this tree node
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the title of the webpage for this tree node.
     *
     * @return the title of the webpage
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the depth level of this tree node in the tree structure.
     *
     * @return the depth of this node
     */
    public int getDepth() {
        return depth;
    }
}
// to generate javadoc
//javadoc -d javadoc -cp ".;..\libs\jsoup-1.19.1.jar" TreeNodeLevel5.java