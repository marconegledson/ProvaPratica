package br.com.montreal.provapratica.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.montreal.provapratica.domain.Produto;

@Component
public class ProdutoValidator implements Validator{

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object object, Errors errors) {
		Produto produto = (Produto) object;
		if(StringUtils.isEmpty(produto.getNome())){
			errors.rejectValue("nome", "Nome incorreto.");
		}
		
	}

}
