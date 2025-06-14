package com.javaex.option.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.option.model.HyunOptionTypeVO;
import com.javaex.option.model.HyunOptionValueVO;
import com.javaex.option.repository.HyunOptionDAO;

@Service
public class HyunOptionService {

	@Autowired
	private HyunOptionDAO hyunOptionDAO;

	// 1. 옵션 유형 및 옵션 값 가져오기
	public List<HyunOptionTypeVO> getOptionsWithValues(int venderId) {
		List<HyunOptionTypeVO> optionTypes = hyunOptionDAO.getAllOptionTypes();
		for (HyunOptionTypeVO optionType : optionTypes) {
			List<HyunOptionValueVO> optionValues = hyunOptionDAO.getOptionValuesByTypeAndVender(venderId,
					optionType.getOptionTypeId());
			optionType.setOptionValues(optionValues);
		}
		return optionTypes;
	}
	//2. 옵션 값 추가하기
	public void addOptionValue(HyunOptionValueVO optionValueVO) {
		hyunOptionDAO.insertOptionValue(optionValueVO);
	}

	//3. 옵션 값 삭제하기
	public void deleteOptionValue(int optionValueId) {
		int rowsAffected = hyunOptionDAO.deleteOptionValue(optionValueId);
		if (rowsAffected == 0) {
			throw new IllegalArgumentException("삭제할 수 있는 옵션 값이 없습니다: " + optionValueId);
		}
	}
	
    public List<HyunOptionValueVO> getOptionDetailsByIds(List<Integer> optionValueIds) {
        return hyunOptionDAO.getOptionDetailsByIds(optionValueIds);
    }
}
