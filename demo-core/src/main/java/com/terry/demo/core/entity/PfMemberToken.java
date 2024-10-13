package com.terry.demo.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@BatchSize(size = 100)
@Table(name = "pf_member_token")
@Comment("jwt token 관리")
public class PfMemberToken extends PfBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    @Column(name = "token_id", unique = true, nullable = false)
    private Long tokenId;

    @Comment("사용자 idEmail")
    @Column(name = "id_email", nullable = false)
    private String idEmail;

    @Comment("토큰 사용 여부(true: 사용, false: 미사용)")
    @Column(name = "is_use", columnDefinition = "boolean default true", nullable = false)
    private Boolean isUse;

    @Comment("토큰")
    @Column(name="access_token", nullable = false, length = 512)
    private String accessToken;

    @Comment("토큰 만료일")
    @ColumnDefault("current_timestamp()")
    @Column(name = "access_token_dt", updatable = false, nullable = false)
    private LocalDateTime accessTokenDt;

    @Comment("리플리쉬 토큰")
    @Column(name="refresh_token", nullable = false, length = 512)
    private String refreshToken;

    @Comment("리플리쉬 토큰 만료알")
    @ColumnDefault("current_timestamp()")
    @Column(name = "refresh_token_dt", updatable = false, nullable = false)
    private LocalDateTime refreshTokenDt;

}
