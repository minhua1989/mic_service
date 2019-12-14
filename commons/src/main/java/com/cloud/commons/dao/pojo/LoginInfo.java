package com.cloud.commons.dao.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huamin on 2018/5/30.
 * 用户信息结构（保存于Session中）
 */
public class LoginInfo {

    private String accountid;//账号id

    private String accountname;//账号名

    private String ename;//用户名

    private String modifyPassWordTime;//记录修改密码时间

    private String userid;//用户信息id

    private String realname;//真实姓名

    private String roleid;//角色id

    private String roletype;//角色类别

    private String unit;    // 归属处室(单位)

    private String type;    // 管理员类型

    private String roletypename;

    private String districtid;

    private String districtcode;

    private String schoolid;

    private String njid;    // 年级id

    private String classid; // 班级id

    private String schoolcode;

    private String schoolname;//学校名称

    private String currentURL;//接口地址

    private String currentURLName;//接口名称

    private String ipAddress;//ip

    private String xq;  //学期

    private String urlPrefix; // 项目地址

    private String cdnPrefix; // cdn前缀

    private String avatar; // 头像

    private String clientIp; // 客户端ip

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNjid() {
        return njid;
    }

    public void setNjid(String njid) {
        this.njid = njid;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getModifyPassWordTime() {
        return modifyPassWordTime;
    }

    public void setModifyPassWordTime(String modifyPassWordTime) {
        this.modifyPassWordTime = modifyPassWordTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchoolcode() {
        return schoolcode;
    }

    public void setSchoolcode(String schoolcode) {
        this.schoolcode = schoolcode;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getCurrentURL() {
        return currentURL;
    }

    public void setCurrentURL(String currentURL) {
        this.currentURL = currentURL;
    }

    public String getCurrentURLName() {
        return currentURLName;
    }

    public void setCurrentURLName(String currentURLName) {
        this.currentURLName = currentURLName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getRoletypename() {
        return roletypename;
    }

    public void setRoletypename(String roletypename) {
        this.roletypename = roletypename;
    }

    public static LoginInfo createLoginInfoByMap(Map<String, Object> map) throws InvocationTargetException, IllegalAccessException {
        LoginInfo rs = new LoginInfo();
        Field[] fields = LoginInfo.class.getDeclaredFields();
        List<Method> setMethods = getAllSetMethods(LoginInfo.class);
        for (Field field : fields) {
            Object value = map.get(field.getName());
            if (value != null) {
                Method method = searchSetMethodNameByFieldName(field.getName(), setMethods);
                if (method != null) method.invoke(rs, value);
            }
        }
        return rs;
    }

    private static Method searchSetMethodNameByFieldName(String fieldName, List<Method> methods) {
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("set" + fieldName)) return method;
        }
        return null;
    }

    private static List<Method> getAllSetMethods(Class clz) {
        Method[] methods = clz.getDeclaredMethods();
        List<Method> setMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) setMethods.add(method);
        }
        return setMethods;
    }
}
