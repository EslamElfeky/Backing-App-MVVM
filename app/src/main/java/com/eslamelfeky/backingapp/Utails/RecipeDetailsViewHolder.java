package com.eslamelfeky.backingapp.Utails;

import android.view.View;
import android.widget.TextView;

import com.eslamelfeky.backingapp.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailsViewHolder extends RecyclerView.ViewHolder {
    TextView tvRecipeDetails;
    CardView recipeCardDetails;
    public RecipeDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        tvRecipeDetails=itemView.findViewById(R.id.tv_recipe_details_name);
        recipeCardDetails=itemView.findViewById(R.id.cv_recipe_details_card);
    }
}
