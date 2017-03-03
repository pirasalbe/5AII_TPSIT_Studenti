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
                query="select * from scuola where nome='" + request.getParameter("scuolaI") + "'";
                rset = smt.executeQuery(query);
                
                rset.next();
                int cod = rset.getInt("cod");
                
                //obtain student
                query="select * from studenti where nome='" + request.getParameter("nomeI") + "' and cognome='" + request.getParameter("cognomeI") + "'";
                rset = smt.executeQuery(query);
                
                rset.next();
                int stud = rset.getInt("cod");
                
                //obtain class
                query="select * from classe where nome='" + request.getParameter("classeI") + "' and scuola='" + cod +"'";
                rset = smt.executeQuery(query);
                
                rset.next();
                int classe = rset.getInt("cod");
                
                query="insert into storico(studente,scuola,classe,anno) values("+ stud +"','"+cod +"','" + classe + "','" + request.getParameter("anno") +"')";
                
                out.println("<p>Storico</p>");
            } else if(request.getParameter("scuole")!=null){
                out.println("<p>Scuole</p>");
                query="insert into scuola(nome, provincia, regione) values('" + request.getParameter("nome") + "','" + request.getParameter("provincia") + "','" + request.getParameter("regione") + "')";
            } else if(request.getParameter("classi")!=null){//obtain school
                query="select * from scuola where nome='" + request.getParameter("scuolaS") + "'";
                rset = smt.executeQuery(query);
                
                rset.next();
                int cod = rset.getInt("cod");
                
                query="insert into classe(nome,scuola,indirizzo,opzione) values("+request.getParameter("classe")+"','"+request.getParameter("scuolaS") +"','"+request.getParameter("indirizzo")+"','"+request.getParameter("opzione")+"')";
                out.println("<p>Classi</p>");
            } else if(request.getParameter("studenti")!=null){
                //create student
                query="insert into studenti(cognome,nome,luogonascita,datanascita) values("+request.getParameter("nomeS")+"','"+request.getParameter("cognome") +"','"+request.getParameter("luogo")+"','"+request.getParameter("data")+"')";
                out.println("<p>Studenti</p>");
            }
            
            smt.executeUpdate(query);
            
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
