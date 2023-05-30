package kg.ecommerce.priceservice.service;

import kg.ecommerce.priceservice.models.dto.PriceDto;

import java.util.List;

public interface PriceService {
  List<PriceDto> getPrices();

  PriceDto getPrice(Long id);
  void savePrices(PriceDto priceDtos);

  boolean delete(Long id);
}
