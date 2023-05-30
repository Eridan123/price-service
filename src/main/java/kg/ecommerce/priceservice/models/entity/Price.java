package kg.ecommerce.priceservice.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class Price {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sku", nullable = false)
  private String sku;

  @Column(name = "value", nullable = false)
  private Double value;

  @CreationTimestamp
  @Column(name = "created_date", updatable = false, nullable = false, columnDefinition = "TIMESTAMP")
  private LocalDateTime createdDate;

  @UpdateTimestamp
  @Column(name = "last_modified_date", nullable = false, columnDefinition = "TIMESTAMP")
  private LocalDateTime lastModifiedDate;
}
