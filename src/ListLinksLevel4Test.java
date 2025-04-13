// this is just the level 4 test file so that we can see each level more clear 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

/**
 * ListLinksLevel4Test is a simple test class that demonstrates how to
 * crawl a website up to a specified depth using the TreeNodeLevel4 and TreeIteratorLevel4 classes.
 * It prints the page titles level by level, allowing a clear view of the tree structure.
 * 
 * <p>
 * This example starts from a given URL and explores hyperlinks down to level 3.
 * </p>
 * 
 * @author Arjina
 * @version 1.0
 */
public class ListLinksLevel4Test {
    /**
     * The main method serves as the entry point for the program.
     * It initializes the web crawling process starting from the specified URL and
     * prints the collected web page titles grouped by their level in the tree.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String startURL = "https://www.hunter.cuny.edu";
        TreeNodeLevel4 root = new TreeNodeLevel4(startURL, 0);
        TreeIteratorLevel4.buildTree(root, 3);  // go 3 levels deep (Level 0–3)

        printTreeByLevel(root);
    }

    /**
     * This prints only titles, grouped by level.
     * 
     * @param root The root node of the tree representing the starting URL.
     */
    public static void printTreeByLevel(TreeNodeLevel4 root) {
        Queue<TreeNodeLevel4> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;

        System.out.println("Level " + currentLevel + ":");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNodeLevel4 node = queue.poll();
                if (node == null) continue;
                System.out.println(node.getTitle());
                for (TreeNodeLevel4 child : node.getChildren()) {
                    queue.add(child);
                }
            }

            // Peek next level
            if (!queue.isEmpty()) {
                currentLevel++;
                System.out.println("\nLevel " + currentLevel + ":");
            }
        }
    }
}
/*Compile with jsoup: 
 javac -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel4Test.java TreeNodeLevel4.java TreeIteratorLevel4.java
*/

// to run the jar file: java -jar TreeLevel4.jar
// to run the javadoc for this test file: javadoc -d javadoc -cp ..\libs\jsoup-1.19.1.jar ListLinksLevel4Test.java TreeNodeLevel4.java TreeIteratorLevel4.java ListLinksLevel4.java

