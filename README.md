# Conversor de Monedas en Java

Este es un proyecto sencillo para convertir monedas usando Java, la librería Gson 2.13.1 y la API gratuita [exchangerate-api.com](https://www.exchangerate-api.com/).

---

## Características

- Consulta tasas de cambio en tiempo real desde la API `exchangerate-api.com`.
- Conversión entre cualquier par de monedas soportadas por la API.
- Interfaz de consola simple y amigable.
- Código organizado en clases para facilitar mantenimiento y ampliación.

---

## Requisitos

- Java 11 o superior.
- IntelliJ IDEA (u otro IDE compatible con Java).
- Librería Gson 2.13.1 (puede añadirse vía Maven o manualmente).

---

## Cómo usar este proyecto

1. Clona o descarga el proyecto.
2. Abre el proyecto en IntelliJ.
3. Asegúrate de tener añadida la dependencia de Gson 2.13.1:
    - Si usas Maven, añade esta dependencia en tu `pom.xml`:

      ```xml
      <dependency>
          <groupId>com.google.code.gson</groupId>
          <artifactId>gson</artifactId>
          <version>2.13.1</version>
      </dependency>
      ```
    - O añade el archivo `.jar` de Gson manualmente a las librerías del proyecto.
4. Asegúrate de que el archivo `CurrencyConverter.java` esté en `src` y su nombre coincida con la clase pública.
5. Ejecuta la clase `CurrencyConverter`.
6. Sigue las instrucciones en la consola para convertir monedas:
    - Introduce la moneda base (ejemplo: USD).
    - Introduce la moneda destino (ejemplo: EUR).
    - Introduce la cantidad a convertir.
    - El programa mostrará el resultado.
7. Escribe `salir` para finalizar la aplicación.

---

## API usada

Este proyecto usa la API pública de **[exchangerate-api.com](https://www.exchangerate-api.com/)** para obtener tasas de cambio actualizadas.

---

## Notas

- Se utiliza la clave API pública en el código. Por temas de seguridad y uso intensivo, considera obtener tu propia clave API en la web oficial para uso prolongado.
- El programa depende de conexión a internet para obtener las tasas.
- La interfaz es de consola, por lo que no requiere interfaz gráfica adicional.

---

## ¿Quieres contribuir?

Puedes contribuir mejorando el código, agregando soporte a más formatos, interfaz gráfica o pruebas automáticas. ¡Las contribuciones son bienvenidas!

---

## Autor

Aldana Palacio

---

¡Gracias por usar este conversor de monedas!
