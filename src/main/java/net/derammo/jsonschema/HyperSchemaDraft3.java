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

import java.util.ArrayList;

public class HyperSchemaDraft3<ApplicationSchema extends SchemaDraft3<ApplicationSchema> > extends SchemaDraft3<ApplicationSchema> {
    // NOTE: it is better to default this so we don't have to check for null later
    private ArrayList<HyperLink> links = new ArrayList<HyperLink>();

    public HyperSchemaDraft3() {
        super();
    }

    public ArrayList<HyperLink> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<HyperLink> links) {
        this.links = links;
    }

}