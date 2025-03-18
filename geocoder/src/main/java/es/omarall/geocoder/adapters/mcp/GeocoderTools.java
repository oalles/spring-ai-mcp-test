package es.omarall.geocoder.adapters.mcp;

import es.omarall.geocoder.GeoCodeResult;
import es.omarall.geocoder.Geocoder;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeocoderTools {

    private final Geocoder geocoder;

    @Tool(name = "geocoder-tools" , description = "Get geocode data from a city")
    public GeoCodeResult geocode(@ToolParam(description = "City to be geocoded") String address) throws Exception {
        return geocoder.geocode(address);
    }
}
