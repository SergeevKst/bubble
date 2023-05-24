package com.generation.application.dto;

import java.math.BigDecimal;

public record StorehouseCreateUpdateDto(String materialName,
                                        BigDecimal balance,
                                        int materialCount) {

}
