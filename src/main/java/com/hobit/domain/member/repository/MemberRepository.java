package com.hobit.domain.member.repository;

import com.hobit.domain.member.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member,String> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByRefresh(String refresh);
    boolean existsByRefresh(String refresh);
    boolean existsByEmail(String email);
}
