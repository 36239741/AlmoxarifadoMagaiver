package com.br.almoxarifado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket detalheApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.select()
		.apis(RequestHandlerSelectors.basePackage("com.br.almoxarifado"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(this.informacoesApi().build());
		return docket;
	}
	private ApiInfoBuilder informacoesApi() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("Almoxarifado Magaiver");
		apiInfoBuilder.description("API para uso de almoxarifdo / Trabalho web 4 Prof Marciely");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: somente por pessoas autorizadas");
		apiInfoBuilder.license("licenca - Open Source");
		apiInfoBuilder.licenseUrl("http://www.api.com.br");
		apiInfoBuilder.contact(this.contato());
		return apiInfoBuilder;
	}
	private Contact contato() {
		return new Contact("Henrique Nitatori, Josias Fonseca, Matheus Henrique","http://api.com.br","henrique_nitatori@hotmail.com" );
	}
}
