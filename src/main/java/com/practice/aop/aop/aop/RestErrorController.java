package com.practice.aop.aop.aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;

@RestControllerAdvice
public class RestErrorController {
    @Value("${web.webhoot.url}")
    private String url;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> responseError(RuntimeException runtimeException) {

        sentNotification(runtimeException.getMessage() + "\n" + runtimeException + "\n" +
                runtimeException.getStackTrace()[0]);

        return new ResponseEntity<>("Error dentro de la aplicacion", HttpStatus.BAD_GATEWAY);
    }


    private void sentNotification(String text) {
        HttpClient client = HttpClient.newHttpClient();

        String message = "{\n" +
                "    'cardsV2': [\n" +
                "        {\n" +
                "      'cardId': 'createCardMessage',\n" +
                "      'card': {\n" +
                "                \"header\": {\n" +
                "                    \"title\": \"Reporte de Error\",\n" +
                "                    \"subtitle\": \"Error app\",\n" +
                "                    \"imageUrl\": \"https://img.icons8.com/?size=256&id=44000&format=png\",\n" +
                "                    \"imageType\": \"CIRCLE\"\n" +
                "                },\n" +
                "                \"sections\": [\n" +
                "                    {\n" +
                "                        \"header\": \"Estado del Error\",\n" +
                "                        \"collapsible\": true,\n" +
                "                        \"uncollapsibleWidgetsCount\": 1,\n" +
                "                        \"widgets\": [\n" +
                "                            {\n" +
                "                                \"textParagraph\": {\n" +
                "                                    \"text\": \""+text+"\"\n" +
                "                                }\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        try {

            HttpRequest request = HttpRequest.newBuilder(
                            URI.create(url))
                    .header("accept", "application/json; charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(message))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Estado del envio de la notificacion: " + response.statusCode());
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
