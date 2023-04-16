package com.sparrow.mini.chat.seq.annotations;

import com.sparrow.mini.chat.seq.entity.Scene;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Push {
    Scene[] scenes() default {};
}
