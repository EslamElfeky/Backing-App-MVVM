package com.eslamelfeky.backingapp.Utails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.R;
import java.util.List;
import androidx.annotation.NonNull;

public class RecipeAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecipeViewHolder> {

  public   interface ClickListener{
        void onClickListener(int postion);
    }

  private List<RecipeResults> recipeResults;
    ClickListener clickListener;



  public void setClickListener(ClickListener clickListener){
      this.clickListener=clickListener;

  }
  public RecipeAdapter (List<RecipeResults> recipeResults){
    this.recipeResults=recipeResults;
  }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row,parent,false);
        RecipeViewHolder recipeViewHolder=new RecipeViewHolder(view);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
      holder.tvRecipe.setText(recipeResults.get(position).getName());
      holder.recipeCard.setOnClickListener(v -> {
          if(clickListener!=null){
              clickListener.onClickListener(position);
          }
      });

    }

    @Override
    public int getItemCount() {
        return recipeResults.size();
    }
}
