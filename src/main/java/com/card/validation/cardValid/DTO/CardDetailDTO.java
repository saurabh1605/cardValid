package com.card.validation.cardValid.DTO;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDetailDTO {

	private String cardBank;

	private String cardType;

	private String nameOnCard;

	private BigInteger cardNum;

	private String expiryDate;
}
