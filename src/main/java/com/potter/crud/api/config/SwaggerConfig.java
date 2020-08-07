package com.potter.crud.api.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.potter.crud.api.utils.Mappers;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Cria um Docket para configurar o Swagger
	 * 
	 * O valor do "base_package" que é o pacote das classes
	 * O valor do "path" é de onde vai ser originado 
	 * o mapeamentos dos serviços através do swagger
	 * 
	 * @return Docket
	 * 
	 * */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(Mappers.SWAGGER_BASE_PACKAGE))
				.paths(regex(Mappers.API_CHARACTER + ".*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	/**
	 * Este método é para passar informações ao Docket
	 * Estas informações serão apresentandas na página html do swagger-ui
	 * 
	 * Atributos que compõe o objeto ApiInfo:
	 * Titulo, Descricao, Versao, Termo de Serviço, Contao, Licença e URL Licença
	 * 
	 * @return ApiInfo
	 */
	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {
		
		ApiInfo apiInfo = new ApiInfo(
				Mappers.TITULO_API_INFO,
				Mappers.DESCRICAO_API_INFO,
				Mappers.VERSION_API_INFO,
				Mappers.TERMO_API_INFO,
				new Contact(Mappers.NOME_CONTATO_API_INFO, Mappers.URL_CONTATO_API_INFO, Mappers.EMAIL_CONTATO_API_INFO),
				Mappers.LICENCA_API_INFO,
				Mappers.URL_LICENCA_API_INFO, new ArrayList<VendorExtension>() 
		);  
		
		return apiInfo;
	}
	
	
}
