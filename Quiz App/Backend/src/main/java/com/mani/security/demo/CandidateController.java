package com.mani.security.demo;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins="http:/localhost:3000")
@RestController
@RequestMapping("/Question")
public class CandidateController {
	 @Autowired
    private  QuestionService Service;


	@GetMapping
	public List<Question> retrievQuestion() {
		return Service.retrieveQuestion();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin:create')")
	public Question createQuestion(@RequestBody Question question) {
		return Service.createQuestion(question);
	}

	@PutMapping
	@PreAuthorize("hasAuthority('admin:update')")
	public String updateQuestion(@RequestBody Question question) {
		Service.updateQuestion(question);
		return "Updated successfully";
	}

    

   @DeleteMapping("/{quesId}")
   @PreAuthorize("hasAuthority('admin:delete')")
   public String deleteQuestion(@PathVariable Integer quesId){
    Service.deleteQuestion(quesId);
    return "deleted Successfully";
  }
}
	