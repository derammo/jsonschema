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
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.type.JavaType;

/**
 * Custom deserializer that determines what to build based on structure of "type" value
 * @author ammo
 */
class TypeDeserializer extends JsonDeserializer<TypeDeserializer.TypeVariant> {
    /**
     * Base for helper classes representing the various forms of 'type'
     * @author ammo
     */
    static class TypeVariant {
    }   

    /**
     * A type specified as an entire schema instance, only found in unions
     * @author ammo
     */
    static class SchemaType<ApplicationSchema> extends TypeDeserializer.TypeVariant {
        private ApplicationSchema schema;

        public SchemaType(ApplicationSchema schema) {
            this.schema = schema;
        }

        public ApplicationSchema getSchema() {
            return schema;
        }
    }    

    /**
     * A simple type consisting only of a type name
     * @author ammo
     */
    static class SimpleType extends TypeDeserializer.TypeVariant {
        private String name;

        public SimpleType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    
    /**
     * A union of types, as represented by an array of simple and schema types
     * @author ammo
     */
    static class UnionType<ApplicationSchema> extends TypeDeserializer.TypeVariant {
        private ArrayList<TypeDeserializer.TypeVariant> union;

        public UnionType(ArrayList<TypeDeserializer.TypeVariant> types) {
            this.union = types;
        }

        @SuppressWarnings("unchecked")
        public Collection<ApplicationSchema> getSchemaTypes() {
            LinkedList<ApplicationSchema> schemas = new LinkedList<ApplicationSchema>();
            for (TypeDeserializer.TypeVariant type : union) {
                if (type instanceof SchemaType<?>) {
                    schemas.add(((SchemaType<ApplicationSchema>) type).getSchema());
                }
            }
            return schemas;
        }

        public Collection<String> getSimpleTypes() {
            LinkedList<String> names = new LinkedList<String>();
            for (TypeDeserializer.TypeVariant type : union) {
                if (type instanceof SimpleType) {
                    names.add(((SimpleType) type).getName());
                }
            }
            return names;
        }
    }  
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public TypeVariant deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonToken token = parser.getCurrentToken();
        DeserializationConfig config = ctxt.getConfig();
        JavaType type = null;
        BeanProperty property = null;
        switch (token) {
        case START_ARRAY: {
            // read each item of the array according to its type
            ArrayList<TypeVariant> typeList;

            // WARNING: cannot use the Jackson ArrayList deserializer because it will not use our custom
            // deserializer for its objects
            //
            // type = ctxt.getTypeFactory().constructCollectionType(ArrayList.class, Type.class);
            // @SuppressWarnings("unchecked")
            // typeList = (ArrayList<Type>) ctxt.getDeserializerProvider().findValueDeserializer(config, type,
            // property).deserialize(parser, ctxt);
            // for (Type schemaType : typeList) {
            // if (schemaType instanceof UnionType) {
            // throw ...
            // }
            // }

            // manually deserialize the array
            typeList = new ArrayList<TypeVariant>();
            while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
                switch (token) {
                case VALUE_NULL:
                    throw new JsonParseException(
                            "union types can only directly contain simple types and schemas, not null",
                            parser.getCurrentLocation());
                case START_ARRAY:
                    throw new JsonParseException(
                            "union types can only directly contain simple types and schemas, not other unions",
                            parser.getCurrentLocation());
                case VALUE_STRING:
                    type = ctxt.getTypeFactory().constructType(SimpleType.class);
                    typeList.add((TypeVariant) ctxt.getDeserializerProvider()
                            .findValueDeserializer(config, type, property).deserialize(parser, ctxt));
                    break;
                case START_OBJECT:
                    // we always create the specified application schema for all items, which allows the caller
                    // to register an extended schema, which will then be used everywhere
                    type = ctxt.getTypeFactory().constructType(SchemaDraft3.getApplicationSchemaClass());
                    typeList.add(new SchemaType(ctxt.getDeserializerProvider().findValueDeserializer(config, type, property).deserialize(parser, ctxt)));
                    break;
                }
            }
            return new UnionType(typeList);
        }
        case VALUE_STRING:
            type = ctxt.getTypeFactory().constructType(SimpleType.class);
            return (TypeVariant) ctxt.getDeserializerProvider().findValueDeserializer(config, type, property)
                    .deserialize(parser, ctxt);
        case START_OBJECT:
        default:
        }
        throw new JsonParseException("type must be a simple type or a union (array) of types",
                parser.getCurrentLocation());
    }
}