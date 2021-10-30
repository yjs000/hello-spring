package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();// 공유되는 변수일때는 동시성문제때문에 concurrency별로 해줘야됨
    private static long sequence = 0L; //실무에서는 동시성 문제 -> autumn long?등을 해야함

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)) ;
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // loop를 돌림
                .filter(member -> member.getName().equals(name))
                .findAny(); // 맞는게 하나라도 있으면 반환(optional로 반환됨)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
