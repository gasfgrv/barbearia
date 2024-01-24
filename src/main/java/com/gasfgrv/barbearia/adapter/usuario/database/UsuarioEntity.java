package com.gasfgrv.barbearia.adapter.usuario.database;

import com.gasfgrv.barbearia.adapter.perfil.database.PerfilEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class UsuarioEntity implements UserDetails, Serializable {
    @Id
    private String login;
    private String senha;
    @OneToOne
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    private PerfilEntity perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(perfil);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
