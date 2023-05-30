package kg.ecommerce.priceservice.mapper;

import kg.ecommerce.priceservice.models.dto.PriceDto;
import kg.ecommerce.priceservice.models.elastic.ElasticPrice;
import kg.ecommerce.priceservice.models.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = org.mapstruct.MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

  List<PriceDto> mapPriceListToPriceDtoList(List<Price> prices);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "sku", target = "sku")
  @Mapping(source = "value", target = "value")
  @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
  @Mapping(source = "createdDate", target = "createdDate")
  PriceDto mapPriceToPriceDto(Price price);

  List<Price> mapPriceDtoListToPriceList(List<PriceDto> priceDtos);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "sku", target = "sku")
  @Mapping(source = "value", target = "value")
  @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
  @Mapping(source = "createdDate", target = "createdDate")
  Price mapPriceDtoToPrice(PriceDto priceDto);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "sku", target = "sku")
  @Mapping(source = "value", target = "value")
  @Mapping(source = "lastModifiedDate", target = "lastModifiedDate")
  @Mapping(source = "createdDate", target = "createdDate")
  ElasticPrice mapPriceToElasticPrice(Price price);
}
