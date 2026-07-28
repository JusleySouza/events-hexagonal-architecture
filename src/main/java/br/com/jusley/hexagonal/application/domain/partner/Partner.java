package br.com.jusley.hexagonal.application.domain.partner;

import br.com.jusley.hexagonal.application.domain.person.Cnpj;
import br.com.jusley.hexagonal.application.domain.person.Email;
import br.com.jusley.hexagonal.application.domain.person.Name;
import br.com.jusley.hexagonal.application.exceptions.ValidationException;
import java.util.Objects;

//agregado de partner
public class Partner {

    private final PartnerId partnerId;
    private Name name;
    private Cnpj cnpj;
    private Email email;

    public Partner(final PartnerId partnerId, final String name, final String cnpj, final String email) {
        if(partnerId == null){
            throw new ValidationException("Invalid partnerId for Partner");
        }

        this.partnerId = partnerId;
        this.setName(name);
        this.setCnpj(cnpj);
        this.setEmail(email);
    }

    public static Partner newPartner(final String name, final String cnpj, final String email) {
        return new Partner(PartnerId.unique(), name, cnpj, email);
    }

    public PartnerId partnerId() {
        return partnerId;
    }

    public Name name() {
        return name;
    }

    public Cnpj cnpj() {
        return cnpj;
    }

    public Email email() {
        return email;
    }

    private void setCnpj( final String cnpj) {this.cnpj = new Cnpj(cnpj);}

    private void setEmail(final String email) {this.email = new Email(email);}

    private void setName(final String name) {this.name = new Name(name);}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partner partner = (Partner) o;
        return Objects.equals(partnerId, partner.partnerId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(partnerId);
    }
}
