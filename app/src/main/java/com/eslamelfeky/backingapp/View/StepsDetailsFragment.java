package com.eslamelfeky.backingapp.View;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.Model.StepsItem;
import com.eslamelfeky.backingapp.R;
import com.eslamelfeky.backingapp.Utails.StepsDetailAdapter;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import java.util.List;


public class StepsDetailsFragment extends Fragment implements SendMessage {
   List<StepsItem>  stepsItem;
   int position;
   @BindView(R.id.rv_steps)
   RecyclerView rvStepsList;
   @BindView(R.id.button_next)
    Button nextButton;
    @BindView(R.id.button_back)
    Button backButton;
   @BindView(R.id.exo_playerView)
   PlayerView playerView;
   SimpleExoPlayer simpleExoPlayer=null;
   Task task;
   int screenWidth;


    public StepsDetailsFragment() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        task=(Task)getActivity();
       // task.fullScreen();





        screenWidth=task.checkScreenWidth();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_steps_details, container, false);
        ButterKnife.bind(this,view);
        Task actionBar=(Task)getActivity();
        RecipeResults recipeResults=getArguments().getParcelable("RecipeResultsObject");
        if(savedInstanceState==null){
        position =getArguments().getInt("StepPosition");
        }else{
            position=savedInstanceState.getInt("position");
        }
        stepsItem=recipeResults.getSteps();
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE &&screenWidth<600) {
            actionBar.hideActionBar();
            hideView();


                playVideo(position,false);



        } else {

            playVideo(position,false);
            initRecyclerView( stepsItem);
            
        }



        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",position);
    }

    private void hideView() {
        nextButton.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);
        rvStepsList.setVisibility(View.GONE);
    }

    private void fullScreen() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            if(screenWidth<600) {
                getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                        , WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }


    }




    private void initRecyclerView(List<StepsItem> stepsItem) {
        rvStepsList.setLayoutManager(new LinearLayoutManager(getContext()));
        StepsDetailAdapter stepsDetailAdapter=new StepsDetailAdapter(stepsItem);

        stepsDetailAdapter.setClickListener((int position) -> {
            playVideo(position,true);
            this.position =position;
        });
        rvStepsList.setAdapter(stepsDetailAdapter);

    }

    private void playVideo(int position, Boolean isExoPlayerRelease) {
       if(isExoPlayerRelease){simpleExoPlayer.release();}
       String videoUrl =stepsItem.get(position).getVideoURL();
       if(videoUrl!=""){
        Uri uri=Uri.parse(videoUrl);
        DataSource.Factory dataSourceFactory;
        MediaSource videoSource;

        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(getContext());
        playerView.setPlayer(simpleExoPlayer);
        dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "ApplicationName"));
         videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uri);
        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setPlayWhenReady(true);
                        }
    }

    @OnClick(R.id.button_next)
    public void onClickNext(){
        if(position <stepsItem.size()-1){
            position++;
            playVideo(position,true);
        }



    }

    @OnClick(R.id.button_back)
    public void onClickBack(){
        if(position!=0){
            position--;
            playVideo(position,true);
        }

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }

    @Override
    public void SendMessage(String message) {
        playVideo(Integer.parseInt(message),true);
    }
}
