package ch.stnikolauswohlen.santacloud.v1.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.helper.HelperDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperListDTO;
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
class HelperMapperTest
{
    private final EasyRandom generator = new EasyRandom();

    @InjectMocks
    private HelperMapper helperMapper;

    @Test
    void mapHelperDAOToHelperDTO()
    {
        HelperDAO testHelperDAO = generator.nextObject(HelperDAO.class);

        HelperDTO resultHelperDTO = helperMapper.mapHelperDAOToHelperDTO(testHelperDAO);

        assertNotNull(resultHelperDTO);
        assertNotNull(resultHelperDTO.getId());
        assertFalse(StringUtils.isEmpty(resultHelperDTO.getFirstname()));
        assertFalse(StringUtils.isEmpty(resultHelperDTO.getLastname()));
        assertFalse(StringUtils.isEmpty(resultHelperDTO.getStreet()));
        assertFalse(StringUtils.isEmpty(resultHelperDTO.getCity()));
        assertFalse(StringUtils.isEmpty(resultHelperDTO.getPhonenumbers()));
        assertFalse(StringUtils.isEmpty(resultHelperDTO.getEmail()));
        assertFalse(StringUtils.isEmpty(resultHelperDTO.getDescription()));
    }

    @Test
    void mapHelpersDataToHelperListDTOS()
    {
        List<HelperDAO> testHelperDAOS = generator.objects(HelperDAO.class, 10).collect(Collectors.toList());
        PageImpl<HelperDAO> helperDAOPage = new PageImpl<>(testHelperDAOS);

        List<HelperListDTO> resultHelperListDTOS = helperMapper.mapHelpersDataToHelperListDTOS(helperDAOPage);
        HelperDAO resultFirstHelperDAOInList = helperDAOPage.getContent().get(0);

        assertEquals(10, resultHelperListDTOS.size());
        assertNotNull(resultFirstHelperDAOInList);
        assertNotNull(resultFirstHelperDAOInList.getId());
        assertFalse(StringUtils.isEmpty(resultFirstHelperDAOInList.getFirstname()));
        assertFalse(StringUtils.isEmpty(resultFirstHelperDAOInList.getLastname()));
        assertFalse(StringUtils.isEmpty(resultFirstHelperDAOInList.getStreet()));
        assertFalse(StringUtils.isEmpty(resultFirstHelperDAOInList.getCity()));
        assertFalse(StringUtils.isEmpty(resultFirstHelperDAOInList.getPhonenumbers()));
        assertFalse(StringUtils.isEmpty(resultFirstHelperDAOInList.getEmail()));
        assertFalse(StringUtils.isEmpty(resultFirstHelperDAOInList.getDescription()));
    }

    @Test
    void mapHelperDTOToHelperDAO()
    {
        HelperDTO testHelperDTO = generator.nextObject(HelperDTO.class);

        HelperDAO resultHelperDAO = helperMapper.mapHelperDTOToHelperDAO(testHelperDTO);

        assertNotNull(resultHelperDAO);
        assertNotNull(resultHelperDAO.getId());
        assertFalse(StringUtils.isEmpty(resultHelperDAO.getFirstname()));
        assertFalse(StringUtils.isEmpty(resultHelperDAO.getLastname()));
        assertFalse(StringUtils.isEmpty(resultHelperDAO.getStreet()));
        assertFalse(StringUtils.isEmpty(resultHelperDAO.getCity()));
        assertFalse(StringUtils.isEmpty(resultHelperDAO.getPhonenumbers()));
        assertFalse(StringUtils.isEmpty(resultHelperDAO.getEmail()));
        assertFalse(StringUtils.isEmpty(resultHelperDAO.getDescription()));
    }
}
