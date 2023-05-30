package kg.ecommerce.priceservice.repository;

import kg.ecommerce.priceservice.models.entity.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
  @Query(value = "select p from Price p order by p.id asc")
  List<Price> findAllByPage(Pageable page);

  Price findPriceBySku(String sku);

  @Modifying
  @Query(
          "update Price s set s.value = :value, s.lastModifiedDate = :lastModified where s.sku = :sku " +
                  "and s.lastModifiedDate < :lastModifiedDate")
  void saveUpdatedPrice(@Param("lastModifiedDate") LocalDateTime lastModifiedDate,
                        @Param("sku") String sku, @Param("value") Double value);
}
