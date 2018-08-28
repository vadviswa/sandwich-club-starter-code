package com.udacity.sandwichclub.utils;

import android.util.JsonReader;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {

            JSONObject root = new JSONObject(json);

            JSONObject name = root.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            JSONArray alsoKnownAsJsonArray = name.getJSONArray("alsoKnownAs");
            List<String> modelAlsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                modelAlsoKnownAs.add((String) alsoKnownAsJsonArray.get(i));
            }
            sandwich.setAlsoKnownAs(modelAlsoKnownAs);
            sandwich.setPlaceOfOrigin(root.getString("placeOfOrigin"));
            sandwich.setDescription(root.getString("description"));
            sandwich.setImage(root.getString("image").trim());

            JSONArray jsonIngredients = root.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();
            for (int i = 0; i < jsonIngredients.length(); i++) {
                ingredients.add((String) jsonIngredients.get(i));
            }
            sandwich.setIngredients(ingredients);


        } catch (JSONException e) {
            Log.e("Sandwich.JsonUtils", e.getMessage());
        }

        return sandwich;
    }
}
