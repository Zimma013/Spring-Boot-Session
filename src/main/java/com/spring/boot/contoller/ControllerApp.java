package com.spring.boot.contoller;

import com.spring.boot.contoller.designpattern.facade.CoRFacade;
import com.spring.boot.model.ConversationDB;
import com.spring.boot.model.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerApp {

    @Autowired
    private UserDB userDB;

    @Autowired
    private ConversationDB conversationDB;

    @Autowired
    private CoRFacade coRFacade;
/*
    @Autowired
    private MongoClient mongoClient;

    private DB mongoDB = mongoClient.getDB("mongoDatabase");
    private DBCollection mongoUserCollection = mongoDB.getCollection("users");
    private DBCollection mongoConvCollection = mongoDB.getCollection("conversations");
*/

    private String message = "";

    /* wyświetla zawartość login.jsp  */
    @RequestMapping(value = "/")
    public String home(Model m){
        message = "Witaj - wprowadź swoje dane";
        m.addAttribute("login_msg",message);
        return "/login";
    }

    /* wyświetla zawartość login.jsp */
    @RequestMapping(value = "/login")
    public String login(){
        return "/login";
    }

    /* wyświetla zawartość register.jsp */
    @RequestMapping(value = "/register")
    public String register(){
        return "/register";
    }

    /* wyświetla zawartość convChangeName.jsp */
    @RequestMapping(value = "convChangeName")
    public String convNameChange(){
        return "/convChangeName";
    }

    /* wyświetla zawartość mainPage.jsp - główna strona aplikacji;
     * na niej zawarte są istotne dane przechowywane w konwersacji
     * @RequestMapping(value = "/mainPage") mapuje URL na działanie
     *  request pochodzi z /loginValidate */
    @RequestMapping(value = "/mainPage")
    public String mainPage(WebRequest request,Model m){

        // pobieranie tokena klienta z zapytania
        String token = request.getParameter("t");

        // *pobieranie* z aktywnej konwersacji wszystkich wartości parametrów strony,
        // które są zapamiętywane w pamięci dla danej konwersacji,
        // a nie przekazywane przez URL
        // *przekazanie* do widoku danych strony
        this.loadValuesFromConv(token,m);

        // przekazanie do widoku listy konwersacji danego klienta
        m.addAttribute("list_var",this.conversationDB.printConvList(token));

        // przekazanie do widoku adresu (akcji wykonywanej po przesłaniu danych strony)
        // w celu zachowania jej wartości parametrów
        m.addAttribute("t","saveForm?t="+token);

        // przekazanie do widoku adresu (dla akcji wykonywanej po wybraniu akcji z lewego menu)
        m.addAttribute("redirectCRUD","convCRUD?t="+token);

        // wyświetla zawartość mainPage.jsp
        return "/mainPage";
    }
    /* zapisywanie danych strony do konwersacji  request pochodzi z mainPage.jsp*/
    @RequestMapping(value = "/saveForm")
    public String saveForm(WebRequest request, RedirectAttributes redirected){
        // Token klienta dla ktorego konwersacji bedzie zapisana strona
        String token = request.getParameter("t");
/*
        System.out.println("SAVE FORM token : "+token);
        System.out.println("TEXT FROM REQUEST : "+request.getParameter("form_text"));
*/
        //Zapisywanie wartosci atrybutów strony do odpowiedniej konwersacji
        this.saveValuesToConv(token, request);

        //przekazaniue dalej tokena klienta
        redirected.addAttribute("t",token);

        return "redirect:/mainPage";
    }

    /*Mapowanie walidacji Logowania -  request pochodzi z login.jsp*/
    @RequestMapping(value = "/loginValidate")
    public String validateLogin(WebRequest request, Model m, RedirectAttributes redirected){

        if(this.userDB.userExists(request)){
            if(this.userDB.checkPassword(request)){

                String token = this.userDB.getToken(request.getParameter("login"));

                if(this.conversationDB.findActiveConversation(token) == null) {
                    //tworzenie pierwszej konwersacji klienta
                    this.conversationDB.newConversation(token);
                }
                //przekazaniue dalej tokena klienta
                redirected.addAttribute("t",token);
                //przekierowanie do mainPage.jsp
                return "redirect:/mainPage";
            }else{
                message = "Podałeś złe hasło!";
                m.addAttribute("login_msg",message);
                return "/login";
            }
        }else{
            message = "Podałeś zły login!";
            m.addAttribute("login_msg",message);
            return "/login";
        }
    }

    /*Mapowanie walidacji Rejestacji - request pochodzi z register.jsp */
    @RequestMapping(value = "/registerValidate")
    public String validateRegister(WebRequest request, Model m){

        if(request.getParameter("login").isEmpty()) {
            message = "Login nie może być pusty";
            m.addAttribute("register_msg",message);
            return "/register";
        } else if(this.userDB.userExists(request)) {
           message = "Login zajęty!";
           m.addAttribute("register_msg",message);
           return "/register";

        } else {
            if(!request.getParameter("password").isEmpty()) {

                if(request.getParameter("password")
                        .equals(request.getParameter("password2"))) {
                    this.userDB.register(request);
                    message = "Twoje konto zostało założone. Zaloguj się:";
                    m.addAttribute("login_msg",message);
                    return "/login";
                } else {
                    message = "Podane hasła się nie zgadzają!";
                    m.addAttribute("register_msg",message);
                    return "/register";
                }

            } else {
                message = "Przynajmniej jedno pole jest puste";
                m.addAttribute("register_msg",message);
                return "/register";
            }

        }

    }

    //Obsługa zapytań pochodzących z lewego menu
    @RequestMapping("/convCRUD")
    public String convCRUD(WebRequest request,  Model m,  RedirectAttributes redirected){

        //Zmienna określająca żądane zachowanie
        String type = request.getParameter("submitType");

        //zwrot konkretnego widoku do wyswietlenia
        return this.coRFacade.run(type,request,m,redirected,this.conversationDB);
    }

    //Walidacja zmiany nazwy konwersacji, request pochodzi z convChangeName.jsp
    @RequestMapping("/validateNameChange")
    public String validateNameChange(WebRequest request, RedirectAttributes redirected){

        String userToken = request.getParameter("t");
        redirected.addAttribute("t",userToken);

        //Zmiana nazwy konwersacji
        this.conversationDB.findConvByCID(userToken, request.getParameter("convCID"))
                .setName(request.getParameter("newConvName"));
        //przekierowanie na główną stronę
        return "redirect:/mainPage";
    }

    private void loadValuesFromConv(String token, Model m){
        m.addAttribute("form_text_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("form_text"));
        m.addAttribute("form_name_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("form_name"));
        m.addAttribute("form_surname_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("form_surname"));

        //Wczytanie checkboxow jezyki
        m.addAttribute("lang_c_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("lang_c"));
        m.addAttribute("lang_j_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("lang_j"));
        m.addAttribute("lang_pa_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("lang_pa"));
        m.addAttribute("lang_py_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("lang_py"));
        m.addAttribute("lang_a_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("lang_a"));

        //Wczytanie checkboxow wzorce
        m.addAttribute("design_strategy_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_strategy"));
        m.addAttribute("design_abstractfactory_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_abstractfactory"));
        m.addAttribute("design_factorym_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_factorym"));
        m.addAttribute("design_prototype_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_prototype"));
        m.addAttribute("design_singleton_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_singleton"));
        m.addAttribute("design_adapter_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_adapter"));
        m.addAttribute("design_decorator_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_decorator"));
        m.addAttribute("design_fasade_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_fasade"));
        m.addAttribute("design_composite_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_composite"));
        m.addAttribute("design_bridge_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_bridge"));
        m.addAttribute("design_proxy_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_proxy"));
        m.addAttribute("design_flyweight_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_flyweight"));
        m.addAttribute("design_interpreter_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_interpreter"));
        m.addAttribute("design_iterator_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_iterator"));
        m.addAttribute("design_chain_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_chain"));
        m.addAttribute("design_mediator_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_mediator"));
        m.addAttribute("design_template_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_template"));
        m.addAttribute("design_observer_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_observer"));
        m.addAttribute("design_visitor_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_visitor"));
        m.addAttribute("design_memento_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_memento"));
        m.addAttribute("design_command_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_command"));
        m.addAttribute("design_state_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_state"));
        m.addAttribute("design_strategy_var",
                this.conversationDB.findActiveConversation(token)
                        .retrieveAttribute("design_strategy"));
    }

    private void saveValuesToConv(String token, WebRequest request){
        this.conversationDB.findActiveConversation(token)
                .editAttribute("form_text",request.getParameter("form_text"));
        this.conversationDB.findActiveConversation(token)
                .editAttribute("form_name",request.getParameter("form_name"));
        this.conversationDB.findActiveConversation(token)
                .editAttribute("form_surname",request.getParameter("form_surname"));

        //TUTAJ zaczynaja się checkboxy - zasada dzialania taka sama dla roznych rodzajow
        // Jesli klient zaznaczyl dany checkbox to zapis w konwersacji danego atrybutu na "checked"
        // w innym wypadku przeslanie pustego stringa - chceckbox odznaczony
        if(request.getParameter("lang_c")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_c","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_c","");
        }
        if(request.getParameter("lang_j")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_j","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_j","");
        }
        if(request.getParameter("lang_pa")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_pa","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_pa","");
        }
        if(request.getParameter("lang_py")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_py","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_py","");
        }
        if(request.getParameter("lang_a")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_a","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("lang_a","");
        }

        //wzorce
        if(request.getParameter("design_builder")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_builder","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_builder","");
        }
        if(request.getParameter("design_abstractfactory")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_abstractfactory","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_abstractfactory","");
        }
        if(request.getParameter("design_factorym")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_factorym","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_factorym","");
        }
        if(request.getParameter("design_prototype")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_prototype","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_prototype","");
        }
        if(request.getParameter("design_singleton")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_singleton","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_singleton","");
        }
        if(request.getParameter("design_adapter")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_adapter","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_adapter","");
        }
        if(request.getParameter("design_decorator")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_decorator","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_decorator","");
        }
        if(request.getParameter("design_fasade")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_fasade","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_fasade","");
        }
        if(request.getParameter("design_composite")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_composite","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_composite","");
        }
        if(request.getParameter("design_bridge")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_bridge","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_bridge","");
        }
        if(request.getParameter("design_proxy")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_proxy","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_proxy","");
        }
        if(request.getParameter("design_flyweight")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_flyweight","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_flyweight","");
        }
        if(request.getParameter("design_interpreter")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_interpreter","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_interpreter","");
        }
        if(request.getParameter("design_iterator")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_iterator","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_iterator","");
        }
        if(request.getParameter("design_chain")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_chain","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_chain","");
        }
        if(request.getParameter("design_mediator")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_mediator","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_mediator","");
        }
        if(request.getParameter("design_template")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_template","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_template","");
        }
        if(request.getParameter("design_observer")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_observer","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_observer","");
        }
        if(request.getParameter("design_visitor")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_visitor","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_visitor","");
        }
        if(request.getParameter("design_memento")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_memento","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_memento","");
        }
        if(request.getParameter("design_command")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_command","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_command","");
        }
        if(request.getParameter("design_state")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_state","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_state","");
        }
        if(request.getParameter("design_strategy")!=null){
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_strategy","checked=\"checked\"");
        }else{
            this.conversationDB.findActiveConversation(token)
                    .editAttribute("design_strategy","");
        }
    }
}