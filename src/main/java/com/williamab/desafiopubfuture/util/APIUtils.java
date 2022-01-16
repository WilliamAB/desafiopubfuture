package com.williamab.desafiopubfuture.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Métodos utilitários para a API.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 *
 */
public final class APIUtils {

	/**
	 * Cria um {@link Pageable} a partir do número da página, com ordenação padrão
	 * por id.
	 * 
	 * @param page o número da página
	 * @return um {@link Pageable}
	 */
	public static Pageable createPageable(int page) {
		return createPageable(page, 20);
	}

	/**
	 * Cria um {@link Pageable} a partir do número da página e do limite de
	 * registros, com ordenação padrão por id.
	 * 
	 * @param page  o número da página
	 * @param limit o limite de registros
	 * @return um {@link Pageable}
	 */
	public static Pageable createPageable(int page, int limit) {
		if (page < 1) {
			page = 1;
		}

		// A paginação começa em 0, por isso deve reduzir 1
		page -= 1;

		// O limite máximo de resultados por página deve ser 20
		if (limit < 1 || limit > 20) {
			limit = 20;
		}

		return PageRequest.of(page, limit, Sort.by("id"));
	}

}
