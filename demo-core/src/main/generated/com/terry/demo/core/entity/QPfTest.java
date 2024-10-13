package com.terry.demo.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPfTest is a Querydsl query type for PfTest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPfTest extends EntityPathBase<PfTest> {

    private static final long serialVersionUID = 1199744156L;

    public static final QPfTest pfTest = new QPfTest("pfTest");

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

    public final StringPath testId = createString("testId");

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QPfTest(String variable) {
        super(PfTest.class, forVariable(variable));
    }

    public QPfTest(Path<? extends PfTest> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPfTest(PathMetadata metadata) {
        super(PfTest.class, metadata);
    }

}

