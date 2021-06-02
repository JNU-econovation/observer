package com.capstone.apm.testproject2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String index() {
        Member member = new Member();
        member.setAge(10);
        member.setName("test");
        memberRepository.save(member);
        return "TestProject2";
    }

}
