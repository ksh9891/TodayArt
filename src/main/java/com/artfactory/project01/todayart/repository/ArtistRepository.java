package com.artfactory.project01.todayart.repository;

import com.artfactory.project01.todayart.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    Artist findByMemberId(Integer memberId);
}
