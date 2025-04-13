/*
Level 4 Goals – building an Iterator and three-layer tree 
To be awarded full credit for Level 4, you must demonstrate the following: 
• Building a TreeIterator that traverses the tree in level-order. 
• Building a Tree that is at least a height of 3.  (That is at least one leaf is 3 pages away from 
the starting page.) 
• Create a test jar that outputs the tree layer by layer with the name of the pages level-by
level. 
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Level 4 - Web Crawler using JSoup, Tree construction and Level-order traversal.
 * <p>
 * This class fetches web pages starting from a root URL,
 * constructs a tree of links up to a given depth, and
 * prints out the tree level by level using a TreeIterator.
 * <p>
 * Goals:
 * - Build a tree at least 3 layers deep.
 * - Avoid duplicate URLs.
 * - Traverse the tree in level-order.
 *
 * @author Elizabeth
 * @author Arjina
 */
public class ListLinksLevel4 {
    /**
     * HashMap to store visited URLs and avoid duplicates during tree construction.
     */
    private static HashMap<String, Integer> urlMap = new HashMap<>();
    
    // Method to fetch the page title, but keeping the other method
   /*  private static String fetchPageTitle(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.title();
    }
     */

    /**
     * This method fetches all hyperlinks from the specified URL using JSoup.
     *
     * @param url The webpage URL to fetch links from.
     * @return A collection of hyperlink elements.
     * @throws IOException if the URL cannot be accessed.
     */
    private static Elements fetchLinks(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.select("a[href]");
    }

    /**
     * This method builds the entire tree of web pages starting from the root URL.
     *
     * @param baseUrl The URL from which to start building the tree.
     * @param depth   The maximum depth for the tree.
     * @return The root node of the constructed tree.
     * @throws IOException if an error occurs during page fetching.
     */
    private static TreeNodeLevel4 buildTree(String baseUrl, int depth) throws IOException {
        TreeNodeLevel4 root = new TreeNodeLevel4(baseUrl, fetchPageTitle(baseUrl));
        buildSubTree(root, baseUrl, depth, 1);
        return root;
    }

    /**
     * This helper method recursively builds a subtree by fetching links from the parent URL and adding child nodes.
     *
     * @param parentNode   The parent node to which children will be attached.
     * @param parentUrl    The URL of the parent node.
     * @param maxDepth     The maximum depth to explore.
     * @param currentDepth The current depth in the recursion.
     * @throws IOException if fetching links fails.
     */
     private static void buildSubTree(TreeNodeLevel4 parentNode, String parentUrl, int maxDepth, int currentDepth) throws IOException {
        if (currentDepth >= maxDepth) return;

        Elements links = fetchLinks(parentUrl);
        for (Element link : links) {
            String relativeUrl = link.attr("href");
            if (!urlMap.containsKey(relativeUrl)) {
                // Mark this URL as visited
                urlMap.put(relativeUrl, 1);
                String fullUrl = parentUrl + relativeUrl;
                String title = fetchPageTitle(fullUrl);
                TreeNodeLevel4 childNode = new TreeNodeLevel4(fullUrl, title);
                parentNode.addChild(childNode);
                buildSubTree(childNode, fullUrl, maxDepth, currentDepth + 1); // Recursively build tree
            }
        }
    }
        
        /* // keeping this method just for future use, this is just the 2nd version that gives more clean url.
        private static void buildSubTree(TreeNodeLevel4 parentNode, String parentUrl, int maxDepth, int currentDepth) throws IOException {
            if (currentDepth >= maxDepth) return;
        
            // Sanitize parent URL first
            parentUrl = sanitizeURL(parentUrl);
        
            Elements links = fetchLinks(parentUrl);
            for (Element link : links) {
                String relativeUrl = link.attr("href");
                
                // Sanitize the relative URL
                String fullUrl = sanitizeURL(relativeUrl);
                
                if (!urlMap.containsKey(fullUrl)) {
                    // Mark this URL as visited
                    urlMap.put(fullUrl, 1);
                    String title = fetchPageTitle(fullUrl);
                    TreeNodeLevel4 childNode = new TreeNodeLevel4(fullUrl, title);
                    parentNode.addChild(childNode);
                    buildSubTree(childNode, fullUrl, maxDepth, currentDepth + 1); // Recursively build tree
                }
            }
        }
        */

