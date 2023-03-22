package net.m_hgn.minegpt.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Gpt3Api {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    public static final String MODEL_NAME = "gpt-3.5-turbo";
    public static String API_KEY = "NO_KEY";

    public static String queryAPI(String query) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        String input = "{\"model\": \"" + MODEL_NAME
                + "\", \"messages\": [{\"role\":\"user\", \"content\": \""
                + sanitizedQuery(query) + "\"}], \"max_tokens\": 1024}";

        connection.setDoOutput(true);
        connection.getOutputStream().write(input.getBytes());

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return new Gson().fromJson(response.toString(), JsonElement.class)
                .getAsJsonObject().getAsJsonArray("choices").get(0).getAsJsonObject()
                .getAsJsonObject("message")
                .get("content").getAsString();
    }

    private static String sanitizedQuery(String query) {
        return query
                .replace("\"", "\\\"")
                .replace("\\", "\\\\");
    }
}
