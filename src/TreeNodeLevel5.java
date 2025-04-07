import java.util.ArrayList;
import java.util.List;

/**
 * TreeNodeLevel5 represents a node in the webpage link tree,
 * extended for Level 5 with depth tracking for DOT graph output.
 */
public class TreeNodeLevel5 {
    private String url;
    private String title;
    private int depth;
    private List<TreeNodeLevel5> children;

    public TreeNodeLevel5(String url, String title, int depth) {
        this.url = url;
        this.title = title;
        this.depth = depth;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNodeLevel5 child) {
        children.add(child);
    }

    public List<TreeNodeLevel5> getChildren() {
        return children;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getDepth() {
        return depth;
    }
}
