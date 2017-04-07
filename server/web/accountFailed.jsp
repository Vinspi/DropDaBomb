<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="card-content title-card">
    <div class="center-align light-green">
        <span class="card-title white-text">1</span>
        <div class="bottom-arrow bottom-arrow-creation-compte"></div>
    </div>
    <div class="center-align titre-creation-compte">
        <span class="card-title white-text">Créer votre compte</span>
    </div>
    <div class="row">
        <form method="POST" action="CreateAccountNavigator" class="col s12">
            <div class="row">
                <div class="input-field col s8 offset-s2">
                    <input placeholder="Pseudo" id="pseudo" name="pseudo" type="text" class="validate">
                    <% if(request.getAttribute("STATUS") != null && request.getAttribute("STATUS").equals(RequestStatus.CREATE_ACCOUNT_FAILED_PSEUDO))
                        out.print("<h8 class='red-text'>Ce pseudo est déjà utilisé.</h8>");%>
                    <label for="pseudo"></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s8 offset-s2">
                    <input placeholder="password" id="password" name="password" type="password" class="validate">
                    <% if(request.getAttribute("STATUS") != null && request.getAttribute("STATUS").equals(RequestStatus.ERR_MDP))
                        out.print("<h8 class='red-text'> votre mot de passe est trop court ("+RequestStatus.MIN_SIZE_PASSWORD+" caractères min).</h8>");%>
                    <label for="password"></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s8 offset-s2">
                    <input placeholder="Confirm password" id="password_confirm" name="password_confirm" type="password" class="validate">
                    <% if(request.getAttribute("STATUS") != null && request.getAttribute("STATUS").equals(RequestStatus.ERR_MDP_NOT_MATCH))
                        out.print("<h8 class='red-text'> les mots de passe ne sont pas les mêmes.</h8>");%>
                    <label for="password_confirm"></label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s8 offset-s2">
                    <input placeholder="Email" id="email" name="email" type="email" class="validate">
                    <% if(request.getAttribute("STATUS") != null && request.getAttribute("STATUS").equals(RequestStatus.CREATE_ACCOUNT_FAILED_EMAIL))
                        out.print("<h8 class='red-text'>Cet email est déjà utilisé ou n'est pas valide.</h8>");%>
                    <label for="email"></label>
                </div>
            </div>
            <div class="row">
                <div class="col s4 offset-s4">
                    <input type="submit" class="btn-large waves-effect waves-light light-green" id="btn-create" value="Create">
                </div>
            </div>
        </form>
    </div>
</div>