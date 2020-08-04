package com.eslamelfeky.backingapp.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.R;
import com.eslamelfeky.backingapp.Utails.RecipeDetailsAdapter;


public class RecipeDetailFragment extends Fragment {

    RecipeResults recipeResults;
    @BindView(R.id.rv_recipe_details)
    RecyclerView rvRecipeDetails;
    Bundle args=new Bundle();
    Task task;
    SendMessage sendMessage;

    public RecipeDetailFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        ButterKnife.bind(this,view);
        task=(Task)getActivity();
        sendMessage=(SendMessage)getActivity();
        recipeResults=getArguments().getParcelable("RecipeResultsObject");
        initRecyclerView( recipeResults);
        return view;
    }

    private void initRecyclerView(RecipeResults recipeResults) {
        int screenWidth=task.checkScreenWidth();

            rvRecipeDetails.setLayoutManager(new LinearLayoutManager(getContext()));


        RecipeDetailsAdapter recipeDetailsAdapter=new RecipeDetailsAdapter(recipeResults);
        recipeDetailsAdapter.setClickListener((int position) -> {
            if(position!=0){
                args.putParcelable("RecipeResultsObject",recipeResults);
                args.putInt("StepPosition",position-1);
                if(screenWidth<600){
                StepsDetailsFragment stepsDetailsFragment=new StepsDetailsFragment();
                stepsDetailsFragment.setArguments(args);

                getFragmentManager().beginTransaction().replace(R.id.recycler_view_fragment,stepsDetailsFragment,"StepsDetailsFragment")
                        .addToBackStack(null).commit();
                }else{
                    sendMessage.SendMessage(String.valueOf(position-1));
                }

            }


        });
        rvRecipeDetails.setAdapter(recipeDetailsAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
