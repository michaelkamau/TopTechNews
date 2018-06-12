package mikekamau.com.toptechnews.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import mikekamau.com.toptechnews.api.NewsArticlesAPIService;
import mikekamau.com.toptechnews.api.schema.NewsArticlesList;
import mikekamau.com.toptechnews.api.NewsAPIServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsArticlesViewModel extends ViewModel {

    private static final String TAG = "NewsArticlesViewModel";
    private MutableLiveData<NewsArticlesList> newsArticleList;
    private NewsArticlesAPIService newsArticlesAPIService;
    private String category;
    private String apiKey;

    public NewsArticlesViewModel(String category, String apiKey) {
        this.category = category;
        this.apiKey = apiKey;
        newsArticlesAPIService = NewsAPIServiceFactory.getNewsArticlesAPI();
    }


    public LiveData<NewsArticlesList> getNewsArticleList() {
        if( newsArticleList == null){
            newsArticleList = new MutableLiveData<>();

            newsArticlesAPIService.getTopHeadlines(category, apiKey)
                    .enqueue(new Callback<NewsArticlesList>() {
                @Override
                public void onResponse(@NonNull Call<NewsArticlesList> call, @NonNull Response<NewsArticlesList> response) {
                    if (response.isSuccessful()){
                        newsArticleList.postValue(response.body());
                    }
                    else{
                        Log.e(TAG, "Could not fetch news items");
                    }
                }

                @Override
                public void onFailure(Call<NewsArticlesList> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        }
        return newsArticleList;
    }
}
