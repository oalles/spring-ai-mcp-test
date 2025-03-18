package es.omarall.timezone.adapters.repositories;

import es.omarall.timezone.TimeZone;
import es.omarall.timezone.TimeZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import us.dustinj.timezonemap.TimeZoneMap;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeZoneServiceImpl implements TimeZoneService {

    private final TimeZoneMap timeZoneMap;

    @Override
    public Optional<TimeZone> getTimeZoneFromLocation(double latitude, double longitude) throws Exception {

        Optional<String> maybeZoneId = Optional.ofNullable(
                timeZoneMap
                        .getOverlappingTimeZone(latitude, longitude)
                        .getZoneId()
        );

        return maybeZoneId.map(java.util.TimeZone::getTimeZone).map(tz -> new TimeZone(tz.getID(), tz.getDisplayName(),
                tz.getRawOffset(), tz.getDSTSavings()));
    }
}