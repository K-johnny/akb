package akb92ru;
/**
 * Created by K_ on 29.11.2014.
 */

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;


public class Parser {

    public static final int minUrlLength = 10;
    public static ArrayList<Category> cats = new ArrayList<>();
    public static final String START_URL_CATEGORY = "http://aet.ua/index.php?route=common/cars";
    public static final String CSS_QUERY = "ul.carmodels > li > a";
    public static final String CSS_QUERY_HOME = "ul.carmodelhome > li ";

    public static void main(String[] args) throws IOException {
        parseUrl(START_URL_CATEGORY);

        int n = cats.size();
        for (int i = 0; i < n; i++) {
            try {
                parseUrl(cats.get(i), i);
            } catch (Exception e){
                System.out.println("Exeption: " + e);
            }

            n = cats.size();
        }
        try (PrintWriter out = new PrintWriter("cat.csv", "UTF-8")) {
            WriteFiles.writeData(cats, out);
        }

    }
    public static void parseUrl(String url) throws IOException {
        Validate.isTrue(url.length() >= minUrlLength, "use: " + url + " to fetch");
        print("Fetching %s...", url);
        Document doc = Jsoup.connect(url).get();
        Elements listElement = doc.select(CSS_QUERY_HOME);
        for (Element link : listElement) {
            print(" * a: <%s> (%s)", link.select("a").first().attr("abs:href"), trim(link.text(), 35));
            print("%s", link.select("a").first().select("img").first().attr("abs:src"));
            print("\"%s\",", link.text());
            try {
                cats.add(new Category(link.select("a").first().attr("abs:href"), link.text(), link.select("a").first().select("img").first().attr("abs:src"), 0, 0));
            } catch (Exception e) {
                System.out.println("Exeption: " + e);
            }
        }
    }

    public static void parseUrl(Category category, int categoryId) throws IOException {
        Validate.isTrue(category.getUrl().length() >= minUrlLength, "use: " + category.getUrl() + " to fetch");
        print("Fetching %s", category.getUrl());
        Document doc = Jsoup.connect(category.getUrl()).get();
        Elements listElement = doc.select(CSS_QUERY);
        for (Element link : listElement) {
            print(" * a: <%s> (%s)", link.attr("abs:href"), trim(link.text(), 35));
            print("%s", link.select("img").first().attr("abs:src"));
            print("\"%s\",", link.text());
            try {
                Parser.cats.add(new Category(link.attr("abs:href"), link.text(), link.select("img").first().attr("abs:src"), categoryId, category.getDepth()));
            } catch (Exception e) {
                System.out.println("Exeption: " + e);
            }
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + "...";
        else
            return s;
    }
}