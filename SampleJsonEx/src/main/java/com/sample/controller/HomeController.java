package com.sample.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.domain.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ResponseEntity<?> getStudentObject() {
		List<Student> students = new ArrayList<Student>();
		Student student = new Student();
		student.setId("1");
		student.setName("Srinivas");
		student.setDept("IT");
		student.setSection("A");
		students.add(student);
		student = new Student();
		student.setId("2");
		student.setName("Rajesh");
		student.setDept("CSE");
		student.setSection("B");
		students.add(student);
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

}
