/*
 * Copyright 2010 JBoss, a divison Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.ioc.rebind;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import org.jboss.errai.bus.rebind.ProcessingContext;
import org.jboss.errai.bus.server.service.metadata.MetaDataScanner;
import org.jboss.errai.ioc.rebind.ioc.InjectorFactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProcessorFactory {
    private Map<Class<? extends Annotation>, AnnotationHandler> annotationHandlers;
    private InjectorFactory injectorFactory;

    public ProcessorFactory(InjectorFactory factory) {
        this.annotationHandlers = new HashMap<Class<? extends Annotation>, AnnotationHandler>();
        this.injectorFactory = factory;
    }

    public void registerHandler(Class<? extends Annotation> annotation, AnnotationHandler handler) {
        annotationHandlers.put(annotation, handler);
    }

    @SuppressWarnings({"unchecked"})
    public void process(MetaDataScanner scanner, ProcessingContext context) {
        for (Class<? extends Annotation> aClass : annotationHandlers.keySet()) {

            Set<Class<?>> classes = scanner.getTypesAnnotatedWith(aClass);
            for (Class<?> clazz : classes) {
                JClassType type = loadType(context.getOracle(), clazz);
                injectorFactory.addType(type);
                annotationHandlers.get(aClass).handle(type, type.getAnnotation(aClass), context);
            }
        }
    }

    private JClassType loadType(TypeOracle oracle, Class<?> clazz) {
        try {
            JClassType visit = oracle.getType(clazz.getName());
            return visit;
        }
        catch (NotFoundException e) {
            throw new RuntimeException("Failed to load type " + clazz.getName(), e);
        }
    }
}
