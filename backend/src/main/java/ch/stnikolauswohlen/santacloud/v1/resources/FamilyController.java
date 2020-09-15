package ch.stnikolauswohlen.santacloud.v1.resources;

import ch.stnikolauswohlen.santacloud.v1.entities.dtos.family.FamilyDTO;
import ch.stnikolauswohlen.santacloud.v1.services.family.FamilyService;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("8f145ee6-7ad1-4bc3-932c-1390ef27a79e")
@RequestMapping(value = "/v1")
@Validated
@PreAuthorize("hasAnyAuthority('ADMIN', 'OFFICE')")
public class FamilyController
{
    private final FamilyService familyService;

    @Autowired
    public FamilyController(final FamilyService familyService)
    {
        this.familyService = familyService;
    }

    @GetMapping(value = "/families")
    public ResponseEntity<Object> getFamilies(
        @RequestParam(name = "_start", defaultValue = "0", required = false) @Min(0) int start,
        @RequestParam(name = "_end", defaultValue = "10", required = false) @Positive int end,
        @RequestParam(name = "_sort", defaultValue = "id", required = false) String sortProperty,
        @RequestParam(name = "_order", defaultValue = "ASC", required = false) Direction sortDirection
    )
    {
        return familyService.getFamilies(
            start,
            end,
            sortProperty,
            sortDirection
        );
    }

    @GetMapping(value = "/families/{id}")
    public ResponseEntity<Object> getFamily(@PathVariable("id") long id)
    {
        return familyService.getFamily(id);
    }

    @PutMapping(value = "/families/{id}")
    public ResponseEntity<Object> updateFamily(
        @PathVariable("id") long id,
        @RequestBody FamilyDTO familyDTO
    )
    {
        return familyService.updateFamily(id, familyDTO);
    }

    @PostMapping(value = "/families")
    public ResponseEntity<Object> createFamily(
        @RequestBody @Valid FamilyDTO familyDTO
    )
    {
        return familyService.createFamily(familyDTO);
    }

    @DeleteMapping(value = "/families/{id}")
    public ResponseEntity<Object> deleteFamily(
        @PathVariable("id") long id
    )
    {
        return familyService.deleteFamily(id);
    }
}
