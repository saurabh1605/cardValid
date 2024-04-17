package com.card.validation.cardValid.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.card.validation.cardValid.Config.CardUtil;
import com.card.validation.cardValid.DTO.ApplicationConstants;
import com.card.validation.cardValid.DTO.CardDetailDTO;
import com.card.validation.cardValid.DTO.GeneralResponseDTO;
import com.card.validation.cardValid.DTO.UserRequestResponseDTO;
import com.card.validation.cardValid.Model.CardDetails;
import com.card.validation.cardValid.Repository.CardValidRepository;

@Service
public class ValidationService {

	@Autowired
	CardValidRepository cardValidRepository;

	@Autowired
	ModelMapper modelMapper;

	@SuppressWarnings("static-access")
	public GeneralResponseDTO saveCardDetails(@RequestBody UserRequestResponseDTO cardLists) {
		GeneralResponseDTO resDto = new GeneralResponseDTO();
		if (Objects.nonNull(cardLists.getCardDetails()) && !cardLists.getCardDetails().isEmpty()) {
			List<CardDetails> modelList = cardLists.getCardDetails().stream().map(i -> {
				CardDetails model = new CardDetails();
				modelMapper.map(i, model);
				model.setUserId(Integer.parseInt(cardLists.getUserId()));
				model.setUserName(cardLists.getUserName());
				return model;
			}).collect(Collectors.toList());
			List<CardDetails> resCardList = cardValidRepository.saveAll(modelList);
			List<CardDetailDTO> dtoList = new ArrayList<>();
			for (CardDetails cardDetails : resCardList) {
				CardDetailDTO dto = new CardDetailDTO();
				modelMapper.map(cardDetails, dto);
				dtoList.add(dto);
			}
			cardLists.setCardDetails(dtoList);
			resDto.builder().acknowledge(true).responseObj(cardLists).message(ApplicationConstants.SUCCESS).build();
		}
		return resDto;
	}

	public GeneralResponseDTO validateCard(CardDetailDTO cardDTO) {
		GeneralResponseDTO resDto = new GeneralResponseDTO();
		if (CardUtil.isCardNumberValid(Long.parseLong(cardDTO.getCardNum().toString()))
				&& CardUtil.isExpiryValid(cardDTO.getExpiryDate()) && CardUtil.isNameValid(cardDTO.getNameOnCard())
				&& CardUtil.isCVVValid()) {
			GeneralResponseDTO.builder().acknowledge(true).message("Card Validated Successfully").build();
		} else {
			GeneralResponseDTO.builder().acknowledge(false).message("Card Validation Failed").build();
		}
		return resDto;
	}

	public GeneralResponseDTO getCardsByUser(String userId, String userName) {

		return null;
	}

	public GeneralResponseDTO updateCard(CardDetailDTO cardDTO) {

		return null;
	}

	public GeneralResponseDTO deleteCard(BigInteger cardNumber) {

		return null;
	}
}
