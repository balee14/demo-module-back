package com.terry.demo.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPfAuthority is a Querydsl query type for PfAuthority
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPfAuthority extends EntityPathBase<PfAuthority> {

    private static final long serialVersionUID = -1569448583L;

    public static final QPfAuthority pfAuthority = new QPfAuthority("pfAuthority");

    public final QPfBaseEntity _super = new QPfBaseEntity(this);

    public final StringPath authorityId = createString("authorityId");

    public final StringPath authorityName = createString("authorityName");

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

    public QPfAuthority(String variable) {
        super(PfAuthority.class, forVariable(variable));
    }

    public QPfAuthority(Path<? extends PfAuthority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPfAuthority(PathMetadata metadata) {
        super(PfAuthority.class, metadata);
    }

}

