package ch.stnikolauswohlen.santacloud.v1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.stnikolauswohlen.santacloud.v1.entities.dao.helper.HelperDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperListDTO;
import ch.stnikolauswohlen.santacloud.v1.mappers.HelperMapper;
import ch.stnikolauswohlen.santacloud.v1.repositories.HelperRepository;
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
class HelperServiceTest
{
    private final EasyRandom generator = new EasyRandom();

    @Mock
    private HelperRepository mockHelperRepository;

    @Mock
    private HelperMapper mockHelperMapper;

    @InjectMocks
    private HelperService helperService;

    @Test
    void getHelpers()
    {
        when(mockHelperRepository.findAll(any(Pageable.class))).thenReturn(mock(Page.class));
        when(mockHelperMapper.mapHelpersDataToHelperListDTOS(any()))
            .thenReturn(generator.objects(HelperListDTO.class, 10).collect(Collectors.toList()));

        ResponseEntity<Object> resultResponseEntity = helperService.getHelpers(0, 10, "id", Direction.ASC);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        assertNotNull(resultResponseEntity.getBody());
    }

    @Test
    void getHelper()
    {
        HelperDAO testHelperDAO = generator.nextObject(HelperDAO.class);
        when(mockHelperRepository.findById(1L)).thenReturn(Optional.of(testHelperDAO));
        when(mockHelperMapper.mapHelperDAOToHelperDTO(testHelperDAO)).thenReturn(mock(HelperDTO.class));

        ResponseEntity<Object> resultResponseEntity = helperService.getHelper(1L);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        assertNotNull(resultResponseEntity.getBody());
    }

    @Test
    void updateHelper()
    {
        HelperDTO testHelperDTO = generator.nextObject(HelperDTO.class);
        HelperDAO testHelperDAO = generator.nextObject(HelperDAO.class);
        when(mockHelperRepository.findById(1L)).thenReturn(Optional.of(testHelperDAO));
        when(mockHelperMapper.mapHelperDTOToHelperDAO(testHelperDTO)).thenReturn(mock(HelperDAO.class));

        ResponseEntity<Object> resultResponseEntity = helperService.updateHelper(1L, testHelperDTO);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        verify(mockHelperRepository).save(any());
    }

    @Test
    void createHelper()
    {
        HelperDTO testHelperDTO = generator.nextObject(HelperDTO.class);
        when(mockHelperMapper.mapHelperDTOToHelperDAO(testHelperDTO)).thenReturn(mock(HelperDAO.class));

        ResponseEntity<Object> resultResponseEntity = helperService.createHelper(testHelperDTO);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        verify(mockHelperRepository).save(any());
    }

    @Test
    void deleteHelper()
    {
        doNothing().when(mockHelperRepository).deleteById(1L);

        ResponseEntity<Object> resultResponseEntity = helperService.deleteHelper(1L);

        assertNotNull(resultResponseEntity);
        assertEquals(HttpStatus.OK, resultResponseEntity.getStatusCode());
        verify(mockHelperRepository).deleteById(1L);
    }
}
