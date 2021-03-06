/*
 * Copyright 2011 Ammo Goettsch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.derammo.jsonschema;

public class HyperLink {
  String href;
  Relation rel;

  public enum Relation {
    related
  }

  public String getHref() {
    // REVISIT: this class should handle the URI escaping rules 
    // for this field, since java.net.URI cannot handle JSON pointers 
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public Relation getRel() {
    return rel;
  }

  public void setRel(Relation rel) {
    this.rel = rel;
  }
}
