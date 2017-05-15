package br.com.montreal.provapratica.exceptionhandler;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.montreal.provapratica.restcontroller.ProdutoRestController;

@ControllerAdvice
public class ControllerExceptionTranslator {

	private static final String PRODUTO_NOT_FOUND = "Produto nao encontrado";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoRestController.class);
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = PRODUTO_NOT_FOUND)
	@ResponseBody
	public String  handleException(EntityNotFoundException exception) {
		LOGGER.error("Entity Not Found Exception {}", exception.getMessage());
		return PRODUTO_NOT_FOUND;
	}

}
