import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * ListLinksLevel5 crawls a website and outputs its structure as a DOT graph.
 * Use Graphviz to visualize the output.
 */
public class ListLinksLevel5 {
    private static final int MAX_DEPTH = 2;  // Adjust depth for testing
    private static Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        String url = "https://www.hunter.cuny.edu";
        System.out.println("Fetching " + url + "...");

        TreeNodeLevel5 root = buildLinkTree(url, 0);

        if (root == null) {
            System.out.println("Root page could not be fetched or parsed.");
        } else {
            System.out.println("digraph G {");
            printDotGraph(root);
            System.out.println("}");
        }
    }

    private static TreeNodeLevel5 buildLinkTree(String url, int depth) {
        if (depth > MAX_DEPTH || visited.contains(url)) {
            return null;
        }

        try {
            visited.add(url);
            Document doc = Jsoup.connect(url).get();
            String title = doc.title().replace("\"", "\\\"");  // Escape quotes
            TreeNodeLevel5 node = new TreeNodeLevel5(url, title, depth);

            if (depth < MAX_DEPTH) {
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String absHref = link.attr("abs:href");
                    if (absHref.startsWith("http") && !visited.contains(absHref)) {
                        TreeNodeLevel5 child = buildLinkTree(absHref, depth + 1);
                        if (child != null) {
                            node.addChild(child);
                        }
                    }
                }
            }

            return node;
        } catch (Exception e) {
            System.err.println("Failed to fetch page: " + url);
            return null;
        }
    }

    private static void printDotGraph(TreeNodeLevel5 node) {
        String nodeName = "\"" + node.getUrl() + "\"";
        String nodeLabel = "[label=\"Level " + node.getDepth() + ": " + node.getTitle() + "\"]";
        System.out.println(nodeName + " " + nodeLabel + ";");

        for (TreeNodeLevel5 child : node.getChildren()) {
            String childName = "\"" + child.getUrl() + "\"";
            System.out.println(nodeName + " -> " + childName + ";");
            printDotGraph(child);
        }
    }
}


// to compile and create class file
//javac -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel5.java TreeNodeLevel5.java TreeIteratorLevel4.java
// to run: java -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel5

// to complie before creating jar file: 
//javac -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel5.java TreeNodeLevel5.java TreeIteratorLevel4.java
// jar cmf ManifestLevel5.txt ListLinksLevel5.jar ListLinksLevel5.class TreeNodeLevel5.class TreeIteratorLevel4.class -C . .
// to create a jar file: jar cmf ManifestLevel5.txt ListLinksLevel5.jar ListLinksLevel5.class TreeNodeLevel5.class TreeIteratorLevel4.class

// to run jar file: java -cp ".;..\libs\jsoup-1.19.1.jar;ListLinksLevel5.jar" ListLinksLevel5

//Graphviz Online link: https://dreampuf.github.io/GraphvizOnline/

// To create dot file:  java -cp ".;..\libs\jsoup-1.19.1.jar;ListLinksLevel5.jar" ListLinksLevel5 > output.dot
// This above command line helps to create a create a file named output.dot containing the DOT graph
