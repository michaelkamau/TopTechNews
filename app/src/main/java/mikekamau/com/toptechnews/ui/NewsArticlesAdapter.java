package mikekamau.com.toptechnews.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import mikekamau.com.toptechnews.R;
import mikekamau.com.toptechnews.api.schema.NewsArticle;

public class NewsArticlesAdapter extends RecyclerView.Adapter<NewsArticlesAdapter.NewsArticleViewHolder> {

    private List<NewsArticle> newsArticlesList;
    private Context context;

    public NewsArticlesAdapter(final Context context) {
        this.newsArticlesList = null;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_article, parent, false);
        return new NewsArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsArticleViewHolder holder, int position) {
        // update the list
        NewsArticle newsArticle = newsArticlesList.get(position);

        // TODO: Complete for image view and time formatting
        String url = newsArticle.getUrlToImage();
        url = (url == null) ? null : url.trim();
        Glide.with(context)
                .load(url)
                .apply(RequestOptions
                        .centerCropTransform()
                        .placeholder(R.drawable.ic_launcher_foreground))
                .into(holder.newsImage);
        holder.title.setText(newsArticle.getTitle());
        holder.source.setText(newsArticle.getSource().getName());
        holder.publishedTimestamp.setText(newsArticle.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return newsArticlesList == null ? 0 : newsArticlesList.size();
    }

    public void updateNewsArticles(List<NewsArticle> newsArticles){
        this.newsArticlesList = newsArticles;
        notifyDataSetChanged();
    }

    public class NewsArticleViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView publishedTimestamp;
        TextView source;
        ImageView newsImage;

        public NewsArticleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_news_title);
            publishedTimestamp = itemView.findViewById(R.id.tv_timestamp);
            source = itemView.findViewById(R.id.tv_news_source);
            newsImage = itemView.findViewById(R.id.iv_news_image);
        }
    }
}
