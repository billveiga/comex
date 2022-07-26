package br.com.alura.comex.compartilhado.adapter.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CacheController {

    @GetMapping("/aW52YWxpZGEgcmVsYXTDs3JpbyBkZSB2ZW5kYXM")
    @CacheEvict(value = "relatorioPedidosPorCategoria", allEntries = true)
    public ResponseEntity<?> invalidarCache() {
        return ResponseEntity.noContent().build();
    }
}
