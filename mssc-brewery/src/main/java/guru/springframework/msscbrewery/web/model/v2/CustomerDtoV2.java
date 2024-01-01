package guru.springframework.msscbrewery.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDtoV2 {
    private UUID id;
    @NotBlank
    //@Min(value = 3)
    //@Max(value = 100)
    @Size(min = 3, max = 100)
    private String name;
}
