// this is just the level 4 test file so that we can see each level more clear 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class ListLinksLevel4Test {
    public static void main(String[] args) {
        String startURL = "https://www.hunter.cuny.edu";
        TreeNodeLevel4 root = new TreeNodeLevel4(startURL, 0);
        TreeIteratorLevel4.buildTree(root, 3);  // go 3 levels deep (Level 0–3)

        printTreeByLevel(root);
    }

    // Print only titles, grouped by level
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
