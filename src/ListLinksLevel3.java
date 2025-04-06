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

