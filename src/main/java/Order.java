import lombok.With;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

@With
public record Order(
        String id,
        List<Product> products,
        OrderStatus status,
        ZonedDateTime timestamp


) {
}
