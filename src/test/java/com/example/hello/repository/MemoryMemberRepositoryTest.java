package com.example.hello.repository;

import java.util.List;

// import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.hello.domain.Member;

class MemoryMemberRepositoryTest {
    
    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    //test는 순서가 보장이 안됨
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    //실무에서는 빌드툴과 엮어서 빌드할때 테스트통과 못하면 못넘어가게 막아버림
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //optional을 바로 get으로 꺼내는 건 좋진 않지만 test에서는 이렇게 해도 됨..
        // Assertions.assertEquals(member, result);
        Assertions.assertThat(member).isEqualTo(result); //요즘엔 junit보다 assertj쪽을 더 씀
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
