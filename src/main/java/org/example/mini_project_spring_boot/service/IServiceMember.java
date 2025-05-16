package org.example.mini_project_spring_boot.service;

import org.example.mini_project_spring_boot.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IServiceMember {
    Member save(Member membre);
    Page<Member> getAllMembers(Pageable pageable);
    Optional<Member> getMemberById(Long id);
    Member updateMember(Long id, Member membre);
    Page<Member> findByPhoneNumberContaining(String phoneNumber, Pageable pageable);

    void deleteMember(Long id);
}
