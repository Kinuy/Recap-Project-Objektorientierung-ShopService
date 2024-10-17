import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class IdServive {
    public String generateId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
