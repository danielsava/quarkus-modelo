package base.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static long toEpochSegundos(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(getZoneOffSet_Brasil_SaoPaulo());
    }

    public static ZoneOffset getZoneOffSet_Brasil_SaoPaulo() {
        return ZoneOffset.of("-03:00");
    }

}
