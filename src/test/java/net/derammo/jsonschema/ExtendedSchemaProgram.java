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

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ExtendedSchemaProgram {
    // NOTE: this class must be static or not embedded, otherwise Jackson can not infer a deserializer
    static class MySchema extends HyperSchemaDraft3<MySchema> {
        protected int foo;

        // this accessor allows Jackson to populate 'foo' from '"foo":1' in the JSON document
        public void setFoo(int foo) {
            this.foo = foo;
        }
    }

    public static void main(String[] args) {
        // cause the deserializer for MySchema.class to be used for all schema
        // instances, so that "foo" is parsed as a schema property even in 
        // schemas referenced by the main schema
        SchemaDraft3.setApplicationSchemaClass(MySchema.class);
        
        try {
            // read and parse
            MySchema schema = new ObjectMapper().readValue(System.in, MySchema.class);
            
            // result is an object of class MySchema
            System.out.println(schema.toString());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
