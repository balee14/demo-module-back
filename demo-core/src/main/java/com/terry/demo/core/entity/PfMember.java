package com.terry.demo.core.entity;

import com.terry.demo.core.converter.MemberStateConverter;
import com.terry.demo.core.enums.PfMemberStateEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@BatchSize(size = 100)
@Table(name = "pf_member")
@Comment("회원")
public class PfMember extends PfBaseEntity {

    @Id
    @Comment("ID")
    @Column(name = "member_id", unique = true, nullable = false, length = 50)
    private String memberId;

    @Comment("pf_member_authority 매핑")
    @OneToMany(mappedBy = "pfMember")
    private List<PfMemberRelationAuthority> pfMemberRelationAuthorityList;

    @Comment("사용자ID")
    @Column(name = "id_email", unique = true, nullable = false)
    private String idEmail;

    @Comment("회원PW")
    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;

    @Comment("이름")
    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Comment("닉네임")
    @Column(name = "member_nick_name", nullable = false)
    private String memberNickName;

    @Comment("대표이메일")
    @Column(name = "member_email", nullable = false)
    private String memberEmail;

    @Comment("휴대폰")
    @Column(name = "member_mobile_phone", nullable = false, length = 15)
    private String memberMobilePhone;

    @Comment("사용여부(사용:Y, 미사용:N)")
    @Column(name = "member_state", nullable = false, length = 1)
    @Convert(converter = MemberStateConverter.class)
    private PfMemberStateEnum memberState;

    @Comment("최종접속시간")
    @LastModifiedDate
    @ColumnDefault("current_timestamp()")
    @Column(name = "last_connection", nullable = false)
    private LocalDateTime lastConnection;

    @Version
    private Integer version;

}

