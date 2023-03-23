package com.mizuhogroup.lob.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findBySide(Character side, Sort sort);

    default List<OrderEntity> findBySide(Character side) {
        Sort sort;
        if (side == 'B') {
            sort = Sort.by("price").descending().and(Sort.by("createdDate"));
        } else {
            sort = Sort.by("price").and(Sort.by("createdDate"));
        }
        return findBySide(side, sort);
    }
}
