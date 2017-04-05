package es.sidelab.urjc;

import java.util.LinkedList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
 
public class PDFView extends AbstractPdfView {

	protected void buildPdfDocument(        
			Map<String, Object> model,        
			Document document,        
			PdfWriter writer,        
			HttpServletRequest req,        
			HttpServletResponse resp)        
					throws Exception {
		
		
		// Get data "articles" from model
		@SuppressWarnings("unchecked")
		LinkedList<Article> articles = (LinkedList<Article>) model.get("articles");
		
		// Fonts
		Font fontTitle = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
		Font fontTag = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);

		for(Article article:articles){

			// 1.NOMBRE EQUIPO
			document.add(new Chunk("Equipo: "));
			Chunk nombre = new Chunk(article.getNombreEquipo(), fontTitle);
			document.add(nombre);
			document.add(new Chunk(" "));

			// -- newline
			document.add(Chunk.NEWLINE);

			// 2.PUNTUACION
			document.add(new Chunk("Puntuacion: "));
			Chunk puntos  = new Chunk(article.getPuntuacion(), fontTag);
			document.add(puntos);
			
			// -- newline
			document.add(Chunk.NEWLINE);

			// 3.VICTORIAS
			document.add(new Chunk("Victorias: "));
			Chunk victorias = new Chunk(article.getNumeroVictorias(), fontTag);
			document.add(victorias);
			document.add(new Chunk(" "));
			
			// -- newline
			document.add(Chunk.NEWLINE);
			
			// 4.DERROTAS
			document.add(new Chunk("Derrotas: "));
			Chunk derrotas = new Chunk(article.getNumeroDerrotas(), fontTag);
			document.add(derrotas);
			document.add(new Chunk(" "));

			// -- newline
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

		}
		
	
	}
	

	
	
}