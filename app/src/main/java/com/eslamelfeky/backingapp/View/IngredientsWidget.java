package com.eslamelfeky.backingapp.View;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import com.eslamelfeky.backingapp.Model.IngredientsItem;
import com.eslamelfeky.backingapp.Model.RecipeResults;
import com.eslamelfeky.backingapp.R;
import com.google.gson.Gson;



public class IngredientsWidget extends AppWidgetProvider {
    SharedPreferences sharedPreferences;
    Gson gson=new Gson();
   static RemoteViews views;

   static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        // Construct the RemoteViews object
         views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
        sharedPreferences=context.getSharedPreferences("pre_widget",Context.MODE_PRIVATE);
        String ingredients=sharedPreferences.getString("ingredients",null);
        RecipeResults recipeResults=gson.fromJson(ingredients,RecipeResults.class);
        String allingredients="";
        int count=1;
        for (IngredientsItem ingredientsItem:recipeResults.getIngredients()) {
            allingredients+=count+"."+ingredientsItem.getIngredient()+"\n";
            count++;
        }
        for (int appWidgetId : appWidgetIds) {
            views.setTextViewText(R.id.appwidget_text,allingredients);
            views.setTextViewText(R.id.appwidget2_text,recipeResults.getName()+" Ingredients");
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }



    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

