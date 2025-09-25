package com.springboot.wooden.dto;

import com.springboot.wooden.domain.Buyer;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuyerDTO {
    private Long buyerNo;
    private String buyerComp;
    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;
    private String buyerAddr;

    public static BuyerDTO fromEntity(Buyer e) {
        return BuyerDTO.builder()
                .buyerNo(e.getBuyerNo())
                .buyerComp(e.getBuyerComp())
                .buyerName(e.getBuyerName())
                .buyerEmail(e.getBuyerEmail())
                .buyerPhone(e.getBuyerPhone())
                .buyerAddr(e.getBuyerAddr())
                .build();
    }

    public Buyer toEntity() {
        Buyer b = new Buyer();
        b.setBuyerNo(this.buyerNo);
        b.setBuyerComp(this.buyerComp);
        b.setBuyerName(this.buyerName);
        b.setBuyerEmail(this.buyerEmail);
        b.setBuyerPhone(this.buyerPhone);
        b.setBuyerAddr(this.buyerAddr);
        return b;
    }
}
