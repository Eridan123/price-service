package kg.ecommerce.priceservice.controller;

import kg.ecommerce.priceservice.models.dto.PriceDto;
import kg.ecommerce.priceservice.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceContoller {
  private final PriceService priceService;

  @GetMapping
  public List<PriceDto> getPrices(){
    return priceService.getPrices();
  }

  @GetMapping("/{id}")
  public PriceDto getPrice(@PathVariable Long id){
    return priceService.getPrice(id);
  }

  @PutMapping
  public void savePrices(@RequestBody PriceDto priceDto){
    priceService.savePrices(priceDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable Long id) {
    priceService.delete(id);
    return ResponseEntity.ok(true);
  }
}
