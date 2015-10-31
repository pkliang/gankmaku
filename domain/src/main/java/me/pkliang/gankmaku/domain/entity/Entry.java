package me.pkliang.gankmaku.domain.entity;

/**
 * Created by Omistaja on 8/10/2015.
 */
public class Entry {
  /**
   * updatedAt : 2015-08-10T04:09:39.939Z
   * desc : 8.10
   * createdAt : 2015-08-10T03:54:05.215Z
   * objectId : 55c8205d00b099ad0ea5f03b
   * used : true
   * type : 福利
   * url : http://ww4.sinaimg.cn/large/610dc034gw1euxdmjl7j7j20r2180wts.jpg
   * who : daimajia
   */
  private String updatedAt;
  private String desc;
  private String createdAt;
  private String objectId;
  private boolean used;
  private String type;
  private String url;
  private String who;

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setObjectId(String objectId) {
    this.objectId = objectId;
  }

  public void setUsed(boolean used) {
    this.used = used;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setWho(String who) {
    this.who = who;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public String getDesc() {
    return desc;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getObjectId() {
    return objectId;
  }

  public boolean isUsed() {
    return used;
  }

  public String getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }

  public String getWho() {
    return who;
  }
}
