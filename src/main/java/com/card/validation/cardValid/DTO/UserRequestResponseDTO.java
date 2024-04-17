package com.card.validation.cardValid.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestResponseDTO {

	private String userId;

	private String userName;
	
	private boolean isCardAvail;
	
	private int countOfCards;
	
	private List<CardDetailDTO> cardDetails;
}
