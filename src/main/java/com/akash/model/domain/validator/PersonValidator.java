package com.akash.model.domain.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.akash.constant.Gender;
import com.akash.model.domain.Person;

public class PersonValidator implements Validator
{
	@Override
	public boolean supports(Class clazz) {
		return Person.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "field name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "field.required", "field age cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "field.required", "field gender cannot be empty");
		Person person = (Person)target;
		validateName(person.getName(), errors);
		validateAge(person.getAge(), errors);
		validateGender(person.getGender(), errors);
	}
	
	private void validateName(String name, Errors errors)
	{
		int length=name.length();
		if(length<=2)
			errors.rejectValue("name", "field.min.length", "length of the name must be greater than 2");
		else if(length>=20)
			errors.rejectValue("name", "field.mix.length", "length of the name must be less than 20");
	}
	
	private void validateAge(int age, Errors errors)
	{
		if(age<18)
			errors.rejectValue("age", "field.min.value", "value of age must be greater or equal to 18");
		else if(age>100)
			errors.rejectValue("age", "field.man.value", "value of age must be lesser or equal to 100");
	}
	
	private void validateGender(Gender gender, Errors errors)
	{
		if(gender==null)
			errors.rejectValue("gender", "field.required", "please select a gender");
	}
}