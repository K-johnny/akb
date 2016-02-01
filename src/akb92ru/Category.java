package akb92ru;

/**
 * Created by K_ on 13.12.2014.
 */
public class Category {
    private String  url;
    private String  name;
    private String  imageUrl;
    private int     sourceParent;


    private int     siteParent;
    private int     siteIthem;
    private int     depth;

    public Category(String url, String name, String imageUrl, int sourceParent, int depth) {
        this.url = url;
        this.name = name;
        this.imageUrl = imageUrl;
        this.sourceParent = sourceParent;
        this.depth = depth + 1;
        siteParent = -1;
        siteIthem = -1;
    }

    public Category(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getSourceParent() {
        return sourceParent;
    }

    public int getDepth() {
        return depth;
    }

    public int getSiteIthem() {
        return siteIthem;
    }

    public int getSiteParent() {
        return siteParent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSourceParent(int sourceParent) {
        this.sourceParent = sourceParent;
    }

    public void setSiteParent(int siteParent) {
        this.siteParent = siteParent;
    }

    public void setSiteIthem(int siteIthem) {
        this.siteIthem = siteIthem;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
