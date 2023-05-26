package org.zerock;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.Member;
import org.zerock.domain.Profile;
import org.zerock.persistence.MemberRepository;
import org.zerock.persistence.ProfileRepository;

import lombok.extern.java.Log;

@AutoConfigureMockMvc
@Log // lombok의 로그를 이용
@Commit // 결과를 DB에 commit시킴
public class ProfileTests {

	@Autowired
	MemberRepository memberRepo;

	@Autowired
	ProfileRepository profileRepo;

	// 더미 회원 데이터 추가
	// @Test
	public void testInsertMembers() {
		IntStream.range(1, 101).forEach(i -> {
			Member member = new Member();
			member.setUid("user" + i);
			member.setUpw("pw" + i);
			member.setUname("사용자" + i);

			memberRepo.save(member);
		});
	}

	// 특정 회원의 프로필 데이터 처리
	// user1이라는 uid를 가진 회원에게 5개의 프로필데이터 생성하고 저장하는 작업
	// @Test
	public void testInsertProfile() {
		Member member = new Member();
		member.setUid("user1");
		// Member 객체의 uid속성에 user1값을 설정함(고유 식별자)
		for (int i = 0; i < 5; i++) {
			Profile profile1 = new Profile();
			profile1.setFname("face" + i + ".jpg");
			
			if (i == 1) {
				profile1.setCurrent(true);
			// i가 1일 때 현재 프로필을 나타내는 current 속성을 true로 설정
			// (현재 프로필 표시할 때 사용)
			}

			profile1.setMember(member);
			// 생성된 Profile 객체에 위에서 생성한 Member 객체를 연결함
			// 이를 통해 회원 - 프로필 관계 설정함
			profileRepo.save(profile1);
			// profileRepo라는 Profile 엔티티의 저장소(repository)를 사용하여 profile1 객체를 데이터베이스에 저장
			// -> 프로필 데이터가 영구 저장소에 유지
		}
	}

	@Test
	public void test() {
		System.out.println(memberRepo.findById("user1"));
		profileRepo.findByMember_uid("user1").forEach(m->System.out.println(m));
	}
	@Test
	public void testFetchJoin1() {
		memberRepo.getMemberWithProfileCount("user1").forEach(m->System.out.println(Arrays.toString(m)));
	}
}
