import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    // Clave API y URL base con la clave incluida tal como brindó el usuario
    private static final String API_KEY = "e7287f28f4cca881991df892";
    private static final String API_URL_BASE = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    private final HttpClient httpClient;
    private final Gson gson;

    public CurrencyConverter() {
        httpClient = HttpClient.newHttpClient();
        gson = new Gson();
    }

    // Método para obtener las tasas de cambio desde la API para la moneda_base dada
    public RatesResponse obtenerTasas(String monedaBase) throws IOException, InterruptedException {
        String url = API_URL_BASE + monedaBase.toUpperCase();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error al obtener datos de la API, código HTTP: " + response.statusCode());
        }

        RatesResponse ratesResponse = gson.fromJson(response.body(), RatesResponse.class);
        if (!"success".equalsIgnoreCase(ratesResponse.result)) {
            throw new IOException("Error en respuesta API: " + ratesResponse.result);
        }
        return ratesResponse;
    }

    // Convierte la cantidad desde monedaOrigen a monedaDestino usando las tasas obtenidas de la API
    public double convertir(String monedaOrigen, String monedaDestino, double cantidad) throws IOException, InterruptedException {
        RatesResponse ratesResponse = obtenerTasas(monedaOrigen);
        Double tasaCambio = ratesResponse.conversion_rates.get(monedaDestino.toUpperCase());
        if (tasaCambio == null) {
            throw new IllegalArgumentException("Moneda destino no soportada: " + monedaDestino);
        }
        return cantidad * tasaCambio;
    }

    public static void main(String[] args) {
        CurrencyConverter conversor = new CurrencyConverter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido al Conversor de Monedas usando exchangerate-api.com!");
        try {
            while (true) {
                System.out.print("Introduce la moneda base (ejemplo USD) o 'salir' para terminar: ");
                String monedaOrigen = scanner.nextLine().trim();
                if (monedaOrigen.equalsIgnoreCase("salir")) break;

                System.out.print("Introduce la moneda destino (ejemplo EUR): ");
                String monedaDestino = scanner.nextLine().trim();

                System.out.print("Introduce la cantidad a convertir: ");
                String cantidadStr = scanner.nextLine().trim();
                double cantidad;
                try {
                    cantidad = Double.parseDouble(cantidadStr);
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad no válida, por favor ingresa un número.");
                    continue;
                }

                try {
                    double resultado = conversor.convertir(monedaOrigen, monedaDestino, cantidad);
                    System.out.printf("%.2f %s equivalen a %.2f %s%n",
                            cantidad, monedaOrigen.toUpperCase(),
                            resultado, monedaDestino.toUpperCase());
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (IOException | InterruptedException ex) {
                    System.out.println("Error obteniendo las tasas de cambio: " + ex.getMessage());
                }
            }
        } finally {
            scanner.close();
        }
        System.out.println("¡Gracias por usar el Conversor de Monedas!");
    }

    // Clase modelo para mapear la respuesta JSON de exchangerate-api.com
    static class RatesResponse {
        String result;
        String documentation;
        String terms_of_use;
        String time_last_update_unix;
        String time_last_update_utc;
        String time_next_update_unix;
        String time_next_update_utc;
        String base_code;
        Map<String, Double> conversion_rates;
    }
}
