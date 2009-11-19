package org.jboss.errai.widgets.rebind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that the field should be bound as a widget mapper class. WidgetMappers are responsible
 * for mapping a collection of many elements to a single, complex widget. 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WidgetMapper {
    String value();
}