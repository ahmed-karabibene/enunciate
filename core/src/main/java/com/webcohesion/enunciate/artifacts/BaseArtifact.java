/**
 * Copyright © 2006-2016 Web Cohesion (info@webcohesion.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webcohesion.enunciate.artifacts;

import java.util.*;

/**
 * Base implementation for an artifact.
 * 
 * @author Ryan Heaton
 */
public abstract class BaseArtifact implements Artifact {

  private final String module;
  private final String id;
  private final List<ArtifactDependency> dependencies = new ArrayList<ArtifactDependency>();
  private final Set<String> aliases = new TreeSet<String>();
  private boolean belongsOnServerSideClasspath = false;

  /**
   * @param module The name of the module.
   * @param id The module id.
   */
  protected BaseArtifact(String module, String id) {
    this.module = module;
    this.id = id;
  }

  /**
   * The module.
   *
   * @return The module.
   */
  public String getModule() {
    return module;
  }

  /**
   * The id.
   *
   * @return The id.
   */
  public String getId() {
    return id;
  }

  /**
   * Compares artifacts by module then by id.
   *
   * @param artifact The artifact to compare.
   * @return The comparison.
   */
  public int compareTo(Artifact artifact) {
    String thisId = this.id == null ? "" : this.id;
    String otherId = artifact.getId();
    if (otherId == null) {
      otherId = "";
    }

    return thisId.compareTo(otherId);
  }

  /**
   * This artifact is, by default, not bundled if it's an artifact bundle.
   *
   * @return This artifact is, by default, not bundled if it's an artifact bundle.
   */
  public boolean isPublic() {
    return (this instanceof ArtifactBundle);
  }

  @Override
  public boolean isBelongsOnServerSideClasspath() {
    return belongsOnServerSideClasspath;
  }

  public void setBelongsOnServerSideClasspath(boolean belongsOnServerSideClasspath) {
    this.belongsOnServerSideClasspath = belongsOnServerSideClasspath;
  }

  /**
   * The dependencies for this artifact.
   *
   * @return The dependencies for this artifact.
   */
  public List<ArtifactDependency> getDependencies() {
    return dependencies;
  }

  /**
   * Adds a dependency to this artifact.
   *
   * @param dependency The dependency to add.
   */
  public void addDependency(ArtifactDependency dependency) {
    this.dependencies.add(dependency);
  }

  /**
   * Sets the dependencies for this artifact.
   *
   * @param dependencies The dependencies to set.
   */
  public void setDependencies(List<ArtifactDependency> dependencies) {
    this.dependencies.clear();
    this.dependencies.addAll(dependencies);
  }

  /**
   * The aliases for this artifact.
   *
   * @return The aliases for this artifact.
   */
  public Set<String> getAliases() {
    return Collections.unmodifiableSet(this.aliases);
  }

  /**
   * Add an alias to this artifact.
   *
   * @param alias An alias for this artifact.
   */
  public void addAlias(String alias) {
    this.aliases.add(alias);
  }
}
