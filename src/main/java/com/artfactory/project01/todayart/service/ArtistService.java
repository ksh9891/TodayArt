package com.artfactory.project01.todayart.service;

import com.artfactory.project01.todayart.entity.Artist;
import com.artfactory.project01.todayart.entity.File;
import com.artfactory.project01.todayart.entity.Member;
import com.artfactory.project01.todayart.repository.ArtistRepository;
import com.artfactory.project01.todayart.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Artist createArtist(int id, Artist artist){
        Member member = memberRepository.findById(id).get();
        artist.setMemberId(member.getMemberId());
        artist.setArtistName(member.getUsername());
        artist.setAdmProductImage(1);
        artist.setArtistDesc("test");
        artist.setAdmProductDesc("test");
        artist.setArtistLevelId(1);
        return artistRepository.save(artist);
    }

    @Transactional
    public Artist updateArtist(int id){
        Date admCheckDated = new Date();
        Artist artist = artistRepository.findById(id).get();
//        if(admBtn == true)
        artist.setAdmCheck(0);
        artist.setAdmCheckDated(admCheckDated);
        return artistRepository.save(artist);
    }
}
