package com.card.validation.cardValid.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.card.validation.cardValid.Model.CardDetails;

@Repository
public interface CardValidRepository extends JpaRepository<CardDetails, Integer> {

	@Query("select a from CardDetails a where a.userId=:userId")
	public CardDetails findByUserId(int userId);
}
