import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        String id = postRequest();
        getRequest(id);
    }

    private static String postRequest() throws URISyntaxException, IOException, InterruptedException {
        Transcript transcript = new Transcript();
        transcript.setAudio_url("https://raw.githubusercontent.com/johnmarty3/JavaAPITutorial/main/Thirsty.mp4");

        // biblioteka gson służy do przekształcania obiektów Java na ich reprezentacje w
        // formacie JSON (JavaScript Object Notation) oraz do deserializacji obiektów JSON z powrotem do obiektów Java

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);


        //tworzenie requesta
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "155c13e22ca14434821c982da8fcee50")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();


        //do wysłania requesta potrzebny jest obiekt HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString()); //mówimy metodzie, że w responsie oczekujemy tutaj jakiegoś stringa

        //tworzymy obiekt Transcript z ciała naszego responsa przy pomocy gson'a
        Transcript transcriptWithId = gson.fromJson(postResponse.body(), Transcript.class);
        String id = transcriptWithId.getId();

        //zwracam to id do użycia w metodzie getRequest() niżej;
        return id;
    }

    private static void getRequest(String id) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        Transcript transcript = new Transcript();


        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/" + id))
                .header("Authorization", "155c13e22ca14434821c982da8fcee50")
                .GET()
                .build();


        //dopoki "status" nie zmieni się na completed. Po completed mamy już gotowy obiekt transcription z polem text uzupełnionym
        while (true) {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            String status = transcript.getStatus();

            System.out.println(status);

            if (status.equals("completed") || status.equals("error")){
                break;
            }

            Thread.sleep(1000);
        }

        System.out.println("Transcription completed, here is text sourced from your file:");
        System.out.println(transcript.getText());

    }
}
