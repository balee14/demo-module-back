package com.terry.demo.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPfTestBatch is a Querydsl query type for PfTestBatch
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPfTestBatch extends EntityPathBase<PfTestBatch> {

    private static final long serialVersionUID = 42451966L;

    public static final QPfTestBatch pfTestBatch = new QPfTestBatch("pfTestBatch");

    public final QPfBaseEntity _super = new QPfBaseEntity(this);

    public final StringPath description = createString("description");

    public final StringPath idEmail = createString("idEmail");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDt = _super.modDt;

    //inherited
    public final StringPath modId = _super.modId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final StringPath regId = _super.regId;

    public final NumberPath<Long> tokenId = createNumber("tokenId", Long.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QPfTestBatch(String variable) {
        super(PfTestBatch.class, forVariable(variable));
    }

    public QPfTestBatch(Path<? extends PfTestBatch> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPfTestBatch(PathMetadata metadata) {
        super(PfTestBatch.class, metadata);
    }

}

