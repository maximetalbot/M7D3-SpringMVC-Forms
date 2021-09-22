package fr.eni.mvc.controller;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import fr.eni.mvc.bean.Adresse;
import fr.eni.mvc.bean.Personne;

@Controller
@SessionAttributes({"heureSession"})
public class PersonneController {

	// �l�ments standards
	@Autowired
	ServletContext context;
	
	@Resource(name="pers1")
	Personne personneInjectee1;
	
	@Autowired
	@Qualifier("pers2")
	Personne personneInjectee2;
	
	// Cette m�thode sera appell�e � chaque fois et renverra une str avec l'heure
	@ModelAttribute("heure")
	public String getHeure() {
		System.out.println("Appel de getHeure");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dh=sdf.format(new Date());
		return dh;
	}
	
	// suivant session
	@ModelAttribute("heureSession")
	public String getHeureSession() {
		System.out.println("Appel de getHeureSession");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dh=sdf.format(new Date());
		return dh;
	}
	
	// selon context applicatif
	@PostConstruct
	public void getHeureContext() {
		System.out.println("Appel de getHeureContext");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dh=sdf.format(new Date());
		context.setAttribute("heureContext", dh);
	}
	
	// Quand j'appelle le formulaire d'ajout, construire un objet de type 
	//  personne qu'on envoie � la JSP pour init le formulaire
	@RequestMapping(method=RequestMethod.GET, path="/ajout")
	public ModelAndView ajout() {
		System.out.println("Appel de ajout()");
		Personne current = new Personne("Elizabeth", "M�rad", new Adresse("38250", "Villard-de-Lans"));
		// appel de "ajout".jsp et je lui passe "current" dans l'attribut "command"
		//	command est li� aux balises form de ajout.jsp
		ModelAndView mav = new ModelAndView("ajout", "command", current);
		return mav;
	}
	
	// ajout selon personne inject�e
	@RequestMapping(method=RequestMethod.GET, path="/ajoutInjPers1")
	public ModelAndView ajoutInjection1() {
		return new ModelAndView("ajout", "command", personneInjectee1);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/ajoutInjPers2")
	public ModelAndView ajoutInjection2() {
		return new ModelAndView("ajout", "command", personneInjectee2);
	}
	
	// Par d�faut, le composant Spring MVS va essayer d'int�grer les �l�ments du form
	// Ici on peut donc r�cup�rer directement les objets Personne
	@RequestMapping(method=RequestMethod.POST, path="/ajout")
	public ModelAndView ajoutValidation(Personne p) {
		// On peut ainsi renvoyer cet objet au success.jsp
		System.out.println("Traitement de "+p);
		ModelAndView mav = new ModelAndView("success", "nouveau", p);
		return mav;
	}
	// Idem mais autre fa�on de faire
	// d�s que le controleur recoit Personne p, l'ins�rer dans le modelMap au nom de "nouveau"
	@RequestMapping(method=RequestMethod.POST, path="/ajoutAutre")
	public String ajoutAutre(@ModelAttribute("nouveau") Personne p) {
		
		System.out.println("Traitement de "+p);
		return "success";
	}

}
