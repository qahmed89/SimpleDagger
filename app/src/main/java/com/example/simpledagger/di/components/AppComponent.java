package com.example.simpledagger.di.components;

import com.example.simpledagger.view.DetailsActivity;
import com.example.simpledagger.view.FirstFragment;
import com.example.simpledagger.view.Main2Activity;
import com.example.simpledagger.view.MainActivity;
import com.example.simpledagger.di.modules.ContextModel;
import com.example.simpledagger.di.modules.NetworkModel;
import com.example.simpledagger.view.SecondFragment;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {NetworkModel.class, ContextModel.class})
public interface AppComponent {


    void inject(MainActivity mainActivity);
    void inject(Main2Activity main2Activity);
    void inject(DetailsActivity detailsActivity);
    void injectFragment(FirstFragment firstFragment);
    void injectFragment(SecondFragment secondFragment);




}
