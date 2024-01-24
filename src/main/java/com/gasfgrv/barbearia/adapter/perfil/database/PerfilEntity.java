package com.gasfgrv.barbearia.adapter.perfil.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "perfil")
@Getter
@Setter
@NoArgsConstructor
public class PerfilEntity implements GrantedAuthority, Serializable {
    @Id
    private int id;
    private String nome;

    @Override
    public String getAuthority() {
        return nome;
    }
}
