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
@Table(name = "pf_test")
@Comment("테스트")
public class PfTest extends PfBaseEntity {

    @Id
    @Comment("id")
    @Column(name = "test_id", unique = true, nullable = false, length = 50)
    private String testId;

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
