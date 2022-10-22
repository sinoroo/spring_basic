package com.sinoroo.demo1.repository;

import java.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.sinoroo.demo1.domain.Member;


public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("MEMBER").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters= new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("SELECT * FROM MEMBER WHERE name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("SELECT * FROM MEMBER", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        /*
        return new RowMapper<Member>(){
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("Name"));
                return member;
            }
        };
        */
        //lamda code
        return (rs, rowNum) ->{
            Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("Name"));
                return member;
        };
    }
}
