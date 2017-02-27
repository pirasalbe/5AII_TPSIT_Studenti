/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author Alberto
 */
public class gainInfo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conn = null;
        Statement smt = null;
        ResultSet rset = null;
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gainSchool</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gainSchool at " + request.getContextPath() + "</h1>");
            
            //query
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Studenti", "root","");
            smt = conn.createStatement();
            
            Boolean scuola = true;
            String query="select * from scuola where provincia='" + request.getParameter("provincia") + "'";
            if(request.getParameter("iscritti")!=null){
                query="select * from storico so inner join studenti si on so.studente=si.cod where scuola='" + request.getParameter("scuola") + "' and anno='" + request.getParameter("anno") + "'";
                scuola=false;
            }
            
            rset = smt.executeQuery(query);

            int count=0;
                while(rset.next()){
                    if(scuola)
                        out.println ("<p>"+ rset.getString("nome")+"</p>");
                    else
                        out.println ("<p>"+ rset.getString("cognome") +" "+rset.getString("nome")+" "+rset.getString("annonascita")+" "+rset.getString("classe")+"</p>");
                    count++;
                }
            
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e){
            System.out.println(e);
        } finally {
            try {
            //step 7 libero le risorse occupate
                if (rset!=null) rset.close();
                if(smt!=null) smt.close();
                if(conn !=null) conn.close();
            } catch (Exception e) {
            System.out.println(e);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
