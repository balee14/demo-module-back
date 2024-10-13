package com.terry.demo.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPfMember is a Querydsl query type for PfMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPfMember extends EntityPathBase<PfMember> {

    private static final long serialVersionUID = 1702301732L;

    public static final QPfMember pfMember = new QPfMember("pfMember");

    public final QPfBaseEntity _super = new QPfBaseEntity(this);

    public final StringPath idEmail = createString("idEmail");

    public final DateTimePath<java.time.LocalDateTime> lastConnection = createDateTime("lastConnection", java.time.LocalDateTime.class);

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberId = createString("memberId");

    public final StringPath memberMobilePhone = createString("memberMobilePhone");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberNickName = createString("memberNickName");

    public final StringPath memberPwd = createString("memberPwd");

    public final EnumPath<com.terry.demo.core.enums.PfMemberStateEnum> memberState = createEnum("memberState", com.terry.demo.core.enums.PfMemberStateEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDt = _super.modDt;

    //inherited
    public final StringPath modId = _super.modId;

    public final ListPath<PfMemberRelationAuthority, QPfMemberRelationAuthority> pfMemberRelationAuthorityList = this.<PfMemberRelationAuthority, QPfMemberRelationAuthority>createList("pfMemberRelationAuthorityList", PfMemberRelationAuthority.class, QPfMemberRelationAuthority.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QPfMember(String variable) {
        super(PfMember.class, forVariable(variable));
    }

    public QPfMember(Path<? extends PfMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPfMember(PathMetadata metadata) {
        super(PfMember.class, metadata);
    }

}

