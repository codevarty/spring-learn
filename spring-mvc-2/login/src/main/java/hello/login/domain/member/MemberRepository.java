package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L; // Member 아이디

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst(); // 먼저 찾은 값을 반환 한다.
    }

    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

//    Test
    public void clearStore() {
        store.clear();
    }
}
