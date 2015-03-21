package com.acbenny.HouseExpenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acbenny.HouseExpenses.model.entity.Share;

public interface ShareRepository extends JpaRepository<Share, Integer> {

}
