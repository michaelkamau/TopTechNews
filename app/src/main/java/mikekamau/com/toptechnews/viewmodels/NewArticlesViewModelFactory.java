package mikekamau.com.toptechnews.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class NewArticlesViewModelFactory implements ViewModelProvider.Factory {

    private final String category;
    private final String apiKey;

    public NewArticlesViewModelFactory(String category, String apiKey) {
        this.category = category;
        this.apiKey = apiKey;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NewsArticlesViewModel(category, apiKey);
    }
}
