package com.hobit.auth.itself.service;


import com.hobit.auth.Principal.PrincipalDetails;
import com.hobit.auth.itself.dto.request.MemberSignUpDTO;
import com.hobit.domain.member.entity.Member;
import com.hobit.domain.member.exception.MemberErrorCode;
import com.hobit.domain.member.repository.MemberRepository;
import com.hobit.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new PrincipalDetails(member);
    }

    public Member signUpMember(MemberSignUpDTO memberSignUpDTO) {

        Member member = memberSignUpDTO.toMember(passwordEncoder);

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("member email이 이미 존재합니다.");
        }

        return memberRepository.save(member);

    }

}
