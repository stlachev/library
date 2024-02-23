package com.bookstore.library.auth;

import static com.bookstore.library.auth.Permission.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum Role {
        USER(Set.of(
                USER_READ
        )),
        ADMIN(
                Set.of(
                        ADMIN_READ,
                        ADMIN_UPDATE,
                        ADMIN_DELETE,
                        ADMIN_CREATE,
                        MANAGER_READ,
                        MANAGER_UPDATE,
                        MANAGER_DELETE,
                        MANAGER_CREATE
                )
        ),
        MANAGER(
                Set.of(
                        MANAGER_READ,
                        MANAGER_UPDATE,
                        MANAGER_DELETE,
                        MANAGER_CREATE
                )
        );

        private final Set<Permission> permissions;

        Role(Set<Permission> permissions) {
                this.permissions = permissions;
        }

        public Set<Permission> getPermissions() {
                return permissions;
        }

        public List<SimpleGrantedAuthority> getAuthorities() {
                var authorities = getPermissions()
                        .stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toList());
                authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
                return authorities;
        }
}