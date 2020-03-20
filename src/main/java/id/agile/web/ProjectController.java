package id.agile.web;

import id.agile.domain.Project;
import id.agile.exception.CustomException;
import id.agile.service.MapValidationErrorService;
import id.agile.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject
            (
                    @Valid
                    @RequestBody Project project,
                    BindingResult result
            ) {
        try {
            ResponseEntity<?> responseEntity = mapValidationErrorService.mapValidationService(result);
            if (responseEntity != null) return responseEntity;
            Project project1 = projectService.saveOrUpdateProject(project);
            return new ResponseEntity<Project>(project, HttpStatus.CREATED);
        } catch (CustomException cus) {
            return ResponseEntity.status(444).body("Project Already Exists");
        } catch (Throwable t) {
            return ResponseEntity.status(555).body(t);
        }

    }

    @GetMapping("/getProjectByIdentifier")
    public ResponseEntity<?> getProjectByIdentifier
            (
                    @RequestParam String project
            ) {
        try {
            Project projectByIdentifier = projectService.findProjectByIdentifier(project);
            return ResponseEntity.status(HttpStatus.OK).body(projectByIdentifier);
        }catch (CustomException cus){
            return ResponseEntity.status(555).body(cus);
        }catch (Throwable t){
            System.out.println(t);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(t);
        }

    }
}
