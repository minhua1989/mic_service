package com.cloud.commons.componet;

import com.cloud.commons.utils.CollectionUtils;
import com.cloud.commons.dao.BaseDao;
import com.cloud.commons.dao.bean.IArguments;
import com.cloud.commons.dao.bean.IParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by The_Answer on 2016/7/22.
 */
@Component
public class BaseDaoComponent implements BaseDao {

    private Logger logger = LoggerFactory.getLogger(BaseDaoComponent.class);

    @Autowired
    private BaseDao baseDao;

    @Override
    public List<Map<String, Object>> selectDataByParams(IParams params) throws SQLException {
        try {
            List<Map<String, Object>> data = baseDao.selectDataByParams(params);
            return CollectionUtils.converMapKeyToLowerCase(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new SQLException("查询失败");
        }

    }

    @Override
    public int updateDataByParams(IParams params) throws SQLException {
        try {
            return baseDao.updateDataByParams(params);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new SQLException("修改失败");
        }
    }

    public void batchUpateDataByParams(IParams... paramses) throws SQLException {
        for (IParams ip : paramses) {
            updateDataByParams(ip);
        }
    }

    @Override
    public void insertDataByParams(IParams params) throws SQLException {
        try {
            baseDao.insertDataByParams(params);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            try {
                logger.error(params.getExecuteSql());
            } catch (Exception ignore) {
            }
            throw new SQLException("新增失败");
        }
    }

    @Override
    public void deleteDataByParams(IParams params) throws SQLException {
        try {
            baseDao.deleteDataByParams(params);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new SQLException("删除失败");
        }
    }

    @Override
    public void createTableByParams(IParams params) throws SQLException {
        baseDao.createTableByParams(params);
    }

    @Override
    public void dropTableByParams(IParams params) throws SQLException {
        baseDao.dropTableByParams(params);
    }

    @Override
    public void alterTableByParams(IParams params) throws SQLException {
        baseDao.alterTableByParams(params);
    }

    @Override
    public List<Map<String, Object>> selectDataByArguments(IArguments arguments) throws SQLException {
        return baseDao.selectDataByArguments(arguments);
    }

}
