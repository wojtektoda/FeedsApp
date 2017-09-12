package pl.feedsapp.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.feedsapp.repository.UserRepository;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UniqueUserName constraintAnnotation) {
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userRepository == null){
			return true; // only because of Init method DbInitService
		}
		
		return userRepository.findByName(username) == null;
	}

}
