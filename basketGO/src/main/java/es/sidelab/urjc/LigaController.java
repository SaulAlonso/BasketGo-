package es.sidelab.urjc;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.tool.xml.XMLWorkerHelper;


@Controller
public class LigaController {
	

	@Autowired
	private LigaRepository liga;
	@Autowired
	private ClasificacionRepository clasificacion;
	@Autowired
	private EquipoRepository equipo;
	
	@PostConstruct
	public void init(){
		
	}
	
	@GetMapping("/liga")
	public String creandoLiga(Model model, HttpSession session){

		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		return "preliga";
	}
	
	
	@GetMapping("/liga/clasificacion")
	
	public String ligaClasificacion(Model model, HttpSession session,
			@RequestParam String nombreLiga){
		
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		List<Liga> listaLigas = liga.findByNombre(nombreLiga);
		Clasificacion clasificacionAux;
		if(listaLigas.size()>0){
			clasificacionAux = listaLigas.get(0).getClasificacion();
			model.addAttribute("clasificacionliga", clasificacionAux.getListaClasificacion());
			return "liga";
		}
		//model.addAttribute("clasificacionliga", liga.findByNombre("Liga1").getClasificacion().getListaClasificacion());
		String mensaje = "No existe la liga";
		model.addAttribute("mensaje", mensaje);
		return "preliga";
	}
	
