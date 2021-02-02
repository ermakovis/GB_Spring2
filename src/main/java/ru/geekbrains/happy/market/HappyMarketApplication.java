package ru.geekbrains.happy.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@PropertySource("classpath:secured.properties")
public class HappyMarketApplication {
	// Домашнее задание:
	// 1. Если пользователь авторизован показываем в шапке какую-нибудь информацию о нем (имя),
	// если нет, там же в шапке должны боть поля логин/пароль и кнопка войти
	// 2. Попробуйте сделать оформление заказа с привязкой к текущему пользователю
	// (делать адрес доставки или что-то еще не требуется)

	public static void main(String[] args) {
		SpringApplication.run(HappyMarketApplication.class, args);
	}
}
