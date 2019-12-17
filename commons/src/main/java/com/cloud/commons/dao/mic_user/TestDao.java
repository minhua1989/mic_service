package com.cloud.commons.dao.mic_user;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestDao {

    List<Map<String, Object>> selectSchSql(Map<String, Object> map);

}
