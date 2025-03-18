package es.omarall.geocoder;

public interface Geocoder {
    GeoCodeResult geocode(String city) throws Exception;
}
