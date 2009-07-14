package org.apache.maven.project;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.List;

import org.apache.maven.artifact.InvalidRepositoryException;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.model.Model;
import org.apache.maven.model.Repository;
import org.codehaus.plexus.classworlds.realm.ClassRealm;

/**
 * Assists the project builder.
 * 
 * @author Benjamin Bentmann
 */
public interface ProjectBuildingHelper
{

    /**
     * Creates the effective artifact repositories from the specified POM repositories.
     * 
     * @param pomRepositories The POM repositories to create the artifact repositories from, must not be {@code null}.
     * @param externalRepositories The external (and already mirrored) repositories to merge into the result list, may
     *            be {@code null}.
     * @return The effective artifact repositories, never {@code null}.
     * @throws InvalidRepositoryException
     */
    List<ArtifactRepository> createArtifactRepositories( List<Repository> pomRepositories,
                                                         List<ArtifactRepository> externalRepositories )
        throws InvalidRepositoryException;

    /**
     * Creates the project realm that hosts the build extensions of the specified model.
     * 
     * @param model The model to create the project realm for, must not be {@code null}
     * @param localRepository The local repository to use for artifact resolution, must not be {@code null}.
     * @param remoteRepositories The remote repositories to use for artifact resolution, must not be {@code null}.
     * @return The project realm, never {@code null}.
     * @throws ArtifactResolutionException If any build extension could not be resolved.
     */
    ClassRealm createProjectRealm( Model model, ArtifactRepository localRepository,
                                   List<ArtifactRepository> remoteRepositories )
        throws ArtifactResolutionException;

}
