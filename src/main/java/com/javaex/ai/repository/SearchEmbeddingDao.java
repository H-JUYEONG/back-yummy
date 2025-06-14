package com.javaex.ai.repository;

import com.javaex.ai.model.SearchEmbeddingVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SearchEmbeddingDao {

    @Autowired
    private SqlSession sqlSession;

    public List<SearchEmbeddingVo> getAllEmbeddings() {
        return sqlSession.selectList("searchEmbedding.getAllEmbeddings");
    }

    public void insert(SearchEmbeddingVo vo) {
        sqlSession.insert("searchEmbedding.insert", vo);
    }

    public boolean existsByTableNameAndOriginalId(String tableName, int originalId) {
        return sqlSession.selectOne("searchEmbedding.existsByTableNameAndOriginalId",
                Map.of("tableName", tableName, "originalId", originalId));
    }


}
