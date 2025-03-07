package com.ll.nextjs20250110.domain.member.member.service;

import com.ll.nextjs20250110.domain.member.member.entity.Member;
import com.ll.nextjs20250110.domain.member.member.repository.MemberRepository;
import com.ll.nextjs20250110.global.exceptions.ServiceException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final AuthTokenService authTokenService;
    private final MemberRepository memberRepository;

    public long count() {
        return memberRepository.count();
    }

    public Member join(String username, String password, String nickname) {
        findByUsername(username)
                .ifPresent(__ -> {
                    throw new IllegalArgumentException(
                            new ServiceException("409-1", "해당 username은 이미 사용중입니다."));
                });

        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .apiKey(UUID.randomUUID().toString())
                .build();

        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(long authorId) {
        return memberRepository.findById(authorId);
    }

    public Optional<Member> findByApiKey(String apiKey) {
        return memberRepository.findByApiKey(apiKey);
    }

    public String genAccessToken(Member member) {
        return authTokenService.genAccessToken(member);
    }

    public String genAuthToken(Member member) {
        return member.getApiKey() + " " + this.genAccessToken(member);
    }

    public Member getMemberFromAccessToken(String accessToken) {
        Map<String, Object> payload = authTokenService.payload(accessToken);

        if (payload == null) {
            return null;
        }

        long id = (long) payload.get("id");
        String username = (String) payload.get("username");

        return new Member(id, username);
    }
}
