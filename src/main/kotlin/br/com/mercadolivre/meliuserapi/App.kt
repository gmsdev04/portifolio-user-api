package br.com.mercadolivre.meliuserapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["br.com.mercadolivre"])
class MeliUserApiApplication

fun main(args: Array<String>) {
	runApplication<MeliUserApiApplication>(*args)
}
