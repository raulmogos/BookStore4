package ro.ubb.bookstore.web.converter;

import ro.ubb.bookstore.core.model.BaseEntity;
import ro.ubb.bookstore.web.dto.BaseDto;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractConverterBaseEntity<Model extends BaseEntity<Long>, Dto extends BaseDto>
        extends AbstractConverter<Model, Dto> implements ConverterBaseEntity<Model, Dto>
{

    public Set<Long> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Long> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toSet());
    }

}