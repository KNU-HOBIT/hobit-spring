package com.hobit.domain.member.entity;


import com.hobit.auth.oauth2.dto.request.OAuth2DTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "member")
@Data
public class Member {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String refresh;
    private Role role;


    @Builder
    public Member(String id,String name,String email,String password,Role role){
        this.id= id;
        this.name =name;
        this.email=email;
        this.password=password;
        this.role=role;
    }

    public void update(OAuth2DTO oAuth2DTO) {
        if (oAuth2DTO.name() != null) {
            this.name = oAuth2DTO.name();
        }

        if (oAuth2DTO.email() != null) {
            this.email = oAuth2DTO.email();
        }

        if (oAuth2DTO.role() != null) {
            this.role = Role.valueOf(oAuth2DTO.role());
        }
    }
    public void updateRefreshToken(String refresh){
        this.refresh=refresh;
    }
}
