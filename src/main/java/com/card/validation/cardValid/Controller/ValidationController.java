package com.card.validation.cardValid.Controller;

import java.math.BigInteger;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.validation.cardValid.DTO.CardDetailDTO;
import com.card.validation.cardValid.DTO.GeneralResponseDTO;
import com.card.validation.cardValid.DTO.UserRequestResponseDTO;
import com.card.validation.cardValid.Service.ValidationService;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/card")
@Slf4j
public class ValidationController {
	
	@Autowired
	ValidationService validationService;

	public ResponseEntity<GeneralResponseDTO> saveCardDetails(@RequestBody UserRequestResponseDTO cardLists) {
		log.info("method ::{saveCardDetails} Object ::{} ",cardLists);
		GeneralResponseDTO resposneDTO = new GeneralResponseDTO();
		try {
			if (Objects.nonNull(cardLists)) {
				resposneDTO =validationService.saveCardDetails(cardLists);
			} else {
				log.error("Object is Null",cardLists);
				resposneDTO.setAcknowledge(false);
				resposneDTO.setErrorMessage("Card List is Empty");
				return new ResponseEntity<GeneralResponseDTO>(resposneDTO, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {

		}
		return new ResponseEntity<GeneralResponseDTO>(resposneDTO, HttpStatus.OK);
	}
	
	public ResponseEntity<GeneralResponseDTO> validateCard(@RequestBody CardDetailDTO cardDTO){
		log.info("method ::{saveCardDetails} Object ::{} ",cardDTO);
		GeneralResponseDTO resposneDTO = new GeneralResponseDTO();
		try {
			if(Objects.nonNull(cardDTO)) {
				validationService.validateCard(cardDTO);
			}
		}catch(Exception e) {
			
		}
		
		return new ResponseEntity<GeneralResponseDTO>(resposneDTO, HttpStatus.OK);
	}
	
	public ResponseEntity<GeneralResponseDTO> getCardsByUser(@PathVariable String userId, @PathVariable String userName){
		log.info("method ::{saveCardDetails} Object ::{}::{} ",userId,userName);
		GeneralResponseDTO resposneDTO = new GeneralResponseDTO();
		try {
			if(!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(userName)) {
				validationService.getCardsByUser(userId,userName);
			}
		}catch(Exception e) {
			
		}
		
		return new ResponseEntity<GeneralResponseDTO>(resposneDTO, HttpStatus.OK);
	}
	
	public ResponseEntity<GeneralResponseDTO> updateCard(@RequestBody CardDetailDTO cardDTO){
		log.info("method ::{saveCardDetails} Object ::{} ",cardDTO);
		GeneralResponseDTO resposneDTO = new GeneralResponseDTO();
		try {
			if(Objects.nonNull(cardDTO)) {
				validationService.updateCard(cardDTO);
			}
		}catch(Exception e) {
			
		}
		
		return new ResponseEntity<GeneralResponseDTO>(resposneDTO, HttpStatus.OK);
	}
	
	public ResponseEntity<GeneralResponseDTO> deleteCard(@PathVariable BigInteger cardNumber){
		log.info("method ::{saveCardDetails} Object ::{} ",cardNumber);
		GeneralResponseDTO resposneDTO = new GeneralResponseDTO();
		try {
			if(Objects.nonNull(cardNumber)) {
				validationService.deleteCard(cardNumber);
			}
		}catch(Exception e) {
			
		}
		
		return new ResponseEntity<GeneralResponseDTO>(resposneDTO, HttpStatus.OK);
	}

}
