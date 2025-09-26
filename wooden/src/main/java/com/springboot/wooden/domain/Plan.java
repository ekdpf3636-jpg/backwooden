package com.springboot.wooden.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "PLAN_TBL")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "plan_no")
    private Long planNo;

    // Item과 1:1 매핑 (FK: item_no)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "item_no",            // PLAN_TBL에 FK 컬럼 생성
            referencedColumnName = "item_no",
            nullable = false,
            unique = true                // 1:1 보장
    )
    private Item item;

    @Column(name = "plan_qty", nullable = false)
    private int planQty;
    @Column (name = "plan_state", nullable = false)
    private String planState; // boolean or string 둘중 하나 선택
    @Column (name = "plan_start_date", nullable = false)
    private LocalDate planStartDate;
    @Column (name = "plan_end_date", nullable = false)
    private LocalDate planEndDate;

    public void changePlanState(String planState) {
        this.planState = planState;
    }

    public void changePlanStartDate(LocalDate planStartDate) {
            this.planStartDate = planStartDate;
    }

    public void changePlanEndDate(LocalDate planEndDate) {
            this.planEndDate = planEndDate;
    }


}
