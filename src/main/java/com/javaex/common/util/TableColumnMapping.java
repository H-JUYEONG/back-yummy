package com.javaex.common.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableColumnMapping {

    /**
     * 메타데이터를 기반으로 테이블.컬럼 형태의 리스트 생성
     */
    public static List<String> generateMappingsFromMetadata(List<Map<String, Object>> metadata) {
        return metadata.stream()
                .map(row -> row.get("TABLE_NAME") + "." + row.get("COLUMN_NAME")) // "table.column" 형태로 반환
                .collect(Collectors.toList());
    }

    /**
     * tableColumnMappings를 기반으로 테이블.컬럼 형태의 리스트 생성
     */
    public static List<String> generateMappingsFromTableMappings(List<Map<String, String>> tableColumnMappings) {
        return tableColumnMappings.stream()
                .map(mapping -> mapping.get("tableName") + "." + mapping.get("searchColumn"))
                .collect(Collectors.toList());
    }
}
