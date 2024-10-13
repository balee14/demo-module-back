package com.terry.demo.core.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@Table(name = "pf_member_relation_authority")
@Comment("회원과 권한을 매핑하는 엔티티")
public class PfMemberRelationAuthority extends PfBaseEntity {

    @Id
    @Comment("id")
    @Column(name = "id", unique = true, nullable = false, length = 50)
    private String id;

    @Comment("PfMember:member_id")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private PfMember pfMember;

    @Comment("PfAuthority:authority_id")
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private PfAuthority pfAuthority;

    @Version
    private Integer version;

}
