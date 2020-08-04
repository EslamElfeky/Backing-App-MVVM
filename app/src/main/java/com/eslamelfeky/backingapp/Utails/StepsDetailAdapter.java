package com.eslamelfeky.backingapp.Utails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eslamelfeky.backingapp.Model.StepsItem;
import com.eslamelfeky.backingapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StepsDetailAdapter extends RecyclerView.Adapter<RecipeDetailsViewHolder> {

    public   interface ClickListener{
        void onClickListener(int postion);
    }

    List<StepsItem> stepsItems;

    public StepsDetailAdapter(List<StepsItem> stepsItems) {
        this.stepsItems = stepsItems;
    }

    private StepsDetailAdapter.ClickListener clickListener;


    public void setClickListener(StepsDetailAdapter.ClickListener clickListener){
        this.clickListener=clickListener;

    }

    @NonNull
    @Override
    public RecipeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_recipe_details,parent,false);
        RecipeDetailsViewHolder recipeDetailsViewHolder=new RecipeDetailsViewHolder(view);
        return recipeDetailsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailsViewHolder holder, int position) {


        holder.tvRecipeDetails.setText(stepsItems.get(position).getDescription());


        holder.recipeCardDetails.setOnClickListener(v -> {
            if(clickListener!=null){
                clickListener.onClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stepsItems.size();
    }
}
