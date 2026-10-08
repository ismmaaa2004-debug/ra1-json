package es.ciudadescolar.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.ciudadescolar.modelo.Alumno;



public class JsonManager {

        private static final Logger LOG= LoggerFactory.getLogger(JsonManager.class);

        private static ObjectMapper mapper = new ObjectMapper();

        public static List<Alumno> parsearJsonAlumnos(File jsonFich)
        {
            List<Alumno> alumnos = null;
        

            JsonNode raizNodo = null;
            JsonNode alumnosNodo = null;
            Alumno alumno = null;

            try {
                // esto es análogo a lo que hacíamos con DOM en xml usando el método parse()
                // mientras que en xml obligatoriamente tengo que tener un elemento raiz
                // en Json no es obligatorio tener un objeto raiz, sino que puede ser un array o un valor(null, cadena, número)

                raizNodo = mapper.readTree(jsonFich);
            
                if (raizNodo.isObject())
                {
                    LOG.info("el curso es: "+raizNodo.get("curso").asText());
                    LOG.info("el codigo es: "+raizNodo.get("codigo").asInt());
                    LOG.info("el centro es: "+raizNodo.get("centro").asText());

                    alumnosNodo = raizNodo.get("alumnos");
                    if (alumnosNodo.isArray())
                    {
                        alumnos = new ArrayList<Alumno>();
                        for(JsonNode alumnoNodo:alumnosNodo)
                        {
                            alumno = new Alumno();
                            alumno.setEdad(Integer.valueOf(alumnoNodo.get("edad").asInt()));
                            //alumno.setEdad(alumno = new Alumno();
                            alumno.setNombre(alumnoNodo.get("nombre").asText());
                            alumno.setExpediente(String.valueOf(alumnoNodo.get("expediente").asInt()));
                            
                            LOG.info("Nuevo alumno: "+alumno);
                            alumnos.add(alumno);
                            LOG.trace("Anaidido alumno a la coleccion");
                        }
                        
                    }
                }

            } 
            catch (IOException e) 
            {
                LOG.error("Error durante el parseo del Json ["+jsonFich.getName());
            }
            
            return alumnos;
        }


        public static void generarJsonAlumnosMayores20anios(List<Alumno> alumnosEntrada, File ficheroJsonSalida) 
        {
            ArrayNode arrayAlumnos = mapper.createArrayNode();
            
            ObjectNode nodoAlumno = null;

            for(Alumno al:alumnosEntrada)
            {
                if (al.getEdad() > 20){

                    nodoAlumno = mapper.createObjectNode();
                    nodoAlumno.put("exp", al.getExpediente());
                    nodoAlumno.put("edad", al.getEdad());
                    nodoAlumno.put("nom", al.getNombre());

                    arrayAlumnos.add(nodoAlumno);
                }
            }

            try 
            {
                mapper.writerWithDefaultPrettyPrinter().writeValue(ficheroJsonSalida, arrayAlumnos);
            } 
            catch (IOException e) 
            {
                LOG.error("Error durante la escritura del fichero Json: "+e.getMessage());
            }
        }
        // La libreria Jackson ya tiene desarollada la funcionalidad de serializaxión de objetos y por tanto
        // no necesitamos crear la estructura en memoria previamente al volacado
        // usariamos la propia serializacion de Jackson
        public static void generarJsonAlumnosV2(List<Alumno> alumnosEntrada, File ficheroJsonSalida) 
        {
            
            try 
            {
                mapper.writerWithDefaultPrettyPrinter().writeValue(ficheroJsonSalida, alumnosEntrada);
            } 
            catch (IOException e) 
            {
                LOG.error("Error en la serializacion de los alumnos en un fichero json");
            }
        }
}
