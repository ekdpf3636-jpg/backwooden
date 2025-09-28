package com.springboot.wooden.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "BOM_TBL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"item_no","part_no"}))
@Getter
@ToString(exclude = {"item","part"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BOM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bom_id")
    private Long bomId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_no", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "part_no", nullable = false)
    private Part part;

    @Column(name = "qty_per_item", nullable = false)
    private int qtyPerItem; // 완제품 1개당 필요한 해당 부품 수량
}
