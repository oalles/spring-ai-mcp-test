package es.omarall.geocoder.adapters.repositories;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NominatimResponse(
        long place_id,
        String osm_type,
        @JsonProperty("osm_id")
        long osmId,
        @JsonProperty("lat") String latitude,
        @JsonProperty("lon") String longitude,
        @JsonProperty("class")
        String cl, // "class" is a reserved keyword in Java, so we use "clazz" instead
        String type,
        @JsonProperty("place_rank")
        int placeRank,
        double importance,

        @JsonProperty("addresstype")
        String addressType,
        String name,
        @JsonProperty("display_name")
        String displayName,
        @JsonProperty("boundingbox")
        String[] boundingBox
) {
}
