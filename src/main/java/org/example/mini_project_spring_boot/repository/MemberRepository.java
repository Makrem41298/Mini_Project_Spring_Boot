package org.example.mini_project_spring_boot.repository;

import org.example.mini_project_spring_boot.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findAll(Pageable pageable);
    Page<Member> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);



}