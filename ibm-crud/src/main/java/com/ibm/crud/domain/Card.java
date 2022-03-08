package com.ibm.crud.domain;

import com.ibm.crud.common.NumberUtils;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.io.Serializable;

import static org.hibernate.id.enhanced.TableGenerator.SEGMENT_VALUE_PARAM;

@Entity
@Table(name = "card",uniqueConstraints=@UniqueConstraint(columnNames={"id", "module_id"}))
@IdClass(PkId.class)
@GenericGenerator(name = "id_gen", strategy = "com.ibm.crud.common.IdGenerator", parameters = {@org.hibernate.annotations.Parameter(name = SEGMENT_VALUE_PARAM, value = "t_card")})
//@TableGenerator(
//        name="ID_CARD",
//        table = "PK_GENERATOR_TABLE",
//        pkColumnName="PK_COLUMN",
//        valueColumnName="PK_VALUE",
//        pkColumnValue="ID_CARD",
//        allocationSize = 1)
public class Card implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_CARD")
    @GeneratedValue(generator = "id_gen")
    private Long id;

    @Id
    @Column(name = "module_id")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_MODULE")
//    @GeneratedValue(generator = "id_gen")
    private Long moduleId;

    @Column
//    @Length(min = 16, max = 16)
//    @Pattern(regexp = "^[0-9]+$")
    private String number;

//    @Length(min = 3, max = 4)
    private String ccv;

//    @Length(max = 50)
    private String type;

    @Column(name="client_id")
    private Long clientId;

    // , insertable = false, updatable = false
    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumns(value={
       @JoinColumn(name = "client_id", referencedColumnName="id", insertable = false, updatable = false),
       @JoinColumn(name = "module_id", referencedColumnName="module_id", insertable = false, updatable = false)
    },foreignKey=@ForeignKey(name="none",value=ConstraintMode.NO_CONSTRAINT))
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "module_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Module module;

    public String getNumber() {
        return NumberUtils.formatCard(number);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", moduleId=" + moduleId +
                ", number='" + number + '\'' +
                ", ccv='" + ccv + '\'' +
                ", type='" + type + '\'' +
                ", clientId=" + clientId +
                '}';
    }
}
