package com.sky7th.deliveryfood.user.member.controller;

import com.sky7th.deliveryfood.user.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/register/confirm")
  public String registerConfirm(@RequestParam(value = "key", required = false) String key, Model model) {
    try {
      memberService.emailVerify(key);
      model.addAttribute("message", "인증을 완료했습니다.");

    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "email-confirm";
  }
}