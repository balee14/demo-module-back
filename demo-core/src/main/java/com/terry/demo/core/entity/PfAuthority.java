package com.terry.demo.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.List;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Table(name = "pf_authority")
@Comment("권한")
public class PfAuthority extends PfBaseEntity {

    @Id
    @Comment("ID")
    @Column(name = "authority_id", unique = true, nullable = false, length = 50)
    private String authorityId;

    @Comment("pf_member_authority 매핑")
    @OneToMany(mappedBy = "pfAuthority")
    private List<PfMemberRelationAuthority> pfMemberRelationAuthorityList;

    @Comment("권한명")
    @Column(name = "authority_name", unique = true, nullable = false, length = 50)
    private String authorityName;

    @Version
    private Integer version;

}
