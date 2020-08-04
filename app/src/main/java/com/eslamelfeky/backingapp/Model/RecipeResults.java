package com.eslamelfeky.backingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;


public class RecipeResults implements Serializable, Parcelable {

	@SerializedName("image")
	private String image;

	@SerializedName("servings")
	private double servings;

	@SerializedName("name")
	private String name;

	@SerializedName("ingredients")
	private List<IngredientsItem> ingredients;

	@SerializedName("id")
	private int id;

	@SerializedName("steps")
	private List<StepsItem> steps;

	protected RecipeResults(Parcel in) {
		image = in.readString();
		servings = in.readDouble();
		name = in.readString();
		id = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeDouble(servings);
		dest.writeString(name);
		dest.writeInt(id);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<RecipeResults> CREATOR = new Creator<RecipeResults>() {
		@Override
		public RecipeResults createFromParcel(Parcel in) {
			return new RecipeResults(in);
		}

		@Override
		public RecipeResults[] newArray(int size) {
			return new RecipeResults[size];
		}
	};

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setServings(double servings){
		this.servings = servings;
	}

	public double getServings(){
		return servings;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setIngredients(List<IngredientsItem> ingredients){
		this.ingredients = ingredients;
	}

	public List<IngredientsItem> getIngredients(){
		return ingredients;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSteps(List<StepsItem> steps){
		this.steps = steps;
	}

	public List<StepsItem> getSteps(){
		return steps;
	}
}