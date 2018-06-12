package mikekamau.com.toptechnews.api;

import mikekamau.com.toptechnews.api.schema.NewsArticlesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsArticlesAPIService {

    String V2_TOP_HEADLINES = "v2/top-headlines/";

    @GET(V2_TOP_HEADLINES)
    Call<NewsArticlesList> getTopHeadlines(@Query("category") String category,
                                           @Query("apiKey") String apiKey);
}
