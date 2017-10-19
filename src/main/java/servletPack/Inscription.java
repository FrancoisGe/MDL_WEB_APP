package servletPack;

import DAO.DAOFactory;
import DAO.UtilisateurDao;
import beans.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "Inscription")
public class Inscription extends HttpServlet {

    private UtilisateurDao utilisateurDao;

    public void init()throws ServletException{
        DAOFactory daoFactory= DAOFactory.getInstance();
        this.utilisateurDao=daoFactory.getUtilisateurDao();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String mdp = request.getParameter("mdp");
        String ville = request.getParameter("ville");
        String adresse = request.getParameter("adresse");
        String code_postal = request.getParameter("code_postal");
        String mail = request.getParameter("mail");
        String pays = request.getParameter("pays");
        String num_tel = request.getParameter("num_tel");
        String date_naissance_String=  request.getParameter("date_naissance");
        Date date_naissance = null;
        try {
            date_naissance = (Date) new SimpleDateFormat("yyyy-MM--dd").parse(date_naissance_String);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setMdp(mdp);
        utilisateur.setVille(ville);
        utilisateur.setAdresse(adresse);
        utilisateur.setCodePostal(code_postal);
        utilisateur.setMail(mail);
        utilisateur.setPays(pays);
        utilisateur.setNumTel(num_tel);
        utilisateur.setDateNaissance(date_naissance);

        utilisateurDao.ajouter(utilisateur);

        this.getServletContext().getRequestDispatcher("/WEB-INF/Acceuil.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request,response);
    }
}
