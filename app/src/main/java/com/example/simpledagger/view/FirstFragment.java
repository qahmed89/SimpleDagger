package com.example.simpledagger.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.simpledagger.BaseApplication;
import com.example.simpledagger.R;
import com.example.simpledagger.model.details.Details;
import com.example.simpledagger.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    int id;
    FloatingActionButton fab1, fab2, fab3;
    ImageView imagePoster,imagebackground;
    Button watch;
    TextView title , rating ;

    boolean flag = true;
    ImageView imageBackground;
    String yt;
    @Inject
    ViewModelProvider.Factory viewModelProvider;
  //  private FragmentFirstBinding activityMain2Binding;
    private UserViewModel userViewModel;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      // activityMain2Binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_first);
        Intent intent = getActivity().getIntent();
        id = intent.getIntExtra("id", 0);
//View.OnClickListener screenListiner=Navigation.createNavigateOnClickListener(R.id.from_1_2);
 watch = view.findViewById(R.id.watch);
        title = view.findViewById(R.id.title_tv);
        rating = view.findViewById(R.id.rate_tv);
        imageBackground = view.findViewById(R.id.background_imgs);



//button.setOnClickListener(screenListiner);
        // Button button2 = view.findViewById(R.id.button2);
        final NavController navController = Navigation.findNavController(view);

        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navController.navigate(R.id.action_homeFragment_to_secondFragment);
                navController.navigate(R.id.from_1_2);

                //  Navigation.createNavigateOnClickListener(R.id.from_1_2);
                // Navigation.findNavController(v).navigate(R.id.from_1_2);
            }
        });


//        activityMain2Binding.watch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 NavController navController = Navigation.findNavController(v);
////Navigation.createNavigateOnClickListener(R.id.from_1_2,null);
//               // NavHostFragment.findNavController(R.id.fragment).navigateUp();
//                Navigation.findNavController(v).navigate(R.id.from_1_2);
//
//            }
//        });
//        activityMain2Binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (flag == true) {
//                    Animation show_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_show);
//                    Animation hide_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_hide);
//                    activityMain2Binding.watch.setVisibility(View.GONE);
//
//                    fab1.setVisibility(View.VISIBLE);
//                    fab2.setVisibility(View.VISIBLE);
//                    fab3.setVisibility(View.VISIBLE);
//                    fab1.startAnimation(show_fab_1);
//                    fab2.startAnimation(show_fab_1);
//                    fab3.startAnimation(show_fab_1);
//                    fab1.setClickable(true);
//                    fab2.setClickable(true);
//                    fab2.setClickable(true);
//
//                    flag = false;
//                } else {
//                    constraintLayout.setVisibility(View.INVISIBLE);
//                    Animation show_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_show);
//                    Animation hide_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_hide);
//
//                    // activityMain2Binding.watch.startAnimation(show_fab_1);
//                    activityMain2Binding.watch.setVisibility(View.VISIBLE);
//                    fab1.setVisibility(View.INVISIBLE);
//                    fab2.setVisibility(View.INVISIBLE);
//                    fab3.setVisibility(View.INVISIBLE);
//                    fab1.startAnimation(hide_fab_1);
//                    fab2.startAnimation(hide_fab_1);
//                    fab3.startAnimation(hide_fab_1);
//                    fab1.setClickable(false);
//                    fab2.setClickable(false);
//                    fab3.setClickable(false);
//                    flag = true;
//
//
//                }
//            }
//        });
        ((BaseApplication) getActivity().getApplication()).getAppComponent().injectFragment(this);
        userViewModel = ViewModelProviders.of(this, viewModelProvider).get(UserViewModel.class);
        userViewModel.getModelMutableLiveDateDetails(getActivity(), id).observe(this, new Observer<Details>() {
            @Override
            public void onChanged(Details details) {
                try {
                    title.setText(details.getData().getMovie().getTitleEnglish());
                    rating.setText("" + details.getData().getMovie().getRating());
                    Glide.with(view)
                            .load(details.getData()
                                    .getMovie()
                                    .getLargeCoverImage())
                            .transition(withCrossFade())
                            .into(imagePoster);
                    Glide.with(view)
                            .load(details.getData()
                                    .getMovie().getBackgroundImage())
                            .into(imageBackground);

                    yt = details.getData().getMovie().getYtTrailerCode();
                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        });



//        activityMain2Binding.watch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + yt));
//                startActivity(intent);
//            }
//        });
//
//
//            }}
    }}
