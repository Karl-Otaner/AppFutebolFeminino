package com.dio.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dio.soccernews.R;
import com.dio.soccernews.databinding.FragmentNewsBinding;
import com.dio.soccernews.ui.adapter.NewsAdapter;
import com.dio.soccernews.ui.favorites.FavoritesViewModel;
import com.google.android.material.snackbar.Snackbar;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        observeNews();
        observeStates();

        binding.srlNews.setOnRefreshListener(newsViewModel::findNews);

        return root;
    }

    public void observeNews() {
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, FavoritesViewModel::saveNews));
        });
    }

    private void observeStates() {
        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    binding.srlNews.setRefreshing(true);
                    break;
                case DONE:
                    binding.srlNews.setRefreshing(false);
                    break;
                case ERROR:
                    binding.srlNews.setRefreshing(false);
                    Snackbar.make(binding.srlNews, R.string.erro_network, Snackbar.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}