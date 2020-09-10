package com.sky7th.deliveryfood.address.domain;

import com.sky7th.deliveryfood.user.member.exception.MismatchMemberException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER_ADDRESS")
@NoArgsConstructor
@Getter
public class MemberAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ADDRESS_ID")
  private Long id;

  @Column(name = "MEMBER_ID")
  private Long memberId;

  @ManyToOne
  @JoinColumn(name = "BUILDING_MANAGEMENT_NUMBER")
  private Address address;

  @Column(name = "DETAILED_ADDRESS")
  private String detailedAddress;

  public MemberAddress(Long memberId, Address address, String detailedAddress) {
    this.memberId  = memberId;
    this.address = address;
    this.detailedAddress = detailedAddress;
  }

  public void update(Address address, String detailedAddress) {
    this.address = address;
    this.detailedAddress = detailedAddress;
  }

  public void checkMember(Long requestMemberId) {
    if (!this.memberId.equals(requestMemberId)) {
      throw new MismatchMemberException();
    }
  }
}
