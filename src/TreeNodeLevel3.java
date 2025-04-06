import java.util.ArrayList;
import java.util.List;

public class TreeNodeLevel3 {
    private String url;
    private int id;
    private List<TreeNodeLevel3> children;

    public TreeNodeLevel3(String url, int id) {
        this.url = url;
        this.id = id;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNodeLevel3 child) {
        children.add(child);
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public List<TreeNodeLevel3> getChildren() {
        return children;
    }
}
