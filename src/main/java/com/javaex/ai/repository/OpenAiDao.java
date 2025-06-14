package com.javaex.ai.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.ai.model.OpenAiVo;

@Repository
public class OpenAiDao {

	@Autowired
	private SqlSession sqlSession;

	   // 키워드로 FAQ 검색 (여러 개 결과 반환)
    public List<OpenAiVo> searchFaqByKeyword(String keyword) {
        return sqlSession.selectList("openAi.searchFaqByKeyword", keyword);
    }
    
//    public List<Map<String, Object>> getTableMetadata() {
//        return sqlSession.selectList("openAi.getTableMetadata");
//    }
    
    public List<OpenAiVo> getAllFaqs() {
        return sqlSession.selectList("openAi.getAllFaqs");
    }

}
