package com.ibm.crud.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import static org.hibernate.id.enhanced.TableGenerator.SEGMENT_VALUE_PARAM;

@Entity
@Table(name = "client",uniqueConstraints=@UniqueConstraint(columnNames={"id", "module_id"}))
@IdClass(PkId.class)
//@TableGenerator(
//        name="ID_CLIENT",
//        table = "PK_GENERATOR_TABLE",
//        pkColumnName = "PK_COLUMN",
//        valueColumnName = "PK_VALUE",
//        pkColumnValue = "ID_CLIENT",
//        allocationSize=1)
@GenericGenerator(name = "id_gen", strategy = "com.ibm.crud.common.IdGenerator", parameters = {@org.hibernate.annotations.Parameter(name = SEGMENT_VALUE_PARAM, value = "t_client")})
public class Client implements Serializable {

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
//    @Length(max = 50)
    private String name;

//    @Length(max = 100)
//    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String address;

//    @Length(max = 30)
//    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String city;

//    @Length(max = 20)
//    @Pattern(regexp = "^[0-9]+$")
    private String telephone;

    //cascade = CascadeType.ALL mappedBy = "client",
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id , moduleId asc")
    private Set<Card> cards;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "module_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Module module;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
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

    @Override
    public String toString() {
        String result = String.format(
                "Client[id=%d, name='%s', address='%s', city='%s', telephone='%s']%n",
                id, name, address, city, telephone);
        if (cards != null) {
            for (Card card : cards) {
                result += String.format("Card[id=%d, number='%s', ccv='%s', type='%s']%n",
                        card.getId(), card.getNumber(), card.getCcv(), card.getType());
            }
        }
        return result;
    }
}
