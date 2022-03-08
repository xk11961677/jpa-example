package com.ibm.crud.common;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 自定义ID生成器
 */
public class IdGenerator extends TableGenerator {

    private static final Logger log = LoggerFactory.getLogger(IdGenerator.class);


    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        params.put(INITIAL_PARAM,1000);
//        if(params.getProperty(PersistentIdentifierGenerator.PK).equalsIgnoreCase("module_id")) {
//            params.put(SEGMENT_VALUE_PARAM,"t_module");
//            super.configure(type, params, serviceRegistry);
//            return;
//        }
        super.configure(type, params, serviceRegistry);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        log.info("测试自定义ID生成器=======================");
        if(obj.getClass().getAnnotation(IdClass.class) != null) {
            Object id = getId(obj);
            if(id != null) {
                log.info("测试自定义ID生成器,联合主键使用id="+id);
                return (Serializable)id;
            }
        }
        return super.generate(session, obj);
    }

    private Object getId(Object obj) {
        Field idFiled = ReflectionUtils.findField(obj.getClass(), "id");
        idFiled.setAccessible(true);
        return ReflectionUtils.getField(idFiled,obj);
    }
}
