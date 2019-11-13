package com.example.simpledagger.repository;

import com.example.simpledagger.model.details.Details;
import com.example.simpledagger.model.movies.Response;
import com.example.simpledagger.remote.UserService;
import com.example.simpledagger.viewmodel.UserViewModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {
    private UserService userService;
@Inject
    public UserRepository(UserService userService) {
        this.userService = userService;
    }
    public Single<Response> dbmodelSingle(){
        return userService.getDb();
    }
    public Single<Details> detailsmodelSingle(int id){return userService.getDetails(id);}

}
