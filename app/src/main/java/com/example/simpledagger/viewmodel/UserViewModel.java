package com.example.simpledagger.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.simpledagger.model.details.Details;
import com.example.simpledagger.model.movies.Response;
import com.example.simpledagger.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private Context context;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Response> modelMutableLiveDataMovie = new MutableLiveData<Response>();
    private MutableLiveData<Details> modelMutableLiveDateDetails = new MutableLiveData<>();

    @Inject

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    public UserViewModel(UserRepository userRepository, Context context) {
//        this.userRepository = userRepository;
//        this.context = context;
//    }

    public LiveData<Response> getModelMutableLiveDatas(Context context) {
        loadDats(context);
        return modelMutableLiveDataMovie;
    }

    public LiveData<Details> getModelMutableLiveDateDetails(Context context, int id) {
        loadDetails(id, context);
        return modelMutableLiveDateDetails;
    }


    private void loadDats(final Context context) {
        disposable.add(userRepository.dbmodelSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response>() {


                    @Override
                    public void onSuccess(Response response) {
                        modelMutableLiveDataMovie.setValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "something wrong happen please restart App", Toast.LENGTH_SHORT).show();
                        Log.e("error", e.toString());

                    }
                })
        );


    }

    private void loadDetails(int id, final Context context) {
        disposable.add(userRepository.detailsmodelSingle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Details>() {
                    @Override
                    public void onSuccess(Details details) {
                        modelMutableLiveDateDetails.setValue(details);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", e.toString());
                        Toast.makeText(context, "something wrong happen please restart App", Toast.LENGTH_SHORT).show();

                    }
                })
        );
    }
}
