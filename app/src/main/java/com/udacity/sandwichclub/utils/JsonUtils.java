package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        JSONObject root = new JSONObject(json);
        JSONObject name = root.getJSONObject("name");
        String mainName = name.getString("mainName");
        List<String> alsoKnownAsList = new ArrayList<>();
        JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
        if (!alsoKnownAsArray.isNull(0)) {
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAsList.add(alsoKnownAsArray.getString(i));
            }
        }
        String placeOfOrigin = root.getString("placeOfOrigin");
        String description = root.getString("description");
        String image = root.getString("image");
        JSONArray ingredientsArray = root.getJSONArray("ingredients");
        List<String> ingredientsList = new ArrayList<>();
        if (!ingredientsArray.isNull(0)) {
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredientsList.add(ingredientsArray.getString(i));
            }
        }
        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
    }
}
