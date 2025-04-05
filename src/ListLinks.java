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

public class ListLinks {
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

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

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