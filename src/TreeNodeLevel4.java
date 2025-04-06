import java.util.ArrayList;
import java.util.List;

public class TreeNodeLevel4 {
    String url;
    String title;
    List<TreeNodeLevel4> children;

    public TreeNodeLevel4(String url, String title) {
        this.url = url;
        this.title = title;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNodeLevel4 child) {
        children.add(child);
    }

    public List<TreeNodeLevel4> getChildren() {
        return children;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
