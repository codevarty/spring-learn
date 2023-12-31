package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {
  // 스프링을 사용하면 싱글톤이 보장되기 때문에 사용할 필요가 없다.
  MemberRepository memberRepository = MemberRepository.getInstance();

  // test가 끝날 때 마다. 값 초기화
  // 명시 하지 않을 경우 test의 순서가 보장 되지 않기 때문에 에러가 발생할 수 있음
  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void save() {
    // given
    Member member = new Member("hello", 20);

    // when
    Member savedMember = memberRepository.save(member);

    // then
    Member findMember = memberRepository.findById(savedMember.getId());

    // 찾아온 멤버는 저장된 넴버와 같아야 한다.
    assertThat(findMember).isEqualTo(savedMember);
  }

  @Test
  void findAll() {
    // given
    Member member1 = new Member("member1", 20);
    Member member2 = new Member("member2", 30);

    memberRepository.save(member1);
    memberRepository.save(member2);

    // when
    List<Member> result = memberRepository.findAll();
    // then
    assertThat(result.size()).isEqualTo(2);
    // result 안에 member1, member2가 있는지 검사 하는 것
    assertThat(result).contains(member1, member2);
  }
}
