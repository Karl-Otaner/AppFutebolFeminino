package com.dio.soccernews.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dio.soccernews.data.SoccerNewsRepository;
import com.dio.soccernews.domain.News;

import java.util.List;

public class FavoritesViewModel extends ViewModel {



    public FavoritesViewModel() {

    }

    public LiveData<List<News>> loadFavoriteNews() {
        return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public static void saveNews(News news){
        AsyncTask.execute(() ->SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));

    }

}