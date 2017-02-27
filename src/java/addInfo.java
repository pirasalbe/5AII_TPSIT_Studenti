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
public class addInfo extends HttpServlet {

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
            
            String query="";
            if(request.getParameter("storici")!=null){
                out.println("<p>Storico</p>");
                //obtain school
                query="select * from scuola where nome='" + request.getParameter("scuolaS") + "'";
                rset = smt.executeQuery(query);
                
                rset.next();
                int cod = rset.getInt("cod");
                
                //create student
                query="insert into studenti(cognome,nome,luogonascita) values("+request.getParameter("nomeS")+"','"+request.getParameter("cognome") +"','vicenza')";
                rset = smt.executeQuery(query);
                
                query="select * from studenti where nome='" + request.getParameter("nomes") + "'";
                rset = smt.executeQuery(query);
                
                rset.next();
                int stud = rset.getInt("cod");
                
                //create class
                query="insert into classe(scuola,nome,indirizzo,opzione) values("+cod+"','"+request.getParameter("classe") +"','vicenza','vicenza')";
                rset = smt.executeQuery(query);
                
                query="select * from classe where nome='" + request.getParameter("classe") + "'";
                rset = smt.executeQuery(query);
                
                rset.next();
                int classe = rset.getInt("cod");
                
                query="insert into storico(studente,scuola,classe,anno) values("+stud+"','"+cod +"','" + classe + "','" + request.getParameter("anno") +"')";
                
                out.println("<p>Storico</p>");
            } else if(request.getParameter("scuole")!=null){
                out.println("<p>Scuole</p>");
                query="insert into scuola(nome, provincia, regione) values('" + request.getParameter("nome") + "','" + request.getParameter("provincia") + "','" + request.getParameter("regione") + "');";
                out.println(query);
            }
            
            rset = smt.executeQuery(query);
            
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e){
            e.printStackTrace();
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
