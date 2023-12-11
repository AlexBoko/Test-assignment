package com.example.testassignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public boolean registerIncome(Socks socks) {
        if (socks.getColor() == null || socks.getCottonPart() < 0 || socks.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid socks data for income registration");
        }

        List<Socks> existingSocks = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());

        if (!existingSocks.isEmpty()) {
            Socks existingSock = existingSocks.get(0);
            existingSock.setQuantity(existingSock.getQuantity() + socks.getQuantity());
            socksRepository.save(existingSock);
        } else {
            socksRepository.save(socks);
        }
        return true;
    }

    public boolean registerOutcome(Socks socks) {
        if (socks.getColor() == null || socks.getCottonPart() < 0 || socks.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid socks data for outcome registration");
        }

        List<Socks> existingSocks = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());

        if (!existingSocks.isEmpty()) {
            Socks existingSock = existingSocks.get(0);
            int currentQuantity = existingSock.getQuantity();
            if (currentQuantity >= socks.getQuantity()) {
                existingSock.setQuantity(currentQuantity - socks.getQuantity());
                socksRepository.save(existingSock);
                return true;
            } else {
                throw new IllegalArgumentException("Not enough socks in stock for outcome registration");
            }
        } else {
            throw new IllegalArgumentException("No socks found in stock for outcome registration");
        }
    }

    public int getSocksQuantity(String color, String operation, int cottonPart) {
        if (!operation.equals("moreThan") && !operation.equals("lessThan") && !operation.equals("equal")) {
            throw new IllegalArgumentException("Invalid operation");
        }

        int quantity;
        if (operation.equals("moreThan")) {
            quantity = socksRepository.countByColorAndCottonPartGreaterThan(color, cottonPart);
        } else if (operation.equals("lessThan")) {
            quantity = socksRepository.countByColorAndCottonPartLessThan(color, cottonPart);
        } else {
            quantity = socksRepository.countByColorAndCottonPartEquals(color, cottonPart);
        }
        return quantity;
    }
}
