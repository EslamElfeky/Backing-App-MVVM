package com.eslamelfeky.backingapp.ViewModel;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;
import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.Repositery.Repositery;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class HomeViewModel extends AndroidViewModel {
    Repositery repositery;
    Context context;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        repositery=Repositery.getInstance();
        context=application.getApplicationContext();
        getRecipeResult();

    }

    public void getRecipeResult(){
        if (isNetworkConnected(context))
        {
            repositery.getRecipeResults();
        }else {
            Toast.makeText(context.getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }



    }

    public LiveData<List<RecipeResults>> observeLiveData(){
        return repositery.observeLiveData();
    }

    private boolean isNetworkConnected(Context AplicationContext) {

        ConnectivityManager cm = (ConnectivityManager) AplicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}


