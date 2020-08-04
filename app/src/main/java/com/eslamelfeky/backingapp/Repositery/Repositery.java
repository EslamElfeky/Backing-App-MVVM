package com.eslamelfeky.backingapp.Repositery;

import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.NetworkHelper.RecipeApi;
import com.eslamelfeky.backingapp.NetworkHelper.RecipeApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import io.reactivex.schedulers.Schedulers;

public class Repositery {
    RecipeApi recipeApi;
    MediatorLiveData<List<RecipeResults>> recipeResultsMediatorLiveData;
    LiveData<List<RecipeResults>> recipeLiveData;
   private static Repositery repositery=null;

    public Repositery() {
     recipeApi=RecipeApiClient.getClient().create(RecipeApi.class);
     recipeResultsMediatorLiveData=new MediatorLiveData<>();
    }


    public static Repositery getInstance(){
        if (repositery==null){
            return repositery=new Repositery();
        }else {
            return repositery;
        }
    }

    public void getRecipeResults(){
        recipeLiveData= LiveDataReactiveStreams.fromPublisher(recipeApi.getRecipeResults().subscribeOn(Schedulers.io()));
        recipeResultsMediatorLiveData.addSource(recipeLiveData, new Observer<List<RecipeResults>>() {
            @Override
            public void onChanged(List<RecipeResults>  recipeResults) {
                recipeResultsMediatorLiveData.setValue(recipeResults);
                recipeResultsMediatorLiveData.removeSource(recipeLiveData);
            }
        });

    }

    public LiveData<List<RecipeResults>> observeLiveData(){
        return recipeResultsMediatorLiveData;
    }

}
