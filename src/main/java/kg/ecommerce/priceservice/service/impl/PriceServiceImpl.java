package kg.ecommerce.priceservice.service.impl;

import kg.ecommerce.priceservice.mapper.PriceMapper;
import kg.ecommerce.priceservice.models.dto.PriceDto;
import kg.ecommerce.priceservice.models.entity.Price;
import kg.ecommerce.priceservice.repository.ElasticSearchQuery;
import kg.ecommerce.priceservice.repository.PriceRepository;
import kg.ecommerce.priceservice.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
  private final PriceRepository priceRepository;
  private final PriceMapper priceMapper;
  private final ElasticSearchQuery elasticSearchQuery;

  @Override
  public List<PriceDto> getPrices() {
    return priceMapper.mapPriceListToPriceDtoList(priceRepository.findAll());
  }

  @Override
  public PriceDto getPrice(Long id) {
    return priceMapper.mapPriceToPriceDto(priceRepository.findById(id).get());
  }

  @Override
  public void savePrices(PriceDto priceDto) {
    Price a =priceMapper.mapPriceDtoToPrice(priceDto);
    priceRepository.saveAndFlush(a);
    try {
      elasticSearchQuery.createOrUpdateDocument(priceMapper.mapPriceToElasticPrice(a));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean delete(Long id) {
    priceRepository.deleteById(id);
    try {
      elasticSearchQuery.deleteDocumentById(String.valueOf(id));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return true;
  }

  Price findPriceBySku(String sku){
    return priceRepository.findPriceBySku(sku);
  }
}
