package com.jtransc.annotation;



import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Repeatable(value = JTranscMethodBodyList.class)
public @interface JTranscMethodBody {
	String target();
	String cond() default "";
	String[] value();
	boolean async() default false;
}