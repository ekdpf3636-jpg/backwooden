package com.springboot.wooden.controller;

import com.springboot.wooden.dto.BuyerDTO;
import com.springboot.wooden.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/buyer/buyercustomer")
@RequiredArgsConstructor
public class BuyerController {

    private final BuyerService service;

    @GetMapping
    public List<BuyerDTO> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BuyerDTO get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<BuyerDTO> add(@PathVariable Long id, @RequestBody BuyerDTO dto){
        BuyerDTO saved = service.save(dto);
        URI location = URI.create("/api/buyer/buyercustomer/" + saved.getBuyerNo());
        return ResponseEntity.created(location).body(saved);
    }

    @PostMapping("/modify/{id}")
    public BuyerDTO modify(@PathVariable Long id, @RequestBody BuyerDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
