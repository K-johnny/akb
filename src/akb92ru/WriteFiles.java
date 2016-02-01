package akb92ru;
/**
 * Created by K_ on 26.12.2014.
 */
import java.io.*;
import java.util.*;


public class WriteFiles {
    protected static void writeData(ArrayList<Category> category, PrintWriter out) throws IOException {
        int i = 0;
        for (Category aCategory : category) {
            writeCategory(out, i, aCategory);
            i++;
        }
    }


    public static void writeCategory(PrintWriter out, int i, Category c) {
        out.println(i + ";" + c.getUrl() + ";" + c.getName() + ";" + c.getImageUrl() + ";" + c.getSourceParent() + ";"
                + c.getDepth() + ";" + c.getSiteParent() + ";" + c.getSiteIthem());
    }
}
