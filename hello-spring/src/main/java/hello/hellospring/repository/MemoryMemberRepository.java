package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L; //primary key
  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id)); // null이어도 감싸서 반환
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
        .filter(member -> member.getName().equals(name)) // member의 name이 파라미터로 넘어온 name과 같은지 확인
        .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    // 반드시 store를 비워줘야 함
    store.clear();
  }
}
