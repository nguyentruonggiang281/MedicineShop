package com.shop.medicineshop.service.product;

import com.shop.medicineshop.model.product.Asset;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.product.Unit;
import com.shop.medicineshop.repository.product.ProductRepository;
import com.shop.medicineshop.repository.product.UnitRepository;
import com.shop.medicineshop.response.product.AssetDTO;
import com.shop.medicineshop.response.product.UnitDTO;
import com.shop.medicineshop.response.product.UnitDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private ProductRepository productRepository;
@Autowired
private UnitDTOMapper unitDTOMapper;


    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }



    public int getIdUnitByNameAndRankAndSpecifications(String name, Integer rank, Integer specifications) {
            Optional<Unit> optionalUnit = unitRepository.findByNameAndRankAndSpecifications(name, rank, specifications);
            if (optionalUnit.isPresent()) {
                return optionalUnit.get().getUnitId();
            }else {
                return -1;
            }
    }

    public List<UnitDTO> getProductUnits(Integer id) {
        Product product = productRepository.getProductsById(id);
        List<UnitDTO> unitDTOS = unitDTOMapper.mapUnitsToDTO(unitRepository.findByProducts_Id(id));
//        for (Unit unit : unitRepository.findByProducts_Id(id)) {
//            unitDTOS.add(unitDTOMapper.apply(unit));
//        }
//        List<UnitDTO> unitTemp = new ArrayList<>(unitDTOS);
//        Collections.reverse(unitTemp);


        for (int i = 0; i < unitDTOS.size(); i++) {
            int index = unitDTOS.size() - i - 1; // calculate index of element to get specifications from
            int specifications = unitDTOS.get(index).getSpecifications();
            float priceUnit = specifications * product.getPrice();
            unitDTOS.get(i).setPriceUnit(priceUnit);
        }

        return unitDTOS;
    }

    public void setPriceListUnitDTO(List<UnitDTO> unitDTOList,UnitDTO unitDTO, Product product) {
        Collections.reverse(unitDTOList);
        for (UnitDTO u : unitDTOList) {
            unitDTO.setPriceUnit(product.getPrice() * u.getSpecifications());
        }
    }
}
