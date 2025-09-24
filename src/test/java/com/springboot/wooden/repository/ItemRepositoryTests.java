package com.springboot.wooden.repository;

import com.springboot.wooden.ItemRepository.ItemRepository;
import com.springboot.wooden.domain.Item;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Log4j2
public class ItemRepositoryTests {
    @Autowired
    private ItemRepository itemRepository;

    // 데이터 삭제
//    @Test
//    public void testDelete() {
//        Long itemNo = 1L;
//        itemRepository.deleteById(itemNo);
//    }

    // 데이터 수정
//    @Test
//    public void testModify() {
//        Long iteNo = 5L;
//
//        java.util.Optional<Item> result = itemRepository.findById(itemNo);
//
//        Item item = result.orElseThrow();
//        item.changeItemCode("코드");
//        item.changeItemName("이름");
//        item.changeItemSpec("스펙");
//        item.changeItemPrice(BigDecimal.valueOf(122));
//
//        itemRepository.save(item);
//    }

    // 데이터 조회
//    @Test
//    public void testRead() {
//        // 존재하는 번호로 확인
//        Long itemNo = 5L;
//        java.util.Optional<Item> result = itemRepository.findById(itemNo);
//        Item item = result.orElseThrow();
//        log.info(item);
//    }

    // 데이터 넣기
    @Test
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Item item = Item.builder().itemCode("상품코드"+i).itemName("상품이름" + i).itemSpec("상품규격" + i).itemPrice(BigDecimal.valueOf(10000 + (i*1000))).build();
            itemRepository.save(item);
        }
    }
}
