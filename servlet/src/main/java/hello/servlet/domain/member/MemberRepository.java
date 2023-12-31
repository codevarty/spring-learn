package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제 고려 하지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence =0L;

  private static final MemberRepository instance = new MemberRepository();

  public static MemberRepository getInstance() {
    return instance;
  }

  // 싱글톤 사용을 위해 생성자 접근 제한
  private MemberRepository() {}

  // 맴버 저장
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);

    return member;
  }

  // id를 통한 맴버 조회
  public Member findById(Long id) {
    return store.get(id);
  }

  // 저장된 맴버 모두 조회
  public List<Member> findAll() {
    // 새로운 배열을 생성하여 반환
    // 기존 배열 사용 X => 기존 것을 수정하면 배열이 변경될 수 있음
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }
}
