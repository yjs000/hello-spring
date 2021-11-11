package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, long> , MemberRepository{

    @Override
    Optional<Member> findByName(String name);
}
