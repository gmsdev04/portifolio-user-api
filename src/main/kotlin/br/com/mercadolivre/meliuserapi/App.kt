package br.com.mercadolivre.meliuserapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MeliUserApiApplication

fun main(args: Array<String>) {
	runApplication<MeliUserApiApplication>(*args)
}
