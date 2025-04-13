import java.util.ArrayList;
import java.util.List;

/**
 * Represents a node in a tree structure used to model a web page and its linked children.
 * Each node stores the page's URL, title, and references to child nodes.
 * 
 * This class is designed for Level 4, where a tree of linked web pages is built and traversed.
 */
public class TreeNodeLevel4 {
    /**
     * The URL of the web page represented by this node.
     */
    String url;

    /**
     * The title of the web page represented by this node.
     */
    String title;

    /**
     * A list of child nodes representing pages linked from this web page.
     */
    List<TreeNodeLevel4> children;

    /**
     * Constructs a TreeNodeLevel4 with the given URL and title.
     *
     * @param url   The URL of the web page.
     * @param title The title of the web page.
     */
    public TreeNodeLevel4(String url, String title) {
        this.url = url;
        this.title = title;
        this.children = new ArrayList<>();
    }

    /**
     * Adds a child node to this node's list of children.
     *
     * @param child The child TreeNodeLevel4 to add.
     */
    public void addChild(TreeNodeLevel4 child) {
        children.add(child);
    }

    /**
     * Returns the list of child nodes linked from this web page.
     *
     * @return A List of TreeNodeLevel4 objects representing the child nodes.
     */
    public List<TreeNodeLevel4> getChildren() {
        return children;
    }


    /**
     * Returns the URL of this web page.
     *
     * @return The URL as a String.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the title of this web page.
     *
     * @return The title as a String.
     */
    public String getTitle() {
        return title;
    }
}
