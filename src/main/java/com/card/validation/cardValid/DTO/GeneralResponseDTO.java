package com.card.validation.cardValid.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseDTO {

	private boolean acknowledge;
	private String errorMessage;
	private String message;
	private Object responseObj;
	
}
