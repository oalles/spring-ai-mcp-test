package es.omarall.timezone;

public record TimeZone(
        String id,
        String name,
        int rawOffset,
        int dstSavings
) {
}
