package mikekamau.com.toptechnews.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import mikekamau.com.toptechnews.BuildConfig;
import mikekamau.com.toptechnews.R;
import mikekamau.com.toptechnews.api.schema.NewsArticlesList;
import mikekamau.com.toptechnews.viewmodels.NewArticlesViewModelFactory;
import mikekamau.com.toptechnews.viewmodels.NewsArticlesViewModel;

public class NewsActivity extends AppCompatActivity {

    private static final String TAG = "NewsActivity";

    private NewsArticlesAdapter newsArticlesAdapter;
    private NewsArticlesViewModel newsArticlesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String newsAPIKey = BuildConfig.NewsAPIKey; // TODO: Add your API Key from: https://newsapi.org/docs/authentication
        NewArticlesViewModelFactory factory = new NewArticlesViewModelFactory("technology", newsAPIKey);
        newsArticlesViewModel = ViewModelProviders.of(this, factory).get(NewsArticlesViewModel.class);
        newsArticlesAdapter = new NewsArticlesAdapter(this);
        initUI();
        fetchNewsArticles();

    }


    private void initUI() {
        RecyclerView newsArticlesReyclerView = findViewById(R.id.rv_news_articles);
        newsArticlesReyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsArticlesReyclerView.setAdapter(newsArticlesAdapter);

        // Pull-to-refresh
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.news_articles_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchNewsArticles();
                swipeRefreshLayout.setRefreshing(false);
            }

        });

    }

    private void fetchNewsArticles() {
        newsArticlesViewModel.getNewsArticleList().observe(this, new Observer<NewsArticlesList>() {
            @Override
            public void onChanged(@Nullable NewsArticlesList newsArticlesList) {
                Log.d(TAG, newsArticlesList.getNewsArticles().toString());
                newsArticlesAdapter.updateNewsArticles(newsArticlesList.getNewsArticles());
            }
        });
    }
}
