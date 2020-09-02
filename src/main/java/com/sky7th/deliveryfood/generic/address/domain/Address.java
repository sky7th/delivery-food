package com.sky7th.deliveryfood.generic.address.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "ADDRESS")
@Getter
public class Address {

  @Id
  @Column(columnDefinition = "VARCHAR(25)      NOT NULL    COMMENT '건물관리번호'")
  private String buildingManagementNumber;

  @Column(columnDefinition = "VARCHAR(10)      NULL        COMMENT '주소관할읍면동코드'")
  private String townCode;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '시도명'")
  private String cityName;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '시군구명'")
  private String countryName;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '읍면동명'")
  private String townName;

  @Column(columnDefinition = "VARCHAR(12)      NULL        COMMENT '도로명코드'")
  private String roadNameCode;

  @Column(columnDefinition = "VARCHAR(80)      NULL        COMMENT '도로명'")
  private String roadName;

  @Column(columnDefinition = "VARCHAR(1)       NULL        COMMENT '지하여부'")
  private String isUnderground;

  @Column(columnDefinition = "INT(5)           NULL        COMMENT '건물본번'")
  private Integer buildingNumber;

  @Column(columnDefinition = "INT(5)           NULL        COMMENT '건물부번'")
  private Integer buildingSideNumber;

  @Column(columnDefinition = "VARCHAR(5)       NULL        COMMENT '우편번호'")
  private String zipCode;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '시군구용건물명'")
  private String buildingNameForCountry;

  @Column(columnDefinition = "VARCHAR(100)     NULL        COMMENT '건축물용도분류'")
  private String buildingUseClassification;

  @Column(columnDefinition = "VARCHAR(10)      NULL        COMMENT '행정동코드'")
  private String administrativeBuildingCode;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '행정동명'")
  private String administrativeBuildingName;

  @Column(columnDefinition = "INT(3)           NULL        COMMENT '지상층수'")
  private Integer groundFloorNumber;

  @Column(columnDefinition = "INT(3)           NULL        COMMENT '지하층수'")
  private Integer undergroundFloorNumber;

  @Column(columnDefinition = "VARCHAR(1)       NULL        COMMENT '공동주택구분'")
  private String apartmentHouseSeparator;

  @Column(columnDefinition = "INT(10)          NULL        COMMENT '건물수'")
  private Integer buildingCount;

  @Column(columnDefinition = "VARCHAR(100)     NULL        COMMENT '상세건물명'")
  private String buildingDetailName;

  @Column(columnDefinition = "VARCHAR(1000)    NULL        COMMENT '건물명변경이력'")
  private String buildingNameChangeHistory;

  @Column(columnDefinition = "VARCHAR(1000)    NULL        COMMENT '상세건물명변경이력'")
  private String buildingDetailNameChangeHistory;

  @Column(columnDefinition = "VARCHAR(1)       NULL        COMMENT '거주여부'")
  private String isLiving;

  @Column(columnDefinition = "DOUBLE(15,6)     NULL        COMMENT '건물중심점_x좌표'")
  private Double buildingCenterPointX;

  @Column(columnDefinition = "DOUBLE(15,6)     NULL        COMMENT '건물중심점_y좌표'")
  private Double buildingCenterPointY;

  @Column(columnDefinition = "DOUBLE(15,6)     NULL        COMMENT '출입구_x좌표'")
  private Double entrancePointX;

  @Column(columnDefinition = "DOUBLE(15,6)     NULL        COMMENT '출입구_y좌표'")
  private Double entrancePointY;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '시도명_영문'")
  private String cityEngName;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '시군구명_영문'")
  private String countryEngName;

  @Column(columnDefinition = "VARCHAR(40)      NULL        COMMENT '읍면동명_영문'")
  private String townEngName;

  @Column(columnDefinition = "VARCHAR(80)      NULL        COMMENT '도로명_영문'")
  private String roadEngName;

  @Column(columnDefinition = "VARCHAR(1)       NULL        COMMENT '읍면동구분'")
  private String townSeparator;

  @Column(columnDefinition = "VARCHAR(2)       NULL        COMMENT '이동사유코드'")
  private String movingReasonCode;

  private Address() {
  }
}
