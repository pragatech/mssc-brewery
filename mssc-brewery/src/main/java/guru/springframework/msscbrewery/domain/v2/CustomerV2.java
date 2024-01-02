package guru.springframework.msscbrewery.domain.v2;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerV2 {
    private UUID id;
    @NotBlank
    //@Min(value = 3)
    //@Max(value = 100)
    @Size(min = 3, max = 100)
    private String name;
}
