package com.postech30.msitems.repository;

import com.postech30.msitems.model.Item;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Override
    Page<Item> findAll(Pageable pageable);

    Page<Item> findByUserId(int userId, Pageable pageable);
}
