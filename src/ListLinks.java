/*
  Level 1 Goals – fetching a page 
To be awarded full credit for Level 1, you must demonstrate the following: 
• Fetching the opening page of www.hunter.cuny.edu from the Internet. 
• Gathering the URLs from that page to be printed to the console. 
• Printing the name of that page onto the console. 
• The ListLinks.java sample code from jsoup is a good starting point, with modifications. 
o https://github.com/jhy/jsoup/blob/master/src/main/java/org/jsoup/examples/ListLi
 nks.java 
o Remove the package statement for simplicity. 
o Remove the media section - we're only looking for regular links. 
o Remove the imports section. 
o The third loop, which handles the links, is the one that is critical. 
o The salient information comes from attr("abs:href"). 
• Create a test jar that prints the page name as well as its links.
 */


import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Level 1 - JSoup Project
 *
 * This program fetches the main page of {@code https://www.hunter.cuny.edu},
 * prints the title of the page, and lists all standard hyperlinks found on that page.
 * It uses the JSoup library to connect to the page and parse HTML content.
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Fetches the HTML document from Hunter College’s website</li>
 *     <li>Prints the page title</li>
 *     <li>Extracts and prints all hyperlink URLs (anchor tags)</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 *     javac -cp ".;../libs/jsoup-1.19.1.jar" ListLinks.java
 *     java -cp ".;../libs/jsoup-1.19.1.jar" ListLinks
 * </pre>
 *
 * <p><b>Note:</b> Ensure {@code jsoup-1.19.1.jar} is in the specified path.</p>
 *
 * @author Elizabeth
 * @author Arjina
 */

public class ListLinks {
     /**
     * The main method initiates the web scraping process:
     * connects to the target URL, extracts the page title,
     * and prints all hyperlinks.
     *
     * @param args Command-line arguments (not used).
     * @throws IOException if the webpage cannot be fetched.
     */
    public static void main(String[] args) throws IOException {
        String url = "https://www.hunter.cuny.edu";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        System.out.println("Page Title: " + doc.title());

        Elements links = doc.select("a[href]");
       
        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
    }

     /**
     * Helper method to format and print messages to the console.
     *
     * @param msg  The message template.
     * @param args Arguments to format into the message.
     */
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    /**
     * Helper method to trim text to a specified width.
     * Adds a period at the end if trimmed.
     *
     * @param s     The input string.
     * @param width Maximum width of the output string.
     * @return Trimmed string with an optional period.
     */
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}

/*  Before compile, varify the jsoup-1.19.1.jar file inside the libs: ls ../libs/
file path: javac -cp ".;../libs/jsoup-1.19.1.jar" ListLinks.java
java -cp ".;../libs/jsoup-1.19.1.jar" ListLinks

*/
// to create javadoc folder: the following command helps to create javadoc for all the files inside the src folder.
// javadoc -d javadoc -cp ..\libs\jsoup-1.19.1.jar *.java
// to create javadoc only for level 1 following command can be used: 
//javadoc -d javadoc -cp ..\libs\jsoup-1.19.1.jar ListLinks.java

// to view html file in web: C:\Users\USER\Documents\Project-2\src\javadoc\index.html

// Re-run Javadoc: javadoc -d javadoc -cp ..\libs\jsoup-1.19.1.jar *.java
// Re-run Javadoc only for level 1: javadoc -d javadoc -cp ..\libs\jsoup-1.19.1.jar ListLinks.java
