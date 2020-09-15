package ch.stnikolauswohlen.santacloud.v1.mappers;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.family.FamilyDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyListDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component("82673e90-d97f-49dc-9e27-ec1e8c102344")
public class FamilyMapper
{
    public FamilyDTO mapFamilyDAOToFamilyDTO(final FamilyDAO familyDAO)
    {
        FamilyDTO familyDTO = new FamilyDTO();

        BeanUtils.copyProperties(familyDAO, familyDTO);

        return familyDTO;
    }

    public List<FamilyListDTO> mapFamiliesDataToFamilyListDTOS(Page<FamilyDAO> familyDAOS)
    {
        return familyDAOS.stream().map(familyDAO -> {
            FamilyListDTO familyListDTO = new FamilyListDTO();

            familyListDTO.setId(familyDAO.getId());
            familyListDTO.setFirstname(familyDAO.getFirstname());
            familyListDTO.setLastname(familyDAO.getLastname());
            familyListDTO.setStreet(familyDAO.getStreet());
            familyListDTO.setPhonenumbers(familyDAO.getPhonenumbers());

            return familyListDTO;
        }).collect(Collectors.toList());
    }

    public FamilyDAO mapFamilyDTOToFamilyDAO(final FamilyDTO familyDTO)
    {
        FamilyDAO familyDAO = new FamilyDAO();

        BeanUtils.copyProperties(familyDTO, familyDAO);

        return familyDAO;
    }
}
