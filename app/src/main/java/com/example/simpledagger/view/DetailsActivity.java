package com.example.simpledagger.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;
import com.example.simpledagger.BaseApplication;
import com.example.simpledagger.R;
import com.example.simpledagger.model.details.Details;
import com.example.simpledagger.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

public class DetailsActivity extends AppCompatActivity {
    int id;
    FloatingActionButton fab1, fab2, fab3;
    boolean flag = true;
    ConstraintLayout constraintLayout;
    String yt;
    @Inject
    ViewModelProvider.Factory viewModelProvider;
  //  private ActivityDetailsBinding activityMain2Binding;
    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
      //  activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

    }
}