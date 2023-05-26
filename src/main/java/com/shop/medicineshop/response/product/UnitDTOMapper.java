package com.shop.medicineshop.response.product;

import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Unit;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Builder
public class UnitDTOMapper implements Function<Unit, UnitDTO> {
    @Override
    public UnitDTO apply(Unit unit) {
        return new UnitDTO(
//                unit.getUnitId(),
                unit.getName(),
                unit.getRank(),
                unit.getSpecifications()
        );
    }

    public List<UnitDTO> mapUnitsToDTO(List<Unit> products) {
        return products.stream()
                .map(this::apply)
                .collect(Collectors.toList());
    }
}
