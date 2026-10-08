import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ciudadescolar.modelo.Alumno;
import es.ciudadescolar.util.JsonManager;

public class App {

    private static final Logger LOG= LoggerFactory.getLogger(App.class);
    public static void main(String[] args) throws Exception {
        File ficheroJson = new File("alumnos.json");
        File ficheroJsonSalida = new File("alumnos2.json");
        
        LOG.info("Comenzando el parseo del fichero Json ["+ficheroJson.getAbsolutePath()+"]");
        List<Alumno> alumnosEntrada = JsonManager.parsearJsonAlumnos(ficheroJson);
        LOG.info("Finalizado el parseo del fichero Json ["+ficheroJson.getAbsolutePath()+"]");

        if (alumnosEntrada != null && !alumnosEntrada.isEmpty())
        {

            LOG.info("comenzando la generacion del fichero Json ["+ficheroJsonSalida.getAbsolutePath()+"]");
            JsonManager.generarJsonAlumnosV2(alumnosEntrada, ficheroJsonSalida);
            LOG.info("finalizada la generacion del fichero Json ["+ficheroJsonSalida.getAbsolutePath()+"]");
        }
        else
        {
            LOG.warn("No se ha recuperado ningún alumno del Json de entrada");
        }
    }
}
