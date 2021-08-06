package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.MediaRental;
import data.RentalDAO;

@WebServlet(name="main-servlet", urlPatterns = "/servlet")
public class MainServlet extends HttpServlet{
	
//	private CopyOnWriteArrayList<MediaRental> mRental = new CopyOnWriteArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MediaRental> rentalList = null;
		System.out.println("GET");
		try(RentalDAO rDao = new RentalDAO())
		{
			rentalList = rDao.get();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(rentalList);
		resp.getWriter().print(json);
		resp.setContentType("/application/json");
	}
	
	@Override
	@JsonIgnoreProperties(ignoreUnknown = true)
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MediaRental rental = mapper.readValue(req.getInputStream(), MediaRental.class);
		System.out.println(rental.toString());
		try(RentalDAO rDao = new RentalDAO())
		{
			rDao.save(rental);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		resp.setStatus(201);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DELETE");
		try(RentalDAO rDao = new RentalDAO())
		{
			rDao.delete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}
