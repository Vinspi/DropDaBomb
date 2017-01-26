<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="css/style.css"  media="screen,projection"/>

    <title>DropDaBomb</title>
  </head>
  <body>
    <div class="container">
      <div class="col s12 m7">
        <div class="card horizontal card_signup">
          <div class="card-image">
            <img src="../img/signup.png">
          </div>
          <div class="card-stacked">
            <div class="card-content">
              <h1 class="center-align" >Create Account</h1>
              <div class="row">
                <form class="col s12">
                  <div class="row">
                    <div class="input-field col s8 offset-s2">
                      <input placeholder="Pseudo" id="pseudo" type="text" class="validate">
                      <label for="pseudo"></label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="input-field col s8 offset-s2">
                      <input placeholder="password" id="password" type="password" class="validate">
                      <label for="password"></label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="input-field col s8 offset-s2">
                      <input placeholder="Confirm password" id="password_confirm" type="password" class="validate">
                      <label for="password_confirm"></label>
                    </div>
                  </div>
                  <div class="row">
                    <div class="input-field col s8 offset-s2">
                      <input placeholder="Email" id="email" type="email" class="validate">
                      <label for="email"></label>
                    </div>
                  </div>

                </form>
              </div>
            </div>
            <div class="card-action">
              <a class="btn-large center-align btn-signup" href="#">Create</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>