package com.javaex.option.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.option.model.HyunOptionTypeVO;
import com.javaex.option.model.HyunOptionValueVO;

@Repository
public class HyunOptionDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "hyunOptionMapper";

    // 1. 모든 옵션 유형 가져오기
    public List<HyunOptionTypeVO> getAllOptionTypes() {
        return sqlSession.selectList(NAMESPACE + ".getAllOptionTypes");
    }

    // 2. 특정 업체의 옵션 값 가져오기
    public List<HyunOptionValueVO> getOptionValuesByTypeAndVender(int venderId, int optionTypeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("venderId", venderId);
        params.put("optionTypeId", optionTypeId);
        return sqlSession.selectList(NAMESPACE + ".getOptionValuesByTypeAndVender", params);
    }
    // 3. 옵션 값 추가하기
    public void insertOptionValue(HyunOptionValueVO optionValueVO) {
        sqlSession.insert(NAMESPACE + ".insertOptionValue", optionValueVO);
    }
    
    // 4. 옵션 값 삭제하기
    public int deleteOptionValue(int optionValueId) {
        return sqlSession.delete(NAMESPACE + ".deleteOptionValue", optionValueId);
    }
    
    // 특정 옵션 ID 리스트를 기반으로 옵션 값 가져오기
    public List<HyunOptionValueVO> getOptionDetailsByIds(List<Integer> optionValueIds) {
        return sqlSession.selectList("hyunOptionMapper.getOptionDetailsByIds", optionValueIds);
    }

}
