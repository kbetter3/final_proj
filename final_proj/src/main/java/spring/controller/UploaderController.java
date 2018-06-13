package spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UploaderController {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(method= {RequestMethod.GET}, value= {"/uploader/config"})
	public String homoe() {
		return "uploader/config";
	}
}
