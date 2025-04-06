/*
 * Level 3 Goals – deduplicating and tree building 
To be awarded full credit for Level 3, you must demonstrate the following: 
• Building a hashtable to hold URLs that are found in parsing webpages.  You may use any 
form of hashtable that is built into Java.  The relative URL should be the “key”. 
o Recommended data structure: HashMap 
https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.h
 tml.  
o Legacy data structure: HashTable 
https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Hashtable.
 html.  
o It may make sense to have a unique number be the “value” inserted into the 
hashtable.  You will need a unique node name for the DOT output in Level 5. 
• Filtering out any duplicate URLs, using the hashtable, as you parse a webpage.  That is, if the 
URL already exists in the table, it should not be printed again. 
• Building an n-ary tree (i.e., not binary) of all of the subsequent links from the initial page.  
This tree will be a height of 1 from the root page.  You will need to define your own Tree and 
TreeNode classes. 
• Create a test jar that demonstrates that the hashtable filters out duplicates and that the 
Tree accepts input.  You do not need to traverse the tree here. 
 */


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class ListLinksLevel3 {

    public static void main(String[] args) throws IOException {
        String baseUrl = "https://www.hunter.cuny.edu";
        System.out.printf("Fetching %s...\n", baseUrl);

        Document doc = Jsoup.connect(baseUrl).get();
        System.out.println("Page Title: " + doc.title());

        Elements links = doc.select("a[href]");
        HashMap<String, Integer> linkMap = new HashMap<>();
        int idCounter = 1;

        // Initialize Tree
        LinkTreeLevel3 tree = new LinkTreeLevel3(baseUrl);

        for (Element link : links) {
            String absUrl = link.attr("abs:href");
            String relUrl = link.attr("href");

            // Filter internal (relative) links only and remove duplicates
            if (!relUrl.startsWith("/") || linkMap.containsKey(relUrl)) {
                continue;
            }

            linkMap.put(relUrl, idCounter);
            TreeNodeLevel3 child = new TreeNodeLevel3(absUrl, idCounter);
            tree.getRoot().addChild(child);
            System.out.printf(" * a: <%s> (%s)\n", absUrl, link.text());

            idCounter++;
        }

        System.out.printf("\nTotal Unique Relative Links: %d\n", linkMap.size());
    }
}


/*
 to compile all files for level 3:

cd C:\Users\USER\Documents\Project-2\src
javac -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel3.java LinkTreeLevel3.java TreeNodeLevel3.java

 */
// only compile the code: javac -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel3.java
// to run the program successfully after compiling: java -cp ".;..\libs\jsoup-1.19.1.jar" ListLinksLevel3

// to create the jar file: jar cfm ListLinksLevel3.jar manifestlevel3.mf *.class
// to run the jar file: java -cp ".;C:\Users\USER\Documents\Project-2\libs\jsoup-1.19.1.jar;ListLinksLevel3.jar" ListLinksLevel3

