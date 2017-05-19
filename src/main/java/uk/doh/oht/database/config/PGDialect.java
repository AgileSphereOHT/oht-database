package uk.doh.oht.database.config;

import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * Created by peterwhitehead on 17/05/2017.
 */
public class PGDialect extends PostgreSQL95Dialect {

    public PGDialect() {
        super();
        registerFunction("similarity", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "similarity(?1, ?2) >= 0.3"));
        registerFunction("sim", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "?1 % ?2"));
    }
}
