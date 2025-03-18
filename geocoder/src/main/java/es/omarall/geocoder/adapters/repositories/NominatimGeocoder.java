package es.omarall.geocoder.adapters.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.omarall.geocoder.GeoCodeResult;
import es.omarall.geocoder.Geocoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class NominatimGeocoder implements Geocoder {

    private static final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search?city=%s&format=json&limit=1";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public NominatimGeocoder() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public GeoCodeResult geocode(String city) throws Exception {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = String.format(NOMINATIM_URL, encodedCity);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "JavaGeocoderApp/1.0")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        NominatimResponse[] results = objectMapper.readValue(response.body(), NominatimResponse[].class);
        if (results.length == 0) {
            throw new RuntimeException("City not found: " + city);
        }

        double lat = Double.parseDouble(results[0].latitude());
        double lon = Double.parseDouble(results[0].longitude());
        return new GeoCodeResult(lat, lon);
    }
}