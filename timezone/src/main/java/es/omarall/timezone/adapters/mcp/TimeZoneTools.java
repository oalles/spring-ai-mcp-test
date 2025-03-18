package es.omarall.timezone.adapters.mcp;

import es.omarall.timezone.TimeZone;
import es.omarall.timezone.TimeZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeZoneTools {

    private final TimeZoneService timeZoneService;

    @Tool(name = "timezone-tools" , description = "Get timezone from location, expressed in latitude and longitude")
    public Optional<TimeZone> getTimeZoneFromLocation(@ToolParam(description = "Latitude") double latitude,
                                                      @ToolParam(description = "Longitude") double longitude) throws Exception {
        return timeZoneService.getTimeZoneFromLocation(latitude, longitude);
    }


}
