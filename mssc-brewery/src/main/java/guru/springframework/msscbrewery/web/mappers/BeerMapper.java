package guru.springframework.msscbrewery.web.mappers;

import guru.springframework.msscbrewery.domain.v2.BeerV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDtoV2 beerToBeerDto(BeerV2 beerV2);
    BeerV2 beerDtoToBeer(BeerDtoV2 beerDtoV2);
}
