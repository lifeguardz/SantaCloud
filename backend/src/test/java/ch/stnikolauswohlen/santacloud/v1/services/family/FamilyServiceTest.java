package ch.stnikolauswohlen.santacloud.v1.services.family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.family.FamilyDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyListDTO;
import ch.stnikolauswohlen.santacloud.v1.mappers.FamilyMapper;
import ch.stnikolauswohlen.santacloud.v1.repositories.family.FamilyRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class FamilyServiceTest
{
    private EasyRandom generator = new EasyRandom();

    @Mock
    private FamilyRepository mockFamilyRepository;

    @Mock
    private FamilyMapper mockFamilyMapper;

    @InjectMocks
    private FamilyService familyService;

    @Test
    void getFamilies()
    {
        when(mockFamilyRepository.findAll(any(Pageable.class))).thenReturn(mock(Page.class));
        when(mockFamilyMapper.mapFamiliesDataToFamilyListDTOS(any()))
            .thenReturn(generator.objects(FamilyListDTO.class, 10).collect(Collectors.toList()));

        ResponseEntity<Object> resultResponseEntity = familyService.getFamilies(0, 10, "id", Direction.ASC);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        assertNotNull(resultResponseEntity.getBody());
    }

    @Test
    void getFamily()
    {
        FamilyDAO testFamilyDAO = generator.nextObject(FamilyDAO.class);
        when(mockFamilyRepository.findById(1L)).thenReturn(Optional.of(testFamilyDAO));
        when(mockFamilyMapper.mapFamilyDAOToFamilyDTO(testFamilyDAO)).thenReturn(mock(FamilyDTO.class));

        ResponseEntity<Object> resultResponseEntity = familyService.getFamily(1L);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        assertNotNull(resultResponseEntity.getBody());
    }

    @Test
    void updateFamily()
    {
        FamilyDTO testFamilyDTO = generator.nextObject(FamilyDTO.class);
        FamilyDAO testFamilyDAO = generator.nextObject(FamilyDAO.class);
        when(mockFamilyRepository.findById(1L)).thenReturn(Optional.of(testFamilyDAO));
        when(mockFamilyMapper.mapFamilyDTOToFamilyDAO(testFamilyDTO)).thenReturn(mock(FamilyDAO.class));

        ResponseEntity<Object> resultResponseEntity = familyService.updateFamily(1L, testFamilyDTO);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        verify(mockFamilyRepository).save(any());

    }

    @Test
    void createFamily()
    {
        FamilyDTO testFamilyDTO = generator.nextObject(FamilyDTO.class);
        when(mockFamilyMapper.mapFamilyDTOToFamilyDAO(testFamilyDTO)).thenReturn(mock(FamilyDAO.class));

        ResponseEntity<Object> resultResponseEntity = familyService.createFamily(testFamilyDTO);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        verify(mockFamilyRepository).save(any());
    }

    @Test
    void deleteFamily()
    {
        doNothing().when(mockFamilyRepository).deleteById(1L);

        ResponseEntity<Object> resultResponseEntity = familyService.deleteFamily(1L);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        verify(mockFamilyRepository).deleteById(1L);
    }
}
