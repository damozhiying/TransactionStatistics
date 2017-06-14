package com.n26.statistics.utils.json;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@JacksonAnnotation
@JacksonAnnotationsInside
@JsonInclude(ALWAYS)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public @interface JsonCustomSnakeCase {
}
