package lk.ijse.Spring;/*@author:Dilshan Rajika Withanachchi*/

import lk.ijse.Spring.db.CrudUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet(urlPatterns = "/Customer")
public class CustomerForm extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
      System.out.println("Calling do get");

         try {
           final ResultSet rst = CrudUtil.execute("Select * from Customer");
             final PrintWriter writer = resp.getWriter();
             resp.setContentType("application/json");
             String json="[";
                    while (rst.next()) {
                        String id=rst.getString(1);
                        String fname=rst.getString(2);
                        String lname= rst.getString(3);
                        String address= rst.getString(4);
                        String contactno=rst.getString(5);
                        String country=  rst.getString(6);
                        String city= rst.getString(7);
                        String zipcode= rst.getString(8);
                        json += "{\"id\":\""+ id +"\",\"fname\":\""+ fname +"\",\"lname\":\""+ lname +"\"," +
                                "\"address\":\""+address +"\",\"contactno\":\""+contactno +"\",\"country\":\""+ country +
                                "\",\"city\":\""+ city +"\",\"zipcode\":\""+ zipcode +"\"},";
                    }
                     json=json.substring(0,json.length()-1);
                    json +="]";
                    writer.write(json);
                     writer.close();

         }catch (Exception e){
         e.printStackTrace();
           }

    }
    /*
                    writer.write("[");
                    while (rst.next()) {
                        String id=rst.getString(1);
                        String fname=rst.getString(2);
                        String lname= rst.getString(3);
                        String address= rst.getString(4);
                        String contactno=rst.getString(5);
                        String country=  rst.getString(6);
                        String city= rst.getString(7);
                        String zipcode= rst.getString(8);
                        writer.write("{\"id\":\""+ id +"\",\"fname\":\""+ fname +"\",\"lname\":\""+ lname +"\"," +
                                "\"address\":\""+address +"\",\"contactno\":\""+contactno +"\",\"country\":\""+ country +
                                "\",\"city\":\""+ city +"\",\"zipcode\":\""+ zipcode +"\"},");
                    }
             writer.write("{}");
             writer.write("]");
             writer.close();*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CustID=req.getParameter("id");
        String CustFirstName=req.getParameter("fname");
        String CustLastName= req.getParameter("lname");
        String CustAddress=req.getParameter("address");
        String CustContactNumber=req.getParameter("contactno");
        String CustCountry= req.getParameter("country");
        String CustCity=req.getParameter("city");
       String  CustZipCode=req.getParameter("zipcode");
        try {
    boolean val= CrudUtil.execute("Insert into Customer values(?,?,?,?,?,?,?,?)", CustID, CustFirstName,
            CustLastName, CustAddress
            , CustContactNumber, CustCountry, CustCity, CustZipCode);

        }catch (Exception E){
    E.printStackTrace();
}



    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPut");

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doDelete");
    }
}
