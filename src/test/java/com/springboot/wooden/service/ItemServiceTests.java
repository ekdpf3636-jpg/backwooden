package com.springboot.wooden.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ItemServiceTests {
    @Autowired
    private ItemService itemService;

    // 조회기능 구현
//    @Test
//    public void testGet() {
//        Long itemNo = 2L;
//        ItemDTO itemDTO = itemService.get(itemNo);
//        log.info(itemDTO);
//    }

    // 등록기능 구현
//    @Test
//    public void addItemTest(){
//        ItemDTO itemDTO = ItemDTO.builder().itemCode("상품코드").itemName("상품이름").itemSpec("상품규격").itemPrice(BigDecimal.valueOf(122)).build();
//        Long itemNo = itemService.addItem(itemDTO);
//        log.info("itemNo: " + itemNo);
//    }
}
