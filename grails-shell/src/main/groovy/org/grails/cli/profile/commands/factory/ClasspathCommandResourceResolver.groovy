/*
 * Copyright 2014 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.cli.profile.commands.factory

import groovy.transform.CompileStatic
import org.grails.cli.profile.Profile
import org.grails.io.support.PathMatchingResourcePatternResolver
import org.grails.io.support.Resource


/**
 * A {@link CommandResourceResolver} that resolves commands from the classpath under the directory META-INF/commands
 *
 * @author Graeme Rocher
 * @since 3.0
 */
@CompileStatic
class ClasspathCommandResourceResolver implements CommandResourceResolver {
    final String fileNamePattern
    PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(Thread.currentThread().contextClassLoader)

    ClasspathCommandResourceResolver(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern
    }

    @Override
    Collection<Resource> findCommandResources(Profile profile) {

        try {
            return resourcePatternResolver.getResources("classpath*:META-INF/commands/$fileNamePattern").toList()
        } catch (Throwable e) {
            return []
        }
    }
}
