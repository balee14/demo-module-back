package com.terry.demo.core.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@BatchSize(size = 100)
@Table(name = "pf_test_batch")
@Comment("테스트")
public class PfTestBatch extends PfBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("id")
    @Column(name = "token_id", unique = true, nullable = false)
    private Long tokenId;

    @Comment("사용자ID")
    @Column(name = "id_email")
    private String idEmail;

    @Comment("설명")
    @Column(name = "description")
    private String description;

    //@Transient
    //private MultipartFile multipartFile;

    @Version
    private Integer version;

}
