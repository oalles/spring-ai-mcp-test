package es.omarall.timezone;

import java.util.Optional;

public interface TimeZoneService {
    Optional<TimeZone> getTimeZoneFromLocation(double latitude, double longitude) throws Exception;
}
