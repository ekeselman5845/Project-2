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
/**
 * Level 2: Refines and filters links from the Hunter College website.
 * <p>
 * This program connects to the Hunter College homepage, retrieves all the hyperlinks,
 * and filters them to include only links that belong to the primary domains:
 * {@code www.hunter.cuny.edu} and {@code hunter.cuny.edu}, excluding any deeper subdomains.
 * <p>
 * Filtered links are printed in a relative path format.
 *
 * <p><strong>Level 2 Goals:</strong></p>
 * <ul>
 *     <li>Filter out links not containing "hunter.cuny.edu".</li>
 *     <li>Allow links from "www.hunter.cuny.edu" and "hunter.cuny.edu".</li>
 *     <li>Exclude links from deeper subdomains like "roosevelthouse.hunter.cuny.edu".</li>
 *     <li>Print valid links as relative URLs.</li>
 * </ul>
 */
public class ListLinksLevel2 {

     /**
     * The main entry point for the program.
     * <p>
     * Connects to {@code https://www.hunter.cuny.edu} using Jsoup, fetches all hyperlink elements, filters them based on domain rules,
     * and prints the valid links in relative format.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException if there is an error connecting to the target website.
     */

    public static void main(String[] args) throws IOException {
        String baseUrl = "https://www.hunter.cuny.edu";
        print("Fetching %s...", baseUrl);
        Document doc = Jsoup.connect(baseUrl).get();

        // To print the page title
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

     /**
     * Validates a URL to determine whether it belongs to the main Hunter College domains
     * {@code www.hunter.cuny.edu} or {@code hunter.cuny.edu} and filters out deeper subdomains.
     *
     * @param url The URL to validate.
     * @return {@code true} if the URL is valid for the main domains and not a subdomain;
     *         {@code false} otherwise.
     */
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

     /**
     * Prints a formatted string to the console.
     *
     * @param format The string format, following {@link String#format} syntax.
     * @param args   The arguments referenced by the format specifiers in the format string.
     */
    private static void print(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

      /**
     * Trims the provided text to fit within the specified maximum width.
     * If the text exceeds the width, it will be cut and appended with a period.
     *
     * @param text     The text to trim.
     * @param maxWidth The maximum allowed width.
     * @return The trimmed string if it exceeds {@code maxWidth}, otherwise the original text.
     */

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

  // to run level2 javadoc: javadoc -d javadoc -cp ..\libs\jsoup-1.19.1.jar ListLinksLevel2.java
