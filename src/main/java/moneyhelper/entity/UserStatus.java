package moneyhelper.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserStatus implements GrantedAuthority {
    ACTIVE, BANNED, DELETED;

    @Override
    public String getAuthority() {
        return name();
    }
}
