package io.daiwei.auto.condition;

import io.daiwei.auto.annotion.EnableAutoConfig;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotatedTypeMetadata;
import sun.reflect.annotation.AnnotationType;

import java.lang.annotation.Annotation;

/**
 * Created by Daiwei on 2021/2/20
 */
public class AutoCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return true;
    }
}
