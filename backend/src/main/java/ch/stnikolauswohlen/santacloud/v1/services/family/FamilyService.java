package ch.stnikolauswohlen.santacloud.v1.services.family;

import ch.stnikolauswohlen.santacloud.errorhandling.ErrorResponseEntity;
import ch.stnikolauswohlen.santacloud.v1.entities.dao.family.FamilyDAO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyDTO;
import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyListDTO;
import ch.stnikolauswohlen.santacloud.v1.mappers.FamilyMapper;
import ch.stnikolauswohlen.santacloud.v1.repositories.family.FamilyRepository;
import ch.stnikolauswohlen.santacloud.v1.services.AbstractService;
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

@Service("b57f766c-29f9-4d74-bfa6-1f8189e7e534")
public class FamilyService extends AbstractService
{
    private static final Logger log = LoggerFactory.getLogger(FamilyService.class);

    private final FamilyRepository familyRepository;
    private final FamilyMapper familyMapper;

    @Autowired
    public FamilyService(
        final FamilyRepository familyRepository,
        final FamilyMapper familyMapper
    )
    {
        this.familyRepository = familyRepository;
        this.familyMapper = familyMapper;
    }

    public ResponseEntity<Object> getFamilies(
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
            Page<FamilyDAO> pagedResult = familyRepository.findAll(pageable);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Page-Total-Count", String.valueOf(pagedResult.getTotalElements()));

            List<FamilyListDTO> familyListDTOS = familyMapper.mapFamiliesDataToFamilyListDTOS(pagedResult);

            return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(familyListDTOS);
        }
        catch (Exception e) {
            writeLog("Couldn't load families from database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    public ResponseEntity<Object> getFamily(final Long familyId)
    {
        try {
            Optional<FamilyDAO> familyDAO = familyRepository.findById(familyId);

            if (!familyDAO.isPresent()) {
                return ErrorResponseEntity.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            }

            FamilyDTO familyDTO = familyMapper.mapFamilyDAOToFamilyDTO(familyDAO.get());

            return ResponseEntity.status(HttpStatus.OK).body(familyDTO);
        }
        catch (Exception e) {
            writeLog("Couldn't load family from database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    public ResponseEntity<Object> updateFamily(final long familyId, final FamilyDTO familyDTO)
    {
        try {
            Optional<FamilyDAO> familyDAO = familyRepository.findById(familyId);

            if (!familyDAO.isPresent()) {
                return ErrorResponseEntity.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            }

            FamilyDAO updatedFamilyDAO = familyMapper.mapFamilyDTOToFamilyDAO(familyDTO);

            familyRepository.save(updatedFamilyDAO);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            writeLog("Couldn't update family with id " + familyId, e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    public ResponseEntity<Object> createFamily(final FamilyDTO familyDTO)
    {
        try {
            FamilyDAO updatedFamilyDAO = familyMapper.mapFamilyDTOToFamilyDAO(familyDTO);

            familyRepository.save(updatedFamilyDAO);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            writeLog("Couldn't save family to database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }

    public ResponseEntity<Object> deleteFamily(final long familyId)
    {
        try {
            familyRepository.deleteById(familyId);

            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e) {
            writeLog("Couldn't delete family with id id " + familyId + " from database!", e);

            return ErrorResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        }
    }
}
