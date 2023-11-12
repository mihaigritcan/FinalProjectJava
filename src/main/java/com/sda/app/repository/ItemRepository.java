package com.sda.app.repository;

import com.sda.app.entity.Item;
import com.sda.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository <Item,Integer> {
    Optional<Item> searchByTitle(String title);
    Optional<Item>searchItemByTitle(String title);
}
