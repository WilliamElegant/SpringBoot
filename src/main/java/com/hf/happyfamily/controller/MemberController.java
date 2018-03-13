package com.hf.happyfamily.controller;

import com.hf.happyfamily.domain.Member;
import com.hf.happyfamily.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private MemberRepository memberRepository = null;

    @Autowired
    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @PostMapping("/member/save")
    public Member save(@RequestParam String name, @RequestParam int age){
        Member member = new Member();
        member.setName(name);
        member.setAge(age);
        if(memberRepository.save(member)){
            System.out.printf("用户对象：%s 保存成功 \n",member);
        }
        return  member;
    }
}
