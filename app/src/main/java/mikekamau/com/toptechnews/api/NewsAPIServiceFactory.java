package mikekamau.com.toptechnews.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsAPIServiceFactory {

    private static Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static NewsArticlesAPIService getNewsArticlesAPI(){
        return retrofit.create(NewsArticlesAPIService.class);
    }
}
