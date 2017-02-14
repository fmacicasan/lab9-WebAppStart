package my.apps.web;

/**
 * @author flo
 * @since 14/02/2017.
 */
public class Article {

    private String link;
    private String date;
    private String summary;
    private String domain;

    public Article(String link, String date, String summary, String domain) {
        this.link = link;
        this.date = date;
        this.summary = summary;
        this.domain = domain;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "Article{" +
                "link='" + link + '\'' +
                ", date='" + date + '\'' +
                ", summary='" + summary + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
