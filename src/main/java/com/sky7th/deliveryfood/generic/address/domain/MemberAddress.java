package com.sky7th.deliveryfood.generic.address.domain;

import com.sky7th.deliveryfood.user.member.domain.Member;
import com.sky7th.deliveryfood.user.member.service.exception.MismatchMemberException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "MEMBER_ADDRESS")
@Getter
public class MemberAddress {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ADDRESS_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "MEMBER_ID")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "BUILDING_MANAGEMENT_NUMBER")
  private Address address;

  @Column(name = "DETAILED_ADDRESS")
  private String detailedAddress;

  public MemberAddress(Member member, Address address, String detailedAddress) {
    this.member = member;
    this.address = address;
    this.detailedAddress = detailedAddress;
  }

  private MemberAddress() {
  }

  public void update(Member member, Address address, String detailedAddress) {
    this.member.same(member);
    this.address = address;
    this.detailedAddress = detailedAddress;
  }

  public void checkMember(Member requestMember) {
    if (!this.member.equals(requestMember)) {
      throw new MismatchMemberException();
    }
  }
}
