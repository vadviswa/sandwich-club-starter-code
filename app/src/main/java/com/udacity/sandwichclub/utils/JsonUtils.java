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

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {

            JSONObject root = new JSONObject(json);

            JSONObject name = root.getJSONObject(NAME);
            sandwich.setMainName(name.getString(MAIN_NAME));
            JSONArray alsoKnownAsJsonArray = name.getJSONArray(ALSO_KNOWN_AS);
            List<String> modelAlsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                modelAlsoKnownAs.add((String) alsoKnownAsJsonArray.get(i));
            }
            sandwich.setAlsoKnownAs(modelAlsoKnownAs);
            sandwich.setPlaceOfOrigin(root.getString(PLACE_OF_ORIGIN));
            sandwich.setDescription(root.getString(DESCRIPTION));
            sandwich.setImage(root.getString(IMAGE).trim());

            JSONArray jsonIngredients = root.getJSONArray(INGREDIENTS);
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
