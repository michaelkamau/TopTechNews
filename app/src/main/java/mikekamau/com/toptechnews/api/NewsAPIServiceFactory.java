package mikekamau.com.toptechnews.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsAPIServiceFactory {

    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl originalUrl = originalRequest.url();

            HttpUrl url = originalUrl.newBuilder()
                    .addQueryParameter("language", "en")
                    .addQueryParameter("pageSize", "100")
                    .addQueryParameter("country", "us")
                    .build();

            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }).build();

    private static Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();


    public static NewsArticlesAPIService getNewsArticlesAPI(){
        return retrofit.create(NewsArticlesAPIService.class);
    }
}
