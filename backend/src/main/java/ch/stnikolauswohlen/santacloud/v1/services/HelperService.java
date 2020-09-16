package ch.stnikolauswohlen.santacloud.v1.services;

import ch.stnikolauswohlen.santacloud.errorhandling.ErrorResponseEntity;
import ch.stnikolauswohlen.santacloud.v1.entities.dao.helper.HelperDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperListDTO;
import ch.stnikolauswohlen.santacloud.v1.mappers.HelperMapper;
import ch.stnikolauswohlen.santacloud.v1.repositories.helper.HelperRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("fef1d459-1084-491a-82c7-fc84e3805bbc")
public class HelperService extends AbstractService
{
    private static final Logger log = LoggerFactory.getLogger(HelperService.class);

    private final HelperRepository helperRepository;
    private final HelperMapper helperMapper;

    @Autowired
    public HelperService(
        final HelperRepository helperRepository,
        final HelperMapper helperMapper
    )
    {
        this.helperRepository = helperRepository;
        this.helperMapper = helperMapper;
    }

    public ResponseEntity<Object> getHelpers(
        final int start,
        final int end,
        final String sortProperty,
        final Direction sortDirection
    )
    {
        try {
            int perPage = end - start;
            int page = end / perPage - 1;

            Pageable pageable = PageRequest.of(page, perPage, sortDirection, sortProperty);
            Page<HelperDAO> pagedResult = helperRepository.findAll(pageable);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Page-Total-Count", String.valueOf(pagedResult.getTotalElements()));

            List<HelperListDTO> helperListDTOS = helperMapper.mapHelpersDataToHelperListDTOS(pagedResult);

            return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(helperListDTOS);
        }
        catch (Exception e) {
            writeLog("Couldn't load helpers from database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    public ResponseEntity<Object> getHelper(final long helperId)
    {
        try {
            Optional<HelperDAO> helperDAO = helperRepository.findById(helperId);

            if (helperDAO.isEmpty()) {
                return ErrorResponseEntity.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            }

            HelperDTO helperDTO = helperMapper.mapHelperDAOToHelperDTO(helperDAO.get());

            return ResponseEntity.status(HttpStatus.OK).body(helperDTO);
        }
        catch (Exception e) {
            writeLog("Couldn't load helper from database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    public ResponseEntity<Object> updateHelper(final long helperId, final HelperDTO helperDTO)
    {
        try {
            Optional<HelperDAO> helperDAO = helperRepository.findById(helperId);

            if (helperDAO.isEmpty()) {
                return ErrorResponseEntity.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            }

            HelperDAO updatedHelperDAO = helperMapper.mapHelperDTOToHelperDAO(helperDTO);

            helperRepository.save(updatedHelperDAO);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            writeLog("Couldn't update helper with id " + helperId, e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    public ResponseEntity<Object> createHelper(final HelperDTO helperDTO)
    {
        try {
            HelperDAO updatedHelperDAO = helperMapper.mapHelperDTOToHelperDAO(helperDTO);

            helperRepository.save(updatedHelperDAO);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            writeLog("Couldn't save helper to database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }

    }

    public ResponseEntity<Object> deleteHelper(final long helperId)
    {
        try {
            helperRepository.deleteById(helperId);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            writeLog("Couldn't delete helper with id " + helperId + " to database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
}
