package br.com.jusley.hexagonal.application.repositories;

import br.com.jusley.hexagonal.application.domain.partner.Partner;
import br.com.jusley.hexagonal.application.domain.partner.PartnerId;
import br.com.jusley.hexagonal.application.domain.person.Cnpj;
import br.com.jusley.hexagonal.application.domain.person.Email;
import java.util.Optional;

//repositorio do agregado
public interface PartnerRepository {

    Optional<Partner> partnerOfId(PartnerId anId);

    Optional<Partner> partnerOfCNPJ(Cnpj cnpj);

    Optional<Partner> partnerOfEmail(Email email);

    Partner create(Partner partner);

    Partner update(Partner partner);

    void deleteAll();
}
