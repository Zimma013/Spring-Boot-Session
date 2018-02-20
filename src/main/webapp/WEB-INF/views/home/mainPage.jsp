<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-02-17
  Time: 04:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="styles/css/podstawowy.css" type="text/css" title="Podstawowy" />
</head>
<body>
<div class="title">
    <p><img src="http://www.robot.agh.edu.pl/img/logos/agh.png" alt="agh" class="agh" /></p>
    <p><img src="http://cdn.crunchify.com/wp-content/uploads/2013/06/Java-Design-Patterns.png" alt="design" class="design" /></p>
    <h1>Profil</h1>
</div>

<div  class="menuleft">
    <dl>
        <dt class="second">Zapisane konwersacje</dt>
        <form method="post" action=${redirectCRUD}>
        ${list_var}
            <p class="radio_action">
                <input type="radio" name="submitType" value="1" checked = "checked">Zmień nazwę<br/>
            </p>
            <p class="radio_action">
            <input class="action" type="radio" name="submitType" value="2">Utwórz nową konwersacje<br/>
            </p>
            <p class="radio_action">
            <input class="action" type="radio" name="submitType" value="3">Zmień bieżącą konwersacje<br/>
            </p>
            <p class="radio_action">
                <input class="action" type="radio" name="submitType" value="4">Usuń konwersacje<br/>
            </p>

            <input type="submit" value="Wykonaj">

        </form>
    </dl>
    <dl>
        <dt class="second">Nawigacja strony</dt>
        <dd><a href="/">Skocz do logowania</a></dd><br/>

    </dl>
</div>





<div class="content">



    <form method="post" action=${t}>

        <h2>Podaj swoje imię</h2>
        <input type="text" name="form_name" value="${form_name_var}" />

        <h2>Podaj swoje nazwisko</h2>
        <input type="text" name="form_surname" value="${form_surname_var}"/>

        <h2>W jakich językach programujesz? </h2>
        <input type = "checkbox" name = "lang_c" ${lang_c_var} /> C/C++
        <input type = "checkbox" name = "lang_j" ${lang_j_var} /> Java
        <input type = "checkbox" name = "lang_pa" ${lang_pa_var} /> Pascal
        <input type = "checkbox" name = "lang_py" ${lang_py_var} /> Python
        <input type = "checkbox" name = "lang_a" ${lang_a_var} /> Assembler x86

        <h1>Jakie znasz wznorce projektowe?</h1>
        <h3>wzorce konstrukcyjne</h3>
        <input type = "checkbox" name = "design_builder" ${design_builder_var} />Builder<br/>
        <input type = "checkbox" name = "design_abstractfactory" ${design_abstractfactory_var} />Abstract Factory<br/>
        <input type = "checkbox" name = "design_factorym" ${design_factorym_var} />Factory Method<br/>
        <input type = "checkbox" name = "design_prototype" ${design_prototype_var} />Prototype<br/>
        <input type = "checkbox" name = "design_singleton" ${design_singleton_var} />Singleton<br/>
        <h3>wzorce strukturalne</h3>
        <input type = "checkbox" name = "design_adapter" ${design_adapter_var} />Adapter<br/>
        <input type = "checkbox" name = "design_decorator" ${design_decorator_var} />Decorator<br/>
        <input type = "checkbox" name = "design_fasade" ${design_fasade_var} />Facade<br/>
        <input type = "checkbox" name = "design_composite" ${design_composite_var} />Composite<br/>
        <input type = "checkbox" name = "design_bridge" ${design_bridge_var} />Bridge<br/>
        <input type = "checkbox" name = "design_proxy" ${design_proxy_var} />Proxy<br/>
        <input type = "checkbox" name = "design_flyweight" ${design_flyweight_var} />Flyweight<br/>
        <h3>wzorce operacyjne</h3>
        <input type = "checkbox" name = "design_interpreter" ${design_interpreter_var} />Interpreter<br/>
        <input type = "checkbox" name = "design_iterator" ${design_iterator_var} />Iterator<br/>
        <input type = "checkbox" name = "design_chain" ${design_chain_var} />Chain of Responsibility<br/>
        <input type = "checkbox" name = "design_mediator" ${design_mediator_var} />Mediator<br/>
        <input type = "checkbox" name = "design_template" ${design_template_var} />Template Method<br/>
        <input type = "checkbox" name = "design_observer" ${design_observer_var} />Observer<br/>
        <input type = "checkbox" name = "design_visitor" ${design_visitor_var} />Visitor<br/>
        <input type = "checkbox" name = "design_memento" ${design_memento_var} />Memento<br/>
        <input type = "checkbox" name = "design_command" ${design_command_var} />Command<br/>
        <input type = "checkbox" name = "design_state" ${design_state_var} />State<br/>
        <input type = "checkbox" name = "design_strategy" ${design_strategy_var} />Strategy<br/>

        <h2>Napisz coś o swoich zainteresowaniach</h2>
        <textarea name="form_text" rows="10" cols="80">${form_text_var}</textarea>

        <br />
        <input type="submit" value="Wyślij">
    </form>





</div>





</body>
</html>