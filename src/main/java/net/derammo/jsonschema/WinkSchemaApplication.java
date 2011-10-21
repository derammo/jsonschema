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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig.Feature;

/**
 * This adapter allows Apache Wink to use Jackson when reading both application/json and application/schema+json resources
 * 
 * @author ammo
 */
public class WinkSchemaApplication extends Application {

    private Set<Object> singletons = Collections.emptySet();

    public WinkSchemaApplication() {
        Set<Object> s = new HashSet<Object>();
        JacksonJsonProvider json = new JacksonJsonProvider();
        JacksonJsonProvider jsonSchema = new SchemaProvider();
        jsonSchema.enable(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true); // this is required for schema.items, which can be an array or atom
        s.add(json);
        s.add(jsonSchema);
        setSingletons(s);
    }
    
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    public void setSingletons(final Set<Object> singletons) {
        this.singletons = singletons;
    }

}
