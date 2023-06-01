package org.zerock.repository;

import java.util.List;
import java.util.Optional;

import org.zerock.domain.Member;

public class MemoryMemberRepository implements MemberRepository{

	@Override
	public Member save(Member member) {

		return null;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return null;
	}

	@Override
	public Optional<Member> findByName(String name) {
		return null;
	}

	@Override
	public List<Member> findAll() {
		return null;
	}
	

}
