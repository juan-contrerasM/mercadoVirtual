package co.edu.uniquindio.mercado.controllerView;

import co.edu.uniquindio.mercado.controller.ControllerEstadistica;
import co.edu.uniquindio.mercado.model.Megusta;
import co.edu.uniquindio.mercado.model.Publicacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lombok.SneakyThrows;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LongSummaryStatistics;
import java.util.ResourceBundle;

public class EstadisticaControllerView implements Initializable {

    @FXML
    private BarChart<String, Integer> likes;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    private ControllerEstadistica controllerEstadistica;
    private Publicacion publicacion;
    @FXML
    private Label lblCantVisualizaciones;

    @FXML
    private Label lblCantaComentarios;

    @FXML
    private Label lblCantaLikes;

    @FXML
    private Label lblMediaComentarios;

    @FXML
    private Label lblMediaLikes;

    @FXML
    private Label lblMediaVisualizaciones;


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        controllerEstadistica = new ControllerEstadistica();
        publicacion = controllerEstadistica.obtenerPublicaion();
        publicacion.setListMegusta(controllerEstadistica.obtenerlistPublicaciones());
       float dias=diasDesdeFecha(publicacion.getDiaPublicado());
        lblCantaComentarios.setText(String.valueOf(publicacion.getContadorComentarios()));
        lblCantaLikes.setText(String.valueOf(publicacion.getContadorMegusta()));
        lblCantVisualizaciones.setText(String.valueOf(publicacion.getVisualizacion()));

        //mostramos medias

        float aux= publicacion.getContadorMegusta()/dias;
        lblMediaLikes.setText(aux+"");
        aux= publicacion.getContadorComentarios()/dias;
        lblMediaComentarios.setText(aux+"");
        aux= publicacion.getVisualizacion()/dias;
        lblMediaVisualizaciones.setText(aux+"");


        System.out.println(publicacion);


        LocalDate diaPublicado= publicacion.getDiaPublicado();

        int contador=0;

        for (int i = 0; i <=dias; i++) {
            ArrayList<Megusta> listLikes = buscarMegusta(diaPublicado);
            if (!listLikes.isEmpty() && contador<listLikes.size() ) {
                Megusta megusta = listLikes.get(contador);
                XYChart.Series set1 = new XYChart.Series<>();
                set1.getData().add(new XYChart.Data(megusta.getFecha()+"", listLikes.size()));
                likes.getData().addAll(set1);
            }
            contador=0;
            diaPublicado = incrementarDias(1, diaPublicado);
        }


        //mostramos info de la publicaion





/*
        XYChart.Series set1= new XYChart.Series<>();

        set1.getData().add(new XYChart.Data("jAMES",500));
        likes.getData().addAll(set1);

 */
    }

    public ArrayList<Megusta> buscarMegusta(LocalDate fecha){
        ArrayList<Megusta>lisMegusta= new ArrayList<>();
        for (int i = 0; i <publicacion.getListMegusta().size() ; i++) {
            if(publicacion.getListMegusta().get(i).getFecha().equals(fecha)){
                lisMegusta.add(publicacion.getListMegusta().get(i));
            }
        }
        return  lisMegusta;
    }

    // Método para incrementar la fecha por un número de días
    public LocalDate incrementarDias(long dias, LocalDate fecha) {
      return   fecha = fecha.plusDays(dias);
    }
    public static long diasDesdeFecha(LocalDate fecha) {
        LocalDate fechaActual = LocalDate.now();
        return ChronoUnit.DAYS.between(fecha, fechaActual);
    }
}