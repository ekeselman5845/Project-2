/* Level 2 Goals – refining the URLs 
 * To be awarded full credit for Level 2, you must demonstrate the following: 
• Filtering out any link that does not contain the domain "hunter.cuny.edu".  
• Accepting instances of "www.hunter.cuny.edu" and "hunter.cuny.edu". 
• Filtering out deeper subdomains (e.g., roosevelthouse.hunter.cuny.edu) of Hunter. 
• Make sure that the URLs you print are relative to https://www.hunter.cuny.edu/, not 
absolute links. 
• Create a test jar that prints the new filtered set of relative links. 
 */


// ListLinksLevel2.java

import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListLinksLevel2 {

    public static void main(String[] args) throws IOException {
        String baseUrl = "https://www.hunter.cuny.edu";
        print("Fetching %s...", baseUrl);
        Document doc = Jsoup.connect(baseUrl).get();

        // Print the page title
        System.out.println("Page Title: " + doc.title());

        // Get all <a href> elements
        Elements links = doc.select("a[href]");
        print("\nFiltered Hunter.edu Links: (%d total)", links.size());

        int count = 0;
        for (Element link : links) {
            String absHref = link.attr("abs:href");

            if (isValidHunterLink(absHref)) {
                String relativeLink = absHref.replaceFirst("^https?://(www\\.)?hunter\\.cuny\\.edu", "");
                print(" * a: <%s> (%s)", relativeLink, trim(link.text(), 35));
                count++;
            }
        }

        print("\nTotal Valid Hunter Links: %d", count);
    }

    // Accepts only www.hunter.cuny.edu and hunter.cuny.edu, not subdomains
    private static boolean isValidHunterLink(String url) {
        if (url == null) return false;

        // Only allow if the URL starts with either of the main domains
        if (url.startsWith("https://www.hunter.cuny.edu") || url.startsWith("https://hunter.cuny.edu")) {
            // Reject deeper subdomains like roosevelthouse.hunter.cuny.edu
            String domainPart = url.split("/")[2]; // get the domain (e.g., www.hunter.cuny.edu)
            int dotCount = domainPart.split("\\.").length;
            return dotCount <= 3; // only allow hunter.cuny.edu or www.hunter.cuny.edu
        }

        return false;
    }

    private static void print(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    private static String trim(String text, int maxWidth) {
        if (text.length() > maxWidth) {
            return text.substring(0, maxWidth - 1) + ".";
        }
        return text;
    }
}


/*
 to created .class file in windows:
 PS C:\Users\USER\Documents\Project-2\src> javac -cp ".;C:\Users\USER\Documents\Project-2\libs\jsoup-1.19.1.jar" ListLinksLevel2.java 
PS C:\Users\USER\Documents\Project-2\src> 
 */
/*
 To run the code:
 navigate the directiory: cd C:\Users\USER\Documents\Project-2\src
 to run the file: java -cp ".;C:\Users\USER\Documents\Project-2\libs\jsoup-1.19.1.jar" ListLinksLevel2

 */

 /*
  to create jar file: we created manifestlevel2.txt
  to compile the java file: javac -cp ".;C:\Users\USER\Documents\Project-2\libs\jsoup-1.19.1.jar" ListLinksLevel2.java
  to create jar file: jar cmf manifestlevel2.txt ListLinksLevel2.jar ListLinksLevel2.class
to run jar file: java -cp ".;C:\Users\USER\Documents\Project-2\libs\jsoup-1.19.1.jar;ListLinksLevel2.jar" ListLinksLevel2
//Note: you need to make sure that you are in the right directory to the file with jsoup. 
  */