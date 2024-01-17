package praga.springframework.sfgrestdocsexample.web.mappers;

import praga.springframework.sfgrestdocsexample.domain.Beer;
import praga.springframework.sfgrestdocsexample.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-25.
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);

    Beer BeerDtoToBeer(BeerDto dto);
}
