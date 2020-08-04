package com.eslamelfeky.backingapp.Utails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eslamelfeky.backingapp.Model.IngredientsItem;
import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    RecipeResults recipeResults;
    int ingredientsSize;
    int stepsSize;
    int listSize;
    String ingredient="";
    public   interface ClickListener{
        void onClickListener(int postion);
    }

    private RecipeDetailsAdapter.ClickListener clickListener;


    public void setClickListener(RecipeDetailsAdapter.ClickListener clickListener){
        this.clickListener=clickListener;

    }

    public RecipeDetailsAdapter(RecipeResults recipeResults) {
        this.recipeResults = recipeResults;
         ingredientsSize=recipeResults.getIngredients().size();
         stepsSize=recipeResults.getSteps().size();
         listSize=stepsSize+1;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_recipe_details,parent,false);
        RecipeDetailsViewHolder recipeDetailsViewHolder=new RecipeDetailsViewHolder(view);
        return recipeDetailsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecipeDetailsViewHolder  recipeDetailsHolder=(RecipeDetailsViewHolder) holder;
        if(position==0){
            int i=1;
            for(IngredientsItem temp:recipeResults.getIngredients()){
                ingredient+=i+"."+temp.getIngredient()+"\n  quantity: "+temp.getQuantity()+" "+temp.getMeasure()+"\n\n";
                i++;
            }
            recipeDetailsHolder.tvRecipeDetails.setText("ingredient \n\n"+ingredient);
        }
        else{
            recipeDetailsHolder.tvRecipeDetails.setText(recipeResults.getSteps().get(position-1).getShortDescription());
        }

        recipeDetailsHolder.recipeCardDetails.setOnClickListener(v -> {
            if(clickListener!=null){
                clickListener.onClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listSize;
    }
}