	@GetMapping("/liga/topdf")
	public String get (Model model, @RequestParam String nombreLiga){               
		
		if(nombreLiga.isEmpty()){
			model.addAttribute("mensaje", "Debe introducir el nombre de la liga");
			return "preliga";
		}
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url="http://localhost:8080/verpdf/"+nombreLiga;
		ObjectNode data = restTemplate.getForObject(url, ObjectNode.class);
		
		if(data==null){
			model.addAttribute("mensaje", "No existe la Liga");
			return "preliga";
		}
		
		List<String> nombresEquipos = new ArrayList<String>();
		List<String> puntosEquipos = new ArrayList<String>();
		List<String> victoriasEquipos = new ArrayList<String>();
		List<String> derrotasEquipos = new ArrayList<String>();
		ArrayNode items = (ArrayNode) data.get("listaClasificacion");
		
		if(items.size()<1){
			model.addAttribute("mensaje", "No existe la Liga");
			return "preliga";
		}
		
		for (int i = 0; i < items.size(); i++) {
			JsonNode item = items.get(i);
			String nombresEquipo = item.get("nombreEquipo").asText();
			String puntosEquipo = ""+item.get("puntuacion").asInt();
			String victoriasEquipo = ""+item.get("numeroVictorias").asInt();
			String derrotasEquipo = ""+item.get("numeroDerrotas").asInt();
			nombresEquipos.add(nombresEquipo);
			puntosEquipos.add(puntosEquipo);
			victoriasEquipos.add(victoriasEquipo);
			derrotasEquipos.add(derrotasEquipo);
		}
	
		try {
		    Document document = new Document();
		    try {
		        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/templates/pdf.pdf"));
			} catch (FileNotFoundException fileNotFoundException) {
			    System.out.println("No such file was found to generate the PDF "
			            + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
			}
		document.open();
		document.addTitle("Clasificación de "+nombreLiga);
		document.addSubject("usando iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("BasketGO");
		document.addCreator("BasketGO");
	 
	    // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF
		
		Chunk chunk = new Chunk("Clasificación "+nombreLiga);
		Paragraph parrafo = new Paragraph(chunk);
		document.add(parrafo);
		
		document.add(new Paragraph(""));
		
		PdfPTable tabla = new PdfPTable(4);
		tabla.addCell("Nombre Equipo");
		tabla.addCell("Puntos");
		tabla.addCell("Victorias");
		tabla.addCell("Derrotas");
		for (int i = 0; i < nombresEquipos.size(); i++)
		{
			tabla.addCell(nombresEquipos.get(i));
			tabla.addCell(puntosEquipos.get(i));
			tabla.addCell(victoriasEquipos.get(i));
			tabla.addCell(derrotasEquipos.get(i));
		}
		document.add(tabla);
	 
	    document.close();
		} catch (DocumentException documentException) {
			
		}
		
		model.addAttribute("mensaje", "Se creó el pdf con éxito");
			    
		return "preliga";
			 
	}
	
	
	
	@GetMapping("/liga/creacion") 
	public String creacionLiga(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean creaLiga = true;
		model.addAttribute("creacionliga", creaLiga);
		return "creacionliga";
	}
	
	@GetMapping("/liga/creando") 
	public String creandoLiga(Model model, HttpSession session,
			@RequestParam String nombreLiga, @RequestParam String nombreEquipo1,
			@RequestParam String nombreEquipo2){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean creaLiga = true;
		String mensaje = "";
		if(nombreLiga==""){
			mensaje = "El nombre de la Liga debe tener al menos un caracter";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		if(nombreEquipo1=="" || nombreEquipo2==""){
			mensaje = "El nombre de los Equipos debe tener al menos un caracter";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		
		if(nombreEquipo1.equals(nombreEquipo2)){
			mensaje = "Los equipos deben ser diferentes";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		
		List<Equipo> equipo1 = equipo.findByNombreEquipo(nombreEquipo1);
		List<Equipo> equipo2 = equipo.findByNombreEquipo(nombreEquipo2);
		
		if(equipo1.size()!=1){
			mensaje = "El Equipo 1 no existe";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		if(equipo2.size()!=1){
			mensaje = "El Equipo 2 no existe";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			return "creacionliga";
		}
		
		List<Equipo> lista3 = new ArrayList<Equipo> ();
		
		Clasificacion clasificacion1 = new Clasificacion("Clasificacion"+nombreLiga,lista3);
		
		if(liga.findByNombre(nombreLiga).size()>0){
			creaLiga=true;
			mensaje="Ya existe una Liga con ese nombre";
			model.addAttribute("creacionliga", creaLiga);
			model.addAttribute("mensaje", mensaje);
			
			return "creacionliga";
		}
		
		List<Clasificacion> clasificacionAux = clasificacion.findAll(); 
		
		if(clasificacionAux.size()>0){
			for(Clasificacion cl:clasificacionAux){
				if(equipo.findByNombreEquipo(nombreEquipo1).size()>0){
					creaLiga=true;
					mensaje="El Equipo1 ya pertenecen a otra liga";
					model.addAttribute("creacionliga", creaLiga);
					model.addAttribute("mensaje", mensaje);					
					return "creacionliga";
				}
				if(equipo.findByNombreEquipo(nombreEquipo2).size()>0){
					creaLiga=true;
					mensaje="El Equipo2 ya pertenecen a otra liga";
					model.addAttribute("creacionliga", creaLiga);
					model.addAttribute("mensaje", mensaje);					
					return "creacionliga";
				}
			}
		}
		
		clasificacion1.getListaClasificacion().add(equipo2.get(0));
		clasificacion1.getListaClasificacion().add(equipo1.get(0));
		
		clasificacion.save(clasificacion1);
		
		Liga liga1 = new Liga(nombreLiga, clasificacion1);
				
		liga.save(liga1);
		
		creaLiga=false;
		mensaje="Liga creada correctamente";
		model.addAttribute("creacionliga", creaLiga);
		model.addAttribute("mensaje", mensaje);
		
		return "creacionliga";
	}
	
	@GetMapping("/liga/gestion") 
	public String gestionLiga(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean gestionarliga = true;
		model.addAttribute("gestionarliga", gestionarliga);
		return "gestionarliga";
	}
	
	@GetMapping("/liga/gestionando") 
	public String gestionandoLiga(Model model, HttpSession session,
			@RequestParam String nombreLiga, @RequestParam String nombreEquipo,
			@RequestParam String victoriasEquipo, @RequestParam String derrotasEquipo){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean gestionarliga = true;
		String mensaje = "";
		if(nombreLiga==""){
			mensaje = "El nombre de la Liga debe tener al menos un caracter";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		if(nombreEquipo==""){
			mensaje = "El nombre del Equipo debe tener al menos un caracter";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		List<Equipo> equipo1 = equipo.findByNombreEquipo(nombreEquipo);
		
		if(equipo1.size()<1){
			mensaje = "El Equipo no existe";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		List<Liga> liga1 = liga.findByNombre(nombreLiga);
		
		if(liga1.size()<1){
			mensaje="La liga no existe";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		int vic, der;

		try{
			vic=Integer.parseInt(victoriasEquipo);
		}catch(Exception e){
			mensaje = "El número de victorias debe ser un número válido";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		try{
			der=Integer.parseInt(derrotasEquipo);
		}catch(Exception e){
			mensaje = "El número de derrotas debe ser un número válido";
			model.addAttribute("gestionarliga", gestionarliga);
			model.addAttribute("mensaje", mensaje);
			return "gestionarliga";
		}
		
		equipo1.get(0).setNumeroVictorias(vic);
		equipo1.get(0).setNumeroDerrotas(der);
		equipo1.get(0).setPuntuacion(vic-der);
		equipo.save(equipo1.get(0));
		mensaje="Clasificacion actualizada correctamente";
		model.addAttribute("gestionarliga", gestionarliga);
		model.addAttribute("mensaje", mensaje);					
		return "gestionarliga";	

	}
	
	@GetMapping("/liga/anadir") 
	public String anadirEquipoLiga(Model model, HttpSession session){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean anadirequipoliga = true;
		model.addAttribute("anadirequipoliga", anadirequipoliga);
		return "anadirequipoliga";
	}
	
	@GetMapping("/liga/anadiendo") 
	public String anadiendoEquipoLiga(Model model, HttpSession session,
			@RequestParam String nombreLiga, @RequestParam String nombreEquipo1,
			@RequestParam String nombreEquipo2){
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("admin", (boolean) session.getAttribute("administrador"));
		}
		if((boolean) session.getAttribute("loged")){
			model.addAttribute("user", (boolean) session.getAttribute("usuario"));
		}
		boolean anadirequipoliga = true;
		String mensaje = "";
		if(nombreLiga==""){
			mensaje = "El nombre de la Liga debe tener al menos un caracter";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		if(nombreEquipo1==""){
			mensaje = "El nombre del Equipo1 debe tener al menos un caracter";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		if(nombreEquipo2==""){
			mensaje = "El nombre del Equipo2 debe tener al menos un caracter";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		if(nombreEquipo1.equals(nombreEquipo2)){
			mensaje = "Los equipos deben ser distintos";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		List<Equipo> equipo1 = equipo.findByNombreEquipo(nombreEquipo1);
		List<Equipo> equipo2 = equipo.findByNombreEquipo(nombreEquipo2);
		
		if(equipo1.size()<1){
			mensaje = "El Equipo1 no existe";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		if(equipo2.size()<1){
			mensaje = "El Equipo2 no existe";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		List<Liga> liga1 = liga.findByNombre(nombreLiga);
		
		if(liga1.size()<1){
			mensaje="La liga no existe";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);
			return "anadirequipoliga";
		}
		
		List<Clasificacion> clasificacionAux = clasificacion.findAll();
		
		if(clasificacionAux.size()>=10){
			mensaje="Se ha alcanzado el máximo de Equipos para esta Liga";
			model.addAttribute("anadirequipoliga", anadirequipoliga);
			model.addAttribute("mensaje", mensaje);					
			return "anadirequipoliga";
		}
		
		if(clasificacionAux.size()>0){
			for(Clasificacion cl:clasificacionAux){
				if(equipo.findByNombreEquipo(nombreEquipo1).size()>0){
					mensaje="El Equipo ya pertenece a otra liga";
					model.addAttribute("anadirequipoliga", anadirequipoliga);
					model.addAttribute("mensaje", mensaje);					
					return "anadirequipoliga";
				}
				if(equipo.findByNombreEquipo(nombreEquipo2).size()>0){
					mensaje="El Equipo ya pertenece a otra liga";
					model.addAttribute("anadirequipoliga", anadirequipoliga);
					model.addAttribute("mensaje", mensaje);					
					return "anadirequipoliga";
				}
			}
			
		}
		
		liga1.get(0).getClasificacion().getListaClasificacion().add(equipo1.get(0));
		liga1.get(0).getClasificacion().getListaClasificacion().add(equipo2.get(0));;
		liga.save(liga1.get(0));
		mensaje="Equipos añadidos correctamente";
		model.addAttribute("anadirequipoliga", anadirequipoliga);
		model.addAttribute("mensaje", mensaje);					
		return "anadirequipoliga";	

	}
	
	
	

}
