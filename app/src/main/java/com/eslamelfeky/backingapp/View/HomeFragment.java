package com.eslamelfeky.backingapp.View;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.R;
import com.eslamelfeky.backingapp.Utails.RecipeAdapter;
import com.eslamelfeky.backingapp.ViewModel.HomeViewModel;
import com.google.gson.Gson;

import java.util.List;


public class HomeFragment extends Fragment {
    HomeViewModel homeViewModel;
    @BindView(R.id.rv_sweets)
    RecyclerView rvSweets;

    Bundle args=new Bundle();
    SharedPreferences sharedPreferences;
    Gson gson=new Gson();
    Task task;


    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this,view);

        task=(Task)getActivity();
        sharedPreferences=getActivity().getSharedPreferences("pre_widget",Context.MODE_PRIVATE);
        homeViewModel= ViewModelProviders.of(this).get(HomeViewModel.class);

        homeViewModel.observeLiveData().observe(this, recipeResults ->initRecyclerView( recipeResults));







        return view;
    }
    private void initRecyclerView(final List<RecipeResults> recipeResults) {
        int screenWidth=task.checkScreenWidth();
        if (screenWidth<600) {
            rvSweets.setLayoutManager(new LinearLayoutManager(getContext()));
        }else {
            rvSweets.setLayoutManager(new GridLayoutManager(getContext(),4));
        }
        RecipeAdapter recipeAdapter=new RecipeAdapter(recipeResults);

        recipeAdapter.setClickListener((int position) -> {

            sharedPreferences.edit().putString("ingredients",
                    gson.toJson(recipeResults.get(position))).apply();
            updateWidget();
            args.putParcelable("RecipeResultsObject",recipeResults.get(position));
            if(screenWidth<600){
                task.onBackClick(false);
                RecipeDetailFragment recipeDetailFragment=new RecipeDetailFragment();
                recipeDetailFragment.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.recycler_view_fragment,recipeDetailFragment,"RecipeDetailFragment")
                        .addToBackStack(null).commit();
            }
            else{
                task.setFrameLayoutViability(true);
                task.onBackClick(true);
                RecipeDetailFragment recipeDetailFragment=new RecipeDetailFragment();
                recipeDetailFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.recipe_detail_fragment,recipeDetailFragment,
                        "RecipeDetailFragment").commit();


                StepsDetailsFragment stepsDetailsFragment=new StepsDetailsFragment();
                stepsDetailsFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.steps_details_fragment,stepsDetailsFragment
                        ,"StepsDetailsFragment").commit();

            }



        });
        rvSweets.setAdapter(recipeAdapter);

    }

    private void updateWidget() {
        int[] ids = AppWidgetManager.getInstance(getActivity().getApplication()).getAppWidgetIds(new ComponentName(getActivity().getApplication(),
                IngredientsWidget.class));
        IngredientsWidget myWidget = new IngredientsWidget();
        myWidget.onUpdate(getContext(), AppWidgetManager.getInstance(getContext()),ids);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
