package com.example.hello.service;

import java.util.List;
import java.util.Optional;

import com.example.hello.domain.Member;
import com.example.hello.repository.MemberRepository;
import com.example.hello.repository.MemoryMemberRepository;


public class MemberService {
    /**
     * service는 naming이 조금 더 서비스에 가깝게 (회원가입 ..)
     * repository는 조금 더 기계적으로 (get.. )
     */
    private final MemberRepository memberRepository;

    //Test의 repository와 service의 repository가 다른객체이면 안됨. => DI로 Test에서 주입해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
