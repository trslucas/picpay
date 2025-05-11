package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

public record MessageDTO(BigDecimal amount, String senderName, String receiverEmail, String message) {
}
