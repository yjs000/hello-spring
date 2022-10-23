package com.example.hello.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.hello.domain.Member;
import com.example.hello.repository.MemoryMemberRepository;

public class MemberServiceTest {
    
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void AfterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("springn ");
        
        //when
        Long saveId = memberService.join(member);
        
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //에러검증
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); 
        //에러메시지 검증
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");



        //try catch를 넣어도 되지만 이거땜에 try catch넣는게 애매함 => assertThrows
        // try{
        //     memberService.join(member2);
        //     fail(); //exception으로 넘어감
        // } catch (IllegalStateException e){
        //     Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // }

        //then
    }

    @Test
    void findMembers() {
        
    }

    @Test
    void findOne() {

    }
}
