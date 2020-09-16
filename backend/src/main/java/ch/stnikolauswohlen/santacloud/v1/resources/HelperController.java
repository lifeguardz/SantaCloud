package ch.stnikolauswohlen.santacloud.v1.resources;

import ch.stnikolauswohlen.santacloud.v1.entities.dtos.helper.HelperDTO;
import ch.stnikolauswohlen.santacloud.v1.services.HelperService;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("2c7b04f3-7ea3-4f36-83a8-e94a1cb9f0d3")
@RequestMapping(value = "/v1")
@Validated
@PreAuthorize("hasAnyAuthority('ADMIN', 'OFFICE')")
public class HelperController
{
    private final HelperService helperService;

    @Autowired
    public HelperController(final HelperService helperService)
    {
        this.helperService = helperService;
    }

    @GetMapping(value = "/helpers")
    public ResponseEntity<Object> getHelpers(
        @RequestParam(name = "_start", defaultValue = "0", required = false) @Min(0) int start,
        @RequestParam(name = "_end", defaultValue = "10", required = false) @Positive int end,
        @RequestParam(name = "_sort", defaultValue = "id", required = false) String sortProperty,
        @RequestParam(name = "_order", defaultValue = "ASC", required = false) Direction sortDirection
    )
    {
        return helperService.getHelpers(
            start,
            end,
            sortProperty,
            sortDirection
        );
    }

    @GetMapping(value = "/helpers/{id}")
    public ResponseEntity<Object> getHelper(@PathVariable("id") long id)
    {
        return helperService.getHelper(id);
    }

    @PutMapping(value = "/helpers/{id}")
    public ResponseEntity<Object> updateHelper(
        @PathVariable("id") long id,
        @RequestBody HelperDTO helperDTO
    )
    {
        return helperService.updateHelper(id, helperDTO);
    }

    @PostMapping(value = "/helpers")
    public ResponseEntity<Object> creaateHelper(
        @RequestBody HelperDTO helperDTO
    )
    {
        return helperService.createHelper(helperDTO);
    }

    @DeleteMapping(value = "/helpers/{id}")
    public ResponseEntity<Object> deleteHelper(
        @PathVariable("id") long id
    )
    {
        return helperService.deleteHelper(id);
    }
}
