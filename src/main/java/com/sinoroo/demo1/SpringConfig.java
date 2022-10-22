package com.sinoroo.demo1;

import com.sinoroo.demo1.repository.JdbcTemplateMemberRepository;
import com.sinoroo.demo1.repository.MemberRepository;
import com.sinoroo.demo1.repository.MemoryMemberRepository;
import com.sinoroo.demo1.service.MemberService;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
