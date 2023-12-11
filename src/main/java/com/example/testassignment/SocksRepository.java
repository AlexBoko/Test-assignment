package com.example.testassignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    List<Socks> findByColorAndCottonPart(String color, int cottonPart);

    int countByColorAndCottonPartGreaterThan(String color, int cottonPart);

    int countByColorAndCottonPartLessThan(String color, int cottonPart);

    int countByColorAndCottonPartEquals(String color, int cottonPart);
}
