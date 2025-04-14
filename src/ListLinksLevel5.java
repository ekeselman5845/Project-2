/* Level 5 Goals – output graph and packaging 
To be awarded full credit for Level 5, you must demonstrate the following: 
• Outputting to the console the entire tree in DOT graph language format.  That is, one should 
be able to execute a dot interpreter on the output and receive a visual. 
o For each level print the node + label. 
o Then, print that this node connects to a number of other nodes (names of children). 
• You can test your program by copying the output into a GraphViz generator website. 
• I will test with java -jar yourjar.jar | dot -Tsvg > result.svg.  You can view the command line 
arguments here: https://graphviz.org/doc/info/command.html.  
• Has appropriate JavaDoc documentation for all classes. 
• Has all necessary source code. 
• Has an executable Jar. 
• Has screenshots demonstrating successful operation of Goal 1, 2, 3, and 4. 
• Has a weekly log of accomplishments, with each group member appropriately attributed. 
 */


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * ListLinksLevel5 crawls a website and outputs its structure as a DOT graph.
 * <p>
 * This program starts at a given URL, extracts all the links recursively up to a specified depth,
 * and outputs the results in DOT format. The generated DOT format can be visualized using Graphviz or
 * other compatible visualization tools. The program outputs each node with its title and connects each
 * node to its child nodes.
 * </p>
 * <p>
 * For example, the output can be used to visualize website link structures as a graph. You can paste the
 * output into an online Graphviz generator to view the resulting visualization.
 * </p>
 *
 * <p>
 * <b>Usage:</b><br>
 * To generate the DOT file, run the following command:<br>
 * <code>java -cp ".;..\libs\jsoup-1.19.1.jar;ListLinksLevel5.jar" ListLinksLevel5 > output.dot</code>
 * </p>
 *
 * @see <a href="https://dreampuf.github.io/GraphvizOnline/">Graphviz Online</a>
 * @author Elizabeth
 * @author Arjina
 */

 //ListLinksLevel5 crawls a website and outputs its structure as a DOT graph.
 // Use Graphviz to visualize the output.
public class ListLinksLevel5 {
    private static final int MAX_DEPTH = 2;  // Adjust depth for testing
    private static Set<String> visited = new HashSet<>();

    /**
     * The entry point of the program. Fetches the given URL, builds the link tree, and outputs it in DOT format.
     * <p>
     * This method fetches the starting URL, constructs a tree of links up to the maximum depth, and prints
     * the tree in DOT graph format for visualization.
     * </p>
     * 
     * @param args Command-line arguments (unused)
     */
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

     /**
     * Builds a tree of links starting from the given URL up to a specified depth.
     * <p>
     * This method crawls a given URL, recursively fetches linked pages, and builds a tree of nodes. The tree's
     * depth is limited by the {@link #MAX_DEPTH} constant.
     * </p>
     * 
     * @param url The starting URL to crawl
     * @param depth The current depth in the tree (0 for the root)
     * @return The root node of the tree, or null if the page could not be fetched
     */
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

    /**
     * Recursively prints the DOT graph representation of the tree.
     * <p>
     * This method outputs each node in the tree along with its label and the connections between the nodes
     * in DOT format. Each node represents a webpage, and edges represent links between those pages.
     * </p>
     *
     * @param node The current node to print in DOT format
     */
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
// to run javadoc: javadoc -d javadoc -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel5.java
// to create svg file: & "C:\Program Files\Graphviz\bin\dot.exe" -Tsvg output.dot -o output.svg
