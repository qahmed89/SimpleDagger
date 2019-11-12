package com.example.simpledagger.di.modules;

import androidx.lifecycle.ViewModel;

import com.example.simpledagger.remote.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module (includes = ViewModelModule.class)
public class NetworkModel {
    @Provides
    @Singleton
    static Retrofit provideRetrofit (){

        return  new Retrofit.Builder()

                .baseUrl("https://yts.lt/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }
    @Provides
    @Singleton
    static UserService provideUserServices(Retrofit retrofit){
        return retrofit.create(UserService.class);
    }
}
