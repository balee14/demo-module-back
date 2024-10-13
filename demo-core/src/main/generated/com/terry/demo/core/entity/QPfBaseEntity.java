package com.terry.demo.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPfBaseEntity is a Querydsl query type for PfBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QPfBaseEntity extends EntityPathBase<PfBaseEntity> {

    private static final long serialVersionUID = -1131583842L;

    public static final QPfBaseEntity pfBaseEntity = new QPfBaseEntity("pfBaseEntity");

    public final DateTimePath<java.time.LocalDateTime> modDt = createDateTime("modDt", java.time.LocalDateTime.class);

    public final StringPath modId = createString("modId");

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final StringPath regId = createString("regId");

    public QPfBaseEntity(String variable) {
        super(PfBaseEntity.class, forVariable(variable));
    }

    public QPfBaseEntity(Path<? extends PfBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPfBaseEntity(PathMetadata metadata) {
        super(PfBaseEntity.class, metadata);
    }

}

