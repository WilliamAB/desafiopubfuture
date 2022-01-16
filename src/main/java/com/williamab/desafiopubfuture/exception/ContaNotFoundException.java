package com.williamab.desafiopubfuture.exception;

import javax.persistence.EntityNotFoundException;

/**
 * Exceção que deve ser lançada quando uma conta não for encontrada no banco de
 * dados.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public class ContaNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1009486194051137687L;

	public ContaNotFoundException(Long id) {
		super(String.format("Conta [ID: %d] não encontrada!", id));
	}

}
