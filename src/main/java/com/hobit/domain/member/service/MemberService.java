package com.hobit.domain.member.service;


import com.hobit.auth.oauth2.dto.request.OAuth2DTO;
import com.hobit.domain.member.entity.Member;
import com.hobit.domain.member.exception.MemberErrorCode;
import com.hobit.domain.member.repository.MemberRepository;
import com.hobit.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * oAuth2Dto를 바탕으로 member를 saveOrUpdate
     * @param oAuth2DTO
     * @return
     */

    public Member saveOrUpdate(OAuth2DTO oAuth2DTO){
        Member member=memberRepository.findByEmail(oAuth2DTO.email())
                .map(existingMember -> {
                    existingMember.update(oAuth2DTO);
                    return existingMember;
                })
                .orElseGet(() -> {
                    return oAuth2DTO.oAuth2DtoToMember(oAuth2DTO);
                });

        return memberRepository.save(member);
    }

    public Member findMemberByEmail(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(()-> new GlobalException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberByRefresh(String refresh){
        return memberRepository.findByRefresh(refresh)
                .orElseThrow(()->new GlobalException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
