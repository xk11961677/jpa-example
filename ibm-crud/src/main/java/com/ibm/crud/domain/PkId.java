package com.ibm.crud.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * 主键
 */
@MappedSuperclass
public class PkId implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_CARD")
    @GeneratedValue(generator = "id_gen")
    private Long id;

    @Id
    @Column(name = "module_id")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_MODULE")
//    @GeneratedValue(generator = "id_gen")
    private Long moduleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PkId pkId = (PkId) o;

        return new EqualsBuilder().append(id, pkId.id).append(moduleId, pkId.moduleId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(moduleId).toHashCode();
    }

    @Override
    public String toString() {
        return "PkId{" +"id=" + id +", moduleId=" + moduleId +'}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}
