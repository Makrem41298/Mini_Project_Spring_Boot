package org.example.mini_project_spring_boot.service;

import lombok.AllArgsConstructor;
import org.example.mini_project_spring_boot.entities.Member;
import org.example.mini_project_spring_boot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ServiceMember implements IServiceMember {
    MemberRepository memberRepository;


    @Override
    public Member save(Member membre) {
        return memberRepository.save(membre);
    }

    @Override
    public Page<Member> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }


    @Override
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member updateMember(Long id, Member membre) {
        return memberRepository.save(membre);
    }

    @Override
    public Page<Member> findByPhoneNumberContaining(String phoneNumber, Pageable pageable) {
        return memberRepository.findByPhoneNumberContaining(phoneNumber, pageable);
    }





    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
