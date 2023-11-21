package at.spengergasse.efees.model;

import at.spengergasse.efees.dto.PersonDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Document("persons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Person implements UserDetails {
    @MongoId(FieldType.OBJECT_ID)
    protected ObjectId id;
    @NotNull
    @Setter
    protected String firstName;
    @NotNull
    @Setter
    protected String lastName;
    @NotNull
    @Setter
    @Field()
    protected String email;
    @Setter
    protected String phoneNr;
    @Setter
    protected Role role;
    protected boolean locked;
    protected boolean enabled;
    @Setter
    private String password;
    @Setter
    protected String type;
    @Setter
    @Field
    protected byte[] avatar;
    @Setter
    protected String endpoint;
    @Setter
    @Field
    protected String key;
    @Setter
    protected String auth;

    public byte[] getAuthAsBytes() {
        if (auth == null) {
            return null;
        }
        return Base64.getDecoder().decode(auth.replace(" ", "+"));
    }

    public byte[] getKeyAsBytes() {
        if (key == null) {
            return null;
        }
        return Base64.getDecoder().decode(key.replace(" ", "+"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    protected PersonDto prepareDto() {
        var dto = new PersonDto();
        dto.setId(Optional.ofNullable(getId()).map(ObjectId::toString).orElse(""));
        dto.setFirstName(getFirstName());
        dto.setLastName(getLastName());
        dto.setEmail(getEmail());
        dto.setPhoneNr(getPhoneNr());
        dto.setRole(Optional.ofNullable(role).map(Role::name).orElse("ROLE_STUDENT"));
        return dto;
    }
}
