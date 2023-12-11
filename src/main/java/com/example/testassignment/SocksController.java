package com.example.testassignment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public ResponseEntity<String> registerIncome(@RequestBody Socks socks) {
        try {
            boolean result = socksService.registerIncome(socks);
            if (result) {
                return ResponseEntity.ok("Socks income registered successfully");
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register socks income");
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> registerOutcome(@RequestBody Socks socks) {
        try {
            boolean result = socksService.registerOutcome(socks);
            if (result) {
                return ResponseEntity.ok("Socks outcome registered successfully");
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register socks outcome");
    }

    @GetMapping
    public ResponseEntity<String> getSocksQuantity(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam int cottonPart
    ) {
        try {
            int quantity = socksService.getSocksQuantity(color, operation, cottonPart);
            return ResponseEntity.ok(String.valueOf(quantity));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
