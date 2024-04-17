package com.card.validation.cardValid.Model;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CARD_DETAILS")
public class CardDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	private int userId;
	
	private String userName;
	
	private String cardBank;
	
	private String cardType;
	
	private String nameOnCard;
	
	private BigInteger cardNum;
	
	private String expiryDate;
	

}
