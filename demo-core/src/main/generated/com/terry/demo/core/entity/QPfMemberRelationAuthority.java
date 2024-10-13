package com.terry.demo.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPfMemberRelationAuthority is a Querydsl query type for PfMemberRelationAuthority
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPfMemberRelationAuthority extends EntityPathBase<PfMemberRelationAuthority> {

    private static final long serialVersionUID = -34036509L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPfMemberRelationAuthority pfMemberRelationAuthority = new QPfMemberRelationAuthority("pfMemberRelationAuthority");

    public final QPfBaseEntity _super = new QPfBaseEntity(this);

    public final StringPath id = createString("id");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDt = _super.modDt;

    //inherited
    public final StringPath modId = _super.modId;

    public final QPfAuthority pfAuthority;

    public final QPfMember pfMember;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QPfMemberRelationAuthority(String variable) {
        this(PfMemberRelationAuthority.class, forVariable(variable), INITS);
    }

    public QPfMemberRelationAuthority(Path<? extends PfMemberRelationAuthority> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPfMemberRelationAuthority(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPfMemberRelationAuthority(PathMetadata metadata, PathInits inits) {
        this(PfMemberRelationAuthority.class, metadata, inits);
    }

    public QPfMemberRelationAuthority(Class<? extends PfMemberRelationAuthority> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pfAuthority = inits.isInitialized("pfAuthority") ? new QPfAuthority(forProperty("pfAuthority")) : null;
        this.pfMember = inits.isInitialized("pfMember") ? new QPfMember(forProperty("pfMember")) : null;
    }

}

