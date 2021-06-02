package com.capstone.apm.testproject1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final RestTemplate restTemplate;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException();
    }

    @GetMapping("/insert")
    public Long insertEntity() {
        Member member = new Member();
        member.setAge(10);
        member.setName("test");
        return memberRepository.save(member).getId();
    }

    @GetMapping("/delay")
    public String delay() throws InterruptedException {
        Thread.sleep(500);
        return "delay";
    }

    @GetMapping("/request-another-server")
    public String requestAnotherServer() {
        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8081/", String.class);
        return result.getBody();
    }
}
