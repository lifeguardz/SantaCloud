package ch.stnikolauswohlen.santacloud.v1.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.family.FamilyDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyListDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;

@ExtendWith(MockitoExtension.class)
class FamilyMapperTest
{
    private EasyRandom generator = new EasyRandom();

    @InjectMocks
    private FamilyMapper familyMapper;

    @Test
    void testMapFamilyDAOToFamilyDTO()
    {
        FamilyDAO testFamilyDAO = generator.nextObject(FamilyDAO.class);

        FamilyDTO resultFamilyDTO = familyMapper.mapFamilyDAOToFamilyDTO(testFamilyDAO);

        assertNotNull(resultFamilyDTO);
        assertNotNull(resultFamilyDTO.getId());
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getStatus()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getFirstname()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getLastname()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getStreet()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getCity()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getPhonenumbers()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getEmail()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getAvailabledays()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getFinalDay()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getDescriptionoffice()));
        assertFalse(StringUtils.isEmpty(resultFamilyDTO.getDescriptionteam()));
    }

    @Test
    void mapFamiliesDataToFamilyListDTOS()
    {
        List<FamilyDAO> testFamilyDAOS = generator.objects(FamilyDAO.class, 10).collect(Collectors.toList());
        PageImpl<FamilyDAO> familyDAOPage = new PageImpl<FamilyDAO>(testFamilyDAOS);

        List<FamilyListDTO> resultFamilyListDTOS = familyMapper.mapFamiliesDataToFamilyListDTOS(familyDAOPage);
        FamilyDAO resultFirstFamilyDAOInList = familyDAOPage.getContent().get(0);

        assertEquals(10, resultFamilyListDTOS.size());
        assertNotNull(resultFirstFamilyDAOInList);
        assertNotNull(resultFirstFamilyDAOInList.getId());
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getStatus()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getFirstname()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getLastname()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getStreet()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getCity()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getPhonenumbers()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getEmail()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getAvailabledays()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getFinalDay()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getDescriptionoffice()));
        assertFalse(StringUtils.isEmpty(resultFirstFamilyDAOInList.getDescriptionteam()));
    }

    @Test
    void mapFamilyDTOToFamilyDAO()
    {
        FamilyDTO testFamilyDTO = generator.nextObject(FamilyDTO.class);

        FamilyDAO resultFamilyDAO = familyMapper.mapFamilyDTOToFamilyDAO(testFamilyDTO);

        assertNotNull(resultFamilyDAO);
        assertNotNull(resultFamilyDAO.getId());
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getStatus()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getFirstname()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getLastname()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getStreet()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getCity()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getPhonenumbers()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getEmail()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getAvailabledays()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getFinalDay()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getDescriptionoffice()));
        assertFalse(StringUtils.isEmpty(resultFamilyDAO.getDescriptionteam()));
    }
}
