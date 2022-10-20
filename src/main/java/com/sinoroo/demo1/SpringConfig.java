package com.sinoroo.demo1;

import com.sinoroo.demo1.repository.MemberRepository;
import com.sinoroo.demo1.repository.MemoryMemberRepository;
import com.sinoroo.demo1.service.MemberService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
