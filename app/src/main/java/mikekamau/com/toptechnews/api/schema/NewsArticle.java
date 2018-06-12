package mikekamau.com.toptechnews.api.schema;

import com.google.gson.annotations.Expose;

public class NewsArticle {

    @Expose
    private NewsSource source;
    @Expose
    private String author;
    @Expose
    private String title;

    @Expose
    private String description;

    @Expose
    private String url;

    @Expose
    private String urlToImage;

    @Expose
    private String publishedAt;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public NewsSource getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "source=" + source +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}
