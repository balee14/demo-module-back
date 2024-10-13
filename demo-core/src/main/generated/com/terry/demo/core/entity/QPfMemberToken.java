package com.terry.demo.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPfMemberToken is a Querydsl query type for PfMemberToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPfMemberToken extends EntityPathBase<PfMemberToken> {

    private static final long serialVersionUID = -55234507L;

    public static final QPfMemberToken pfMemberToken = new QPfMemberToken("pfMemberToken");

    public final QPfBaseEntity _super = new QPfBaseEntity(this);

    public final StringPath accessToken = createString("accessToken");

    public final DateTimePath<java.time.LocalDateTime> accessTokenDt = createDateTime("accessTokenDt", java.time.LocalDateTime.class);

    public final StringPath idEmail = createString("idEmail");

    public final BooleanPath isUse = createBoolean("isUse");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDt = _super.modDt;

    //inherited
    public final StringPath modId = _super.modId;

    public final StringPath refreshToken = createString("refreshToken");

    public final DateTimePath<java.time.LocalDateTime> refreshTokenDt = createDateTime("refreshTokenDt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Long> tokenId = createNumber("tokenId", Long.class);

    public QPfMemberToken(String variable) {
        super(PfMemberToken.class, forVariable(variable));
    }

    public QPfMemberToken(Path<? extends PfMemberToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPfMemberToken(PathMetadata metadata) {
        super(PfMemberToken.class, metadata);
    }

}

