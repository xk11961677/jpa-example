package com.ibm.crud.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.util.Set;

import static org.hibernate.id.enhanced.TableGenerator.DEF_SEGMENT_VALUE;
import static org.hibernate.id.enhanced.TableGenerator.SEGMENT_VALUE_PARAM;

@Entity
@Table(name = "module")
@GenericGenerator(name = "id_gen", strategy = "com.ibm.crud.common.IdGenerator",
        parameters={@org.hibernate.annotations.Parameter(name=SEGMENT_VALUE_PARAM, value="t_module")})
//@TableGenerator(
//        name="ID_MODULE",
//        table = "PK_GENERATOR_TABLE",
//        pkColumnName = "PK_COLUMN",
//        valueColumnName = "PK_VALUE",
//        pkColumnValue = "ID_MODULE",
//        allocationSize=1)
public class Module {
    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_MODULE")
    @GeneratedValue(generator = "id_gen")
    private Long id;

    @Column
//    @Length(max = 50)
    private String name;

    @OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    private Set<Card> cards;

    @OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
    @org.hibernate.annotations.ForeignKey(name = "none")
    @Fetch(FetchMode.SUBSELECT)
    @OrderBy("id asc")
    private Set<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cards=" + cards +
                ", clients=" + clients +
                '}';
    }
}
