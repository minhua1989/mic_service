package com.cloud.commons.dao.pojo;

import java.io.Serializable;

/**
 * created by lrs on 2019/3/14
 */
public class FileMgrInfo implements Serializable {

    private static final long serialVersionUID = -7064820907230945301L;

    private String cdnFilepath;

    private byte[] fileByte;

    public String getCdnFilepath() {
        return cdnFilepath;
    }

    public void setCdnFilepath(String cdnFilepath) {
        this.cdnFilepath = cdnFilepath;
    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }
}
