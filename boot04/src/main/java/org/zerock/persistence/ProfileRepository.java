package org.zerock.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Profile;


public interface ProfileRepository extends CrudRepository<Profile, Long> {
	List<Profile> findByMember_uid(String uid);
	// 상속을 받았기 때문에 save,,등등 다 들어있다,,
	
}
