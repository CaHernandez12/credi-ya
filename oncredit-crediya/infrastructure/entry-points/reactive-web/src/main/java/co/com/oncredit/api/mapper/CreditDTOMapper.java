package co.com.oncredit.api.mapper;

import co.com.oncredit.api.credit.dto.CreateCreditDTO;
import co.com.oncredit.model.credit.Credit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreditDTOMapper {

    Credit toModel(CreateCreditDTO createCreditDTO);

}
