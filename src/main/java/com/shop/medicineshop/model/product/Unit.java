package com.shop.medicineshop.model.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Integer unitId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rank", nullable = false)
    private Integer rank;//0 is highest rank. ex: hộp/vỉ/viên -> hộp :0, vỉ:1, viên:2

    @Column(name = "specifications")
    private Integer specifications;// quantity of unit, rank = 0 -> specifications = 1

    @ManyToMany(mappedBy = "units")
    private List<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Unit unit = (Unit) o;
        return getUnitId() != null && Objects.equals(getUnitId(), unit.getUnitId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
