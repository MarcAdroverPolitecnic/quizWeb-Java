package org.example.quizweb.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.quizweb.model.Difficulty;
import org.example.quizweb.model.Question;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class QuestionApiService {
    private static final String LIST_ENDPOINT = "https://the-trivia-api.com/v2/questions?limit=1&difficulties=";

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new GsonBuilder().create();

    public QuestionApiService() {}

    //Mètode que agafa les preguntes de la API depenent de la dificultat
    public List<Question> fetchQuestionByDifficulty(Difficulty difficulty) throws IOException, InterruptedException {
        URI uri = URI.create(LIST_ENDPOINT + difficulty.getValue());

        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ensureSuccess(response, uri.toString());

        Type listType = new TypeToken<List<Question>>() {}.getType();
        return gson.fromJson(response.body(), listType);
    }

    private void ensureSuccess(HttpResponse<?> response, String url) {
        if (response.statusCode() >= 400) {
            throw new RuntimeException("La llamada " + url + " ha fallado con el código " + response.statusCode());
        }
    }
}
