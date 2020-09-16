package ch.stnikolauswohlen.santacloud.v1.mappers;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.helper.HelperDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperListDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component("7af82777-b3fc-442f-8bed-cf55a8d7516a")
public class HelperMapper
{
    public HelperDTO mapHelperDAOToHelperDTO(final HelperDAO helperDAO)
    {
        HelperDTO helperDTO = new HelperDTO();

        BeanUtils.copyProperties(helperDAO, helperDTO);

        return helperDTO;
    }

    public List<HelperListDTO> mapHelpersDataToHelperListDTOS(Page<HelperDAO> helperDAOS)
    {
        return helperDAOS.stream().map(helperDAO ->
            HelperListDTO.builder()
                .role(helperDAO.getRole())
                .firstname(helperDAO.getFirstname())
                .lastname(helperDAO.getLastname())
                .birthyear(helperDAO.getBirthyear())
                .fieldingdays(helperDAO.getFieldingdays())
                .phonenumbers(helperDAO.getPhonenumbers())
                .build()
        ).collect(Collectors.toList());
    }

    public HelperDAO mapHelperDTOToHelperDAO(final HelperDTO helperDTO)
    {
        HelperDAO helperDAO = new HelperDAO();

        BeanUtils.copyProperties(helperDTO, helperDAO);

        return helperDAO;
    }
}
