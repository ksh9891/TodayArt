package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, Integer> {
}
