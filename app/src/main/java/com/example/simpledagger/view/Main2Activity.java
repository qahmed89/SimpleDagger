package com.example.simpledagger.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.simpledagger.BaseApplication;
import com.example.simpledagger.R;
import com.example.simpledagger.adapter.profileAdapter;
import com.example.simpledagger.databinding.ActivityMain2Binding;

import com.example.simpledagger.model.movies.MoviesItem;
import com.example.simpledagger.model.movies.Response;
import com.example.simpledagger.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Main2Activity extends AppCompatActivity implements profileAdapter.OnMovieclickListener {
    @Inject
    ViewModelProvider.Factory viewModelProvider;
    profileAdapter profileAdapter;
    List<MoviesItem> dbs;
    private ActivityMain2Binding activityMain2Binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dbs = new ArrayList<>();

        profileAdapter = new profileAdapter(dbs, Main2Activity.this, this);

        activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        activityMain2Binding.recycleview.setLayoutManager(layoutManager);
        activityMain2Binding.recycleview.setHasFixedSize(true);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(UserViewModel.class);
        userViewModel.getModelMutableLiveDatas(getApplicationContext()).observe(Main2Activity.this, new Observer<Response>() {
            @Override
            public void onChanged(Response movieModel) {

                activityMain2Binding.shimmerViewContainer.startShimmer();
                dbs.addAll(movieModel.getData().getMovies());
                activityMain2Binding.recycleview.setAdapter(profileAdapter);
                activityMain2Binding.shimmerViewContainer.setVisibility(View.GONE);
                activityMain2Binding.shimmerViewContainer.stopShimmer();
            }
        });
activityMain2Binding.swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        userViewModel.getModelMutableLiveDatas(getApplicationContext()).observe(Main2Activity.this, new Observer<Response>() {
            @Override
            public void onChanged(Response movieModel) {
                activityMain2Binding.shimmerViewContainer.startShimmer();
                dbs.addAll(movieModel.getData().getMovies());
                activityMain2Binding.recycleview.setAdapter(profileAdapter);
                activityMain2Binding.shimmerViewContainer.setVisibility(View.GONE);
                activityMain2Binding.shimmerViewContainer.stopShimmer();
                activityMain2Binding.swiprefresh.setRefreshing(false);
            }
        });
    }
});

    }

    @Override
    public void onMovieClick(int Postion) {
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("id", dbs.get(Postion).getId());
        startActivity(i);
    }
}
