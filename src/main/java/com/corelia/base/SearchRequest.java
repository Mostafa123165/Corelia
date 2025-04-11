package com.corelia.base;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class SearchRequest {

    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private String sortableColumn = "id" ;
    private Integer sortableType = 2 ;    //  1 for ascending , 2 for descending

    private Map<String, Object> searchCriteria = new HashMap<String, Object>();

    public void addSearchCriteria(String key, Object value) {
        getSearchCriteria().put(key, value);
    }
}
