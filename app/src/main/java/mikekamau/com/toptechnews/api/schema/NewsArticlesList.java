package mikekamau.com.toptechnews.api.schema;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsArticlesList {

    @SerializedName("articles")
    @Expose
    private List<NewsArticle> newsArticles;

    public List<NewsArticle> getNewsArticles() {
        return newsArticles;
    }
}