    /**
     * This method prints the constructed tree level by level using TreeIterator.
     *
     * @param root The root node of the tree.
     */
    private static void printTreeLevels(TreeNodeLevel4 root) {
        TreeIteratorLevel4 iterator = new TreeIteratorLevel4(root);
        int level = 0;
        while (iterator.hasNext()) {
            TreeNodeLevel4 node = iterator.next();
            if (level == 0) {
                System.out.println("Level " + level + ": " + node.getTitle() + " (" + node.getUrl() + ")");
            } else {
                System.out.println("Level " + level + ": " + node.getTitle() + " (" + node.getUrl() + ")");
            }
            level++;
        }
    }

     // Modify your fetchPageTitle or wherever the error happens
   /*  public static String sanitizeURL(String url) {
    try {
        URL validURL = new URL(url);
        URI uri = validURL.toURI(); // this will throw if there's an illegal fragment
        return uri.toString();
    } catch (MalformedURLException | URISyntaxException e) {
        System.err.println("Skipping invalid URL: " + url);
        return null;
    }
  }*/

   /**
     * Sanitizes a given URL by resolving relative paths, removing fragments, and validating structure.
     *
     * @param url The URL string to sanitize.
     * @return A cleaned, valid URL string, or null if invalid.
     */
  public static String sanitizeURL(String url) {
    try {
        // to handle cases where URL already contains a fragment
        String baseUrl = "https://www.hunter.cuny.edu";  // Can be dynamic or passed if needed

        // to check if the URL is a relative URL (i.e., it doesn't contain http:// or https://)
        if (url.startsWith("http://") || url.startsWith("https://")) {
            // to remove any fragment (anything after the # symbol)
            int fragmentIndex = url.indexOf("#");
            if (fragmentIndex != -1) {
                url = url.substring(0, fragmentIndex);  // it removes fragment part
            }
            return new URL(url).toURI().toString();  // to fix the URL
        } else {
            // to handle relative URLs by converting them to absolute
            if (!url.startsWith("/")) {
                url = "/" + url;  // Make sure relative URLs have the leading slash
            }
            String fullUrl = new URL(new URL(baseUrl), url).toURI().toString();
            return fullUrl;
        }
    } catch (MalformedURLException | URISyntaxException e) {
        System.err.println("Skipping invalid URL: " + url);
        return null;
    }
}

   /**
     * Fetches the title of a web page, given a URL. If the URL is invalid or cannot be reached, returns "N/A".
     *
     * @param url The URL to fetch.
     * @return The page title, or "N/A" if unavailable.
     */
    public static String fetchPageTitle(String url) {
        String sanitizedUrl = sanitizeURL(url);
        if (sanitizedUrl != null) {
            try {
                Document doc = Jsoup.connect(sanitizedUrl).get();
                return doc.title();
            } catch (IOException e) {
                System.err.println("Failed to fetch page: " + sanitizedUrl);
            }
        }
        return "N/A";
    }
    
   /**
     * Main entry point for running the Level 4 tree-building and traversal program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // Start from the root URL
            String baseUrl = "https://www.hunter.cuny.edu";
            System.out.println("Fetching " + baseUrl + "...");
            TreeNodeLevel4 root = buildTree(baseUrl, 3);  // Build the tree up to 3 layers
            printTreeLevels(root);  // Print the tree level by level
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// to compile level 4: javac -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel4.java TreeNodeLevel4.java TreeIteratorLevel4.java
// to run the compiled program: java -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel4
// to recompile the program: 
//javac -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel4.java TreeNodeLevel4.java TreeIteratorLevel4.java
// after compiled to run the file:
// java -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel4
// To run level 4 javadoc: javadoc -d javadoc -cp ..\libs\jsoup-1.19.1.jar ListLinksLevel4.java TreeNodeLevel4.java TreeIteratorLevel4.java
